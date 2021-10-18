package cn.sparrow.permission.service;

import java.util.Set;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import cn.sparrow.model.common.PermissionTargetEnum;
import cn.sparrow.model.permission.FilePermissionPK;

@Service
public class FilePermissionService extends AbstractPermissionService<FilePermissionPK> {
  private static Logger logger = LoggerFactory.getLogger(FilePermissionService.class);


  @Override
  public boolean isConfigPermission(FilePermissionPK target,
      PermissionTargetEnum permissionTarget) {
    switch (permissionTarget) {
      case USER:
        if (userFilePermissionRepository.countByIdFilePermission(target) > 0) {
          logger.debug("配置了文件用户权限 {} {} {}", target.getFileId(), target.getPermission(),
              target.getPermissionType());
          return true;
        }
        break;

      default:
        break;
    }
    return false;
  }

  @Override
  public boolean hasPermission(FilePermissionPK target, String username) {
    // TODO Auto-generated method stub
    return false;
  }

  @Override
  public boolean addPermission(FilePermissionPK target,
      PermissionTargetEnum permissionTarget) {
    // TODO Auto-generated method stub
    return false;
  }

  @Override
  public boolean addPermissions(Set<FilePermissionPK> targets,
      PermissionTargetEnum permissionTarget) {
    // TODO Auto-generated method stub
    return false;
  }

  @Override
  public boolean delPermssion(FilePermissionPK target,
      PermissionTargetEnum permissionTarget) {
    // TODO Auto-generated method stub
    return false;
  }

  @Override
  public boolean delPermssions(FilePermissionPK target,
      PermissionTargetEnum permissionTarget) {
    // TODO Auto-generated method stub
    return false;
  }

}
