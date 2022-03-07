package cn.sparrow.permission.mgt.service;

import org.junit.jupiter.api.Test;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import cn.sparrow.permission.model.resource.SparrowApi;

@SpringJUnitConfig
@SpringBootTest
@EnableAutoConfiguration
//@ExtendWith(SpringRunner.class)
@ContextConfiguration(classes = {SparrowApi.class})
public class ApiTest {

	@Test
	void test() {
		
	}
}
