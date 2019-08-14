package sv.com.sadrosoft.oauth.event;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationEventPublisher;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import feign.FeignException;
import sv.com.sadrosoft.oauth.services.IUserService;
import sv.com.sadrosoft.users.commons.models.entity.SgUser;

@Component
public class AuthenticationSuccessErrorHandler implements AuthenticationEventPublisher {

	@Autowired
	private IUserService userService;
	
	@Override
	public void publishAuthenticationSuccess(Authentication authentication) {
		UserDetails user = (UserDetails) authentication.getPrincipal();
		System.out.println("Success login: "+user.getUsername());
		SgUser userAuth = userService.findByUserName(authentication.getName());
		if(userAuth.getCountsLogin() != null && userAuth.getCountsLogin() > 0) {
			userAuth.setCountsLogin(0);
			userService.update(userAuth, userAuth.getId());
		}
		
	}

	@Override
	public void publishAuthenticationFailure(AuthenticationException exception, Authentication authentication) {
		System.out.println("Error login: " + exception.getMessage());
		try {
			SgUser user = userService.findByUserName(authentication.getName());
			if(user.getCountsLogin() == null) {
				user.setCountsLogin(0);
			}
			user.setCountsLogin(user.getCountsLogin()+1);
			if(user.getCountsLogin() >= 3) {
				user.setEnabled(false);
			}
			userService.update(user, user.getId());
			
		} catch (FeignException e) {
			System.out.println("Error login: No existe usuario" +authentication.getName());
		}
	}

	
}
