package cn.sparrow.permission.listener;

import javax.persistence.PrePersist;
import javax.validation.ValidationException;

import org.springframework.beans.factory.ObjectFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import cn.sparrow.permission.constant.PermissionEnum;
import cn.sparrow.permission.model.AbstractSparrowEntity;
import cn.sparrow.permission.service.EmployeeTokenService;
import cn.sparrow.permission.service.PermissionService;
import cn.sparrow.permission.service.PermissionTokenService;

@Component
public final class AuthorPermissionListener extends AbstractPermissionListener{
//
//	private PermissionService permissionService;
//	private PermissionTokenService permissionTokenService;
//	private EmployeeTokenService employeeTokenService;
//
//	@Autowired
//	private ObjectFactory<PermissionService> permissionServiceFactory;
//	@Autowired
//	private ObjectFactory<PermissionTokenService> permissionTokenServiceFactory;
//	@Autowired
//	private ObjectFactory<EmployeeTokenService> employeeTokenServiceFactory;

//	public AuthorPermissionListener(PermissionService permissionService, PermissionTokenService permissionTokenService,
//			EmployeeTokenService employeeTokenService) {
//		this.permissionService = permissionService;
//		this.permissionTokenService = permissionTokenService;
//		this.employeeTokenService = employeeTokenService;
//	}

//
//  @Autowired
//  public void setPermissionService(PermissionService permissionService) {
//    AuthorPermissionListener.permissionService = permissionService;
//  }
//
//  @Autowired
//  public void setPermissionTokenService(PermissionTokenService permissionTokenService) {
//    AuthorPermissionListener.permissionTokenService = permissionTokenService;
//  }
//
//  @Autowired
//  public void setEmployeeTokenService(EmployeeTokenService employeeTokenService) {
//    AuthorPermissionListener.employeeTokenService = employeeTokenService;
//  }

	// 新建和编辑单据权限检查
	@PrePersist
	private void beforeAnyUpdate(AbstractSparrowEntity abstractEntity) {
//		this.permissionService = permissionServiceFactory.getObject();
//		this.permissionTokenService = permissionTokenServiceFactory.getObject();
//		this.employeeTokenService = employeeTokenServiceFactory.getObject();
		this.init();
		String username = CurrentUser.INSTANCE.get();
		// 检查是否有新建权限
		// 用户是否在拒绝权限列表

		if (!permissionService.hasPermission(employeeTokenService.getEmployeeToken(username),
				permissionTokenService.getModelPermissionToken(abstractEntity.getClass().getName()),
				PermissionEnum.AUTHOR)) {
			throw new ValidationException(
					"SPR_MD_C_DN-40" + "模型拒绝新建权限" + abstractEntity.getClass().getName() + username);
		}
	}
}
