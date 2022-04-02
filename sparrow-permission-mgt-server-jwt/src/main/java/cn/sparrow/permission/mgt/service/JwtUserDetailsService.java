package cn.sparrow.permission.mgt.service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import cn.sparrow.permission.mgt.api.PreserveRole;
import cn.sparrow.permission.mgt.model.SparrowUser;
import cn.sparrow.permission.mgt.service.repository.UserSysroleRepository;

@Component
public class JwtUserDetailsService implements UserDetailsService {

	@Autowired
	UserRepository userRepository;

	@Autowired
	UserSysroleRepository userSysroleRepository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		try {
			SparrowUser user = userRepository.findById(username).get();
//			userSysroleRepository.findByIdUsername(username).stream().map(f -> f.getSysrole().getName())
//					.collect(Collectors.toList()).toArray(new String[] {});
			System.out.println(user.getPassword());
			return User.withUsername(username).password(user.getPassword().replace("{bcrypt}", ""))
					.roles(userSysroleRepository.findByIdUsername(username).stream().map(f -> f.getSysrole().getCode())
							.collect(Collectors.toList()).toArray(new String[] {}))
					.build();
		} catch (NoSuchElementException e) {
			throw new UsernameNotFoundException("User not found with username: " + username);
		}
	}

}