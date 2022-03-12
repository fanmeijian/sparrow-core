package cn.sparrow.permission.mgt;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;


@EntityScan("cn.sparrow.permission.*")
@SpringBootApplication
public class SparrowApplication {

	public static void main(String[] args) {
		SpringApplication.run(SparrowApplication.class, args);
		
	}

}
