package cn.sparrow.permission.service;

import java.util.Set;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.sparrow.model.common.PermissionEnum;
import cn.sparrow.model.common.PermissionTargetEnum;
import cn.sparrow.model.common.PermissionTypeEnum;
import cn.sparrow.model.permission.AbstractDataPermissionPK;
import cn.sparrow.model.permission.DataPermission;
import cn.sparrow.model.permission.SysroleDataPermission;
import cn.sparrow.model.permission.SysroleDataPermissionPK;
import cn.sparrow.model.permission.UserDataPermission;
import cn.sparrow.model.permission.UserDataPermissionPK;
import cn.sparrow.model.permission.UserSysrole;
import cn.sparrow.permission.repository.SysroleDataPermissionRepository;
import cn.sparrow.permission.repository.UserDataPermissionRepository;

@Service
public class DataPermissionService extends AbstractPermissionService<AbstractDataPermissionPK> {

	private static Logger logger = LoggerFactory.getLogger(DataPermissionService.class);
	
	@Autowired UserDataPermissionRepository userDataPermissionRepository;
	@Autowired SysroleDataPermissionRepository sysroleDataPermissionRepository;

//	@Override
//	public boolean isConfigPermission(AbstractDataPermissionPK target) {
//		if (userDataPermissionRepository.countByIdModelNameAndIdPermissionAndIdPermissionTypeAndIdDataId(
//				target.getModelName(), target.getPermission(), target.getPermissionType(), target.getDataId()) > 0) {
//			return true;
//		} else {
//			logger.debug("没有配置权限 {} {} {} {}", target.getModelName(), target.getPermission(),
//					target.getPermissionType(), target.getDataId());
//			return false;
//		}
//	}

	@Override
	public boolean hasPermission(AbstractDataPermissionPK target, String username) {
		// 检查拒绝所有权限
		if (target.getPermissionType().equals(PermissionTypeEnum.DENY)) {
			if (isConfigPermission(new AbstractDataPermissionPK(target.getModelName(), PermissionEnum.ALL,
					PermissionTypeEnum.DENY, target.getDataId()), PermissionTargetEnum.USER)) {
				if ((userDataPermissionRepository.findById(new UserDataPermissionPK(target.getModelName(),
						PermissionEnum.ALL, PermissionTypeEnum.DENY, target.getDataId(), username))
						.orElse(null)) != null) {
					logger.debug("用户 {} 拥有权限 {} {} {} {}", username, target.getModelName(), PermissionEnum.ALL,
							PermissionTypeEnum.DENY, target.getDataId());
					return true;
				}
			}

			// 检查拒绝所有子操作权限
			if (isConfigPermission(new AbstractDataPermissionPK(target.getModelName(), target.getPermission(),
					PermissionTypeEnum.DENY, target.getDataId()), PermissionTargetEnum.USER)) {
				if ((userDataPermissionRepository.findById(new UserDataPermissionPK(target.getModelName(),
						PermissionEnum.resolveAll(target.getPermission()), PermissionTypeEnum.DENY, target.getDataId(),
						username)).orElse(null)) != null) {
					logger.debug("用户 {} 拥有权限 {} {} {} {}", username, target.getModelName(),
							PermissionEnum.resolveAll(target.getPermission()), PermissionTypeEnum.DENY,
							target.getDataId());
					return true;
				}
			}

			// 检查本条拒绝操作权限
			if (isConfigPermission(new AbstractDataPermissionPK(target.getModelName(), target.getPermission(),
					PermissionTypeEnum.DENY, target.getDataId()), PermissionTargetEnum.USER)) {
				if ((userDataPermissionRepository.findById(new UserDataPermissionPK(target.getModelName(),
						target.getPermission(), PermissionTypeEnum.DENY, target.getDataId(), username))
						.orElse(null)) != null) {
					logger.debug("用户 {} 拥有权限 {} {} {} {}", username, target.getModelName(), target.getPermission(),
							PermissionTypeEnum.DENY, target.getDataId());
					return true;
				}
			}

			for (UserSysrole userSysrole : userSysroleRepository.findByIdUsername(username)) {
				if (isConfigPermission(new AbstractDataPermissionPK(target.getModelName(), PermissionEnum.ALL,
						PermissionTypeEnum.DENY, target.getDataId()), PermissionTargetEnum.SYSROLE)) {
					if ((sysroleDataPermissionRepository
							.findById(new SysroleDataPermissionPK(target.getModelName(), PermissionEnum.ALL,
									PermissionTypeEnum.DENY, target.getDataId(), userSysrole.getId().getSysroleId()))
							.orElse(null)) != null) {
						logger.debug("角色 {} 拥有权限 {} {} {} {}", userSysrole.getId().getSysroleId(),
								target.getModelName(), PermissionEnum.ALL, PermissionTypeEnum.DENY, target.getDataId());
						return true;
					}
				}

				// 检查拒绝所有子操作权限
				if (isConfigPermission(new AbstractDataPermissionPK(target.getModelName(), target.getPermission(),
						PermissionTypeEnum.DENY, target.getDataId()), PermissionTargetEnum.SYSROLE)) {
					if ((sysroleDataPermissionRepository.findById(new SysroleDataPermissionPK(target.getModelName(),
							PermissionEnum.resolveAll(target.getPermission()), PermissionTypeEnum.DENY,
							target.getDataId(), userSysrole.getId().getSysroleId())).orElse(null)) != null) {
						logger.debug("角色 {} 拥有权限 {} {} {} {}", userSysrole.getId().getSysroleId(),
								target.getModelName(), PermissionEnum.resolveAll(target.getPermission()),
								PermissionTypeEnum.DENY, target.getDataId());
						return true;
					}
				}

				// 检查本条拒绝操作权限
				if (isConfigPermission(new AbstractDataPermissionPK(target.getModelName(), target.getPermission(),
						PermissionTypeEnum.DENY, target.getDataId()), PermissionTargetEnum.SYSROLE)) {
					if ((sysroleDataPermissionRepository
							.findById(new SysroleDataPermissionPK(target.getModelName(), target.getPermission(),
									PermissionTypeEnum.DENY, target.getDataId(), userSysrole.getId().getSysroleId()))
							.orElse(null)) != null) {
						logger.debug("角色 {} 拥有权限 {} {} {} {}", userSysrole.getId().getSysroleId(),
								target.getModelName(), target.getPermission(), PermissionTypeEnum.DENY,
								target.getDataId());
						return true;
					}
				}
			}

		}

		// 检查允许权限
		if (target.getPermissionType().equals(PermissionTypeEnum.ALLOW)) {
			if (isConfigPermission(target, PermissionTargetEnum.USER)) {
				if (userDataPermissionRepository.findById(new UserDataPermissionPK(target.getModelName(),
						target.getPermission(), target.getPermissionType(), target.getDataId(), username))
						.orElse(null) != null) {
					logger.debug("用户 {} 拥有权限 {} {} {} {}", username, target.getModelName(), target.getPermission(),
							target.getPermissionType(), target.getDataId());
					return true;
				} else {
					logger.debug("用户 {} 不拥有权限 {} {} {} {}", username, target.getModelName(), target.getPermission(),
							target.getPermissionType(), target.getDataId());
				}
			} else {
				if (isConfigPermission(target, PermissionTargetEnum.SYSROLE)) {
					for (UserSysrole userSysrole : userSysroleRepository.findByIdUsername(username)) {
						if (sysroleDataPermissionRepository.findById(new SysroleDataPermissionPK(target.getModelName(),
								target.getPermission(), target.getPermissionType(), target.getDataId(),
								userSysrole.getId().getSysroleId())).orElse(null) != null) {
							logger.debug("角色 {} 拥有权限 {} {} {} {}", userSysrole.getId().getSysroleId(),
									target.getModelName(), target.getPermission(), target.getPermissionType(),
									target.getDataId());
							return true;
						} else {
							logger.debug("角色 {} 不拥有权限 {} {} {} {}", userSysrole.getId().getSysroleId(),
									target.getModelName(), target.getPermission(), target.getPermissionType(),
									target.getDataId());
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
	public boolean isConfigPermission(AbstractDataPermissionPK target, PermissionTargetEnum permissionTarget) {
		switch (permissionTarget) {
		case USER:
			if (userDataPermissionRepository.countByIdModelNameAndIdPermissionAndIdPermissionTypeAndIdDataId(
					target.getModelName(), target.getPermission(), target.getPermissionType(),
					target.getDataId()) > 0) {
				return true;
			} else {
				logger.debug("没有配置用户数据权限 {} {} {} {}", target.getModelName(), target.getPermission(),
						target.getPermissionType(), target.getDataId());
				return false;
			}
		case SYSROLE:
			if (sysroleDataPermissionRepository.countByIdModelNameAndIdPermissionAndIdPermissionTypeAndIdDataId(
					target.getModelName(), target.getPermission(), target.getPermissionType(),
					target.getDataId()) > 0) {
				return true;
			} else {
				logger.debug("没有配置角色数据权限 {} {} {} {}", target.getModelName(), target.getPermission(),
						target.getPermissionType(), target.getDataId());
				return false;
			}

		default:
			return false;
		}

	}

  @Override
  public boolean addPermission(AbstractDataPermissionPK target,
      PermissionTargetEnum permissionTarget) {
    // TODO Auto-generated method stub
    return false;
  }

  @Override
  public boolean addPermissions(Set<AbstractDataPermissionPK> targets,
      PermissionTargetEnum permissionTarget) {
    // TODO Auto-generated method stub
    return false;
  }

  @Override
  public boolean delPermssion(AbstractDataPermissionPK target,
      PermissionTargetEnum permissionTarget) {
    // TODO Auto-generated method stub
    return false;
  }

  @Override
  public boolean delPermssions(AbstractDataPermissionPK target,
      PermissionTargetEnum permissionTarget) {
    // TODO Auto-generated method stub
    return false;
  }
  
  public void addPermissions(DataPermission permission) {
    if(permission.getUserDataPermissionPKs()!=null) {
    	permission.getUserDataPermissionPKs().forEach(f->{
    		userDataPermissionRepository.save(new UserDataPermission(f));
    	});
    }
    
    if(permission.getSysroleDataPermissionPKs()!=null) {
    	permission.getSysroleDataPermissionPKs().forEach(f->{
    		sysroleDataPermissionRepository.save(new SysroleDataPermission(f));
    	});
    }
  }

  public void delPermissions(DataPermission permission) {
    if(permission.getUserDataPermissionPKs()!=null)
    	userDataPermissionRepository.deleteByIdIn(permission.getUserDataPermissionPKs());
    
    if(permission.getSysroleDataPermissionPKs()!=null)
    	sysroleDataPermissionRepository.deleteByIdIn(permission.getSysroleDataPermissionPKs());
  }

}
