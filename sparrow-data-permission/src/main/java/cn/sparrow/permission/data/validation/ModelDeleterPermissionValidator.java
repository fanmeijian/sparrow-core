package cn.sparrow.permission.data.validation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import cn.sparrow.permission.data.model.AbstractDataPermissionPK;
import cn.sparrow.permission.data.model.AbstractModelPermissionPK;
import cn.sparrow.permission.data.model.AbstractSparrowEntity;
import cn.sparrow.permission.data.repository.PermissionEnum;
import cn.sparrow.permission.data.repository.PermissionTypeEnum;
import cn.sparrow.permission.data.service.IPermission;

// 这个是再repository的校验级别，不是jpa的
@Component
public class ModelDeleterPermissionValidator implements Validator {

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
				PermissionEnum.DELETER, PermissionTypeEnum.DENY), username)) {
			errors.reject("SPR_MD_D_DN-40", "模型拒绝删除权限" + target.getClass().getName() + username);
		}

		// 1.先检查模型的作者权限
		if (!modelPermissionService.hasPermission(new AbstractModelPermissionPK(target.getClass().getName(),
				PermissionEnum.DELETER, PermissionTypeEnum.ALLOW), username)) {
			errors.reject("SPR_MD_D-40", "无模型删除权限" + target.getClass().getName() + username);
		}

		// 2.本条数据的的编辑权限
		if (dataPermissionService.hasPermission(new AbstractDataPermissionPK(target.getClass().getName(),
				PermissionEnum.DELETER, PermissionTypeEnum.DENY, sparrowEntity.getId()), username)) {
			errors.reject("SPR_DT_D_DN-40",
					"数据拒绝删除权限" + target.getClass().getName() + ((AbstractSparrowEntity) target).getId() + username);
		}

		// 2.本条数据的的编辑权限
		if (!dataPermissionService.hasPermission(new AbstractDataPermissionPK(target.getClass().getName(),
				PermissionEnum.DELETER, PermissionTypeEnum.ALLOW, sparrowEntity.getId()), username)) {
			errors.reject("SPR_DT_D-40",
					"无本条数据删除权限" + target.getClass().getName() + ((AbstractSparrowEntity) target).getId() + username);
		}

		// 触发验证失败
		if (sparrowEntity.getErrorMessage().size() > 0) {
			errors.reject("0101", sparrowEntity.getErrorMessage().toString());
		}

		// 2.检查字段的创建权限

	}

}
