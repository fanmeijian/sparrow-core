package cn.sparrow.permission.validation;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import cn.sparrow.permission.model.AbstractSparrowUuidEntity;

// 这个是再repository的校验级别，不是jpa的
@Component
public class ModelAuthorPermissionValidator implements Validator {

//	@Autowired
//	IPermission<ModelPermissionPK> modelPermissionService;

	@Override
	public boolean supports(Class<?> clazz) {
		return (AbstractSparrowUuidEntity.class.isAssignableFrom(clazz));
	}

	@Override
	public void validate(Object target, Errors errors) {
		String username = SecurityContextHolder.getContext().getAuthentication().getName();
		// 检查是否有新建权限
		// 用户是否在拒绝权限列表
//		if (modelPermissionService.hasPermission(new ModelPermissionPK(target.getClass().getName(),
//				PermissionEnum.AUTHOR, PermissionTypeEnum.DENY), username))
//			errors.reject("SPR_MD_C_DN-40", "模型拒绝新建权限" + target.getClass().getName() + username);
//
//		// 1.先检查模型的作者权限
//		if (!modelPermissionService.hasPermission(new ModelPermissionPK(target.getClass().getName(),
//				PermissionEnum.AUTHOR, PermissionTypeEnum.ALLOW), username)) {
//			errors.reject("SPR_MD_C-40", "无模型新建权限" + target.getClass().getName() + username);
//		}

		// 2.检查字段的创建权限

	}

}
