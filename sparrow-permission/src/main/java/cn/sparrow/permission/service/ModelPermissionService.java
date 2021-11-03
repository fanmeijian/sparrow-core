package cn.sparrow.permission.service;

import java.util.Set;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import cn.sparrow.model.common.PermissionEnum;
import cn.sparrow.model.common.PermissionTargetEnum;
import cn.sparrow.model.common.PermissionTypeEnum;
import cn.sparrow.model.permission.ModelPermissionPK;
import cn.sparrow.model.permission.SysroleModelPermissionPK;
import cn.sparrow.model.permission.UserModelPermissionPK;
import cn.sparrow.model.permission.UserSysrole;

@Service
public class ModelPermissionService extends AbstractPermissionService<ModelPermissionPK> {

	private static Logger logger = LoggerFactory.getLogger(ModelPermissionService.class);

	@Override
	public boolean hasPermission(ModelPermissionPK target, String username) {
		// 检查拒绝所有权限
		if (target.getPermissionType().equals(PermissionTypeEnum.DENY)) {
			// 用户权限
			if (isConfigPermission(
					new ModelPermissionPK(target.getModelName(), PermissionEnum.ALL, PermissionTypeEnum.DENY),
					PermissionTargetEnum.USER)) {
				if ((userModelPermissionRepository.findById(new UserModelPermissionPK(target.getModelName(),
						PermissionEnum.ALL, PermissionTypeEnum.DENY, username)).orElse(null)) != null) {
					logger.debug("用户 {} 拥有权限 {} {} {}", username, target.getModelName(), PermissionEnum.ALL,
							PermissionTypeEnum.DENY);
					return true;
				}
			}

			// 检查拒绝所有子操作权限
			if (isConfigPermission(new ModelPermissionPK(target.getModelName(), target.getPermission(),
					PermissionTypeEnum.DENY),PermissionTargetEnum.USER)) {
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
			if (isConfigPermission(new ModelPermissionPK(target.getModelName(), target.getPermission(),
					PermissionTypeEnum.DENY),PermissionTargetEnum.USER)) {
				if ((userModelPermissionRepository.findById(new UserModelPermissionPK(target.getModelName(),
						target.getPermission(), PermissionTypeEnum.DENY, username)).orElse(null)) != null) {
					logger.debug("用户 {} 拥有权限 {} {} {}", username, target.getModelName(), target.getPermission(),
							PermissionTypeEnum.DENY);
					return true;
				}
			}

			// 角色权限
			for (UserSysrole userSysrole : userSysroleRepository.findByIdUsername(username)) {
				if (isConfigPermission(new ModelPermissionPK(target.getModelName(), PermissionEnum.ALL,
						PermissionTypeEnum.DENY), PermissionTargetEnum.SYSROLE)) {
					if (sysroleModelPermissionRepository
							.findById(new SysroleModelPermissionPK(target.getModelName(), PermissionEnum.ALL,
									PermissionTypeEnum.DENY, userSysrole.getId().getSysroleId()))
							.orElse(null) != null) {
						logger.debug("角色 {} 拥有权限 {} {} {}", userSysrole.getId().getSysroleId(), target.getModelName(),
								PermissionEnum.ALL, PermissionTypeEnum.DENY);
						return true;
					}
				}

				if (isConfigPermission(new ModelPermissionPK(target.getModelName(), target.getPermission(),
						PermissionTypeEnum.DENY), PermissionTargetEnum.SYSROLE)) {
					if ((sysroleModelPermissionRepository.findById(new SysroleModelPermissionPK(target.getModelName(),
							PermissionEnum.resolveAll(target.getPermission()), PermissionTypeEnum.DENY,
							userSysrole.getId().getSysroleId())).orElse(null)) != null) {
						logger.debug("角色 {} 拥有权限 {} {} {}", userSysrole.getId().getSysroleId(), target.getModelName(),
								PermissionEnum.resolveAll(target.getPermission()), PermissionTypeEnum.DENY);
						return true;
					}
				}

				if (isConfigPermission(new ModelPermissionPK(target.getModelName(), target.getPermission(),
						PermissionTypeEnum.DENY), PermissionTargetEnum.SYSROLE)) {
					if ((sysroleModelPermissionRepository
							.findById(new SysroleModelPermissionPK(target.getModelName(), target.getPermission(),
									PermissionTypeEnum.DENY, userSysrole.getId().getSysroleId()))
							.orElse(null)) != null) {
						logger.debug("角色 {} 拥有权限 {} {} {}", userSysrole.getId().getSysroleId(), target.getModelName(),
								target.getPermission(), PermissionTypeEnum.DENY);
						return true;
					}
				}
			}

		}

		// 检查允许权限
		if (target.getPermissionType().equals(PermissionTypeEnum.ALLOW)) {
			if (isConfigPermission(target, PermissionTargetEnum.USER)) {
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
				if (isConfigPermission(target, PermissionTargetEnum.SYSROLE)) {
					for (UserSysrole userSysrole : userSysroleRepository.findByIdUsername(username)) {
						if (sysroleModelPermissionRepository
								.findById(new SysroleModelPermissionPK(target.getModelName(), target.getPermission(),
										target.getPermissionType(), userSysrole.getId().getSysroleId()))
								.orElse(null) != null) {
							logger.debug("角色 {} 拥有权限 {} {} {} ", userSysrole.getId().getSysroleId(),
									target.getModelName(), target.getPermission(), target.getPermissionType());
							return true;
						} else {
							logger.debug("角色 {} 不拥有权限 {} {} {} ", userSysrole.getId().getSysroleId(),
									target.getModelName(), target.getPermission(), target.getPermissionType());
						}

					}
				} else {
					return true;
				}
			}
		}

		return false;
	}

	@Override
	public boolean isConfigPermission(ModelPermissionPK target, PermissionTargetEnum permissionTarget) {
		switch (permissionTarget) {
		case USER:
			if (userModelPermissionRepository.countByIdModelNameAndIdPermissionAndIdPermissionType(
					target.getModelName(), target.getPermission(), target.getPermissionType()) > 0) {
				logger.debug("配置了模型用户权限 {} {} {}", target.getModelName(), target.getPermission(),
						target.getPermissionType());
				return true;
			} else {
				logger.debug("没有配置模型用户权限 {} {} {}", target.getModelName(), target.getPermission(),
						target.getPermissionType());
				return false;
			}
		case SYSROLE:
			if (sysroleModelPermissionRepository.countByIdModelNameAndIdPermissionAndIdPermissionType(
					target.getModelName(), target.getPermission(), target.getPermissionType()) > 0) {
				logger.debug("配置了模型角色权限 {} {} {}", target.getModelName(), target.getPermission(),
						target.getPermissionType());
				return true;
			} else {
				logger.debug("没有配置模型角色权限 {} {} {}", target.getModelName(), target.getPermission(),
						target.getPermissionType());
				return false;
			}
		default:
			return false;
		}

	}

  @Override
  public boolean addPermission(ModelPermissionPK target,
      PermissionTargetEnum permissionTarget) {
    // TODO Auto-generated method stub
    return false;
  }

  @Override
  public boolean addPermissions(Set<ModelPermissionPK> targets,
      PermissionTargetEnum permissionTarget) {
    // TODO Auto-generated method stub
    return false;
  }

  @Override
  public boolean delPermssion(ModelPermissionPK target,
      PermissionTargetEnum permissionTarget) {
    // TODO Auto-generated method stub
    return false;
  }

  @Override
  public boolean delPermssions(ModelPermissionPK target,
      PermissionTargetEnum permissionTarget) {
    // TODO Auto-generated method stub
    return false;
  }

}
