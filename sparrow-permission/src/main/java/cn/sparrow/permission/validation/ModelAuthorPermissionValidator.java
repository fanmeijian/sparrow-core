package cn.sparrow.permission.validation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import cn.sparrow.model.common.AbstractSparrowEntity;
import cn.sparrow.model.common.PermissionEnum;
import cn.sparrow.model.common.PermissionTypeEnum;
import cn.sparrow.model.permission.AbstractModelPermissionPK;
import cn.sparrow.permission.service.IPermission;

// 这个是再repository的校验级别，不是jpa的
@Component
public class ModelAuthorPermissionValidator implements Validator {

	@Autowired
	IPermission<AbstractModelPermissionPK> modelPermissionService;

	@Override
	public boolean supports(Class<?> clazz) {
		return (AbstractSparrowEntity.class.isAssignableFrom(clazz));
	}

	@Override
	public void validate(Object target, Errors errors) {
		String username = SecurityContextHolder.getContext().getAuthentication().getName();
		// 检查是否有新建权限
		// 用户是否在拒绝权限列表
		if (modelPermissionService.hasPermission(new AbstractModelPermissionPK(target.getClass().getName(),
				PermissionEnum.AUTHOR, PermissionTypeEnum.DENY), username))
			errors.reject("SPR_MD_C_DN-40", "模型拒绝新建权限" + target.getClass().getName() + username);

		// 1.先检查模型的作者权限
		if (!modelPermissionService.hasPermission(new AbstractModelPermissionPK(target.getClass().getName(),
				PermissionEnum.AUTHOR, PermissionTypeEnum.ALLOW), username)) {
			errors.reject("SPR_MD_C-40", "无模型新建权限" + target.getClass().getName() + username);
		}

		// 2.检查字段的创建权限

	}

}
