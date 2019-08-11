package sv.com.sadrosoft.zuul.oauth;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;

@Configuration
@EnableResourceServer
public class ResourceServerConfig extends ResourceServerConfigurerAdapter {

	@Override
	public void configure(ResourceServerSecurityConfigurer resources) throws Exception {

		resources.tokenStore(tokenStore());
		super.configure(resources);
	}

	@Override
	public void configure(HttpSecurity http) throws Exception {

		http.authorizeRequests()
		.antMatchers("/api/security/oauth/**").permitAll()
		.antMatchers(HttpMethod.GET, "/api/products/list", "/api/items/list", "/api/usr/users").permitAll()
		.antMatchers(HttpMethod.GET, "/api/products/see/{id}", "/api/items/see/{id}/{count}", 
				"/api/usr/users/{id}").hasAnyRole("USER", "ADMIN")
		.antMatchers("/api/products/**", "/api/items/**", "/api/usr/**").hasRole("ADMIN")
		.anyRequest().authenticated();
		 /* 
		 * this difine rutes
		 * .antMatchers(HttpMethod.POST, "/api/products/create", "/api/items/create", "/api/usr/users").hasRole("ADMIN")
		.antMatchers(HttpMethod.PUT, "/api/products/edit/{id}", "/api/items/edit/{id}", "/api/usr/users/{id}").hasRole("ADMIN")
		.antMatchers(HttpMethod.DELETE, "/api/products/delete/{id}", "/api/items/delete/{id}", "/api/usr/users/{id}").hasRole("ADMIN")
		*/
		super.configure(http);
	}
	
	@Bean
	public TokenStore tokenStore() {
		return new JwtTokenStore(accessTokenConverter());
	}

	@Bean
	public JwtAccessTokenConverter accessTokenConverter() {
		JwtAccessTokenConverter tokenConverter = new JwtAccessTokenConverter();
		tokenConverter.setSigningKey("jhsdjhfsjd4534$$##$jksadfljkslldlld");
		return tokenConverter;
	}

	
}
