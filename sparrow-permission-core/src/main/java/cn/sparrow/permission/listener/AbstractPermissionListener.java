package cn.sparrow.permission.listener;

import javax.persistence.MappedSuperclass;

import org.springframework.beans.factory.ObjectFactory;
import org.springframework.beans.factory.annotation.Autowired;

import cn.sparrow.permission.service.EmployeeTokenService;
import cn.sparrow.permission.service.PermissionService;
import cn.sparrow.permission.service.PermissionTokenService;

@MappedSuperclass
public abstract class AbstractPermissionListener {
	protected PermissionService permissionService;
	protected PermissionTokenService permissionTokenService;
	protected EmployeeTokenService employeeTokenService;

	@Autowired
	protected ObjectFactory<PermissionService> permissionServiceFactory;
	@Autowired
	protected ObjectFactory<PermissionTokenService> permissionTokenServiceFactory;
	@Autowired
	protected ObjectFactory<EmployeeTokenService> employeeTokenServiceFactory;

	public void init() {
		this.permissionService = permissionServiceFactory.getObject();
		this.permissionTokenService = permissionTokenServiceFactory.getObject();
		this.employeeTokenService = employeeTokenServiceFactory.getObject();
	}
}
