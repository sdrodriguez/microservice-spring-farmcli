package sv.com.sadrosoft.oauth.config;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.common.DefaultOAuth2AccessToken;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.TokenEnhancer;
import org.springframework.stereotype.Component;

import sv.com.sadrosoft.oauth.services.IUserService;
import sv.com.sadrosoft.users.commons.models.entity.SgUser;

@Component
public class AdditionalInfoToken implements TokenEnhancer {

	@Autowired
	private IUserService userService;
	
	@Override
	public OAuth2AccessToken enhance(OAuth2AccessToken accessToken, OAuth2Authentication authentication) {
		Map<String, Object> info = new HashMap<>();
		SgUser user = userService.findByUserName(authentication.getName());
		info.put("sgname", user.getSgName());
		info.put("sglastname", user.getSglastName());
		info.put("email", user.getSgEmail());
		((DefaultOAuth2AccessToken) accessToken).setAdditionalInformation(info);
		return accessToken;
	}

}
