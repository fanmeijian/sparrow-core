package cn.sparrow.permission.validation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import cn.sparrow.model.common.AbstractSparrowUuidEntity;
import cn.sparrow.model.common.PermissionEnum;
import cn.sparrow.model.common.PermissionTypeEnum;
import cn.sparrow.model.permission.AbstractDataPermissionPK;
import cn.sparrow.model.permission.ModelPermissionPK;
import cn.sparrow.permission.service.IPermission;

// 这个是再repository的校验级别，不是jpa的
@Component
public class ModelDeleterPermissionValidator implements Validator {

	@Autowired
	IPermission<ModelPermissionPK> modelPermissionService;
	@Autowired
	IPermission<AbstractDataPermissionPK> dataPermissionService;

	@Override
	public boolean supports(Class<?> clazz) {
		return (AbstractSparrowUuidEntity.class.isAssignableFrom(clazz));
	}

	@Override
	public void validate(Object target, Errors errors) {
		String username = SecurityContextHolder.getContext().getAuthentication().getName();
		AbstractSparrowUuidEntity sparrowEntity = (AbstractSparrowUuidEntity) target;
		// 检查是否有新建权限
		if (modelPermissionService.hasPermission(new ModelPermissionPK(target.getClass().getName(),
				PermissionEnum.DELETER, PermissionTypeEnum.DENY), username)) {
			errors.reject("SPR_MD_D_DN-40", "模型拒绝删除权限" + target.getClass().getName() + username);
		}

		// 1.先检查模型的作者权限
		if (!modelPermissionService.hasPermission(new ModelPermissionPK(target.getClass().getName(),
				PermissionEnum.DELETER, PermissionTypeEnum.ALLOW), username)) {
			errors.reject("SPR_MD_D-40", "无模型删除权限" + target.getClass().getName() + username);
		}

		// 2.本条数据的的编辑权限
		if (dataPermissionService.hasPermission(new AbstractDataPermissionPK(target.getClass().getName(),
				PermissionEnum.DELETER, PermissionTypeEnum.DENY, sparrowEntity.getId()), username)) {
			errors.reject("SPR_DT_D_DN-40",
					"数据拒绝删除权限" + target.getClass().getName() + ((AbstractSparrowUuidEntity) target).getId() + username);
		}

		// 2.本条数据的的编辑权限
		if (!dataPermissionService.hasPermission(new AbstractDataPermissionPK(target.getClass().getName(),
				PermissionEnum.DELETER, PermissionTypeEnum.ALLOW, sparrowEntity.getId()), username)) {
			errors.reject("SPR_DT_D-40",
					"无本条数据删除权限" + target.getClass().getName() + ((AbstractSparrowUuidEntity) target).getId() + username);
		}

		// 触发验证失败
		if (sparrowEntity.getErrorMessage().size() > 0) {
			errors.reject("0101", sparrowEntity.getErrorMessage().toString());
		}

		// 2.检查字段的创建权限

	}

}
