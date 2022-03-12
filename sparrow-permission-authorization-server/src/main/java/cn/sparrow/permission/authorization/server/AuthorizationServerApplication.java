package cn.sparrow.permission.authorization.server;

import java.sql.Connection;
import java.sql.SQLException;

import javax.sql.DataSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;

import cn.sparrow.permission.authorization.server.repository.UserRepository;

@SpringBootApplication
public class AuthorizationServerApplication {
  // insert admin user
  private static Logger logger = LoggerFactory.getLogger(AuthorizationServerApplication.class);
  @Autowired
  static UserRepository userRepository;

  
  @Autowired
  static JdbcTemplate jdbcTemplate;

  public static void main(String[] args) {
    ConfigurableApplicationContext context = SpringApplication.run(AuthorizationServerApplication.class, args);
    
    DataSource ds = context.getBean(DataSource.class);
    System.out.println(ds.getClass().getName()); //默认的使用的是tomcat的数据源
    Connection connection = null;
	try {
		connection = ds.getConnection();
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
    try {
		System.out.println(connection.getCatalog());
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} //test
    System.out.println(context.getBean(JdbcTemplate.class));
    JdbcTemplate jdbcTemplate = context.getBean(JdbcTemplate.class);
    jdbcTemplate.execute("insert into spr_user(username,password, enabled) values('ROOT','"+PasswordEncoderFactories.createDelegatingPasswordEncoder().encode("password")+"',1);");
    logger.info("Create user ROOT with password password");
    jdbcTemplate.execute("insert into spr_oauth_client(client_id,access_token_validity,autoapprove, client_secret,grant_type,redirect_uri,refresh_token_validity) values('sparrow',3600,1,'"+PasswordEncoderFactories.createDelegatingPasswordEncoder().encode("password")+"','password','',3600);");
    logger.info("Create oauth client sparrow with password password");
    jdbcTemplate.execute("insert into spr_sysrole(name, code) values('超级管理员','SYSADMIN');");
    logger.info("Create sysrole 超级管理员 SYSADMIN");
    jdbcTemplate.execute("insert into spr_user_sysrole(username, sysrole_id) values('ROOT','SYSADMIN');");
    logger.info("Grant sysrole SYSADMIN to user ROOT");

  }
}
