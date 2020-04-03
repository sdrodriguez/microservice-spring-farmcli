package sv.com.sadrosoft.oauth.event;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationEventPublisher;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import brave.Tracer;
import feign.FeignException;
import sv.com.sadrosoft.oauth.services.IUserService;
import sv.com.sadrosoft.users.commons.models.entity.SgUser;

@Component
public class AuthenticationSuccessErrorHandler implements AuthenticationEventPublisher {

	@Autowired
	private IUserService userService;
	
	@Autowired
	private Tracer tracer;
	
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
		StringBuilder errorers = new StringBuilder();
		String error1 = "Error login: " + exception.getMessage();
		System.out.println(error1);
		errorers.append(error1);
		
		try {
			SgUser user = userService.findByUserName(authentication.getName());
		
			if(user.getCountsLogin() == null) {
				user.setCountsLogin(0);
			}
			user.setCountsLogin(user.getCountsLogin()+1);
			if(user.getCountsLogin() >= 3) {
				user.setEnabled(false);
				errorers.append(" Se ha desabilidato el usuario: " + authentication.getName());
			}
			userService.update(user, user.getId());
			System.out.println("login count: " + user.getCountsLogin());
			errorers.append(" login count: " + user.getCountsLogin());
		} catch (FeignException e) {
			System.out.println("Error login: No existe usuario" +authentication.getName());
			errorers.append(" Error login: No existe usuario" +authentication.getName());
		}
		
		tracer.currentSpan().tag("error.mensaje", errorers.toString());
	}

	
}
