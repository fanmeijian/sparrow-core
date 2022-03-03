package cn.sparrow.permission.listener;

import javax.persistence.EntityManager;
import javax.persistence.MappedSuperclass;

import cn.sparrow.permission.service.EmployeeTokenService;
import cn.sparrow.permission.service.EmployeeTokenServiceImpl;
import cn.sparrow.permission.service.PermissionService;
import cn.sparrow.permission.service.PermissionServiceImpl;
import cn.sparrow.permission.service.PermissionTokenService;
import cn.sparrow.permission.service.PermissionTokenServiceImpl;

@MappedSuperclass
public abstract class AbstractPermissionListener {
	protected PermissionService permissionService;
	protected PermissionTokenService permissionTokenService;
	protected EmployeeTokenService employeeTokenService;
	protected EntityManager entityManager;
//	@Autowired
//	protected ObjectFactory<PermissionService> permissionServiceFactory;
//	@Autowired
//	protected ObjectFactory<PermissionTokenService> permissionTokenServiceFactory;
//	@Autowired
//	protected ObjectFactory<EmployeeTokenService> employeeTokenServiceFactory;

	public void init() {
//		this.permissionService = permissionServiceFactory.getObject();
//		this.permissionTokenService = permissionTokenServiceFactory.getObject();
//		this.employeeTokenService = employeeTokenServiceFactory.getObject();

		this.permissionService = new PermissionServiceImpl();
		this.permissionTokenService = new PermissionTokenServiceImpl(entityManager);
		this.employeeTokenService = new EmployeeTokenServiceImpl(entityManager);
	}
}
