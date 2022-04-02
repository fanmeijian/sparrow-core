package cn.sparrow.permission.mgt.service;

import java.util.ArrayList;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import cn.sparrow.permission.mgt.api.PreserveRole;
import cn.sparrow.permission.mgt.api.PreserveScope;
import cn.sparrow.permission.mgt.api.scopes.ApiScope;

@Service
public class JwtUserDetailsService implements UserDetailsService {

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		if ("javainuse".equals(username)) {
//			return new User("javainuse", "$2a$10$slYQmyNdGzTn7ZLBXBChFOC9f6kFjAqPhccnP6DxlWXx2lPk1C3G6",
//					new ArrayList<>());
			return User.withUsername(username).password("$2a$10$slYQmyNdGzTn7ZLBXBChFOC9f6kFjAqPhccnP6DxlWXx2lPk1C3G6").roles(PreserveRole.ROLE_SYSADMIN,PreserveRole.ROLE_ADMIN, PreserveRole.ROLE_SUPER_ADMIN,PreserveRole.ROLE_SUPER_SYSADMIN).build();
		} else {
			throw new UsernameNotFoundException("User not found with username: " + username);
		}
	}

}