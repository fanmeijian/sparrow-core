package cn.sparrow.permission.mgt.service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;

import cn.sparrow.permission.core.api.PermissionService;
import cn.sparrow.permission.core.service.PermissionServiceImpl;

@EntityScan("cn.sparrow.permission.*")
@SpringBootApplication
public class TestApplication {
    public static void main(String[] args) {
		SpringApplication.run(TestApplication.class, args);
	}
}
