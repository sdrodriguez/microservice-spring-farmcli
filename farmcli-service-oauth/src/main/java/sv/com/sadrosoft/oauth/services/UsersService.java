package sv.com.sadrosoft.oauth.services;

import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import brave.Tracer;
import feign.FeignException;
import sv.com.sadrosoft.oauth.clients.UsersClient;
import sv.com.sadrosoft.users.commons.models.entity.SgUser;

@Service
public class UsersService implements IUserService, UserDetailsService {
	
	private Logger log = LoggerFactory.getLogger(UsersService.class);

	@Autowired
	private UsersClient client;
	
	@Autowired
	private Tracer tracer;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

		try {
			SgUser user = client.findByUserName(username);
			List<GrantedAuthority> authorities = user.getRols().stream()
					.map(role -> new SimpleGrantedAuthority(role.getRolname()))
					.peek(authority -> log.info("Role: " + authority.getAuthority()))
					.collect(Collectors.toList());
			return new User(user.getSgUserName(),
					user.getPassword(),
					user.getEnabled(),
					true,true,true,
					authorities);
		} catch (FeignException e) {
			String error = "Error for login, because not exist user: " + username;
			log.error(error);
			tracer.currentSpan().tag("error.mensaje", error);
			throw new UsernameNotFoundException("Error for login, because not exist user: " + username);
		}
	}

	@Override
	public SgUser findByUserName(String username) {
		return client.findByUserName(username);
	}

	@Override
	public SgUser update(SgUser sgUser, Long id) {
		return client.update(sgUser, id);
	}

}
