package cn.sparrow.permission.mgt;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.jdbc.core.JdbcTemplate;

import cn.sparrow.permission.mgt.service.UserRepository;

@EntityScan("cn.sparrow.permission.*")
@SpringBootApplication
public class SpringBootHelloWorldApplication {

	@Autowired
	static UserRepository userRepository;

	@Autowired
	static JdbcTemplate jdbcTemplate;

	public static void main(String[] args) {
		SpringApplication.run(SpringBootHelloWorldApplication.class, args);

	}

}