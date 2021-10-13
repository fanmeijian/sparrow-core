package cn.sparrow.common.service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.transaction.Transactional;
import javax.validation.constraints.NotEmpty;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import cn.sparrow.common.repository.SysroleMenuRepository;
import cn.sparrow.common.repository.SysroleRepository;
import cn.sparrow.common.repository.SysroleUrlPermissionRepository;
import cn.sparrow.common.repository.UrlRepository;
import cn.sparrow.common.repository.UserSysroleRepository;
import cn.sparrow.model.menu.SysroleMenu;
import cn.sparrow.model.menu.SysroleMenuPK;
import cn.sparrow.model.permission.AbstractDataPermissionPK;
import cn.sparrow.model.permission.AbstractModelPermissionPK;
import cn.sparrow.model.permission.SysroleDataPermission;
import cn.sparrow.model.permission.SysroleDataPermissionPK;
import cn.sparrow.model.permission.SysroleModelPermission;
import cn.sparrow.model.permission.SysroleModelPermissionPK;
import cn.sparrow.model.permission.SysroleUrlPermission;
import cn.sparrow.model.permission.SysroleUrlPermissionPK;
import cn.sparrow.model.sysrole.PreserveSysroleEnum;
import cn.sparrow.model.sysrole.Sysrole;
import cn.sparrow.model.sysrole.UserSysrole;
import cn.sparrow.model.sysrole.UserSysrolePK;
import cn.sparrow.permission.repository.SysroleDataPermissionRepository;
import cn.sparrow.permission.repository.SysroleModelPermissionRepository;

@Service
public class SysroleService {
  @Autowired SysroleRepository sysroleRepository;
  @Autowired SysroleUrlPermissionRepository sysroleUrlPermissionRepository;
  @Autowired UrlRepository urlRepository;
  @Autowired UserSysroleRepository userSysroleRepository;
  @Autowired SysroleMenuRepository sysroleMenuRepository;
  @Autowired SysroleModelPermissionRepository sysroleModelPermissionRepository;
  @Autowired SysroleDataPermissionRepository sysroleDataPermissionRepository;
  
  private static Logger logger = LoggerFactory.getLogger(SysroleService.class);
  
  public void delMenus(String sysroleId, List<String> menuIds) {
	  sysroleMenuRepository.deleteByIdSysroleIdAndIdMenuIdIn(sysroleId, menuIds);
  }
  
  public void addMenus(String sysroleId, List<String> menuIds) {
    Set<SysroleMenu> sysroleMenus = new HashSet<SysroleMenu>();
    menuIds.forEach(f -> {
    	sysroleMenus.add(new SysroleMenu(new SysroleMenuPK(sysroleId, f)));
    });
    sysroleMenuRepository.saveAll(sysroleMenus);
  }
  
  public void addUrlPermissions(String sysroleId, List<String> urlIds) {
	  urlIds.forEach(f->{
		  sysroleUrlPermissionRepository.save(new SysroleUrlPermission(new SysroleUrlPermissionPK(sysroleId, sysroleId)));
	  });
  }
  
  @Transactional
  public void delUrlPermissions(String sysroleId, List<String> urlIds) {
	  urlIds.forEach(f->{
		  sysroleUrlPermissionRepository.delete(new SysroleUrlPermission(new SysroleUrlPermissionPK(sysroleId, sysroleId)));
	  });
  }
  
  public void addDataPermissions(String sysroleId, Set<AbstractDataPermissionPK> dataPermissionPKs) {
    dataPermissionPKs.forEach(f->{
      sysroleDataPermissionRepository.save(new SysroleDataPermission(new SysroleDataPermissionPK(f.getModelName(), f.getPermission(), f.getPermissionType(), f.getDataId(), sysroleId)));
    });
  }
  
  @Transactional
  public void delDataPermissions(String sysroleId, Set<AbstractDataPermissionPK> dataPermissionPKs) {
    dataPermissionPKs.forEach(f->{
      sysroleDataPermissionRepository.deleteById(new SysroleDataPermissionPK(f.getModelName(), f.getPermission(), f.getPermissionType(), f.getDataId(), sysroleId));
    });
  }
  
  
  public void init() {
    sysroleRepository.save(new Sysrole(PreserveSysroleEnum.SYSADMIN.name(), null, null));
    logger.info("Create sysrole {}",PreserveSysroleEnum.SYSADMIN.name());
    
    sysroleRepository.save(new Sysrole(PreserveSysroleEnum.ADMIN.name(), null, null));
    logger.info("Create sysrole {}",PreserveSysroleEnum.ADMIN.name());
    
    urlRepository.findAll().forEach(f->{
      if(!f.getMethod().equals(HttpMethod.GET)) {
        sysroleUrlPermissionRepository.save(new SysroleUrlPermission(new SysroleUrlPermissionPK(sysroleRepository.findByName(PreserveSysroleEnum.SYSADMIN.name()).get(0).getId(), f.getId()), null));
        logger.info("Grant sysrole {} url permission {}",PreserveSysroleEnum.ADMIN.name(),f.getUri());
      }
    });
    
    userSysroleRepository.save(new UserSysrole(new UserSysrolePK("ROOT", sysroleRepository.findByName(PreserveSysroleEnum.SYSADMIN.name()).get(0).getId())));
    logger.info("Grant user {} sysrole SYSADMIN",PreserveSysroleEnum.ADMIN.name());

  }

  public void addModelPermission(@NotEmpty String sysroleId, Set<AbstractModelPermissionPK> modelPermissionPKs) {
      modelPermissionPKs.forEach(f->{
        sysroleModelPermissionRepository.save(new SysroleModelPermission(new SysroleModelPermissionPK(f.getModelName(), f.getPermission(), f.getPermissionType(), sysroleId)));
      });
    
  }

  public void delModelPermission(@NotEmpty String sysroleId, Set<AbstractModelPermissionPK> modelPermissionPKs) { 
      modelPermissionPKs.forEach(f->{
        sysroleModelPermissionRepository.delete(new SysroleModelPermission(new SysroleModelPermissionPK(f.getModelName(), f.getPermission(), f.getPermissionType(), sysroleId)));
      });
  }
}
