package cn.sparrow.permission.mgt.common.listener;

import javax.persistence.PreUpdate;
import javax.validation.ValidationException;

import cn.sparrow.permission.constant.PermissionEnum;
import cn.sparrow.permission.model.common.AbstractSparrowEntity;

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
