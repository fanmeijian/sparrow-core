package cn.sparrow.permission.mgt.service.impl;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.transaction.Transactional;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.ExampleMatcher.StringMatcher;
import org.springframework.stereotype.Service;

import cn.sparrow.permission.constant.PreserveSysroleEnum;
import cn.sparrow.permission.mgt.api.SysroleService;
import cn.sparrow.permission.mgt.service.repository.ApiRepository;
import cn.sparrow.permission.mgt.service.repository.SysroleMenuRepository;
import cn.sparrow.permission.mgt.service.repository.SysroleRepository;
import cn.sparrow.permission.mgt.service.repository.UserSysroleRepository;
import cn.sparrow.permission.model.resource.Sysrole;
import cn.sparrow.permission.model.resource.SysroleMenu;
import cn.sparrow.permission.model.resource.SysroleMenuPK;
import cn.sparrow.permission.model.resource.UserSysrole;
import cn.sparrow.permission.model.resource.UserSysrolePK;
import lombok.extern.slf4j.Slf4j;

@Slf4j
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

  public void delMenus(String sysroleId, List<String> menuIds) {
    sysroleMenuRepository.deleteByIdSysroleIdAndIdMenuIdIn(sysroleId, menuIds);
  }

  @Override
  public List<String> getUsers( String sysroleId) {
    List<String> usernames= new ArrayList<String>();
    userSysroleRepository.findByIdSysroleId(sysroleId).forEach(f->{
      usernames.add(f.getId().getUsername());
    });
    return usernames;
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
    log.info("Create sysrole {}", PreserveSysroleEnum.SYSADMIN.name());

    sysroleRepository.save(new Sysrole("系统管理员", PreserveSysroleEnum.ADMIN.name()));
    log.info("Create sysrole {}", PreserveSysroleEnum.ADMIN.name());

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
    log.info("Grant user {} sysrole SYSADMIN", PreserveSysroleEnum.ADMIN.name());

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

  @Override
  @Transactional
  public void addPermissions( List<UserSysrolePK> userSysroles) {
    userSysroles.forEach(f->{
      userSysroleRepository.save(new UserSysrole(f));
    });
  }

  @Override
  @Transactional
  public void removePermissions(@NotNull List<UserSysrolePK> userSysrolePKs) {
    userSysroleRepository.deleteAllByIdInBatch(userSysrolePKs);
  }

@Override
@Transactional
public void delete( List<String> ids) {
  sysroleRepository.deleteAllByIdInBatch(ids);
}

@Override
public Page<Sysrole> all(Pageable pageable, Sysrole sysrole) {
  ExampleMatcher matcher = ExampleMatcher.matching().withIgnoreCase().withStringMatcher(StringMatcher.CONTAINING);
		return sysroleRepository.findAll(Example.of(sysrole, matcher), pageable);
}

@Override
public Sysrole create(Sysrole sysrole) {
  return sysroleRepository.save(sysrole);
}

@Override
public Sysrole update(String sysroleId, Map<String, Object> map) {
  Sysrole source=sysroleRepository.getById(sysroleId);
  PatchUpdateHelper.merge(source, map);
  return sysroleRepository.save(source);
}

@Override
public Sysrole get(String sysroleId) {
  return sysroleRepository.findById(sysroleId).get();
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
