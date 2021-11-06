//package cn.sparrow.permission.service;
//
//import java.util.Set;
//
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.stereotype.Service;
//
//import cn.sparrow.model.common.PermissionEnum;
//import cn.sparrow.model.common.PermissionTargetEnum;
//import cn.sparrow.model.common.PermissionTypeEnum;
//import cn.sparrow.model.permission.FilePermissionPK;
//import cn.sparrow.model.permission.SysroleFilePermissionPK;
//import cn.sparrow.model.permission.UserFilePermissionPK;
//import cn.sparrow.model.permission.UserSysrole;
//
//@Service
//public class FilePermissionService extends AbstractPermissionService<FilePermissionPK> {
//	private static Logger logger = LoggerFactory.getLogger(FilePermissionService.class);
//
//	@Override
//	public boolean isConfigPermission(FilePermissionPK target, PermissionTargetEnum permissionTarget) {
//		switch (permissionTarget) {
//		case USER:
//			if (userFilePermissionRepository.countByIdFilePermissionPK(target) > 0) {
//				logger.debug("配置了文件用户权限 {} {} {}", target.getFileId(), target.getPermission(),
//						target.getPermissionType());
//				return true;
//			}
//
//		case SYSROLE:
//			if (sysroleFilePermissionRepository.countByIdFilePermissionPK(target) > 0) {
//				logger.debug("配置了文件角色权限 {} {} {}", target.getFileId(), target.getPermission(),
//						target.getPermissionType());
//				return true;
//			} else {
//				logger.debug("没有配置文件角色权限 {} {} {}", target.getFileId(), target.getPermission(),
//						target.getPermissionType());
//				return false;
//			}
//		default:
//			return false;
//		}
//
//	}
//
//	@Override
//	public boolean hasPermission(FilePermissionPK target, String username) {
//		// 检查拒绝所有权限
//		if (target.getPermissionType().equals(PermissionTypeEnum.DENY)) {
//			// 用户权限
//			if (isConfigPermission(
//					new FilePermissionPK(target.getFileId(), PermissionEnum.ALL, PermissionTypeEnum.DENY),
//					PermissionTargetEnum.USER)) {
//				if ((userFilePermissionRepository.findById(new UserFilePermissionPK(
//						new FilePermissionPK(target.getFileId(), PermissionEnum.ALL, PermissionTypeEnum.DENY),
//						username)).orElse(null)) != null) {
//					logger.debug("用户 {} 拥有权限 {} {} {}", username, target.getFileId(), PermissionEnum.ALL,
//							PermissionTypeEnum.DENY);
//					return true;
//				}
//			}
//
//			// 检查拒绝所有子操作权限
//			if (isConfigPermission(
//					new FilePermissionPK(target.getFileId(), target.getPermission(), PermissionTypeEnum.DENY),
//					PermissionTargetEnum.USER)) {
//				if ((userFilePermissionRepository
//						.findById(new UserFilePermissionPK(
//								new FilePermissionPK(target.getFileId(),
//										PermissionEnum.resolveAll(target.getPermission()), PermissionTypeEnum.DENY),
//								username))
//						.orElse(null)) != null) {
//					logger.debug("用户 {} 拥有权限 {} {} {}", username, target.getFileId(),
//							PermissionEnum.resolveAll(target.getPermission()), PermissionTypeEnum.DENY);
//					return true;
//				}
//			}
//
//			// 检查拒绝本条权限
//			if (isConfigPermission(
//					new FilePermissionPK(target.getFileId(), target.getPermission(), PermissionTypeEnum.DENY),
//					PermissionTargetEnum.USER)) {
//				if ((userFilePermissionRepository.findById(new UserFilePermissionPK(
//						new FilePermissionPK(target.getFileId(), target.getPermission(), PermissionTypeEnum.DENY),
//						username)).orElse(null)) != null) {
//					logger.debug("用户 {} 拥有权限 {} {} {}", username, target.getFileId(), target.getPermission(),
//							PermissionTypeEnum.DENY);
//					return true;
//				}
//			}
//
//			// 角色权限
//			for (UserSysrole userSysrole : userSysroleRepository.findByIdUsername(username)) {
//				if (isConfigPermission(
//						new FilePermissionPK(target.getFileId(), PermissionEnum.ALL, PermissionTypeEnum.DENY),
//						PermissionTargetEnum.SYSROLE)) {
//					if (sysroleFilePermissionRepository
//							.findById(new SysroleFilePermissionPK(new FilePermissionPK(target.getFileId(),
//									PermissionEnum.ALL, PermissionTypeEnum.DENY), userSysrole.getId().getSysroleId()))
//							.orElse(null) != null) {
//						logger.debug("角色 {} 拥有权限 {} {} {}", userSysrole.getId().getSysroleId(), target.getFileId(),
//								PermissionEnum.ALL, PermissionTypeEnum.DENY);
//						return true;
//					}
//				}
//
//				if (isConfigPermission(
//						new FilePermissionPK(target.getFileId(), target.getPermission(), PermissionTypeEnum.DENY),
//						PermissionTargetEnum.SYSROLE)) {
//					if ((sysroleFilePermissionRepository
//							.findById(new SysroleFilePermissionPK(
//									new FilePermissionPK(target.getFileId(),
//											PermissionEnum.resolveAll(target.getPermission()), PermissionTypeEnum.DENY),
//									userSysrole.getId().getSysroleId()))
//							.orElse(null)) != null) {
//						logger.debug("角色 {} 拥有权限 {} {} {}", userSysrole.getId().getSysroleId(), target.getFileId(),
//								PermissionEnum.resolveAll(target.getPermission()), PermissionTypeEnum.DENY);
//						return true;
//					}
//				}
//
//				if (isConfigPermission(
//						new FilePermissionPK(target.getFileId(), target.getPermission(), PermissionTypeEnum.DENY),
//						PermissionTargetEnum.SYSROLE)) {
//					if ((sysroleFilePermissionRepository.findById(new SysroleFilePermissionPK(
//							new FilePermissionPK(target.getFileId(), target.getPermission(), PermissionTypeEnum.DENY),
//							userSysrole.getId().getSysroleId())).orElse(null)) != null) {
//						logger.debug("角色 {} 拥有权限 {} {} {}", userSysrole.getId().getSysroleId(), target.getFileId(),
//								target.getPermission(), PermissionTypeEnum.DENY);
//						return true;
//					}
//				}
//			}
//
//		}
//
//		// 检查允许权限
//		if (target.getPermissionType().equals(PermissionTypeEnum.ALLOW)) {
//			if (isConfigPermission(target, PermissionTargetEnum.USER)) {
//				if (userFilePermissionRepository.findById(new UserFilePermissionPK(
//						new FilePermissionPK(target.getFileId(), target.getPermission(), target.getPermissionType()),
//						username)).orElse(null) != null) {
//					logger.debug("用户 {} 拥有权限 {} {} {} ", username, target.getFileId(), target.getPermission(),
//							target.getPermissionType());
//					return true;
//				} else {
//					logger.debug("用户 {} 不拥有权限 {} {} {} ", username, target.getFileId(), target.getPermission(),
//							target.getPermissionType());
//				}
//			} else {
//				if (isConfigPermission(target, PermissionTargetEnum.SYSROLE)) {
//					for (UserSysrole userSysrole : userSysroleRepository.findByIdUsername(username)) {
//						if (sysroleFilePermissionRepository
//								.findById(
//										new SysroleFilePermissionPK(
//												new FilePermissionPK(target.getFileId(), target.getPermission(),
//														target.getPermissionType()),
//												userSysrole.getId().getSysroleId()))
//								.orElse(null) != null) {
//							logger.debug("角色 {} 拥有权限 {} {} {} ", userSysrole.getId().getSysroleId(), target.getFileId(),
//									target.getPermission(), target.getPermissionType());
//							return true;
//						} else {
//							logger.debug("角色 {} 不拥有权限 {} {} {} ", userSysrole.getId().getSysroleId(),
//									target.getFileId(), target.getPermission(), target.getPermissionType());
//						}
//
//					}
//				} else {
//					return true;
//				}
//			}
//		}
//
//		return false;
//	}
//
//	@Override
//	public boolean addPermission(FilePermissionPK target, PermissionTargetEnum permissionTarget) {
//		// TODO Auto-generated method stub
//		return false;
//	}
//
//	@Override
//	public boolean addPermissions(Set<FilePermissionPK> targets, PermissionTargetEnum permissionTarget) {
//		// TODO Auto-generated method stub
//		return false;
//	}
//
//	@Override
//	public boolean delPermssion(FilePermissionPK target, PermissionTargetEnum permissionTarget) {
//		// TODO Auto-generated method stub
//		return false;
//	}
//
//	@Override
//	public boolean delPermssions(FilePermissionPK target, PermissionTargetEnum permissionTarget) {
//		// TODO Auto-generated method stub
//		return false;
//	}
//
//}
