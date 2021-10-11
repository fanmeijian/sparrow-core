package cn.sparrow.permission.data.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import cn.sparrow.permission.data.model.AbstractDataPermissionPK;
import cn.sparrow.permission.data.model.UserDataPermissionPK;
import cn.sparrow.permission.data.repository.PermissionEnum;
import cn.sparrow.permission.data.repository.PermissionTypeEnum;

@Service
public class DataPermissionService extends AbstractPermissionService<AbstractDataPermissionPK> {

	private static Logger logger = LoggerFactory.getLogger(DataPermissionService.class);
	
	@Override
	public boolean isConfigPermission(AbstractDataPermissionPK target) {
		if (userDataPermissionRepository.countByIdModelNameAndIdPermissionAndIdPermissionTypeAndIdDataId(target.getModelName(),
				target.getPermission(), target.getPermissionType(), target.getDataId()) > 0) {
			return true;
		} else {
			logger.debug("没有配置权限 {} {} {} {}", target.getModelName(), target.getPermission(), target.getPermissionType(), target.getDataId());
			return false;
		}
	}

	@Override
	public boolean hasPermission(AbstractDataPermissionPK target, String username) {
		// 检查拒绝所有权限
				if (target.getPermissionType().equals(PermissionTypeEnum.DENY)) {
					if (isConfigPermission(new AbstractDataPermissionPK(target.getModelName(), PermissionEnum.ALL,
							PermissionTypeEnum.DENY, target.getDataId()))) {
						if ((userDataPermissionRepository.findById(new UserDataPermissionPK(target.getModelName(),
								PermissionEnum.ALL, PermissionTypeEnum.DENY, target.getDataId(), username)).orElse(null)) != null) {
							logger.debug("用户 {} 拥有权限 {} {} {} {}", username, target.getModelName(), PermissionEnum.ALL,
									PermissionTypeEnum.DENY,target.getDataId());
							return true;
						}
					}

					// 检查拒绝所有子操作权限
					if (isConfigPermission(new AbstractDataPermissionPK(target.getModelName(), target.getPermission(),
							PermissionTypeEnum.DENY, target.getDataId()))) {
						if ((userDataPermissionRepository
								.findById(new UserDataPermissionPK(target.getModelName(),
										PermissionEnum.resolveAll(target.getPermission()), PermissionTypeEnum.DENY, target.getDataId(), username))
								.orElse(null)) != null) {
							logger.debug("用户 {} 拥有权限 {} {} {} {}", username, target.getModelName(),
									PermissionEnum.resolveAll(target.getPermission()), PermissionTypeEnum.DENY,target.getDataId());
							return true;
						}
					}
				}

				// 检查允许权限
				if (target.getPermissionType().equals(PermissionTypeEnum.ALLOW)) {
					if (isConfigPermission(target)) {
						if (userDataPermissionRepository.findById(new UserDataPermissionPK(target.getModelName(),
								target.getPermission(), target.getPermissionType(), target.getDataId(), username)).orElse(null) != null) {
							logger.debug("用户 {} 拥有权限 {} {} {} {}", username, target.getModelName(), target.getPermission(),
									target.getPermissionType(),target.getDataId());
							return true;
						} else {
							logger.debug("用户 {} 不拥有权限 {} {} {} {}", username, target.getModelName(), target.getPermission(),
									target.getPermissionType(),target.getDataId());
						}
					} else {
						return true;
					}
				}

		return false;
	}

}
