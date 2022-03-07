package cn.sparrow.permission.common.listener;

import javax.persistence.EntityManager;
import javax.persistence.MappedSuperclass;

import cn.sparrow.permission.core.api.EmployeeTokenService;
import cn.sparrow.permission.core.api.PermissionService;
import cn.sparrow.permission.core.api.PermissionTokenService;
import cn.sparrow.permission.core.service.EmployeeTokenServiceImpl;
import cn.sparrow.permission.core.service.PermissionServiceImpl;
import cn.sparrow.permission.core.service.PermissionTokenServiceImpl;

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
