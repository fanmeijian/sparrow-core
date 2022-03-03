package cn.sparrow.permission;

import javax.persistence.EntityManagerFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SparrowApplication {
	@Autowired
	static
	EntityManagerFactory entityManagerFactory;
	public static void main(String[] args) {
		SpringApplication.run(SparrowApplication.class, args);
		
		System.out.println("-23-84908455348005&&&&&&&&&&&&&&"+entityManagerFactory);
		
	}

}
