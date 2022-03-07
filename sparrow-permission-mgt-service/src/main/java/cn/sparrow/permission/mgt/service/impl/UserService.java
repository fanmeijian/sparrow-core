package cn.sparrow.permission.mgt.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.sparrow.permission.constant.PreserveSysroleEnum;
import cn.sparrow.permission.mgt.service.repository.UserMenuRepository;
import cn.sparrow.permission.mgt.service.repository.UserSysroleRepository;
import cn.sparrow.permission.model.resource.UserSysrole;
import cn.sparrow.permission.model.resource.UserSysrolePK;

@Service
public class UserService {

  @Autowired UserMenuRepository userMenuRepository;
  @Autowired UserSysroleRepository userSysroleRepository;
//  @Autowired UserModelPermissionRepository userModelPermissionRepository;
//  @Autowired UserDataPermissionRepository userDataPermissionRepository;
  
  private static Logger logger = LoggerFactory.getLogger(UserService.class);

//  public void addModelPermissions(String username,Set<AbstractModelPermissionPK> modelPermissionPKs) {
//    modelPermissionPKs.forEach(f->{
//      userModelPermissionRepository.save(new UserModelPermission(new UserModelPermissionPK(f.getModelName(), f.getPermission(), f.getPermissionType(), username)));
//    });
//  }
//  
//  @Transactional
//  public void delModelPermissions(String username,Set<AbstractModelPermissionPK> modelPermissionPKs) {
//    modelPermissionPKs.forEach(f->{
//      userModelPermissionRepository.delete(new UserModelPermission(new UserModelPermissionPK(f.getModelName(), f.getPermission(), f.getPermissionType(), username)));
//    });
//  }
//  
//  public void addDataPermissions(String username, Set<AbstractDataPermissionPK> dataPermissionPKs) {
//    dataPermissionPKs.forEach(f->{
//      userDataPermissionRepository.save(new UserDataPermission(new UserDataPermissionPK(f.getModelName(), f.getPermission(), f.getPermissionType(), f.getDataId(), username)));
//    });
//  }
//  
//  @Transactional
//  public void delDataPermissions(String username, Set<AbstractDataPermissionPK> dataPermissionPKs) {
//    dataPermissionPKs.forEach(f->{
//      userDataPermissionRepository.deleteById(new UserDataPermissionPK(f.getModelName(), f.getPermission(), f.getPermissionType(), f.getDataId(), username));
//    });
//  }
//  
  public void init(String username) {
    userSysroleRepository.save(new UserSysrole(new UserSysrolePK(username,PreserveSysroleEnum.SYSADMIN.name())));
    logger.info("Grant user {} with role", username, PreserveSysroleEnum.SYSADMIN.name());
  }

}
