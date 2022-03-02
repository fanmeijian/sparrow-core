package cn.sparrow.permission.listener;

import javax.persistence.PreUpdate;
import javax.validation.ValidationException;

import org.springframework.stereotype.Component;

import cn.sparrow.permission.constant.PermissionEnum;
import cn.sparrow.permission.model.AbstractSparrowEntity;

@Component
public final class EditorPermissionListener extends AbstractPermissionListener {

	@PreUpdate
	private void beforeAnyUpdate(AbstractSparrowEntity abstractEntity) {
		this.init();
		String username = CurrentUser.INSTANCE.get();
		if (!permissionService.hasPermission(employeeTokenService.getEmployeeToken(username),
				permissionTokenService.getModelPermissionToken(abstractEntity.getClass().getName()),
				PermissionEnum.EDITOR)) {
			throw new ValidationException(
					"SPR_MD_C_DN-40" + "模型拒绝编辑权限" + abstractEntity.getClass().getName() + username);
		}
	}
}
