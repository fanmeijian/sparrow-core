package cn.sparrow.permission.mgt.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.data.domain.Pageable;

import cn.sparrow.permission.constant.ApiPermissionEnum;
import cn.sparrow.permission.constant.HttpMethodEnum;
import cn.sparrow.permission.core.api.PermissionService;
import cn.sparrow.permission.core.service.PermissionServiceImpl;
import cn.sparrow.permission.mgt.service.impl.ApiServiceImpl;
import cn.sparrow.permission.mgt.service.impl.SysroleServiceImpl;
import cn.sparrow.permission.mgt.service.repository.ApiRepository;
import cn.sparrow.permission.mgt.service.repository.SysroleRepository;
import cn.sparrow.permission.model.resource.SparrowApi;
import cn.sparrow.permission.model.resource.Sysrole;
import cn.sparrow.permission.model.resource.SysroleApiPK;
import lombok.extern.slf4j.Slf4j;

// @SpringJUnitConfig
// @SpringBootTest
// @EnableAutoConfiguration
// @ExtendWith(SpringRunner.class)
// @ContextConfiguration(classes = {SparrowApi.class})
// @ExtendWith(SpringExtension.class)

@Slf4j
@SpringBootTest
public class ApiTests {
	@Autowired
	private static TestEntityManager entityManager;

	@Autowired
	ApiRepository apiRepository;
	@Autowired
	SysroleRepository sysroleRepository;

	@Autowired
	SysroleServiceImpl sysroleService;

	@Autowired
	ApiServiceImpl apiService;

	// @BeforeEach
	// public void before(){
	// 	new PermissionServiceImpl((EntityManager) entityManager);
	// }

	@TestConfiguration
    static class PermissionServiceImplTestContextConfiguration {
        @Bean
        public PermissionService permissionService() {
            return new PermissionServiceImpl((EntityManager) entityManager);
        }
    }

	@Test
	void test() throws Exception {
		Sysrole sysrole = new Sysrole("test", "test");
		assertNotNull(sysroleService.create(sysrole));
		SparrowApi sparrowApi = new SparrowApi("-","-",HttpMethodEnum.GET,"",ApiPermissionEnum.AUTHENTICATED);
		assertNotNull(apiService.create(sparrowApi));
		List<SysroleApiPK> sysroleApiPKs =new ArrayList<>();
		sysroleApiPKs.add(new SysroleApiPK(sysrole.getId(), sparrowApi.getId()));
		apiService.addPermissions(sysroleApiPKs);
		log.info("add permission:{} {}", sysrole, sparrowApi);
		assertEquals(1, apiService.getPermissions(sparrowApi.getId(), Pageable.unpaged()).getSize());
		apiService.delPermissions(sysroleApiPKs);
		log.info("del permission:{} {}", sysrole, sparrowApi);
		assertEquals(0, apiService.getPermissions(sparrowApi.getId(), Pageable.unpaged()).getSize());

	}
}
