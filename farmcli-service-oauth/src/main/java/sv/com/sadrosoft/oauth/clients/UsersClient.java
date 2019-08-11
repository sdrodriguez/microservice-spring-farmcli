package sv.com.sadrosoft.oauth.clients;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import sv.com.sadrosoft.users.commons.models.entity.SgUser;

@FeignClient(name = "farmcli-service-users")
public interface UsersClient {
	
	@GetMapping("/users/search/search-user")
	public SgUser findByUserName(@RequestParam String sgUserName);
}
