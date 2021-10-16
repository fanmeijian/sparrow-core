package cn.sparrow.common.service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import cn.sparrow.common.repository.UserMenuRepository;
import cn.sparrow.common.repository.UserSysroleRepository;
import cn.sparrow.model.common.PreserveSysroleEnum;
import cn.sparrow.model.permission.AbstractDataPermissionPK;
import cn.sparrow.model.permission.AbstractModelPermissionPK;
import cn.sparrow.model.permission.UserDataPermission;
import cn.sparrow.model.permission.UserDataPermissionPK;
import cn.sparrow.model.permission.UserMenu;
import cn.sparrow.model.permission.UserMenuPK;
import cn.sparrow.model.permission.UserModelPermission;
import cn.sparrow.model.permission.UserModelPermissionPK;
import cn.sparrow.model.permission.UserSysrole;
import cn.sparrow.model.permission.UserSysrolePK;
import cn.sparrow.permission.repository.UserDataPermissionRepository;
import cn.sparrow.permission.repository.UserModelPermissionRepository;

@Service
public class UserService {

  @Autowired UserMenuRepository userMenuRepository;
  @Autowired UserSysroleRepository userSysroleRepository;
  @Autowired UserModelPermissionRepository userModelPermissionRepository;
  @Autowired UserDataPermissionRepository userDataPermissionRepository;
  
  private static Logger logger = LoggerFactory.getLogger(UserService.class);

  public void delMenus(String username, List<String> menuIds) {
    userMenuRepository.deleteByIdUsernameAndIdMenuIdIn(username, menuIds);
  }

  public void addMenus(String username, List<String> menuIds) {
    Set<UserMenu> userMenus = new HashSet<UserMenu>();
    menuIds.forEach(f -> {
      userMenus.add(new UserMenu(new UserMenuPK(username, f)));
    });
    addMenus(userMenus);
  }

  public void addMenusByUserMenuPK(Set<UserMenuPK> userMenuPKs) {
    Set<UserMenu> userMenus = new HashSet<UserMenu>();
    userMenuPKs.forEach(f -> {
      userMenus.add(new UserMenu(f));
    });
    addMenus(userMenus);
  }

  public void addMenus(Set<UserMenu> userMenus) {
    userMenuRepository.saveAll(userMenus);
  }
  
  public void addModelPermissions(String username,Set<AbstractModelPermissionPK> modelPermissionPKs) {
    modelPermissionPKs.forEach(f->{
      userModelPermissionRepository.save(new UserModelPermission(new UserModelPermissionPK(f.getModelName(), f.getPermission(), f.getPermissionType(), username)));
    });
  }
  
  @Transactional
  public void delModelPermissions(String username,Set<AbstractModelPermissionPK> modelPermissionPKs) {
    modelPermissionPKs.forEach(f->{
      userModelPermissionRepository.delete(new UserModelPermission(new UserModelPermissionPK(f.getModelName(), f.getPermission(), f.getPermissionType(), username)));
    });
  }
  
  public void addDataPermissions(String username, Set<AbstractDataPermissionPK> dataPermissionPKs) {
    dataPermissionPKs.forEach(f->{
      userDataPermissionRepository.save(new UserDataPermission(new UserDataPermissionPK(f.getModelName(), f.getPermission(), f.getPermissionType(), f.getDataId(), username)));
    });
  }
  
  @Transactional
  public void delDataPermissions(String username, Set<AbstractDataPermissionPK> dataPermissionPKs) {
    dataPermissionPKs.forEach(f->{
      userDataPermissionRepository.deleteById(new UserDataPermissionPK(f.getModelName(), f.getPermission(), f.getPermissionType(), f.getDataId(), username));
    });
  }
  
  public void init(String username) {
    userSysroleRepository.save(new UserSysrole(new UserSysrolePK(username,PreserveSysroleEnum.SYSADMIN.name())));
    logger.info("Grant user {} with role", username, PreserveSysroleEnum.SYSADMIN.name());
  }

}
