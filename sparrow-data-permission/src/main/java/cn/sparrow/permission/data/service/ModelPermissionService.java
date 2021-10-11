package cn.sparrow.permission.data.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import cn.sparrow.permission.data.model.AbstractModelPermissionPK;
import cn.sparrow.permission.data.model.UserModelPermissionPK;
import cn.sparrow.permission.data.repository.PermissionEnum;
import cn.sparrow.permission.data.repository.PermissionTypeEnum;

@Service
public class ModelPermissionService extends AbstractPermissionService<AbstractModelPermissionPK> {

	private static Logger logger = LoggerFactory.getLogger(ModelPermissionService.class);

	@Override
	public boolean isConfigPermission(AbstractModelPermissionPK target) {
		if (userModelPermissionRepository.countByIdModelNameAndIdPermissionAndIdPermissionType(target.getModelName(),
				target.getPermission(), target.getPermissionType()) > 0) {
			return true;
		} else {
			logger.debug("没有配置权限 {} {} {}", target.getModelName(), target.getPermission(), target.getPermissionType());
			return false;
		}
	}

	@Override
	public boolean hasPermission(AbstractModelPermissionPK target, String username) {
		// 检查拒绝所有权限
		if (target.getPermissionType().equals(PermissionTypeEnum.DENY)) {
			if (isConfigPermission(new AbstractModelPermissionPK(target.getModelName(), PermissionEnum.ALL,
					PermissionTypeEnum.DENY))) {
				if ((userModelPermissionRepository.findById(new UserModelPermissionPK(target.getModelName(),
						PermissionEnum.ALL, PermissionTypeEnum.DENY, username)).orElse(null)) != null) {
					logger.debug("用户 {} 拥有权限 {} {} {}", username, target.getModelName(), PermissionEnum.ALL,
							PermissionTypeEnum.DENY);
					return true;
				}
			}

			// 检查拒绝所有子操作权限
			if (isConfigPermission(new AbstractModelPermissionPK(target.getModelName(), target.getPermission(),
					PermissionTypeEnum.DENY))) {
				if ((userModelPermissionRepository
						.findById(new UserModelPermissionPK(target.getModelName(),
								PermissionEnum.resolveAll(target.getPermission()), PermissionTypeEnum.DENY, username))
						.orElse(null)) != null) {
					logger.debug("用户 {} 拥有权限 {} {} {}", username, target.getModelName(),
							PermissionEnum.resolveAll(target.getPermission()), PermissionTypeEnum.DENY);
					return true;
				}
			}

			// 检查拒绝本条权限
			if (isConfigPermission(new AbstractModelPermissionPK(target.getModelName(), target.getPermission(),
					PermissionTypeEnum.DENY))) {
				if ((userModelPermissionRepository.findById(new UserModelPermissionPK(target.getModelName(),
						target.getPermission(), PermissionTypeEnum.DENY, username)).orElse(null)) != null) {
					logger.debug("用户 {} 拥有权限 {} {} {}", username, target.getModelName(),
							PermissionEnum.resolveAll(target.getPermission()), PermissionTypeEnum.DENY);
					return true;
				}
			}
		}

		// 检查允许权限
		if (target.getPermissionType().equals(PermissionTypeEnum.ALLOW)) {
			if (isConfigPermission(target)) {
				if (userModelPermissionRepository.findById(new UserModelPermissionPK(target.getModelName(),
						target.getPermission(), target.getPermissionType(), username)).orElse(null) != null) {
					logger.debug("用户 {} 拥有权限 {} {} {} ", username, target.getModelName(), target.getPermission(),
							target.getPermissionType());
					return true;
				} else {
					logger.debug("用户 {} 不拥有权限 {} {} {} ", username, target.getModelName(), target.getPermission(),
							target.getPermissionType());
				}
			} else {
				return true;
			}
		}

		return false;
	}

}
