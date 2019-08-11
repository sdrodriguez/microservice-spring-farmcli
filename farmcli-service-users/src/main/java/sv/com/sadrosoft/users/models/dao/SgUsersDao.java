package sv.com.sadrosoft.users.models.dao;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.annotation.RestResource;

import sv.com.sadrosoft.users.commons.models.entity.SgUser;

@RepositoryRestResource(path = "users")
public interface SgUsersDao extends PagingAndSortingRepository<SgUser, Long> {

	@RestResource(path = "search-user")
	public SgUser findBySgUserName(@Param("sgUserName") String sgUserName);
	
	@Query("select u from SgUser u where u.sgUserName = ?1")
	public SgUser getBySgUserName(String sgUserName);
}
