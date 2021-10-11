package cn.sparrow.organization.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import cn.sparrow.model.permission.User;

public interface UserRepository extends JpaRepository<User, String> {
//	@Query("SELECT s.authorities FROM User s WHERE s.username = ?1")
//	List<SparrowUrl> userAuthorities(String username);
//
//	@Query("SELECT s.sysroles FROM User s WHERE s.username = ?1")
//	List<Sysrole> userSysroles(String username);
	
	
//	@Query("select u from User u where u.lastname like %:#{[0]}% and u.lastname like %:lastname%")
//	List<User> findByLastnameWithSpelExpression(@Param("lastname") String lastname);
	
	
//	Page<User> findByUsernameContaining(String username,Pageable p);

}
