package cn.sparrow.permission.mgt.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Table;
import javax.persistence.metamodel.EntityType;
import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;

import cn.sparrow.permission.mgt.api.ModelService;
import cn.sparrow.permission.mgt.api.SparrowService;
import cn.sparrow.permission.mgt.api.SysroleService;
import cn.sparrow.permission.mgt.service.repository.ApiRepository;
import cn.sparrow.permission.mgt.service.repository.ModelAttributeRepository;
import cn.sparrow.permission.mgt.service.repository.ModelRepository;
import cn.sparrow.permission.model.resource.Model;
import cn.sparrow.permission.model.resource.ModelAttribute;
import cn.sparrow.permission.model.resource.ModelAttributePK;

@Service
public class SparrowServiceImpl implements SparrowService{

	private static Logger logger = LoggerFactory.getLogger(SparrowServiceImpl.class);

	@Autowired
	SysroleService sysroleService;
	@Autowired
	ModelService modelService;

	@Autowired
	private ConfigurableApplicationContext appContext;

	private DataSource ds ;
	private JdbcTemplate jdbcTemplate ;
	private List<String> urlList = new ArrayList<String>();
	private List<String> menuList = new ArrayList<String>();
	private String sysroleId = UUID.randomUUID().toString().replaceAll("-", "");

	public void init(ConfigurableApplicationContext appContext) {
		this.appContext = appContext;
		 ds = appContext.getBean(DataSource.class);
		 jdbcTemplate = appContext.getBean(JdbcTemplate.class);
		initSysrole();
		initMenu();
		initUrl();
//		initUrlPermission();
		modelService.init();
		logger.info("finish model init.");

	}

	public void initSysrole() {		
		jdbcTemplate.execute(
				"insert into spr_sysrole(id, name, code,  created_date,created_by, modified_date, modified_by,is_system) values('"
						+ sysroleId + "','超级管理员','SYSADMIN',now(),'SparrowSystem',now(),'SparrowSystem',true);");
		logger.info("Create sysrole SYSADMIN");

		jdbcTemplate.execute(
				"insert into spr_sysrole(id, name, code, created_date,created_by, modified_date, modified_by,is_system) values('"
						+ UUID.randomUUID().toString().replaceAll("-", "") + "','管理员','ADMIN',now(),'SparrowSystem',now(),'SparrowSystem',true);");
		logger.info("Create sysrole ADMIN");

		jdbcTemplate.execute(
				"insert into spr_user_sysrole(username, sysrole_id, created_date,created_by, modified_date, modified_by) values('ROOT','"
						+ sysroleId + "',now(),'SparrowSystem',now(),'SparrowSystem');");
		logger.info("Grant sysrole SYSADMIN to user ROOT");
		
		
	}

	public void initUrl() {
		
		for (int i = 0; i < 85; i++) {
			urlList.add(UUID.randomUUID().toString().replaceAll("-", ""));
		}
		jdbcTemplate.execute("insert into spr_api(id,client_id,method,name,permission,uri,created_by,created_date,modified_by,modified_date,is_system) values('"+urlList.get(0)+"','sparrow','GET','模型属性列表','RESTRICT','/modelAttributes','SparrowSystem',now(),'SparrowSystem',now(),true);");
		jdbcTemplate.execute("insert into spr_api(id,client_id,method,name,permission,uri,created_by,created_date,modified_by,modified_date,is_system) values('"+urlList.get(1)+"','sparrow','POST','新增模型属性','RESTRICT','/modelAttributes/batch','SparrowSystem',now(),'SparrowSystem',now(),true);");
		jdbcTemplate.execute("insert into spr_api(id,client_id,method,name,permission,uri,created_by,created_date,modified_by,modified_date,is_system) values('"+urlList.get(2)+"','sparrow','PATCH','修改模型属性','RESTRICT','/modelAttributes/batch','SparrowSystem',now(),'SparrowSystem',now(),true);");
		jdbcTemplate.execute("insert into spr_api(id,client_id,method,name,permission,uri,created_by,created_date,modified_by,modified_date,is_system) values('"+urlList.get(3)+"','sparrow','DELETE','删除模型属性','RESTRICT','/modelAttributes/batch','SparrowSystem',now(),'SparrowSystem',now(),true);");
		jdbcTemplate.execute("insert into spr_api(id,client_id,method,name,permission,uri,created_by,created_date,modified_by,modified_date,is_system) values('"+urlList.get(4)+"','sparrow','POST','新增模型属性权限','RESTRICT','/modelAttributes/permissions','SparrowSystem',now(),'SparrowSystem',now(),true);");
		jdbcTemplate.execute("insert into spr_api(id,client_id,method,name,permission,uri,created_by,created_date,modified_by,modified_date,is_system) values('"+urlList.get(5)+"','sparrow','DELETE','移除模型属性权限','RESTRICT','/modelAttributes/permissions','SparrowSystem',now(),'SparrowSystem',now(),true);");
		jdbcTemplate.execute("insert into spr_api(id,client_id,method,name,permission,uri,created_by,created_date,modified_by,modified_date,is_system) values('"+urlList.get(6)+"','sparrow','GET','角色列表','RESTRICT','/sysroles','SparrowSystem',now(),'SparrowSystem',now(),true);");
		jdbcTemplate.execute("insert into spr_api(id,client_id,method,name,permission,uri,created_by,created_date,modified_by,modified_date,is_system) values('"+urlList.get(7)+"','sparrow','POST','新增角色','RESTRICT','/sysroles/batch','SparrowSystem',now(),'SparrowSystem',now(),true);");
		jdbcTemplate.execute("insert into spr_api(id,client_id,method,name,permission,uri,created_by,created_date,modified_by,modified_date,is_system) values('"+urlList.get(8)+"','sparrow','PATCH','更新角色','RESTRICT','/sysroles/batch','SparrowSystem',now(),'SparrowSystem',now(),true);");
		jdbcTemplate.execute("insert into spr_api(id,client_id,method,name,permission,uri,created_by,created_date,modified_by,modified_date,is_system) values('"+urlList.get(9)+"','sparrow','DELETE','删除角色','RESTRICT','/sysroles/batch','SparrowSystem',now(),'SparrowSystem',now(),true);");
		jdbcTemplate.execute("insert into spr_api(id,client_id,method,name,permission,uri,created_by,created_date,modified_by,modified_date,is_system) values('"+urlList.get(10)+"','sparrow','GET','查找角色','RESTRICT','/sysroles/search/findContain','SparrowSystem',now(),'SparrowSystem',now(),true);");
		jdbcTemplate.execute("insert into spr_api(id,client_id,method,name,permission,uri,created_by,created_date,modified_by,modified_date,is_system) values('"+urlList.get(11)+"','sparrow','POST','新增角色授权','RESTRICT','/sysroles/permissions','SparrowSystem',now(),'SparrowSystem',now(),true);");
		jdbcTemplate.execute("insert into spr_api(id,client_id,method,name,permission,uri,created_by,created_date,modified_by,modified_date,is_system) values('"+urlList.get(12)+"','sparrow','DELETE','移除角色授权','RESTRICT','/sysroles/permissions','SparrowSystem',now(),'SparrowSystem',now(),true);");
		jdbcTemplate.execute("insert into spr_api(id,client_id,method,name,permission,uri,created_by,created_date,modified_by,modified_date,is_system) values('"+urlList.get(13)+"','sparrow','GET','组织树','RESTRICT','/organizations/getTreeByParentId','SparrowSystem',now(),'SparrowSystem',now(),true);");
		jdbcTemplate.execute("insert into spr_api(id,client_id,method,name,permission,uri,created_by,created_date,modified_by,modified_date,is_system) values('"+urlList.get(14)+"','sparrow','GET','当前用户的组织树','RESTRICT','/organizations/getMyTree','SparrowSystem',now(),'SparrowSystem',now(),true);");
		jdbcTemplate.execute("insert into spr_api(id,client_id,method,name,permission,uri,created_by,created_date,modified_by,modified_date,is_system) values('"+urlList.get(15)+"','sparrow','POST','新增组织','RESTRICT','/organizations/batch','SparrowSystem',now(),'SparrowSystem',now(),true);");
		jdbcTemplate.execute("insert into spr_api(id,client_id,method,name,permission,uri,created_by,created_date,modified_by,modified_date,is_system) values('"+urlList.get(16)+"','sparrow','PATCH','更新组织','RESTRICT','/organizations/batch','SparrowSystem',now(),'SparrowSystem',now(),true);");
		jdbcTemplate.execute("insert into spr_api(id,client_id,method,name,permission,uri,created_by,created_date,modified_by,modified_date,is_system) values('"+urlList.get(17)+"','sparrow','DELETE','删除组织','RESTRICT','/organizations/batch','SparrowSystem',now(),'SparrowSystem',now(),true);");
		jdbcTemplate.execute("insert into spr_api(id,client_id,method,name,permission,uri,created_by,created_date,modified_by,modified_date,is_system) values('"+urlList.get(18)+"','sparrow','GET','查找组织','RESTRICT','/organizations/search/findContain','SparrowSystem',now(),'SparrowSystem',now(),true);");
		jdbcTemplate.execute("insert into spr_api(id,client_id,method,name,permission,uri,created_by,created_date,modified_by,modified_date,is_system) values('"+urlList.get(19)+"','sparrow','POST','设置组织关系','RESTRICT','/organization/relations/batch','SparrowSystem',now(),'SparrowSystem',now(),true);");
		jdbcTemplate.execute("insert into spr_api(id,client_id,method,name,permission,uri,created_by,created_date,modified_by,modified_date,is_system) values('"+urlList.get(20)+"','sparrow','DELETE','删除组织关系','RESTRICT','/organization/relations/batch','SparrowSystem',now(),'SparrowSystem',now(),true);");
		jdbcTemplate.execute("insert into spr_api(id,client_id,method,name,permission,uri,created_by,created_date,modified_by,modified_date,is_system) values('"+urlList.get(21)+"','sparrow','GET','岗位树','RESTRICT','/roles/getTreeByParentId','SparrowSystem',now(),'SparrowSystem',now(),true);");
		jdbcTemplate.execute("insert into spr_api(id,client_id,method,name,permission,uri,created_by,created_date,modified_by,modified_date,is_system) values('"+urlList.get(22)+"','sparrow','POST','新增岗位','RESTRICT','/roles/batch','SparrowSystem',now(),'SparrowSystem',now(),true);");
		jdbcTemplate.execute("insert into spr_api(id,client_id,method,name,permission,uri,created_by,created_date,modified_by,modified_date,is_system) values('"+urlList.get(23)+"','sparrow','PATCH','更新岗位','RESTRICT','/roles/batch','SparrowSystem',now(),'SparrowSystem',now(),true);");
		jdbcTemplate.execute("insert into spr_api(id,client_id,method,name,permission,uri,created_by,created_date,modified_by,modified_date,is_system) values('"+urlList.get(24)+"','sparrow','DELETE','删除岗位','RESTRICT','/roles/batch','SparrowSystem',now(),'SparrowSystem',now(),true);");
		jdbcTemplate.execute("insert into spr_api(id,client_id,method,name,permission,uri,created_by,created_date,modified_by,modified_date,is_system) values('"+urlList.get(25)+"','sparrow','GET','查找岗位','RESTRICT','/roles/search/findContain','SparrowSystem',now(),'SparrowSystem',now(),true);");
		jdbcTemplate.execute("insert into spr_api(id,client_id,method,name,permission,uri,created_by,created_date,modified_by,modified_date,is_system) values('"+urlList.get(26)+"','sparrow','POST','新增岗位关系','RESTRICT','/roles/relations/batch','SparrowSystem',now(),'SparrowSystem',now(),true);");
		jdbcTemplate.execute("insert into spr_api(id,client_id,method,name,permission,uri,created_by,created_date,modified_by,modified_date,is_system) values('"+urlList.get(27)+"','sparrow','DELETE','删除岗位关系','RESTRICT','/roles/relations/batch','SparrowSystem',now(),'SparrowSystem',now(),true);");
		jdbcTemplate.execute("insert into spr_api(id,client_id,method,name,permission,uri,created_by,created_date,modified_by,modified_date,is_system) values('"+urlList.get(28)+"','sparrow','GET','级别树','RESTRICT','/levels/getTreeByParentId','SparrowSystem',now(),'SparrowSystem',now(),true);");
		jdbcTemplate.execute("insert into spr_api(id,client_id,method,name,permission,uri,created_by,created_date,modified_by,modified_date,is_system) values('"+urlList.get(29)+"','sparrow','POST','新增级别','RESTRICT','/levels/batch','SparrowSystem',now(),'SparrowSystem',now(),true);");
		jdbcTemplate.execute("insert into spr_api(id,client_id,method,name,permission,uri,created_by,created_date,modified_by,modified_date,is_system) values('"+urlList.get(30)+"','sparrow','PATCH','更新级别','RESTRICT','/levels/batch','SparrowSystem',now(),'SparrowSystem',now(),true);");
		jdbcTemplate.execute("insert into spr_api(id,client_id,method,name,permission,uri,created_by,created_date,modified_by,modified_date,is_system) values('"+urlList.get(31)+"','sparrow','DELETE','删除级别','RESTRICT','/levels/batch','SparrowSystem',now(),'SparrowSystem',now(),true);");
		jdbcTemplate.execute("insert into spr_api(id,client_id,method,name,permission,uri,created_by,created_date,modified_by,modified_date,is_system) values('"+urlList.get(32)+"','sparrow','GET','查找级别','RESTRICT','/levels/search/findContain','SparrowSystem',now(),'SparrowSystem',now(),true);");
		jdbcTemplate.execute("insert into spr_api(id,client_id,method,name,permission,uri,created_by,created_date,modified_by,modified_date,is_system) values('"+urlList.get(33)+"','sparrow','POST','新增级别关系','RESTRICT','/level/relations/batch','SparrowSystem',now(),'SparrowSystem',now(),true);");
		jdbcTemplate.execute("insert into spr_api(id,client_id,method,name,permission,uri,created_by,created_date,modified_by,modified_date,is_system) values('"+urlList.get(34)+"','sparrow','DELETE','删除级别关系','RESTRICT','/level/relations/batch','SparrowSystem',now(),'SparrowSystem',now(),true);");
		jdbcTemplate.execute("insert into spr_api(id,client_id,method,name,permission,uri,created_by,created_date,modified_by,modified_date,is_system) values('"+urlList.get(35)+"','sparrow','GET','模型列表','RESTRICT','/models','SparrowSystem',now(),'SparrowSystem',now(),true);");
		jdbcTemplate.execute("insert into spr_api(id,client_id,method,name,permission,uri,created_by,created_date,modified_by,modified_date,is_system) values('"+urlList.get(36)+"','sparrow','PATCH','修改模型','RESTRICT','/models/batch','SparrowSystem',now(),'SparrowSystem',now(),true);");
		jdbcTemplate.execute("insert into spr_api(id,client_id,method,name,permission,uri,created_by,created_date,modified_by,modified_date,is_system) values('"+urlList.get(37)+"','sparrow','POST','新增模型','RESTRICT','/models/batch','SparrowSystem',now(),'SparrowSystem',now(),true);");
		jdbcTemplate.execute("insert into spr_api(id,client_id,method,name,permission,uri,created_by,created_date,modified_by,modified_date,is_system) values('"+urlList.get(38)+"','sparrow','POST','设置模型权限','RESTRICT','/models/permissions','SparrowSystem',now(),'SparrowSystem',now(),true);");
		jdbcTemplate.execute("insert into spr_api(id,client_id,method,name,permission,uri,created_by,created_date,modified_by,modified_date,is_system) values('"+urlList.get(39)+"','sparrow','DELETE','移除模型权限','RESTRICT','/models/permissions','SparrowSystem',now(),'SparrowSystem',now(),true);");
		jdbcTemplate.execute("insert into spr_api(id,client_id,method,name,permission,uri,created_by,created_date,modified_by,modified_date,is_system) values('"+urlList.get(40)+"','sparrow','GET','查看模型权限','RESTRICT','/models/permissions','SparrowSystem',now(),'SparrowSystem',now(),true);");
		jdbcTemplate.execute("insert into spr_api(id,client_id,method,name,permission,uri,created_by,created_date,modified_by,modified_date,is_system) values('"+urlList.get(41)+"','sparrow','DELETE','删除模型','RESTRICT','/models/batch','SparrowSystem',now(),'SparrowSystem',now(),true);");
		jdbcTemplate.execute("insert into spr_api(id,client_id,method,name,permission,uri,created_by,created_date,modified_by,modified_date,is_system) values('"+urlList.get(42)+"','sparrow','GET','根据父ID获取群组树','RESTRICT','/groups/getTreeByParentId','SparrowSystem',now(),'SparrowSystem',now(),true);");
		jdbcTemplate.execute("insert into spr_api(id,client_id,method,name,permission,uri,created_by,created_date,modified_by,modified_date,is_system) values('"+urlList.get(43)+"','sparrow','GET','根据组织ID获取群组树','RESTRICT','/groups/getTreeByOrganizationId','SparrowSystem',now(),'SparrowSystem',now(),true);");
		jdbcTemplate.execute("insert into spr_api(id,client_id,method,name,permission,uri,created_by,created_date,modified_by,modified_date,is_system) values('"+urlList.get(44)+"','sparrow','GET','群组成员列表','RESTRICT','/groups/*/members','SparrowSystem',now(),'SparrowSystem',now(),true);");
		jdbcTemplate.execute("insert into spr_api(id,client_id,method,name,permission,uri,created_by,created_date,modified_by,modified_date,is_system) values('"+urlList.get(45)+"','sparrow','POST','新增群组','RESTRICT','/groups/batch','SparrowSystem',now(),'SparrowSystem',now(),true);");
		jdbcTemplate.execute("insert into spr_api(id,client_id,method,name,permission,uri,created_by,created_date,modified_by,modified_date,is_system) values('"+urlList.get(46)+"','sparrow','DELETE','删除群组','RESTRICT','/groups/batch','SparrowSystem',now(),'SparrowSystem',now(),true);");
		jdbcTemplate.execute("insert into spr_api(id,client_id,method,name,permission,uri,created_by,created_date,modified_by,modified_date,is_system) values('"+urlList.get(47)+"','sparrow','GET','查找群组','RESTRICT','/groups/search/findContain','SparrowSystem',now(),'SparrowSystem',now(),true);");
		jdbcTemplate.execute("insert into spr_api(id,client_id,method,name,permission,uri,created_by,created_date,modified_by,modified_date,is_system) values('"+urlList.get(48)+"','sparrow','POST','添加群组成员','RESTRICT','/groups/members','SparrowSystem',now(),'SparrowSystem',now(),true);");
		jdbcTemplate.execute("insert into spr_api(id,client_id,method,name,permission,uri,created_by,created_date,modified_by,modified_date,is_system) values('"+urlList.get(49)+"','sparrow','DELETE','移除群组成员','RESTRICT','/groups/members','SparrowSystem',now(),'SparrowSystem',now(),true);");
		jdbcTemplate.execute("insert into spr_api(id,client_id,method,name,permission,uri,created_by,created_date,modified_by,modified_date,is_system) values('"+urlList.get(50)+"','sparrow','GET','审计日志列表','RESTRICT','/operationLogs','SparrowSystem',now(),'SparrowSystem',now(),true);");
		jdbcTemplate.execute("insert into spr_api(id,client_id,method,name,permission,uri,created_by,created_date,modified_by,modified_date,is_system) values('"+urlList.get(51)+"','sparrow','GET','根据父ID获取菜单树','RESTRICT','/menus/getTreeByParentId','SparrowSystem',now(),'SparrowSystem',now(),true);");
		jdbcTemplate.execute("insert into spr_api(id,client_id,method,name,permission,uri,created_by,created_date,modified_by,modified_date,is_system) values('"+urlList.get(52)+"','sparrow','GET','根据用户名获取菜单树','RESTRICT','/menus/getTreeByUsername','SparrowSystem',now(),'SparrowSystem',now(),true);");
		jdbcTemplate.execute("insert into spr_api(id,client_id,method,name,permission,uri,created_by,created_date,modified_by,modified_date,is_system) values('"+urlList.get(53)+"','sparrow','GET','根据角色ID获取菜单树','RESTRICT','/menus/getTreeBySysroleId','SparrowSystem',now(),'SparrowSystem',now(),true);");
		jdbcTemplate.execute("insert into spr_api(id,client_id,method,name,permission,uri,created_by,created_date,modified_by,modified_date,is_system) values('"+urlList.get(54)+"','sparrow','GET','获取当前用户菜单树','RESTRICT','/menus/getMyTree','SparrowSystem',now(),'SparrowSystem',now(),true);");
		jdbcTemplate.execute("insert into spr_api(id,client_id,method,name,permission,uri,created_by,created_date,modified_by,modified_date,is_system) values('"+urlList.get(55)+"','sparrow','POST','新增菜单','RESTRICT','/menus/batch','SparrowSystem',now(),'SparrowSystem',now(),true);");
		jdbcTemplate.execute("insert into spr_api(id,client_id,method,name,permission,uri,created_by,created_date,modified_by,modified_date,is_system) values('"+urlList.get(56)+"','sparrow','POST','设置菜单权限','RESTRICT','/menus/permissions','SparrowSystem',now(),'SparrowSystem',now(),true);");
		jdbcTemplate.execute("insert into spr_api(id,client_id,method,name,permission,uri,created_by,created_date,modified_by,modified_date,is_system) values('"+urlList.get(57)+"','sparrow','DELETE','移除菜单权限','RESTRICT','/menus/permissions','SparrowSystem',now(),'SparrowSystem',now(),true);");
		jdbcTemplate.execute("insert into spr_api(id,client_id,method,name,permission,uri,created_by,created_date,modified_by,modified_date,is_system) values('"+urlList.get(58)+"','sparrow','PATCH','修改菜单','RESTRICT','/menus/batch','SparrowSystem',now(),'SparrowSystem',now(),true);");
		jdbcTemplate.execute("insert into spr_api(id,client_id,method,name,permission,uri,created_by,created_date,modified_by,modified_date,is_system) values('"+urlList.get(59)+"','sparrow','DELETE','删除菜单','RESTRICT','/menus/batch','SparrowSystem',now(),'SparrowSystem',now(),true);");
		jdbcTemplate.execute("insert into spr_api(id,client_id,method,name,permission,uri,created_by,created_date,modified_by,modified_date,is_system) values('"+urlList.get(60)+"','sparrow','POST','设置菜单权限','RESTRICT','/menus/permissions','SparrowSystem',now(),'SparrowSystem',now(),true);");
		jdbcTemplate.execute("insert into spr_api(id,client_id,method,name,permission,uri,created_by,created_date,modified_by,modified_date,is_system) values('"+urlList.get(61)+"','sparrow','DELETE','删除菜单权限','RESTRICT','/menus/permissions','SparrowSystem',now(),'SparrowSystem',now(),true);");
		jdbcTemplate.execute("insert into spr_api(id,client_id,method,name,permission,uri,created_by,created_date,modified_by,modified_date,is_system) values('"+urlList.get(62)+"','sparrow','GET','URL资源列表','RESTRICT','/sparrowUrls','SparrowSystem',now(),'SparrowSystem',now(),true);");
		jdbcTemplate.execute("insert into spr_api(id,client_id,method,name,permission,uri,created_by,created_date,modified_by,modified_date,is_system) values('"+urlList.get(63)+"','sparrow','POST','新增URL资源','RESTRICT','/sparrowUrls/batch','SparrowSystem',now(),'SparrowSystem',now(),true);");
		jdbcTemplate.execute("insert into spr_api(id,client_id,method,name,permission,uri,created_by,created_date,modified_by,modified_date,is_system) values('"+urlList.get(64)+"','sparrow','POST','设置URL资源访问权限','RESTRICT','/sparrowUrls/permissions','SparrowSystem',now(),'SparrowSystem',now(),true);");
		jdbcTemplate.execute("insert into spr_api(id,client_id,method,name,permission,uri,created_by,created_date,modified_by,modified_date,is_system) values('"+urlList.get(65)+"','sparrow','DELETE','移除URL资源访问权限','RESTRICT','/sparrowUrls/permissions','SparrowSystem',now(),'SparrowSystem',now(),true);");
		jdbcTemplate.execute("insert into spr_api(id,client_id,method,name,permission,uri,created_by,created_date,modified_by,modified_date,is_system) values('"+urlList.get(66)+"','sparrow','PATCH','更新URL资源','RESTRICT','/sparrowUrls/batch','SparrowSystem',now(),'SparrowSystem',now(),true);");
		jdbcTemplate.execute("insert into spr_api(id,client_id,method,name,permission,uri,created_by,created_date,modified_by,modified_date,is_system) values('"+urlList.get(67)+"','sparrow','DELETE','删除URL资源','RESTRICT','/sparrowUrls/batch','SparrowSystem',now(),'SparrowSystem',now(),true);");
		jdbcTemplate.execute("insert into spr_api(id,client_id,method,name,permission,uri,created_by,created_date,modified_by,modified_date,is_system) values('"+urlList.get(68)+"','sparrow','GET','查看数据权限','RESTRICT','/dataPermissions','SparrowSystem',now(),'SparrowSystem',now(),true);");
		jdbcTemplate.execute("insert into spr_api(id,client_id,method,name,permission,uri,created_by,created_date,modified_by,modified_date,is_system) values('"+urlList.get(69)+"','sparrow','POST','新增数据权限','RESTRICT','/dataPermissions/batch','SparrowSystem',now(),'SparrowSystem',now(),true);");
		jdbcTemplate.execute("insert into spr_api(id,client_id,method,name,permission,uri,created_by,created_date,modified_by,modified_date,is_system) values('"+urlList.get(70)+"','sparrow','DELETE','删除数据权限','RESTRICT','/dataPermissions/batch','SparrowSystem',now(),'SparrowSystem',now(),true);");
		jdbcTemplate.execute("insert into spr_api(id,client_id,method,name,permission,uri,created_by,created_date,modified_by,modified_date,is_system) values('"+urlList.get(71)+"','sparrow','GET','查看数据字段权限','RESTRICT','/dataFieldPermissions','SparrowSystem',now(),'SparrowSystem',now(),true);");
		jdbcTemplate.execute("insert into spr_api(id,client_id,method,name,permission,uri,created_by,created_date,modified_by,modified_date,is_system) values('"+urlList.get(72)+"','sparrow','POST','新增数据字段权限','RESTRICT','/dataFieldPermissions/batch','SparrowSystem',now(),'SparrowSystem',now(),true);");
		jdbcTemplate.execute("insert into spr_api(id,client_id,method,name,permission,uri,created_by,created_date,modified_by,modified_date,is_system) values('"+urlList.get(73)+"','sparrow','DELETE','删除数据字段权限','RESTRICT','/dataFieldPermissions/batch','SparrowSystem',now(),'SparrowSystem',now(),true);");
		jdbcTemplate.execute("insert into spr_api(id,client_id,method,name,permission,uri,created_by,created_date,modified_by,modified_date,is_system) values('"+urlList.get(74)+"','sparrow','GET','员工详情','RESTRICT','/employees/*','SparrowSystem',now(),'SparrowSystem',now(),true);");
		jdbcTemplate.execute("insert into spr_api(id,client_id,method,name,permission,uri,created_by,created_date,modified_by,modified_date,is_system) values('"+urlList.get(75)+"','sparrow','GET','员工树','RESTRICT','/employees/getTreeByParentId','SparrowSystem',now(),'SparrowSystem',now(),true);");
		jdbcTemplate.execute("insert into spr_api(id,client_id,method,name,permission,uri,created_by,created_date,modified_by,modified_date,is_system) values('"+urlList.get(76)+"','sparrow','POST','新增员工','RESTRICT','/employees/batch','SparrowSystem',now(),'SparrowSystem',now(),true);");
		jdbcTemplate.execute("insert into spr_api(id,client_id,method,name,permission,uri,created_by,created_date,modified_by,modified_date,is_system) values('"+urlList.get(77)+"','sparrow','PATCH','修改员工信息','RESTRICT','/employees/batch','SparrowSystem',now(),'SparrowSystem',now(),true);");
		jdbcTemplate.execute("insert into spr_api(id,client_id,method,name,permission,uri,created_by,created_date,modified_by,modified_date,is_system) values('"+urlList.get(78)+"','sparrow','DELETE','删除员工','RESTRICT','/employees/batch','SparrowSystem',now(),'SparrowSystem',now(),true);");
		jdbcTemplate.execute("insert into spr_api(id,client_id,method,name,permission,uri,created_by,created_date,modified_by,modified_date,is_system) values('"+urlList.get(79)+"','sparrow','POST','员工岗位任职','RESTRICT','/employees/roles/batch','SparrowSystem',now(),'SparrowSystem',now(),true);");
		jdbcTemplate.execute("insert into spr_api(id,client_id,method,name,permission,uri,created_by,created_date,modified_by,modified_date,is_system) values('"+urlList.get(80)+"','sparrow','DELETE','删除员工岗位','RESTRICT','/employees/roles/batch','SparrowSystem',now(),'SparrowSystem',now(),true);");
		jdbcTemplate.execute("insert into spr_api(id,client_id,method,name,permission,uri,created_by,created_date,modified_by,modified_date,is_system) values('"+urlList.get(81)+"','sparrow','POST','员工级别任职','RESTRICT','/employees/levels/batch','SparrowSystem',now(),'SparrowSystem',now(),true);");
		jdbcTemplate.execute("insert into spr_api(id,client_id,method,name,permission,uri,created_by,created_date,modified_by,modified_date,is_system) values('"+urlList.get(82)+"','sparrow','DELETE','删除员工级别','RESTRICT','/employees/levels/batch','SparrowSystem',now(),'SparrowSystem',now(),true);");
		jdbcTemplate.execute("insert into spr_api(id,client_id,method,name,permission,uri,created_by,created_date,modified_by,modified_date,is_system) values('"+urlList.get(83)+"','sparrow','POST','设置员工关系','RESTRICT','/employees/relations/batch','SparrowSystem',now(),'SparrowSystem',now(),true);");
		jdbcTemplate.execute("insert into spr_api(id,client_id,method,name,permission,uri,created_by,created_date,modified_by,modified_date,is_system) values('"+urlList.get(84)+"','sparrow','DELETE','删除员工关系','RESTRICT','/employees/relations/batch','SparrowSystem',now(),'SparrowSystem',now(),true);");
		logger.info("Init Url finished");
	}

	public void initMenu() {
		for (int i = 0; i < 20; i++) {
			menuList.add(UUID.randomUUID().toString().replaceAll("-", ""));
		}
		jdbcTemplate.execute("insert into spr_menu(id, name,code, icon,parent_id, url, created_date, modified_by, modified_date,created_by,is_system) values('"+ menuList.get(0) + "','组织管理','001','team',null,'/#',now(),'SparrowSystem',now(),'SparrowSystem',true);");
		jdbcTemplate.execute("insert into spr_menu(id, name,code, icon,parent_id, url, created_date, modified_by, modified_date,created_by,is_system) values('"+ menuList.get(1) + "','机构管理','002','','"+ menuList.get(0) + "','/organization',now(),'SparrowSystem',now(),'SparrowSystem',true);");
		jdbcTemplate.execute("insert into spr_menu(id, name,code, icon,parent_id, url, created_date, modified_by, modified_date,created_by,is_system) values('"+ menuList.get(2) + "','岗位管理','003','','"+ menuList.get(0) + "','/role',now(),'SparrowSystem',now(),'SparrowSystem',true);");
		jdbcTemplate.execute("insert into spr_menu(id, name,code, icon,parent_id, url, created_date, modified_by, modified_date,created_by,is_system) values('"+ menuList.get(3) + "','级别管理','004','','"+ menuList.get(0) + "','/level',now(),'SparrowSystem',now(),'SparrowSystem',true);");
		jdbcTemplate.execute("insert into spr_menu(id, name,code, icon,parent_id, url, created_date, modified_by, modified_date,created_by,is_system) values('"+ menuList.get(4) + "','群组管理','005','','"+ menuList.get(0) + "','/group',now(),'SparrowSystem',now(),'SparrowSystem',true);");
		jdbcTemplate.execute("insert into spr_menu(id, name,code, icon,parent_id, url, created_date, modified_by, modified_date,created_by,is_system) values('"+ menuList.get(5) + "','员工管理','006','','"+ menuList.get(0) + "','/employee',now(),'SparrowSystem',now(),'SparrowSystem',true);");
		jdbcTemplate.execute("insert into spr_menu(id, name,code, icon,parent_id, url, created_date, modified_by, modified_date,created_by,is_system) values('"+ menuList.get(6) + "','权限管理','007','safety-certificate',null,'/#',now(),'SparrowSystem',now(),'SparrowSystem',true);");
		jdbcTemplate.execute("insert into spr_menu(id, name,code, icon,parent_id, url, created_date, modified_by, modified_date,created_by,is_system) values('"+ menuList.get(7) + "','接口管理','008','','"+ menuList.get(6) + "','/url',now(),'SparrowSystem',now(),'SparrowSystem',true);");
		jdbcTemplate.execute("insert into spr_menu(id, name,code, icon,parent_id, url, created_date, modified_by, modified_date,created_by,is_system) values('"+ menuList.get(8) + "','菜单管理','009','','"+ menuList.get(6) + "','/menu',now(),'SparrowSystem',now(),'SparrowSystem',true);");
		jdbcTemplate.execute("insert into spr_menu(id, name,code, icon,parent_id, url, created_date, modified_by, modified_date,created_by,is_system) values('"+ menuList.get(9) + "','应用管理','010','','"+ menuList.get(6) + "','/sparrowApp',now(),'SparrowSystem',now(),'SparrowSystem',true);");
		jdbcTemplate.execute("insert into spr_menu(id, name,code, icon,parent_id, url, created_date, modified_by, modified_date,created_by,is_system) values('"+ menuList.get(10) + "','角色管理','011','','"+ menuList.get(6) + "','/sysrole',now(),'SparrowSystem',now(),'SparrowSystem',true);");
		jdbcTemplate.execute("insert into spr_menu(id, name,code, icon,parent_id, url, created_date, modified_by, modified_date,created_by,is_system) values('"+ menuList.get(11) + "','模型管理','012','','"+ menuList.get(6) + "','/model',now(),'SparrowSystem',now(),'SparrowSystem',true);");
		jdbcTemplate.execute("insert into spr_menu(id, name,code, icon,parent_id, url, created_date, modified_by, modified_date,created_by,is_system) values('"+ menuList.get(12) + "','模型权限管理','013','','"+ menuList.get(6) + "','/modelPermissionManagement',now(),'SparrowSystem',now(),'SparrowSystem',true);");
		jdbcTemplate.execute("insert into spr_menu(id, name,code, icon,parent_id, url, created_date, modified_by, modified_date,created_by,is_system) values('"+ menuList.get(13) + "','数据权限管理','014','','"+ menuList.get(6) + "','/dataPermissionManagement',now(),'SparrowSystem',now(),'SparrowSystem',true);");
		jdbcTemplate.execute("insert into spr_menu(id, name,code, icon,parent_id, url, created_date, modified_by, modified_date,created_by,is_system) values('"+ menuList.get(14) + "','文件权限管理','015','','"+ menuList.get(6) + "','/filePermission',now(),'SparrowSystem',now(),'SparrowSystem',true);");
		jdbcTemplate.execute("insert into spr_menu(id, name,code, icon,parent_id, url, created_date, modified_by, modified_date,created_by,is_system) values('"+ menuList.get(15) + "','系统配置','016','setting',null,'/#',now(),'SparrowSystem',now(),'SparrowSystem',true);");
		jdbcTemplate.execute("insert into spr_menu(id, name,code, icon,parent_id, url, created_date, modified_by, modified_date,created_by,is_system) values('"+ menuList.get(16) + "','数据字典','017','','"+ menuList.get(15) + "','/dict',now(),'SparrowSystem',now(),'SparrowSystem',true);");
		jdbcTemplate.execute("insert into spr_menu(id, name,code, icon,parent_id, url, created_date, modified_by, modified_date,created_by,is_system) values('"+ menuList.get(17) + "','流水号','018','','"+ menuList.get(15) + "','/serialNumber',now(),'SparrowSystem',now(),'SparrowSystem',true);");
		jdbcTemplate.execute("insert into spr_menu(id, name,code, icon,parent_id, url, created_date, modified_by, modified_date,created_by,is_system) values('"+ menuList.get(18) + "','全局配置','019','','"+ menuList.get(15) + "','/config',now(),'SparrowSystem',now(),'SparrowSystem',true);");
		jdbcTemplate.execute("insert into spr_menu(id, name,code, icon,parent_id, url, created_date, modified_by, modified_date,created_by,is_system) values('"+ menuList.get(19) + "','审计日志','020','','"+ menuList.get(15) + "','/log',now(),'SparrowSystem',now(),'SparrowSystem',true);");
		logger.info("Init menu finished");
		jdbcTemplate.execute("insert into spr_sysrole_menu(sysrole_id, menu_id) values('"+sysroleId+"','"+menuList.get(0)+"')");
		jdbcTemplate.execute("insert into spr_sysrole_menu(sysrole_id, menu_id) values('"+sysroleId+"','"+menuList.get(6)+"')");
		jdbcTemplate.execute("insert into spr_sysrole_menu(sysrole_id, menu_id) values('"+sysroleId+"','"+menuList.get(15)+"')");

	}

	public void initUrlPermission() {
		for (String url : urlList) {
			jdbcTemplate.execute(
					"insert into spr_sysrole_api(api_id, sysrole_id, created_date, modified_by, modified_date,created_by) values('" + url
							+ "','" + sysroleId  + "',now(),'SparrowSystem',now(),'SparrowSystem');");
		}
	}
	
	@Autowired
	SparrowService sparrowService;

	

	@Autowired
	ModelRepository modelRepository;

	@Autowired
	ModelAttributeRepository modelAttributeRepository;

	@Autowired
	ApiRepository urlRepository;

	@PersistenceContext
	EntityManager entityManager;
	@Autowired
	private ApplicationContext applicationContext;
	

	@GetMapping("/models/syncToTable")
	public Map<String, List<String>> syncToTable() {
		Set<EntityType<?>> entityTypes = entityManager.getMetamodel().getEntities();
		Map<String, List<String>> entitiesMap = new HashMap<String, List<String>>();

		entityTypes.forEach(e -> {
			Model model = modelRepository.save(new Model(e.getJavaType().getName()));

			List<String> attributes = new ArrayList<String>();
			e.getAttributes().forEach(a -> {
				attributes.add(a.getName());

				modelAttributeRepository.save(new ModelAttribute(new ModelAttributePK(a.getName(), model.getName()),
						a.getJavaType().getName()));

			});
			entitiesMap.put(e.getJavaType().getName(), attributes);

		});
		return entitiesMap;

	}

	@GetMapping("/models/allEntities")
	public Map<String, Object> allEntities() {
		Map<String, Object> beans = applicationContext.getBeansWithAnnotation(Table.class);
		return beans;
	}

}
