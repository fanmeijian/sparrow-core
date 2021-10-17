package cn.youweisoft.sparrow.permission;

import java.util.ArrayList;
import java.util.List;

import org.jeasy.random.EasyRandom;
import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;
import org.springframework.data.repository.Repository;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import cn.sparrow.common.repository.SysroleRepository;
import cn.sparrow.common.repository.SysroleUrlPermissionRepository;
import cn.sparrow.common.repository.UrlRepository;
import cn.sparrow.model.permission.SparrowUrl;
import cn.sparrow.model.permission.Sysrole;
import cn.sparrow.model.permission.SysroleUrlPermissionPK;
import cn.sparrow.permission.service.UrlPermissionService;

//@RunWith(SpringJUnit4ClassRunner.class)
//@ContextConfiguration(classes = { TestJpaConfig.class,
//		UrlPermissionService.class }, loader = AnnotationConfigContextLoader.class)
//@Transactional

@RunWith(SpringRunner.class)
@DataJpaTest(includeFilters = @ComponentScan.Filter(type = FilterType.ANNOTATION, classes = Repository.class))
@ContextConfiguration(classes = {UrlPermissionService.class})
@AutoConfigureTestDatabase(replace=Replace.NONE)
@EnableAutoConfiguration
//@Import(UrlPermissionService.class)
class UrServicelUnitTest extends SampleBaseTestCase {

	@Autowired
	UrlPermissionService urlPermissionService;

	@Autowired
	UrlRepository urlRepository;
	@Autowired
	SysroleRepository sysroleRepository;
	@Autowired SysroleUrlPermissionRepository sysroleUrlPermissionRepository;

	EasyRandom easyRandom = new EasyRandom();
	
	@Before
    public void setUp(){
        MockitoAnnotations.initMocks(this);
    }

	@Test
	void test() {
		List<SysroleUrlPermissionPK> list = new ArrayList<SysroleUrlPermissionPK>();
		Sysrole sysrole = new Sysrole(easyRandom.nextObject(String.class), easyRandom.nextObject(String.class));
		list.add(new SysroleUrlPermissionPK(sysroleRepository.save(sysrole).getId(),
				urlRepository.save(easyRandom.nextObject(SparrowUrl.class)).getId()));
		urlPermissionService.addSysroleUrlPermission(list);
	}

}
