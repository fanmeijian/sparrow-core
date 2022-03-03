package cn.sparrow.permission.service.impl;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import cn.sparrow.permission.constant.PreserveSysroleEnum;
import cn.sparrow.permission.model.Sysrole;
import cn.sparrow.permission.model.SysroleMenu;
import cn.sparrow.permission.model.SysroleMenuPK;
import cn.sparrow.permission.model.UserSysrole;
import cn.sparrow.permission.model.UserSysrolePK;
import cn.sparrow.permission.service.SysroleService;
import cn.sparrow.permission.service.repository.ApiRepository;
import cn.sparrow.permission.service.repository.SysroleMenuRepository;
import cn.sparrow.permission.service.repository.SysroleRepository;
import cn.sparrow.permission.service.repository.UserSysroleRepository;

@Service
public class SysroleServiceImpl implements SysroleService{
  @Autowired
  SysroleRepository sysroleRepository;
  // @Autowired SysroleUrlPermissionRepository sysroleUrlPermissionRepository;
  @Autowired
  ApiRepository urlRepository;
  @Autowired
  UserSysroleRepository userSysroleRepository;
  @Autowired
  SysroleMenuRepository sysroleMenuRepository;
  // @Autowired SysroleModelPermissionRepository sysroleModelPermissionRepository;
  // @Autowired SysroleDataPermissionRepository sysroleDataPermissionRepository;

  private static Logger logger = LoggerFactory.getLogger(SysroleServiceImpl.class);

  public void delMenus(String sysroleId, List<String> menuIds) {
    sysroleMenuRepository.deleteByIdSysroleIdAndIdMenuIdIn(sysroleId, menuIds);
  }

  public List<UserSysrole> getUserSysroles(@NotBlank String username) {
    return userSysroleRepository.findByIdUsername(username, Pageable.unpaged()).toList();
  }

  public void addMenus(String sysroleId, List<String> menuIds) {
    Set<SysroleMenu> sysroleMenus = new HashSet<SysroleMenu>();
    menuIds.forEach(f -> {
      sysroleMenus.add(new SysroleMenu(new SysroleMenuPK(sysroleId, f)));
    });
    sysroleMenuRepository.saveAll(sysroleMenus);
  }

  // public void addUrlPermissions(String sysroleId, List<String> urlIds) {
  // urlIds.forEach(f->{
  // sysroleUrlPermissionRepository.save(new SysroleUrlPermission(new
  // SysroleUrlPermissionPK(sysroleId, sysroleId)));
  // });
  // }
  //
  // @Transactional
  // public void delUrlPermissions(String sysroleId, List<String> urlIds) {
  // urlIds.forEach(f->{
  // sysroleUrlPermissionRepository.delete(new SysroleUrlPermission(new
  // SysroleUrlPermissionPK(sysroleId, sysroleId)));
  // });
  // }

  // public void addDataPermissions(String sysroleId, Set<AbstractDataPermissionPK>
  // dataPermissionPKs) {
  // dataPermissionPKs.forEach(f->{
  // sysroleDataPermissionRepository.save(new SysroleDataPermission(new
  // SysroleDataPermissionPK(f.getModelName(), f.getPermission(), f.getPermissionType(),
  // f.getDataId(), sysroleId)));
  // });
  // }
  //
  // @Transactional
  // public void delDataPermissions(String sysroleId, Set<AbstractDataPermissionPK>
  // dataPermissionPKs) {
  // dataPermissionPKs.forEach(f->{
  // sysroleDataPermissionRepository.deleteById(new SysroleDataPermissionPK(f.getModelName(),
  // f.getPermission(), f.getPermissionType(), f.getDataId(), sysroleId));
  // });
  // }


  public void init() {
    sysroleRepository.save(new Sysrole("超级管理员", PreserveSysroleEnum.SYSADMIN.name()));
    logger.info("Create sysrole {}", PreserveSysroleEnum.SYSADMIN.name());

    sysroleRepository.save(new Sysrole("系统管理员", PreserveSysroleEnum.ADMIN.name()));
    logger.info("Create sysrole {}", PreserveSysroleEnum.ADMIN.name());

    // urlRepository.findAll().forEach(f->{
    // if(!f.getMethod().equals(HttpMethod.GET)) {
    // sysroleUrlPermissionRepository.save(new SysroleUrlPermission(new
    // SysroleUrlPermissionPK(sysroleRepository.findByCode(PreserveSysroleEnum.SYSADMIN.name()).get(0).getId(),
    // f.getId())));
    // logger.info("Grant sysrole {} url permission
    // {}",PreserveSysroleEnum.ADMIN.name(),f.getUri());
    // }
    // });

    userSysroleRepository.save(new UserSysrole(new UserSysrolePK("ROOT",
        sysroleRepository.findByCode(PreserveSysroleEnum.SYSADMIN.name()).get(0).getId())));
    logger.info("Grant user {} sysrole SYSADMIN", PreserveSysroleEnum.ADMIN.name());

  }
  //
  // public void addModelPermission(@NotEmpty String sysroleId, Set<AbstractModelPermissionPK>
  // modelPermissionPKs) {
  // modelPermissionPKs.forEach(f->{
  // sysroleModelPermissionRepository.save(new SysroleModelPermission(new
  // SysroleModelPermissionPK(f.getModelName(), f.getPermission(), f.getPermissionType(),
  // sysroleId)));
  // });
  //
  // }
  //
  // public void delModelPermission(@NotEmpty String sysroleId, Set<AbstractModelPermissionPK>
  // modelPermissionPKs) {
  // modelPermissionPKs.forEach(f->{
  // sysroleModelPermissionRepository.delete(new SysroleModelPermission(new
  // SysroleModelPermissionPK(f.getModelName(), f.getPermission(), f.getPermissionType(),
  // sysroleId)));
  // });
  // }

  public void addPermissions(@NotNull List<UserSysrole> userSysroles) {
    userSysroleRepository.saveAll(userSysroles);
  }

  public void delPermissions(@NotNull List<UserSysrolePK> userSysrolePKs) {
    userSysroleRepository.deleteByIdIn(userSysrolePKs);
  }

@Override
public void delete(@NotNull String[] ids) {
	// TODO Auto-generated method stub
	
}


  // public void addPermissions(SysrolePermission permission) {
  // if(permission.getUserSysrolePKs()!=null) {
  // permission.getUserSysrolePKs().forEach(f->{
  // userSysroleRepository.save(new UserSysrole(f));
  // });
  // }
  // }
  //
  // public void delPermissions(SysrolePermission permission) {
  // if(permission.getUserSysrolePKs()!=null) {
  // userSysroleRepository.deleteByIdIn(permission.getUserSysrolePKs());
  // }
  // }
}
