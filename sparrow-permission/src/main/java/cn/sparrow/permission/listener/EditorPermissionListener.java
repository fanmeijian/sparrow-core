package cn.sparrow.permission.listener;

import javax.persistence.PreUpdate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.core.RepositoryConstraintViolationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import cn.sparrow.permission.constant.PermissionEnum;
import cn.sparrow.permission.model.AbstractSparrowEntity;
import cn.sparrow.permission.service.PermissionService;
import cn.sparrow.permission.service.PermissionTokenService;
import cn.sparrow.permission.service.organization.EmployeeTokenService;

@Component
public final class EditorPermissionListener {
	private static PermissionService permissionService;
	private static PermissionTokenService permissionTokenService;
	private static EmployeeTokenService employeeTokenService;

	@Autowired
	public void setPermissionService(PermissionService permissionService) {
		EditorPermissionListener.permissionService = permissionService;
	}

	@Autowired
	public void setPermissionTokenService(PermissionTokenService permissionTokenService) {
		EditorPermissionListener.permissionTokenService = permissionTokenService;
	}

	@Autowired
	public void setEmployeeTokenService(EmployeeTokenService employeeTokenService) {
		EditorPermissionListener.employeeTokenService = employeeTokenService;
	}

	@PreUpdate
	private void beforeAnyUpdate(AbstractSparrowEntity abstractEntity) {
		String username = SecurityContextHolder.getContext().getAuthentication() == null ? ""
				: SecurityContextHolder.getContext().getAuthentication().getName();
		if (!permissionService.hasPermission(employeeTokenService.getEmployeeToken(username),
				permissionTokenService.getModelPermissionToken(abstractEntity.getClass().getName()),
				PermissionEnum.EDITOR)) {
			throw new RepositoryConstraintViolationException(RepositoryErrorFactory.getErros(abstractEntity,
					"SPR_MD_C_DN-40", "模型拒绝编辑权限" + abstractEntity.getClass().getName() + username));
		}
	}
}
