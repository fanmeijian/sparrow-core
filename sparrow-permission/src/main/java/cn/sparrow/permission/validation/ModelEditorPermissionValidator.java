package cn.sparrow.permission.validation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import cn.sparrow.model.common.AbstractSparrowEntity;
import cn.sparrow.model.common.PermissionEnum;
import cn.sparrow.model.common.PermissionTypeEnum;
import cn.sparrow.model.permission.AbstractDataPermissionPK;
import cn.sparrow.model.permission.AbstractModelPermissionPK;
import cn.sparrow.permission.service.IPermission;

// 这个是再repository的校验级别，不是jpa的
@Component
public class ModelEditorPermissionValidator implements Validator {

	@Autowired
	IPermission<AbstractModelPermissionPK> modelPermissionService;
	@Autowired
	IPermission<AbstractDataPermissionPK> dataPermissionService;

	@Override
	public boolean supports(Class<?> clazz) {
		return (AbstractSparrowEntity.class.isAssignableFrom(clazz));
	}

	@Override
	public void validate(Object target, Errors errors) {
		String username = SecurityContextHolder.getContext().getAuthentication().getName();
		AbstractSparrowEntity sparrowEntity = (AbstractSparrowEntity) target;
		// 检查是否有新建权限

		if (modelPermissionService.hasPermission(new AbstractModelPermissionPK(target.getClass().getName(),
				PermissionEnum.EDITOR, PermissionTypeEnum.DENY), username)) {
			errors.reject("SPR_MD_U_DN-40", "模型拒绝更新权限" + target.getClass().getName() + username);
		}

		// 1.先检查模型的编辑者权限
		if (!modelPermissionService.hasPermission(new AbstractModelPermissionPK(target.getClass().getName(),
				PermissionEnum.EDITOR, PermissionTypeEnum.ALLOW), username)) {
			errors.reject("SPR_MD_U-40", "无此模型更新权限" + target.getClass().getName() + username);
		}

		// 2.本条数据的的编辑权限
		if (dataPermissionService.hasPermission(new AbstractDataPermissionPK(target.getClass().getName(),
				PermissionEnum.EDITOR, PermissionTypeEnum.DENY, sparrowEntity.getId()), username)) {
			errors.reject("SPR_DT_U_DN-40",
					"本条数据拒绝更新权限" + target.getClass().getName() + ((AbstractSparrowEntity) target).getId() + username);
		}

		// 2.本条数据的的编辑权限
		if (!dataPermissionService.hasPermission(new AbstractDataPermissionPK(target.getClass().getName(),
				PermissionEnum.EDITOR, PermissionTypeEnum.ALLOW, sparrowEntity.getId()), username)) {
			errors.reject("SPR_DT_U-40",
					"无本条数据更新权限" + target.getClass().getName() + ((AbstractSparrowEntity) target).getId() + username);
		}

		if (sparrowEntity.getErrorMessage().size() > 0) {
			errors.reject("0101", sparrowEntity.getErrorMessage().toString());
		}

	}

}
