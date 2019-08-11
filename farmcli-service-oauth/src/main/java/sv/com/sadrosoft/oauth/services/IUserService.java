package sv.com.sadrosoft.oauth.services;

import sv.com.sadrosoft.users.commons.models.entity.SgUser;

public interface IUserService {
	
	public SgUser findByUserName(String username);

}
