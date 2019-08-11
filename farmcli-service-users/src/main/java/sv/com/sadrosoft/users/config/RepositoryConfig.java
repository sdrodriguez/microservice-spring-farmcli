/**
 * 
 */
package sv.com.sadrosoft.users.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;
import org.springframework.data.rest.webmvc.config.RepositoryRestConfigurer;

import sv.com.sadrosoft.users.commons.models.entity.SgRole;
import sv.com.sadrosoft.users.commons.models.entity.SgUser;

/**
 * @author DevLider
 *
 */
@Configuration
public class RepositoryConfig implements RepositoryRestConfigurer {

	@Override
	public void configureRepositoryRestConfiguration(RepositoryRestConfiguration config) {
		config.exposeIdsFor(SgUser.class, SgRole.class);
		RepositoryRestConfigurer.super.configureRepositoryRestConfiguration(config);
	}
	
	

}
