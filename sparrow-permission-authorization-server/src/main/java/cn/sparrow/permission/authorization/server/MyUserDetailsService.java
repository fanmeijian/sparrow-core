package cn.sparrow.permission.authorization.server;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import cn.sparrow.permission.authorization.server.model.LoginLog;
import cn.sparrow.permission.authorization.server.model.SparrowUser;
import cn.sparrow.permission.authorization.server.repository.LoginLogRepository;
import cn.sparrow.permission.authorization.server.repository.UserRepository;

@Component
public class MyUserDetailsService implements UserDetailsService {
  public static Logger logger = LoggerFactory.getLogger(MyUserDetailsService.class);

  @Autowired
  UserRepository userRepository;

  @Autowired
  PasswordEncoder passwordEncoder;
  
  @Autowired LoginLogRepository loginLogRepository;
  @Autowired
  private HttpServletRequest request;

  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    SparrowUser sparrowUser = userRepository.findById(username).orElse(null);
    // Assert.notNull(swdUser, "can not found user" + username);
    // Logger.getLogger(this.toString()).info("----------" + username + swdUser.getPassword() +
    // swdUser.getPassword());
    logger.info("{} {}", sparrowUser.getPassword(), passwordEncoder.encode("password"));
    loginLogRepository.save(new LoginLog(username, request.getRemoteAddr()));
    
    List<String> roles = new ArrayList<String>();
    
    sparrowUser.getSysroles().forEach(f -> {
      roles.add(f.getCode());
    });

    logger.info("user {} sysroles: {}", sparrowUser.getUsername(), roles.toString());
    return User.withUsername(username).password(sparrowUser.getPassword()).authorities(roles.toArray(new String[] {})).build();

  }
}
