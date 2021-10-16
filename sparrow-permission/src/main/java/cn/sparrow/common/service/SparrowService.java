package cn.sparrow.common.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import cn.sparrow.permission.service.ModelService;
import cn.sparrow.permission.service.SysroleService;
import cn.sparrow.permission.service.UrlPermissionService;

@Service
public class SparrowService {

	private static Logger logger = LoggerFactory.getLogger(UrlPermissionService.class);

	@Autowired
	SysroleService sysroleService;
	@Autowired
	UrlPermissionService urlPermissionService;
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
		initUrlPermission();
//		urlPermissionService.init();
//		logger.info("finish url init.");
//		sysroleService.init();
//		logger.info("finish sysrole init.");
		modelService.init();
		logger.info("finish model init.");

	}

	public void initSysrole() {
		jdbcTemplate.execute(
				"insert into spr_sysrole(id, name, code,  created_date,created_by, modified_date, modified_by) values('"
						+ UUID.randomUUID().toString().replaceAll("-", "") + "','超级管理员','SYSADMIN',now(),'SparrowSystem',now(),'SparrowSystem');");
		logger.info("Create sysrole SYSADMIN");

		jdbcTemplate.execute(
				"insert into spr_sysrole(id, name, code, created_date,created_by, modified_date, modified_by) values('"
						+ sysroleId + "','管理员','ADMIN',now(),'SparrowSystem',now(),'SparrowSystem');");
		logger.info("Create sysrole ADMIN");

		jdbcTemplate.execute(
				"insert into spr_user_sysrole(username, sysrole_id, created_date,created_by, modified_date, modified_by) values('ROOT','"
						+ sysroleId + "',now(),'SparrowSystem',now(),'SparrowSystem');");
		logger.info("Grant sysrole SYSADMIN to user ROOT");
	}

	public void initUrl() {
		
		for (int i = 0; i < 198; i++) {
			urlList.add(UUID.randomUUID().toString().replaceAll("-", ""));
		}
		jdbcTemplate.execute("insert into spr_url(id,client_id,method,name,permission,uri,created_by,created_date,modified_by,modified_date) values('"+urlList.get(0)+"','sparrow','GET','角色菜单列表','AUTHENTICATED','/sysroleMenus/**','SparrowSystem',now(),'SparrowSystem',now());");
		jdbcTemplate.execute("insert into spr_url(id,client_id,method,name,permission,uri,created_by,created_date,modified_by,modified_date) values('"+urlList.get(1)+"','sparrow','POST','新增角色菜单','RESTRICT','/sysroles/*/addMenus','SparrowSystem',now(),'SparrowSystem',now());");
		jdbcTemplate.execute("insert into spr_url(id,client_id,method,name,permission,uri,created_by,created_date,modified_by,modified_date) values('"+urlList.get(2)+"','sparrow','DELETE','移除角色菜单','RESTRICT','/sysroles/*/delMenus','SparrowSystem',now(),'SparrowSystem',now());");
		jdbcTemplate.execute("insert into spr_url(id,client_id,method,name,permission,uri,created_by,created_date,modified_by,modified_date) values('"+urlList.get(3)+"','sparrow','GET','模型属性列表','AUTHENTICATED','/modelAttributes/**','SparrowSystem',now(),'SparrowSystem',now());");
		jdbcTemplate.execute("insert into spr_url(id,client_id,method,name,permission,uri,created_by,created_date,modified_by,modified_date) values('"+urlList.get(4)+"','sparrow','POST','新增模型属性','RESTRICT','/modelAttributes/**','SparrowSystem',now(),'SparrowSystem',now());");
		jdbcTemplate.execute("insert into spr_url(id,client_id,method,name,permission,uri,created_by,created_date,modified_by,modified_date) values('"+urlList.get(5)+"','sparrow','PUT','modelAttributes','DENY','/modelAttributes/**','SparrowSystem',now(),'SparrowSystem',now());");
		jdbcTemplate.execute("insert into spr_url(id,client_id,method,name,permission,uri,created_by,created_date,modified_by,modified_date) values('"+urlList.get(6)+"','sparrow','PATCH','修改模型属性','RESTRICT','/modelAttributes/**','SparrowSystem',now(),'SparrowSystem',now());");
		jdbcTemplate.execute("insert into spr_url(id,client_id,method,name,permission,uri,created_by,created_date,modified_by,modified_date) values('"+urlList.get(7)+"','sparrow','DELETE','删除模型属性','RESTRICT','/modelAttributes/**','SparrowSystem',now(),'SparrowSystem',now());");
		jdbcTemplate.execute("insert into spr_url(id,client_id,method,name,permission,uri,created_by,created_date,modified_by,modified_date) values('"+urlList.get(8)+"','sparrow','POST','新增角色模型权限','RESTRICT','/sysroles/*/addModelPermission','SparrowSystem',now(),'SparrowSystem',now());");
		jdbcTemplate.execute("insert into spr_url(id,client_id,method,name,permission,uri,created_by,created_date,modified_by,modified_date) values('"+urlList.get(9)+"','sparrow','DELETE','移除角色模型权限','RESTRICT','/sysroles/*/delModelPermission','SparrowSystem',now(),'SparrowSystem',now());");
		jdbcTemplate.execute("insert into spr_url(id,client_id,method,name,permission,uri,created_by,created_date,modified_by,modified_date) values('"+urlList.get(10)+"','sparrow','GET','groupModelPermissions','AUTHENTICATED','/groupModelPermissions/**','SparrowSystem',now(),'SparrowSystem',now());");
		jdbcTemplate.execute("insert into spr_url(id,client_id,method,name,permission,uri,created_by,created_date,modified_by,modified_date) values('"+urlList.get(11)+"','sparrow','POST','groupModelPermissions','RESTRICT','/groupModelPermissions/**','SparrowSystem',now(),'SparrowSystem',now());");
		jdbcTemplate.execute("insert into spr_url(id,client_id,method,name,permission,uri,created_by,created_date,modified_by,modified_date) values('"+urlList.get(12)+"','sparrow','PUT','groupModelPermissions','DENY','/groupModelPermissions/**','SparrowSystem',now(),'SparrowSystem',now());");
		jdbcTemplate.execute("insert into spr_url(id,client_id,method,name,permission,uri,created_by,created_date,modified_by,modified_date) values('"+urlList.get(13)+"','sparrow','PATCH','groupModelPermissions','RESTRICT','/groupModelPermissions/**','SparrowSystem',now(),'SparrowSystem',now());");
		jdbcTemplate.execute("insert into spr_url(id,client_id,method,name,permission,uri,created_by,created_date,modified_by,modified_date) values('"+urlList.get(14)+"','sparrow','DELETE','groupModelPermissions','RESTRICT','/groupModelPermissions/**','SparrowSystem',now(),'SparrowSystem',now());");
		jdbcTemplate.execute("insert into spr_url(id,client_id,method,name,permission,uri,created_by,created_date,modified_by,modified_date) values('"+urlList.get(15)+"','sparrow','GET','sysroles','AUTHENTICATED','/sysroles/**','SparrowSystem',now(),'SparrowSystem',now());");
		jdbcTemplate.execute("insert into spr_url(id,client_id,method,name,permission,uri,created_by,created_date,modified_by,modified_date) values('"+urlList.get(16)+"','sparrow','POST','sysroles','RESTRICT','/sysroles/**','SparrowSystem',now(),'SparrowSystem',now());");
		jdbcTemplate.execute("insert into spr_url(id,client_id,method,name,permission,uri,created_by,created_date,modified_by,modified_date) values('"+urlList.get(17)+"','sparrow','PUT','sysroles','DENY','/sysroles/**','SparrowSystem',now(),'SparrowSystem',now());");
		jdbcTemplate.execute("insert into spr_url(id,client_id,method,name,permission,uri,created_by,created_date,modified_by,modified_date) values('"+urlList.get(18)+"','sparrow','PATCH','sysroles','RESTRICT','/sysroles/**','SparrowSystem',now(),'SparrowSystem',now());");
		jdbcTemplate.execute("insert into spr_url(id,client_id,method,name,permission,uri,created_by,created_date,modified_by,modified_date) values('"+urlList.get(19)+"','sparrow','DELETE','sysroles','RESTRICT','/sysroles/**','SparrowSystem',now(),'SparrowSystem',now());");
		jdbcTemplate.execute("insert into spr_url(id,client_id,method,name,permission,uri,created_by,created_date,modified_by,modified_date) values('"+urlList.get(20)+"','sparrow','GET','organizations','AUTHENTICATED','/organizations/**','SparrowSystem',now(),'SparrowSystem',now());");
		jdbcTemplate.execute("insert into spr_url(id,client_id,method,name,permission,uri,created_by,created_date,modified_by,modified_date) values('"+urlList.get(21)+"','sparrow','POST','organizations','RESTRICT','/organizations/**','SparrowSystem',now(),'SparrowSystem',now());");
		jdbcTemplate.execute("insert into spr_url(id,client_id,method,name,permission,uri,created_by,created_date,modified_by,modified_date) values('"+urlList.get(22)+"','sparrow','PUT','organizations','DENY','/organizations/**','SparrowSystem',now(),'SparrowSystem',now());");
		jdbcTemplate.execute("insert into spr_url(id,client_id,method,name,permission,uri,created_by,created_date,modified_by,modified_date) values('"+urlList.get(23)+"','sparrow','PATCH','organizations','RESTRICT','/organizations/**','SparrowSystem',now(),'SparrowSystem',now());");
		jdbcTemplate.execute("insert into spr_url(id,client_id,method,name,permission,uri,created_by,created_date,modified_by,modified_date) values('"+urlList.get(24)+"','sparrow','DELETE','organizations','RESTRICT','/organizations/**','SparrowSystem',now(),'SparrowSystem',now());");
		jdbcTemplate.execute("insert into spr_url(id,client_id,method,name,permission,uri,created_by,created_date,modified_by,modified_date) values('"+urlList.get(25)+"','sparrow','GET','roles','AUTHENTICATED','/roles/**','SparrowSystem',now(),'SparrowSystem',now());");
		jdbcTemplate.execute("insert into spr_url(id,client_id,method,name,permission,uri,created_by,created_date,modified_by,modified_date) values('"+urlList.get(26)+"','sparrow','POST','roles','RESTRICT','/roles/**','SparrowSystem',now(),'SparrowSystem',now());");
		jdbcTemplate.execute("insert into spr_url(id,client_id,method,name,permission,uri,created_by,created_date,modified_by,modified_date) values('"+urlList.get(27)+"','sparrow','PUT','roles','DENY','/roles/**','SparrowSystem',now(),'SparrowSystem',now());");
		jdbcTemplate.execute("insert into spr_url(id,client_id,method,name,permission,uri,created_by,created_date,modified_by,modified_date) values('"+urlList.get(28)+"','sparrow','PATCH','roles','RESTRICT','/roles/**','SparrowSystem',now(),'SparrowSystem',now());");
		jdbcTemplate.execute("insert into spr_url(id,client_id,method,name,permission,uri,created_by,created_date,modified_by,modified_date) values('"+urlList.get(29)+"','sparrow','DELETE','roles','RESTRICT','/roles/**','SparrowSystem',now(),'SparrowSystem',now());");
		jdbcTemplate.execute("insert into spr_url(id,client_id,method,name,permission,uri,created_by,created_date,modified_by,modified_date) values('"+urlList.get(30)+"','sparrow','GET','userDataFieldPermissions','AUTHENTICATED','/userDataFieldPermissions/**','SparrowSystem',now(),'SparrowSystem',now());");
		jdbcTemplate.execute("insert into spr_url(id,client_id,method,name,permission,uri,created_by,created_date,modified_by,modified_date) values('"+urlList.get(31)+"','sparrow','POST','userDataFieldPermissions','RESTRICT','/userDataFieldPermissions/**','SparrowSystem',now(),'SparrowSystem',now());");
		jdbcTemplate.execute("insert into spr_url(id,client_id,method,name,permission,uri,created_by,created_date,modified_by,modified_date) values('"+urlList.get(32)+"','sparrow','PUT','userDataFieldPermissions','DENY','/userDataFieldPermissions/**','SparrowSystem',now(),'SparrowSystem',now());");
		jdbcTemplate.execute("insert into spr_url(id,client_id,method,name,permission,uri,created_by,created_date,modified_by,modified_date) values('"+urlList.get(33)+"','sparrow','PATCH','userDataFieldPermissions','RESTRICT','/userDataFieldPermissions/**','SparrowSystem',now(),'SparrowSystem',now());");
		jdbcTemplate.execute("insert into spr_url(id,client_id,method,name,permission,uri,created_by,created_date,modified_by,modified_date) values('"+urlList.get(34)+"','sparrow','DELETE','userDataFieldPermissions','RESTRICT','/userDataFieldPermissions/**','SparrowSystem',now(),'SparrowSystem',now());");
		jdbcTemplate.execute("insert into spr_url(id,client_id,method,name,permission,uri,created_by,created_date,modified_by,modified_date) values('"+urlList.get(35)+"','sparrow','GET','sparrowApps','AUTHENTICATED','/sparrowApps/**','SparrowSystem',now(),'SparrowSystem',now());");
		jdbcTemplate.execute("insert into spr_url(id,client_id,method,name,permission,uri,created_by,created_date,modified_by,modified_date) values('"+urlList.get(36)+"','sparrow','POST','sparrowApps','RESTRICT','/sparrowApps/**','SparrowSystem',now(),'SparrowSystem',now());");
		jdbcTemplate.execute("insert into spr_url(id,client_id,method,name,permission,uri,created_by,created_date,modified_by,modified_date) values('"+urlList.get(37)+"','sparrow','PUT','sparrowApps','DENY','/sparrowApps/**','SparrowSystem',now(),'SparrowSystem',now());");
		jdbcTemplate.execute("insert into spr_url(id,client_id,method,name,permission,uri,created_by,created_date,modified_by,modified_date) values('"+urlList.get(38)+"','sparrow','PATCH','sparrowApps','RESTRICT','/sparrowApps/**','SparrowSystem',now(),'SparrowSystem',now());");
		jdbcTemplate.execute("insert into spr_url(id,client_id,method,name,permission,uri,created_by,created_date,modified_by,modified_date) values('"+urlList.get(39)+"','sparrow','DELETE','sparrowApps','RESTRICT','/sparrowApps/**','SparrowSystem',now(),'SparrowSystem',now());");
		jdbcTemplate.execute("insert into spr_url(id,client_id,method,name,permission,uri,created_by,created_date,modified_by,modified_date) values('"+urlList.get(40)+"','sparrow','GET','groupDataPermissions','AUTHENTICATED','/groupDataPermissions/**','SparrowSystem',now(),'SparrowSystem',now());");
		jdbcTemplate.execute("insert into spr_url(id,client_id,method,name,permission,uri,created_by,created_date,modified_by,modified_date) values('"+urlList.get(41)+"','sparrow','POST','groupDataPermissions','RESTRICT','/groupDataPermissions/**','SparrowSystem',now(),'SparrowSystem',now());");
		jdbcTemplate.execute("insert into spr_url(id,client_id,method,name,permission,uri,created_by,created_date,modified_by,modified_date) values('"+urlList.get(42)+"','sparrow','PUT','groupDataPermissions','DENY','/groupDataPermissions/**','SparrowSystem',now(),'SparrowSystem',now());");
		jdbcTemplate.execute("insert into spr_url(id,client_id,method,name,permission,uri,created_by,created_date,modified_by,modified_date) values('"+urlList.get(43)+"','sparrow','PATCH','groupDataPermissions','RESTRICT','/groupDataPermissions/**','SparrowSystem',now(),'SparrowSystem',now());");
		jdbcTemplate.execute("insert into spr_url(id,client_id,method,name,permission,uri,created_by,created_date,modified_by,modified_date) values('"+urlList.get(44)+"','sparrow','DELETE','groupDataPermissions','RESTRICT','/groupDataPermissions/**','SparrowSystem',now(),'SparrowSystem',now());");
		jdbcTemplate.execute("insert into spr_url(id,client_id,method,name,permission,uri,created_by,created_date,modified_by,modified_date) values('"+urlList.get(45)+"','sparrow','GET','models','AUTHENTICATED','/models/**','SparrowSystem',now(),'SparrowSystem',now());");
		jdbcTemplate.execute("insert into spr_url(id,client_id,method,name,permission,uri,created_by,created_date,modified_by,modified_date) values('"+urlList.get(46)+"','sparrow','POST','models','RESTRICT','/models/**','SparrowSystem',now(),'SparrowSystem',now());");
		jdbcTemplate.execute("insert into spr_url(id,client_id,method,name,permission,uri,created_by,created_date,modified_by,modified_date) values('"+urlList.get(47)+"','sparrow','PUT','models','DENY','/models/**','SparrowSystem',now(),'SparrowSystem',now());");
		jdbcTemplate.execute("insert into spr_url(id,client_id,method,name,permission,uri,created_by,created_date,modified_by,modified_date) values('"+urlList.get(48)+"','sparrow','PATCH','models','RESTRICT','/models/**','SparrowSystem',now(),'SparrowSystem',now());");
		jdbcTemplate.execute("insert into spr_url(id,client_id,method,name,permission,uri,created_by,created_date,modified_by,modified_date) values('"+urlList.get(49)+"','sparrow','DELETE','models','RESTRICT','/models/**','SparrowSystem',now(),'SparrowSystem',now());");
		jdbcTemplate.execute("insert into spr_url(id,client_id,method,name,permission,uri,created_by,created_date,modified_by,modified_date) values('"+urlList.get(50)+"','sparrow','GET','dataLists','AUTHENTICATED','/dataLists/**','SparrowSystem',now(),'SparrowSystem',now());");
		jdbcTemplate.execute("insert into spr_url(id,client_id,method,name,permission,uri,created_by,created_date,modified_by,modified_date) values('"+urlList.get(51)+"','sparrow','POST','dataLists','RESTRICT','/dataLists/**','SparrowSystem',now(),'SparrowSystem',now());");
		jdbcTemplate.execute("insert into spr_url(id,client_id,method,name,permission,uri,created_by,created_date,modified_by,modified_date) values('"+urlList.get(52)+"','sparrow','PUT','dataLists','DENY','/dataLists/**','SparrowSystem',now(),'SparrowSystem',now());");
		jdbcTemplate.execute("insert into spr_url(id,client_id,method,name,permission,uri,created_by,created_date,modified_by,modified_date) values('"+urlList.get(53)+"','sparrow','PATCH','dataLists','RESTRICT','/dataLists/**','SparrowSystem',now(),'SparrowSystem',now());");
		jdbcTemplate.execute("insert into spr_url(id,client_id,method,name,permission,uri,created_by,created_date,modified_by,modified_date) values('"+urlList.get(54)+"','sparrow','DELETE','dataLists','RESTRICT','/dataLists/**','SparrowSystem',now(),'SparrowSystem',now());");
		jdbcTemplate.execute("insert into spr_url(id,client_id,method,name,permission,uri,created_by,created_date,modified_by,modified_date) values('"+urlList.get(55)+"','sparrow','GET','userFiles','AUTHENTICATED','/userFiles/**','SparrowSystem',now(),'SparrowSystem',now());");
		jdbcTemplate.execute("insert into spr_url(id,client_id,method,name,permission,uri,created_by,created_date,modified_by,modified_date) values('"+urlList.get(56)+"','sparrow','POST','userFiles','RESTRICT','/userFiles/**','SparrowSystem',now(),'SparrowSystem',now());");
		jdbcTemplate.execute("insert into spr_url(id,client_id,method,name,permission,uri,created_by,created_date,modified_by,modified_date) values('"+urlList.get(57)+"','sparrow','PUT','userFiles','DENY','/userFiles/**','SparrowSystem',now(),'SparrowSystem',now());");
		jdbcTemplate.execute("insert into spr_url(id,client_id,method,name,permission,uri,created_by,created_date,modified_by,modified_date) values('"+urlList.get(58)+"','sparrow','PATCH','userFiles','RESTRICT','/userFiles/**','SparrowSystem',now(),'SparrowSystem',now());");
		jdbcTemplate.execute("insert into spr_url(id,client_id,method,name,permission,uri,created_by,created_date,modified_by,modified_date) values('"+urlList.get(59)+"','sparrow','DELETE','userFiles','RESTRICT','/userFiles/**','SparrowSystem',now(),'SparrowSystem',now());");
		jdbcTemplate.execute("insert into spr_url(id,client_id,method,name,permission,uri,created_by,created_date,modified_by,modified_date) values('"+urlList.get(60)+"','sparrow','GET','userModelPermissions','AUTHENTICATED','/userModelPermissions/**','SparrowSystem',now(),'SparrowSystem',now());");
		jdbcTemplate.execute("insert into spr_url(id,client_id,method,name,permission,uri,created_by,created_date,modified_by,modified_date) values('"+urlList.get(61)+"','sparrow','POST','userModelPermissions','RESTRICT','/userModelPermissions/**','SparrowSystem',now(),'SparrowSystem',now());");
		jdbcTemplate.execute("insert into spr_url(id,client_id,method,name,permission,uri,created_by,created_date,modified_by,modified_date) values('"+urlList.get(62)+"','sparrow','PUT','userModelPermissions','DENY','/userModelPermissions/**','SparrowSystem',now(),'SparrowSystem',now());");
		jdbcTemplate.execute("insert into spr_url(id,client_id,method,name,permission,uri,created_by,created_date,modified_by,modified_date) values('"+urlList.get(63)+"','sparrow','PATCH','userModelPermissions','RESTRICT','/userModelPermissions/**','SparrowSystem',now(),'SparrowSystem',now());");
		jdbcTemplate.execute("insert into spr_url(id,client_id,method,name,permission,uri,created_by,created_date,modified_by,modified_date) values('"+urlList.get(64)+"','sparrow','DELETE','userModelPermissions','RESTRICT','/userModelPermissions/**','SparrowSystem',now(),'SparrowSystem',now());");
		jdbcTemplate.execute("insert into spr_url(id,client_id,method,name,permission,uri,created_by,created_date,modified_by,modified_date) values('"+urlList.get(65)+"','sparrow','GET','sysroleUrlPermissions','AUTHENTICATED','/sysroleUrlPermissions/**','SparrowSystem',now(),'SparrowSystem',now());");
		jdbcTemplate.execute("insert into spr_url(id,client_id,method,name,permission,uri,created_by,created_date,modified_by,modified_date) values('"+urlList.get(66)+"','sparrow','POST','sysroleUrlPermissions','RESTRICT','/sysroleUrlPermissions/**','SparrowSystem',now(),'SparrowSystem',now());");
		jdbcTemplate.execute("insert into spr_url(id,client_id,method,name,permission,uri,created_by,created_date,modified_by,modified_date) values('"+urlList.get(67)+"','sparrow','PUT','sysroleUrlPermissions','DENY','/sysroleUrlPermissions/**','SparrowSystem',now(),'SparrowSystem',now());");
		jdbcTemplate.execute("insert into spr_url(id,client_id,method,name,permission,uri,created_by,created_date,modified_by,modified_date) values('"+urlList.get(68)+"','sparrow','PATCH','sysroleUrlPermissions','RESTRICT','/sysroleUrlPermissions/**','SparrowSystem',now(),'SparrowSystem',now());");
		jdbcTemplate.execute("insert into spr_url(id,client_id,method,name,permission,uri,created_by,created_date,modified_by,modified_date) values('"+urlList.get(69)+"','sparrow','DELETE','sysroleUrlPermissions','RESTRICT','/sysroleUrlPermissions/**','SparrowSystem',now(),'SparrowSystem',now());");
		jdbcTemplate.execute("insert into spr_url(id,client_id,method,name,permission,uri,created_by,created_date,modified_by,modified_date) values('"+urlList.get(70)+"','sparrow','GET','groupDataFieldPermissions','AUTHENTICATED','/groupDataFieldPermissions/**','SparrowSystem',now(),'SparrowSystem',now());");
		jdbcTemplate.execute("insert into spr_url(id,client_id,method,name,permission,uri,created_by,created_date,modified_by,modified_date) values('"+urlList.get(71)+"','sparrow','POST','groupDataFieldPermissions','RESTRICT','/groupDataFieldPermissions/**','SparrowSystem',now(),'SparrowSystem',now());");
		jdbcTemplate.execute("insert into spr_url(id,client_id,method,name,permission,uri,created_by,created_date,modified_by,modified_date) values('"+urlList.get(72)+"','sparrow','PUT','groupDataFieldPermissions','DENY','/groupDataFieldPermissions/**','SparrowSystem',now(),'SparrowSystem',now());");
		jdbcTemplate.execute("insert into spr_url(id,client_id,method,name,permission,uri,created_by,created_date,modified_by,modified_date) values('"+urlList.get(73)+"','sparrow','PATCH','groupDataFieldPermissions','RESTRICT','/groupDataFieldPermissions/**','SparrowSystem',now(),'SparrowSystem',now());");
		jdbcTemplate.execute("insert into spr_url(id,client_id,method,name,permission,uri,created_by,created_date,modified_by,modified_date) values('"+urlList.get(74)+"','sparrow','DELETE','groupDataFieldPermissions','RESTRICT','/groupDataFieldPermissions/**','SparrowSystem',now(),'SparrowSystem',now());");
		jdbcTemplate.execute("insert into spr_url(id,client_id,method,name,permission,uri,created_by,created_date,modified_by,modified_date) values('"+urlList.get(75)+"','sparrow','GET','userDataPermissions','AUTHENTICATED','/userDataPermissions/**','SparrowSystem',now(),'SparrowSystem',now());");
		jdbcTemplate.execute("insert into spr_url(id,client_id,method,name,permission,uri,created_by,created_date,modified_by,modified_date) values('"+urlList.get(76)+"','sparrow','POST','userDataPermissions','RESTRICT','/userDataPermissions/**','SparrowSystem',now(),'SparrowSystem',now());");
		jdbcTemplate.execute("insert into spr_url(id,client_id,method,name,permission,uri,created_by,created_date,modified_by,modified_date) values('"+urlList.get(77)+"','sparrow','PUT','userDataPermissions','DENY','/userDataPermissions/**','SparrowSystem',now(),'SparrowSystem',now());");
		jdbcTemplate.execute("insert into spr_url(id,client_id,method,name,permission,uri,created_by,created_date,modified_by,modified_date) values('"+urlList.get(78)+"','sparrow','PATCH','userDataPermissions','RESTRICT','/userDataPermissions/**','SparrowSystem',now(),'SparrowSystem',now());");
		jdbcTemplate.execute("insert into spr_url(id,client_id,method,name,permission,uri,created_by,created_date,modified_by,modified_date) values('"+urlList.get(79)+"','sparrow','DELETE','userDataPermissions','RESTRICT','/userDataPermissions/**','SparrowSystem',now(),'SparrowSystem',now());");
		jdbcTemplate.execute("insert into spr_url(id,client_id,method,name,permission,uri,created_by,created_date,modified_by,modified_date) values('"+urlList.get(80)+"','sparrow','GET','userModelAttributePermissions','AUTHENTICATED','/userModelAttributePermissions/**','SparrowSystem',now(),'SparrowSystem',now());");
		jdbcTemplate.execute("insert into spr_url(id,client_id,method,name,permission,uri,created_by,created_date,modified_by,modified_date) values('"+urlList.get(81)+"','sparrow','POST','userModelAttributePermissions','RESTRICT','/userModelAttributePermissions/**','SparrowSystem',now(),'SparrowSystem',now());");
		jdbcTemplate.execute("insert into spr_url(id,client_id,method,name,permission,uri,created_by,created_date,modified_by,modified_date) values('"+urlList.get(82)+"','sparrow','PUT','userModelAttributePermissions','DENY','/userModelAttributePermissions/**','SparrowSystem',now(),'SparrowSystem',now());");
		jdbcTemplate.execute("insert into spr_url(id,client_id,method,name,permission,uri,created_by,created_date,modified_by,modified_date) values('"+urlList.get(83)+"','sparrow','PATCH','userModelAttributePermissions','RESTRICT','/userModelAttributePermissions/**','SparrowSystem',now(),'SparrowSystem',now());");
		jdbcTemplate.execute("insert into spr_url(id,client_id,method,name,permission,uri,created_by,created_date,modified_by,modified_date) values('"+urlList.get(84)+"','sparrow','DELETE','userModelAttributePermissions','RESTRICT','/userModelAttributePermissions/**','SparrowSystem',now(),'SparrowSystem',now());");
		jdbcTemplate.execute("insert into spr_url(id,client_id,method,name,permission,uri,created_by,created_date,modified_by,modified_date) values('"+urlList.get(85)+"','sparrow','GET','groups','AUTHENTICATED','/groups/**','SparrowSystem',now(),'SparrowSystem',now());");
		jdbcTemplate.execute("insert into spr_url(id,client_id,method,name,permission,uri,created_by,created_date,modified_by,modified_date) values('"+urlList.get(86)+"','sparrow','POST','groups','RESTRICT','/groups/**','SparrowSystem',now(),'SparrowSystem',now());");
		jdbcTemplate.execute("insert into spr_url(id,client_id,method,name,permission,uri,created_by,created_date,modified_by,modified_date) values('"+urlList.get(87)+"','sparrow','PUT','groups','DENY','/groups/**','SparrowSystem',now(),'SparrowSystem',now());");
		jdbcTemplate.execute("insert into spr_url(id,client_id,method,name,permission,uri,created_by,created_date,modified_by,modified_date) values('"+urlList.get(88)+"','sparrow','PATCH','groups','RESTRICT','/groups/**','SparrowSystem',now(),'SparrowSystem',now());");
		jdbcTemplate.execute("insert into spr_url(id,client_id,method,name,permission,uri,created_by,created_date,modified_by,modified_date) values('"+urlList.get(89)+"','sparrow','DELETE','groups','RESTRICT','/groups/**','SparrowSystem',now(),'SparrowSystem',now());");
		jdbcTemplate.execute("insert into spr_url(id,client_id,method,name,permission,uri,created_by,created_date,modified_by,modified_date) values('"+urlList.get(90)+"','sparrow','GET','userMenus','AUTHENTICATED','/userMenus/**','SparrowSystem',now(),'SparrowSystem',now());");
		jdbcTemplate.execute("insert into spr_url(id,client_id,method,name,permission,uri,created_by,created_date,modified_by,modified_date) values('"+urlList.get(91)+"','sparrow','POST','userMenus','RESTRICT','/userMenus/**','SparrowSystem',now(),'SparrowSystem',now());");
		jdbcTemplate.execute("insert into spr_url(id,client_id,method,name,permission,uri,created_by,created_date,modified_by,modified_date) values('"+urlList.get(92)+"','sparrow','PUT','userMenus','DENY','/userMenus/**','SparrowSystem',now(),'SparrowSystem',now());");
		jdbcTemplate.execute("insert into spr_url(id,client_id,method,name,permission,uri,created_by,created_date,modified_by,modified_date) values('"+urlList.get(93)+"','sparrow','PATCH','userMenus','RESTRICT','/userMenus/**','SparrowSystem',now(),'SparrowSystem',now());");
		jdbcTemplate.execute("insert into spr_url(id,client_id,method,name,permission,uri,created_by,created_date,modified_by,modified_date) values('"+urlList.get(94)+"','sparrow','DELETE','userMenus','RESTRICT','/userMenus/**','SparrowSystem',now(),'SparrowSystem',now());");
		jdbcTemplate.execute("insert into spr_url(id,client_id,method,name,permission,uri,created_by,created_date,modified_by,modified_date) values('"+urlList.get(95)+"','sparrow','GET','organizationRelations','AUTHENTICATED','/organizationRelations/**','SparrowSystem',now(),'SparrowSystem',now());");
		jdbcTemplate.execute("insert into spr_url(id,client_id,method,name,permission,uri,created_by,created_date,modified_by,modified_date) values('"+urlList.get(96)+"','sparrow','POST','organizationRelations','RESTRICT','/organizationRelations/**','SparrowSystem',now(),'SparrowSystem',now());");
		jdbcTemplate.execute("insert into spr_url(id,client_id,method,name,permission,uri,created_by,created_date,modified_by,modified_date) values('"+urlList.get(97)+"','sparrow','PUT','organizationRelations','DENY','/organizationRelations/**','SparrowSystem',now(),'SparrowSystem',now());");
		jdbcTemplate.execute("insert into spr_url(id,client_id,method,name,permission,uri,created_by,created_date,modified_by,modified_date) values('"+urlList.get(98)+"','sparrow','PATCH','organizationRelations','RESTRICT','/organizationRelations/**','SparrowSystem',now(),'SparrowSystem',now());");
		jdbcTemplate.execute("insert into spr_url(id,client_id,method,name,permission,uri,created_by,created_date,modified_by,modified_date) values('"+urlList.get(99)+"','sparrow','DELETE','organizationRelations','RESTRICT','/organizationRelations/**','SparrowSystem',now(),'SparrowSystem',now());");
		jdbcTemplate.execute("insert into spr_url(id,client_id,method,name,permission,uri,created_by,created_date,modified_by,modified_date) values('"+urlList.get(100)+"','sparrow','GET','employees','AUTHENTICATED','/employees/**','SparrowSystem',now(),'SparrowSystem',now());");
		jdbcTemplate.execute("insert into spr_url(id,client_id,method,name,permission,uri,created_by,created_date,modified_by,modified_date) values('"+urlList.get(101)+"','sparrow','POST','employees','RESTRICT','/employees/**','SparrowSystem',now(),'SparrowSystem',now());");
		jdbcTemplate.execute("insert into spr_url(id,client_id,method,name,permission,uri,created_by,created_date,modified_by,modified_date) values('"+urlList.get(102)+"','sparrow','PUT','employees','DENY','/employees/**','SparrowSystem',now(),'SparrowSystem',now());");
		jdbcTemplate.execute("insert into spr_url(id,client_id,method,name,permission,uri,created_by,created_date,modified_by,modified_date) values('"+urlList.get(103)+"','sparrow','PATCH','employees','RESTRICT','/employees/**','SparrowSystem',now(),'SparrowSystem',now());");
		jdbcTemplate.execute("insert into spr_url(id,client_id,method,name,permission,uri,created_by,created_date,modified_by,modified_date) values('"+urlList.get(104)+"','sparrow','DELETE','employees','RESTRICT','/employees/**','SparrowSystem',now(),'SparrowSystem',now());");
		jdbcTemplate.execute("insert into spr_url(id,client_id,method,name,permission,uri,created_by,created_date,modified_by,modified_date) values('"+urlList.get(105)+"','sparrow','GET','sprFiles','AUTHENTICATED','/sprFiles/**','SparrowSystem',now(),'SparrowSystem',now());");
		jdbcTemplate.execute("insert into spr_url(id,client_id,method,name,permission,uri,created_by,created_date,modified_by,modified_date) values('"+urlList.get(106)+"','sparrow','POST','sprFiles','RESTRICT','/sprFiles/**','SparrowSystem',now(),'SparrowSystem',now());");
		jdbcTemplate.execute("insert into spr_url(id,client_id,method,name,permission,uri,created_by,created_date,modified_by,modified_date) values('"+urlList.get(107)+"','sparrow','PUT','sprFiles','DENY','/sprFiles/**','SparrowSystem',now(),'SparrowSystem',now());");
		jdbcTemplate.execute("insert into spr_url(id,client_id,method,name,permission,uri,created_by,created_date,modified_by,modified_date) values('"+urlList.get(108)+"','sparrow','PATCH','sprFiles','RESTRICT','/sprFiles/**','SparrowSystem',now(),'SparrowSystem',now());");
		jdbcTemplate.execute("insert into spr_url(id,client_id,method,name,permission,uri,created_by,created_date,modified_by,modified_date) values('"+urlList.get(109)+"','sparrow','DELETE','sprFiles','RESTRICT','/sprFiles/**','SparrowSystem',now(),'SparrowSystem',now());");
		jdbcTemplate.execute("insert into spr_url(id,client_id,method,name,permission,uri,created_by,created_date,modified_by,modified_date) values('"+urlList.get(110)+"','sparrow','GET','userSysroles','AUTHENTICATED','/userSysroles/**','SparrowSystem',now(),'SparrowSystem',now());");
		jdbcTemplate.execute("insert into spr_url(id,client_id,method,name,permission,uri,created_by,created_date,modified_by,modified_date) values('"+urlList.get(111)+"','sparrow','POST','userSysroles','RESTRICT','/userSysroles/**','SparrowSystem',now(),'SparrowSystem',now());");
		jdbcTemplate.execute("insert into spr_url(id,client_id,method,name,permission,uri,created_by,created_date,modified_by,modified_date) values('"+urlList.get(112)+"','sparrow','PUT','userSysroles','DENY','/userSysroles/**','SparrowSystem',now(),'SparrowSystem',now());");
		jdbcTemplate.execute("insert into spr_url(id,client_id,method,name,permission,uri,created_by,created_date,modified_by,modified_date) values('"+urlList.get(113)+"','sparrow','PATCH','userSysroles','RESTRICT','/userSysroles/**','SparrowSystem',now(),'SparrowSystem',now());");
		jdbcTemplate.execute("insert into spr_url(id,client_id,method,name,permission,uri,created_by,created_date,modified_by,modified_date) values('"+urlList.get(114)+"','sparrow','DELETE','userSysroles','RESTRICT','/userSysroles/**','SparrowSystem',now(),'SparrowSystem',now());");
		jdbcTemplate.execute("insert into spr_url(id,client_id,method,name,permission,uri,created_by,created_date,modified_by,modified_date) values('"+urlList.get(115)+"','sparrow','GET','organizationModelAttributePermissions','AUTHENTICATED','/organizationModelAttributePermissions/**','SparrowSystem',now(),'SparrowSystem',now());");
		jdbcTemplate.execute("insert into spr_url(id,client_id,method,name,permission,uri,created_by,created_date,modified_by,modified_date) values('"+urlList.get(116)+"','sparrow','POST','organizationModelAttributePermissions','RESTRICT','/organizationModelAttributePermissions/**','SparrowSystem',now(),'SparrowSystem',now());");
		jdbcTemplate.execute("insert into spr_url(id,client_id,method,name,permission,uri,created_by,created_date,modified_by,modified_date) values('"+urlList.get(117)+"','sparrow','PUT','organizationModelAttributePermissions','DENY','/organizationModelAttributePermissions/**','SparrowSystem',now(),'SparrowSystem',now());");
		jdbcTemplate.execute("insert into spr_url(id,client_id,method,name,permission,uri,created_by,created_date,modified_by,modified_date) values('"+urlList.get(118)+"','sparrow','PATCH','organizationModelAttributePermissions','RESTRICT','/organizationModelAttributePermissions/**','SparrowSystem',now(),'SparrowSystem',now());");
		jdbcTemplate.execute("insert into spr_url(id,client_id,method,name,permission,uri,created_by,created_date,modified_by,modified_date) values('"+urlList.get(119)+"','sparrow','DELETE','organizationModelAttributePermissions','RESTRICT','/organizationModelAttributePermissions/**','SparrowSystem',now(),'SparrowSystem',now());");
		jdbcTemplate.execute("insert into spr_url(id,client_id,method,name,permission,uri,created_by,created_date,modified_by,modified_date) values('"+urlList.get(120)+"','sparrow','GET','sysroleDataFieldPermissions','AUTHENTICATED','/sysroleDataFieldPermissions/**','SparrowSystem',now(),'SparrowSystem',now());");
		jdbcTemplate.execute("insert into spr_url(id,client_id,method,name,permission,uri,created_by,created_date,modified_by,modified_date) values('"+urlList.get(121)+"','sparrow','POST','sysroleDataFieldPermissions','RESTRICT','/sysroleDataFieldPermissions/**','SparrowSystem',now(),'SparrowSystem',now());");
		jdbcTemplate.execute("insert into spr_url(id,client_id,method,name,permission,uri,created_by,created_date,modified_by,modified_date) values('"+urlList.get(122)+"','sparrow','PUT','sysroleDataFieldPermissions','DENY','/sysroleDataFieldPermissions/**','SparrowSystem',now(),'SparrowSystem',now());");
		jdbcTemplate.execute("insert into spr_url(id,client_id,method,name,permission,uri,created_by,created_date,modified_by,modified_date) values('"+urlList.get(123)+"','sparrow','PATCH','sysroleDataFieldPermissions','RESTRICT','/sysroleDataFieldPermissions/**','SparrowSystem',now(),'SparrowSystem',now());");
		jdbcTemplate.execute("insert into spr_url(id,client_id,method,name,permission,uri,created_by,created_date,modified_by,modified_date) values('"+urlList.get(124)+"','sparrow','DELETE','sysroleDataFieldPermissions','RESTRICT','/sysroleDataFieldPermissions/**','SparrowSystem',now(),'SparrowSystem',now());");
		jdbcTemplate.execute("insert into spr_url(id,client_id,method,name,permission,uri,created_by,created_date,modified_by,modified_date) values('"+urlList.get(125)+"','sparrow','GET','sysroleDataPermissions','AUTHENTICATED','/sysroleDataPermissions/**','SparrowSystem',now(),'SparrowSystem',now());");
		jdbcTemplate.execute("insert into spr_url(id,client_id,method,name,permission,uri,created_by,created_date,modified_by,modified_date) values('"+urlList.get(126)+"','sparrow','POST','新增角色数据权限','RESTRICT','/sysroles/*/addDataPermission','SparrowSystem',now(),'SparrowSystem',now());");
		jdbcTemplate.execute("insert into spr_url(id,client_id,method,name,permission,uri,created_by,created_date,modified_by,modified_date) values('"+urlList.get(127)+"','sparrow','DELETE','移除角色数据权限','RESTRICT','/sysroles/*/delDataPermission','SparrowSystem',now(),'SparrowSystem',now());");
		jdbcTemplate.execute("insert into spr_url(id,client_id,method,name,permission,uri,created_by,created_date,modified_by,modified_date) values('"+urlList.get(128)+"','sparrow','GET','operationLogs','AUTHENTICATED','/operationLogs/**','SparrowSystem',now(),'SparrowSystem',now());");
		jdbcTemplate.execute("insert into spr_url(id,client_id,method,name,permission,uri,created_by,created_date,modified_by,modified_date) values('"+urlList.get(129)+"','sparrow','POST','operationLogs','RESTRICT','/operationLogs/**','SparrowSystem',now(),'SparrowSystem',now());");
		jdbcTemplate.execute("insert into spr_url(id,client_id,method,name,permission,uri,created_by,created_date,modified_by,modified_date) values('"+urlList.get(130)+"','sparrow','PUT','operationLogs','DENY','/operationLogs/**','SparrowSystem',now(),'SparrowSystem',now());");
		jdbcTemplate.execute("insert into spr_url(id,client_id,method,name,permission,uri,created_by,created_date,modified_by,modified_date) values('"+urlList.get(131)+"','sparrow','PATCH','operationLogs','RESTRICT','/operationLogs/**','SparrowSystem',now(),'SparrowSystem',now());");
		jdbcTemplate.execute("insert into spr_url(id,client_id,method,name,permission,uri,created_by,created_date,modified_by,modified_date) values('"+urlList.get(132)+"','sparrow','DELETE','operationLogs','RESTRICT','/operationLogs/**','SparrowSystem',now(),'SparrowSystem',now());");
		jdbcTemplate.execute("insert into spr_url(id,client_id,method,name,permission,uri,created_by,created_date,modified_by,modified_date) values('"+urlList.get(133)+"','sparrow','GET','organizationDataFieldPermissions','AUTHENTICATED','/organizationDataFieldPermissions/**','SparrowSystem',now(),'SparrowSystem',now());");
		jdbcTemplate.execute("insert into spr_url(id,client_id,method,name,permission,uri,created_by,created_date,modified_by,modified_date) values('"+urlList.get(134)+"','sparrow','POST','organizationDataFieldPermissions','RESTRICT','/organizationDataFieldPermissions/**','SparrowSystem',now(),'SparrowSystem',now());");
		jdbcTemplate.execute("insert into spr_url(id,client_id,method,name,permission,uri,created_by,created_date,modified_by,modified_date) values('"+urlList.get(135)+"','sparrow','PUT','organizationDataFieldPermissions','DENY','/organizationDataFieldPermissions/**','SparrowSystem',now(),'SparrowSystem',now());");
		jdbcTemplate.execute("insert into spr_url(id,client_id,method,name,permission,uri,created_by,created_date,modified_by,modified_date) values('"+urlList.get(136)+"','sparrow','PATCH','organizationDataFieldPermissions','RESTRICT','/organizationDataFieldPermissions/**','SparrowSystem',now(),'SparrowSystem',now());");
		jdbcTemplate.execute("insert into spr_url(id,client_id,method,name,permission,uri,created_by,created_date,modified_by,modified_date) values('"+urlList.get(137)+"','sparrow','DELETE','organizationDataFieldPermissions','RESTRICT','/organizationDataFieldPermissions/**','SparrowSystem',now(),'SparrowSystem',now());");
		jdbcTemplate.execute("insert into spr_url(id,client_id,method,name,permission,uri,created_by,created_date,modified_by,modified_date) values('"+urlList.get(138)+"','sparrow','GET','groupModelAttributePermissions','AUTHENTICATED','/groupModelAttributePermissions/**','SparrowSystem',now(),'SparrowSystem',now());");
		jdbcTemplate.execute("insert into spr_url(id,client_id,method,name,permission,uri,created_by,created_date,modified_by,modified_date) values('"+urlList.get(139)+"','sparrow','POST','groupModelAttributePermissions','RESTRICT','/groupModelAttributePermissions/**','SparrowSystem',now(),'SparrowSystem',now());");
		jdbcTemplate.execute("insert into spr_url(id,client_id,method,name,permission,uri,created_by,created_date,modified_by,modified_date) values('"+urlList.get(140)+"','sparrow','PUT','groupModelAttributePermissions','DENY','/groupModelAttributePermissions/**','SparrowSystem',now(),'SparrowSystem',now());");
		jdbcTemplate.execute("insert into spr_url(id,client_id,method,name,permission,uri,created_by,created_date,modified_by,modified_date) values('"+urlList.get(141)+"','sparrow','PATCH','groupModelAttributePermissions','RESTRICT','/groupModelAttributePermissions/**','SparrowSystem',now(),'SparrowSystem',now());");
		jdbcTemplate.execute("insert into spr_url(id,client_id,method,name,permission,uri,created_by,created_date,modified_by,modified_date) values('"+urlList.get(142)+"','sparrow','DELETE','groupModelAttributePermissions','RESTRICT','/groupModelAttributePermissions/**','SparrowSystem',now(),'SparrowSystem',now());");
		jdbcTemplate.execute("insert into spr_url(id,client_id,method,name,permission,uri,created_by,created_date,modified_by,modified_date) values('"+urlList.get(143)+"','sparrow','GET','sysroleModelAttributePermissions','AUTHENTICATED','/sysroleModelAttributePermissions/**','SparrowSystem',now(),'SparrowSystem',now());");
		jdbcTemplate.execute("insert into spr_url(id,client_id,method,name,permission,uri,created_by,created_date,modified_by,modified_date) values('"+urlList.get(144)+"','sparrow','POST','sysroleModelAttributePermissions','RESTRICT','/sysroleModelAttributePermissions/**','SparrowSystem',now(),'SparrowSystem',now());");
		jdbcTemplate.execute("insert into spr_url(id,client_id,method,name,permission,uri,created_by,created_date,modified_by,modified_date) values('"+urlList.get(145)+"','sparrow','PUT','sysroleModelAttributePermissions','DENY','/sysroleModelAttributePermissions/**','SparrowSystem',now(),'SparrowSystem',now());");
		jdbcTemplate.execute("insert into spr_url(id,client_id,method,name,permission,uri,created_by,created_date,modified_by,modified_date) values('"+urlList.get(146)+"','sparrow','PATCH','sysroleModelAttributePermissions','RESTRICT','/sysroleModelAttributePermissions/**','SparrowSystem',now(),'SparrowSystem',now());");
		jdbcTemplate.execute("insert into spr_url(id,client_id,method,name,permission,uri,created_by,created_date,modified_by,modified_date) values('"+urlList.get(147)+"','sparrow','DELETE','sysroleModelAttributePermissions','RESTRICT','/sysroleModelAttributePermissions/**','SparrowSystem',now(),'SparrowSystem',now());");
		jdbcTemplate.execute("insert into spr_url(id,client_id,method,name,permission,uri,created_by,created_date,modified_by,modified_date) values('"+urlList.get(148)+"','sparrow','GET','menus','AUTHENTICATED','/menus/**','SparrowSystem',now(),'SparrowSystem',now());");
		jdbcTemplate.execute("insert into spr_url(id,client_id,method,name,permission,uri,created_by,created_date,modified_by,modified_date) values('"+urlList.get(149)+"','sparrow','POST','menus','RESTRICT','/menus/**','SparrowSystem',now(),'SparrowSystem',now());");
		jdbcTemplate.execute("insert into spr_url(id,client_id,method,name,permission,uri,created_by,created_date,modified_by,modified_date) values('"+urlList.get(150)+"','sparrow','PUT','menus','DENY','/menus/**','SparrowSystem',now(),'SparrowSystem',now());");
		jdbcTemplate.execute("insert into spr_url(id,client_id,method,name,permission,uri,created_by,created_date,modified_by,modified_date) values('"+urlList.get(151)+"','sparrow','PATCH','menus','RESTRICT','/menus/**','SparrowSystem',now(),'SparrowSystem',now());");
		jdbcTemplate.execute("insert into spr_url(id,client_id,method,name,permission,uri,created_by,created_date,modified_by,modified_date) values('"+urlList.get(152)+"','sparrow','DELETE','menus','RESTRICT','/menus/**','SparrowSystem',now(),'SparrowSystem',now());");
		jdbcTemplate.execute("insert into spr_url(id,client_id,method,name,permission,uri,created_by,created_date,modified_by,modified_date) values('"+urlList.get(153)+"','sparrow','GET','levels','AUTHENTICATED','/levels/**','SparrowSystem',now(),'SparrowSystem',now());");
		jdbcTemplate.execute("insert into spr_url(id,client_id,method,name,permission,uri,created_by,created_date,modified_by,modified_date) values('"+urlList.get(154)+"','sparrow','POST','levels','RESTRICT','/levels/**','SparrowSystem',now(),'SparrowSystem',now());");
		jdbcTemplate.execute("insert into spr_url(id,client_id,method,name,permission,uri,created_by,created_date,modified_by,modified_date) values('"+urlList.get(155)+"','sparrow','PUT','levels','DENY','/levels/**','SparrowSystem',now(),'SparrowSystem',now());");
		jdbcTemplate.execute("insert into spr_url(id,client_id,method,name,permission,uri,created_by,created_date,modified_by,modified_date) values('"+urlList.get(156)+"','sparrow','PATCH','levels','RESTRICT','/levels/**','SparrowSystem',now(),'SparrowSystem',now());");
		jdbcTemplate.execute("insert into spr_url(id,client_id,method,name,permission,uri,created_by,created_date,modified_by,modified_date) values('"+urlList.get(157)+"','sparrow','DELETE','levels','RESTRICT','/levels/**','SparrowSystem',now(),'SparrowSystem',now());");
		jdbcTemplate.execute("insert into spr_url(id,client_id,method,name,permission,uri,created_by,created_date,modified_by,modified_date) values('"+urlList.get(158)+"','sparrow','GET','organizationRelationTypes','AUTHENTICATED','/organizationRelationTypes/**','SparrowSystem',now(),'SparrowSystem',now());");
		jdbcTemplate.execute("insert into spr_url(id,client_id,method,name,permission,uri,created_by,created_date,modified_by,modified_date) values('"+urlList.get(159)+"','sparrow','POST','organizationRelationTypes','RESTRICT','/organizationRelationTypes/**','SparrowSystem',now(),'SparrowSystem',now());");
		jdbcTemplate.execute("insert into spr_url(id,client_id,method,name,permission,uri,created_by,created_date,modified_by,modified_date) values('"+urlList.get(160)+"','sparrow','PUT','organizationRelationTypes','DENY','/organizationRelationTypes/**','SparrowSystem',now(),'SparrowSystem',now());");
		jdbcTemplate.execute("insert into spr_url(id,client_id,method,name,permission,uri,created_by,created_date,modified_by,modified_date) values('"+urlList.get(161)+"','sparrow','PATCH','organizationRelationTypes','RESTRICT','/organizationRelationTypes/**','SparrowSystem',now(),'SparrowSystem',now());");
		jdbcTemplate.execute("insert into spr_url(id,client_id,method,name,permission,uri,created_by,created_date,modified_by,modified_date) values('"+urlList.get(162)+"','sparrow','DELETE','organizationRelationTypes','RESTRICT','/organizationRelationTypes/**','SparrowSystem',now(),'SparrowSystem',now());");
		jdbcTemplate.execute("insert into spr_url(id,client_id,method,name,permission,uri,created_by,created_date,modified_by,modified_date) values('"+urlList.get(163)+"','sparrow','GET','flowNoes','AUTHENTICATED','/flowNoes/**','SparrowSystem',now(),'SparrowSystem',now());");
		jdbcTemplate.execute("insert into spr_url(id,client_id,method,name,permission,uri,created_by,created_date,modified_by,modified_date) values('"+urlList.get(164)+"','sparrow','POST','flowNoes','RESTRICT','/flowNoes/**','SparrowSystem',now(),'SparrowSystem',now());");
		jdbcTemplate.execute("insert into spr_url(id,client_id,method,name,permission,uri,created_by,created_date,modified_by,modified_date) values('"+urlList.get(165)+"','sparrow','PUT','flowNoes','DENY','/flowNoes/**','SparrowSystem',now(),'SparrowSystem',now());");
		jdbcTemplate.execute("insert into spr_url(id,client_id,method,name,permission,uri,created_by,created_date,modified_by,modified_date) values('"+urlList.get(166)+"','sparrow','PATCH','flowNoes','RESTRICT','/flowNoes/**','SparrowSystem',now(),'SparrowSystem',now());");
		jdbcTemplate.execute("insert into spr_url(id,client_id,method,name,permission,uri,created_by,created_date,modified_by,modified_date) values('"+urlList.get(167)+"','sparrow','DELETE','flowNoes','RESTRICT','/flowNoes/**','SparrowSystem',now(),'SparrowSystem',now());");
		jdbcTemplate.execute("insert into spr_url(id,client_id,method,name,permission,uri,created_by,created_date,modified_by,modified_date) values('"+urlList.get(168)+"','sparrow','GET','groupUsers','AUTHENTICATED','/groupUsers/**','SparrowSystem',now(),'SparrowSystem',now());");
		jdbcTemplate.execute("insert into spr_url(id,client_id,method,name,permission,uri,created_by,created_date,modified_by,modified_date) values('"+urlList.get(169)+"','sparrow','POST','groupUsers','RESTRICT','/groupUsers/**','SparrowSystem',now(),'SparrowSystem',now());");
		jdbcTemplate.execute("insert into spr_url(id,client_id,method,name,permission,uri,created_by,created_date,modified_by,modified_date) values('"+urlList.get(170)+"','sparrow','PUT','groupUsers','DENY','/groupUsers/**','SparrowSystem',now(),'SparrowSystem',now());");
		jdbcTemplate.execute("insert into spr_url(id,client_id,method,name,permission,uri,created_by,created_date,modified_by,modified_date) values('"+urlList.get(171)+"','sparrow','PATCH','groupUsers','RESTRICT','/groupUsers/**','SparrowSystem',now(),'SparrowSystem',now());");
		jdbcTemplate.execute("insert into spr_url(id,client_id,method,name,permission,uri,created_by,created_date,modified_by,modified_date) values('"+urlList.get(172)+"','sparrow','DELETE','groupUsers','RESTRICT','/groupUsers/**','SparrowSystem',now(),'SparrowSystem',now());");
		jdbcTemplate.execute("insert into spr_url(id,client_id,method,name,permission,uri,created_by,created_date,modified_by,modified_date) values('"+urlList.get(173)+"','sparrow','GET','loginLogs','AUTHENTICATED','/loginLogs/**','SparrowSystem',now(),'SparrowSystem',now());");
		jdbcTemplate.execute("insert into spr_url(id,client_id,method,name,permission,uri,created_by,created_date,modified_by,modified_date) values('"+urlList.get(174)+"','sparrow','POST','loginLogs','RESTRICT','/loginLogs/**','SparrowSystem',now(),'SparrowSystem',now());");
		jdbcTemplate.execute("insert into spr_url(id,client_id,method,name,permission,uri,created_by,created_date,modified_by,modified_date) values('"+urlList.get(175)+"','sparrow','PUT','loginLogs','DENY','/loginLogs/**','SparrowSystem',now(),'SparrowSystem',now());");
		jdbcTemplate.execute("insert into spr_url(id,client_id,method,name,permission,uri,created_by,created_date,modified_by,modified_date) values('"+urlList.get(176)+"','sparrow','PATCH','loginLogs','RESTRICT','/loginLogs/**','SparrowSystem',now(),'SparrowSystem',now());");
		jdbcTemplate.execute("insert into spr_url(id,client_id,method,name,permission,uri,created_by,created_date,modified_by,modified_date) values('"+urlList.get(177)+"','sparrow','DELETE','loginLogs','RESTRICT','/loginLogs/**','SparrowSystem',now(),'SparrowSystem',now());");
		jdbcTemplate.execute("insert into spr_url(id,client_id,method,name,permission,uri,created_by,created_date,modified_by,modified_date) values('"+urlList.get(178)+"','sparrow','GET','sysroleFiles','AUTHENTICATED','/sysroleFiles/**','SparrowSystem',now(),'SparrowSystem',now());");
		jdbcTemplate.execute("insert into spr_url(id,client_id,method,name,permission,uri,created_by,created_date,modified_by,modified_date) values('"+urlList.get(179)+"','sparrow','POST','sysroleFiles','RESTRICT','/sysroleFiles/**','SparrowSystem',now(),'SparrowSystem',now());");
		jdbcTemplate.execute("insert into spr_url(id,client_id,method,name,permission,uri,created_by,created_date,modified_by,modified_date) values('"+urlList.get(180)+"','sparrow','PUT','sysroleFiles','DENY','/sysroleFiles/**','SparrowSystem',now(),'SparrowSystem',now());");
		jdbcTemplate.execute("insert into spr_url(id,client_id,method,name,permission,uri,created_by,created_date,modified_by,modified_date) values('"+urlList.get(181)+"','sparrow','PATCH','sysroleFiles','RESTRICT','/sysroleFiles/**','SparrowSystem',now(),'SparrowSystem',now());");
		jdbcTemplate.execute("insert into spr_url(id,client_id,method,name,permission,uri,created_by,created_date,modified_by,modified_date) values('"+urlList.get(182)+"','sparrow','DELETE','sysroleFiles','RESTRICT','/sysroleFiles/**','SparrowSystem',now(),'SparrowSystem',now());");
		jdbcTemplate.execute("insert into spr_url(id,client_id,method,name,permission,uri,created_by,created_date,modified_by,modified_date) values('"+urlList.get(183)+"','sparrow','GET','organizationModelPermissions','AUTHENTICATED','/organizationModelPermissions/**','SparrowSystem',now(),'SparrowSystem',now());");
		jdbcTemplate.execute("insert into spr_url(id,client_id,method,name,permission,uri,created_by,created_date,modified_by,modified_date) values('"+urlList.get(184)+"','sparrow','POST','organizationModelPermissions','RESTRICT','/organizationModelPermissions/**','SparrowSystem',now(),'SparrowSystem',now());");
		jdbcTemplate.execute("insert into spr_url(id,client_id,method,name,permission,uri,created_by,created_date,modified_by,modified_date) values('"+urlList.get(185)+"','sparrow','PUT','organizationModelPermissions','DENY','/organizationModelPermissions/**','SparrowSystem',now(),'SparrowSystem',now());");
		jdbcTemplate.execute("insert into spr_url(id,client_id,method,name,permission,uri,created_by,created_date,modified_by,modified_date) values('"+urlList.get(186)+"','sparrow','PATCH','organizationModelPermissions','RESTRICT','/organizationModelPermissions/**','SparrowSystem',now(),'SparrowSystem',now());");
		jdbcTemplate.execute("insert into spr_url(id,client_id,method,name,permission,uri,created_by,created_date,modified_by,modified_date) values('"+urlList.get(187)+"','sparrow','DELETE','organizationModelPermissions','RESTRICT','/organizationModelPermissions/**','SparrowSystem',now(),'SparrowSystem',now());");
		jdbcTemplate.execute("insert into spr_url(id,client_id,method,name,permission,uri,created_by,created_date,modified_by,modified_date) values('"+urlList.get(188)+"','sparrow','GET','sparrowUrls','AUTHENTICATED','/sparrowUrls/**','SparrowSystem',now(),'SparrowSystem',now());");
		jdbcTemplate.execute("insert into spr_url(id,client_id,method,name,permission,uri,created_by,created_date,modified_by,modified_date) values('"+urlList.get(189)+"','sparrow','POST','sparrowUrls','RESTRICT','/sparrowUrls/**','SparrowSystem',now(),'SparrowSystem',now());");
		jdbcTemplate.execute("insert into spr_url(id,client_id,method,name,permission,uri,created_by,created_date,modified_by,modified_date) values('"+urlList.get(190)+"','sparrow','PUT','sparrowUrls','DENY','/sparrowUrls/**','SparrowSystem',now(),'SparrowSystem',now());");
		jdbcTemplate.execute("insert into spr_url(id,client_id,method,name,permission,uri,created_by,created_date,modified_by,modified_date) values('"+urlList.get(191)+"','sparrow','PATCH','sparrowUrls','RESTRICT','/sparrowUrls/**','SparrowSystem',now(),'SparrowSystem',now());");
		jdbcTemplate.execute("insert into spr_url(id,client_id,method,name,permission,uri,created_by,created_date,modified_by,modified_date) values('"+urlList.get(192)+"','sparrow','DELETE','sparrowUrls','RESTRICT','/sparrowUrls/**','SparrowSystem',now(),'SparrowSystem',now());");
		jdbcTemplate.execute("insert into spr_url(id,client_id,method,name,permission,uri,created_by,created_date,modified_by,modified_date) values('"+urlList.get(193)+"','sparrow','GET','organizationDataPermissions','AUTHENTICATED','/organizationDataPermissions/**','SparrowSystem',now(),'SparrowSystem',now());");
		jdbcTemplate.execute("insert into spr_url(id,client_id,method,name,permission,uri,created_by,created_date,modified_by,modified_date) values('"+urlList.get(194)+"','sparrow','POST','organizationDataPermissions','RESTRICT','/organizationDataPermissions/**','SparrowSystem',now(),'SparrowSystem',now());");
		jdbcTemplate.execute("insert into spr_url(id,client_id,method,name,permission,uri,created_by,created_date,modified_by,modified_date) values('"+urlList.get(195)+"','sparrow','PUT','organizationDataPermissions','DENY','/organizationDataPermissions/**','SparrowSystem',now(),'SparrowSystem',now());");
		jdbcTemplate.execute("insert into spr_url(id,client_id,method,name,permission,uri,created_by,created_date,modified_by,modified_date) values('"+urlList.get(196)+"','sparrow','PATCH','organizationDataPermissions','RESTRICT','/organizationDataPermissions/**','SparrowSystem',now(),'SparrowSystem',now());");
		jdbcTemplate.execute("insert into spr_url(id,client_id,method,name,permission,uri,created_by,created_date,modified_by,modified_date) values('"+urlList.get(197)+"','sparrow','DELETE','organizationDataPermissions','RESTRICT','/organizationDataPermissions/**','SparrowSystem',now(),'SparrowSystem',now());");
		logger.info("Init Url finished");
	}

	public void initMenu() {
		for (int i = 0; i < 13; i++) {
			menuList.add(UUID.randomUUID().toString().replaceAll("-", ""));
		}
		jdbcTemplate.execute(
				"insert into spr_menu(id, name, parent_id, sort, url, created_date, modified_by, modified_date,created_by) values('"
						+ menuList.get(0) + "','权限管理',null,0,'/#',now(),'SparrowSystem',now(),'SparrowSystem');");
		jdbcTemplate.execute(
				"insert into spr_menu(id, name, parent_id, sort, url, created_date, modified_by, modified_date,created_by) values('"
						+ menuList.get(1) + "','角色管理','" + menuList.get(0) + "',0,'/page/sysrole',now(),'SparrowSystem',now(),'SparrowSystem');");
		jdbcTemplate.execute(
				"insert into spr_menu(id, name, parent_id, sort, url, created_date, modified_by, modified_date,created_by) values('"
						+ menuList.get(2) + "','URL资源管理','" + menuList.get(0) + "',0,'/page/url',now(),'SparrowSystem',now(),'SparrowSystem');");
		jdbcTemplate.execute(
				"insert into spr_menu(id, name, parent_id, sort, url, created_date, modified_by, modified_date,created_by) values('"
						+ menuList.get(3) + "','菜单管理','" + menuList.get(0) + "',0,'/page/menu',now(),'SparrowSystem',now(),'SparrowSystem');");
		jdbcTemplate.execute(
				"insert into spr_menu(id, name, parent_id, sort, url, created_date, modified_by, modified_date,created_by) values('"
						+ menuList.get(4) + "','模型管理','" + menuList.get(0) + "',0,'/page/model',now(),'SparrowSystem',now(),'SparrowSystem');");
		jdbcTemplate.execute(
				"insert into spr_menu(id, name, parent_id, sort, url, created_date, modified_by, modified_date,created_by) values('"
						+ menuList.get(5) + "','模型属性管理','" + menuList.get(0) + "',0,'/page/modelAttribute',now(),'SparrowSystem',now(),'SparrowSystem');");
		jdbcTemplate.execute(
				"insert into spr_menu(id, name, parent_id, sort, url, created_date, modified_by, modified_date,created_by) values('"
						+ menuList.get(6) + "','数据权限管理','" + menuList.get(0) + "',0,'/page/dataPermission',now(),'SparrowSystem',now(),'SparrowSystem');");
		jdbcTemplate.execute(
				"insert into spr_menu(id, name, parent_id, sort, url, created_date, modified_by, modified_date,created_by) values('"
						+ menuList.get(7) + "','文件权限管理','" + menuList.get(0) + "',0,'/page/dataPermission',now(),'SparrowSystem',now(),'SparrowSystem');");

		jdbcTemplate.execute(
				"insert into spr_menu(id, name, parent_id, sort, url, created_date, modified_by, modified_date,created_by) values('"
						+ menuList.get(8) + "','组织管理',null,0,'/#',now(),'SparrowSystem',now(),'SparrowSystem');");
		jdbcTemplate.execute(
				"insert into spr_menu(id, name, parent_id, sort, url, created_date, modified_by, modified_date,created_by) values('"
						+ menuList.get(9) + "','机构部门管理','" + menuList.get(8) + "',0,'/page/organization',now(),'SparrowSystem',now(),'SparrowSystem');");
		jdbcTemplate.execute(
				"insert into spr_menu(id, name, parent_id, sort, url, created_date, modified_by, modified_date,created_by) values('"
						+ menuList.get(10) + "','岗位管理','" + menuList.get(8) + "',0,'/page/role',now(),'SparrowSystem',now(),'SparrowSystem');");
		jdbcTemplate.execute(
				"insert into spr_menu(id, name, parent_id, sort, url, created_date, modified_by, modified_date,created_by) values('"
						+ menuList.get(11) + "','级别管理','" + menuList.get(8) + "',0,'/page/level',now(),'SparrowSystem',now(),'SparrowSystem');");
		jdbcTemplate.execute(
				"insert into spr_menu(id, name, parent_id, sort, url, created_date, modified_by, modified_date,created_by) values('"
						+ menuList.get(12) + "','群组管理','" + menuList.get(8)
						+ "',0,'/page/group',now(),'SparrowSystem',now(),'SparrowSystem');");

		logger.info("Init menu finished");
	}

	public void initUrlPermission() {
		for (String url : urlList) {
			jdbcTemplate.execute(
					"insert into spr_sysrole_url_permission(url_id, sysrole_id, created_date, modified_by, modified_date,created_by) values('" + url
							+ "','" + sysroleId  + "',now(),'SparrowSystem',now(),'SparrowSystem');");
		}
	}
}
