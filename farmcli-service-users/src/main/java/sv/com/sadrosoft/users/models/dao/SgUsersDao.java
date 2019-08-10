package sv.com.sadrosoft.users.models.dao;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import sv.com.sadrosoft.users.models.entity.SgUser;

public interface SgUsersDao extends PagingAndSortingRepository<SgUser, Long> {

	public SgUser findBySgUserName(String sgUserName);
	
	@Query("select u from SgUser where u.sgUserName = ?1")
	public SgUser getBySgUserName(String sgUserName);
}
