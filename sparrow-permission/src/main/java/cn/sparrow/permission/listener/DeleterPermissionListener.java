package cn.sparrow.permission.listener;

import javax.persistence.PreRemove;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.core.RepositoryConstraintViolationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import cn.sparrow.model.common.AbstractSparrowEntity;
import cn.sparrow.model.common.PermissionEnum;
import cn.sparrow.organization.service.EmployeeTokenService;
import cn.sparrow.permission.service.PermissionService;
import cn.sparrow.permission.service.PermissionTokenService;

@Component
public final class DeleterPermissionListener {

	private static PermissionService permissionService;
	private static PermissionTokenService permissionTokenService;
	private static EmployeeTokenService employeeTokenService;

	@Autowired
	public void setPermissionService(PermissionService permissionService) {
		DeleterPermissionListener.permissionService = permissionService;
	}

	@Autowired
	public void setPermissionTokenService(PermissionTokenService permissionTokenService) {
		DeleterPermissionListener.permissionTokenService = permissionTokenService;
	}

	@Autowired
	public void setEmployeeTokenService(EmployeeTokenService employeeTokenService) {
		DeleterPermissionListener.employeeTokenService = employeeTokenService;
	}

	@PreRemove
	private void beforeAnyUpdate(AbstractSparrowEntity abstractEntity) {
		String username = SecurityContextHolder.getContext().getAuthentication() == null ? ""
				: SecurityContextHolder.getContext().getAuthentication().getName();

		if (!permissionService.hasPermission(employeeTokenService.getEmployeeToken(username),
				permissionTokenService.getModelPermissionToken(abstractEntity.getClass().getName()),
				PermissionEnum.DELETER)) {
			throw new RepositoryConstraintViolationException(RepositoryErrorFactory.getErros(abstractEntity,
					"SPR_MD_C_DN-40", "模型拒绝删除权限" + abstractEntity.getClass().getName() + username));
		}

	}
}
