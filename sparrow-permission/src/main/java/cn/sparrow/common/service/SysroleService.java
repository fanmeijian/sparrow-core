package cn.sparrow.common.service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.transaction.Transactional;

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
import cn.sparrow.model.permission.SysroleUrlPermission;
import cn.sparrow.model.permission.SysroleUrlPermissionPK;
import cn.sparrow.model.sysrole.PreserveSysroleEnum;
import cn.sparrow.model.sysrole.Sysrole;
import cn.sparrow.model.sysrole.UserSysrole;
import cn.sparrow.model.sysrole.UserSysrolePK;

@Service
public class SysroleService {
  @Autowired SysroleRepository sysroleRepository;
  @Autowired SysroleUrlPermissionRepository sysroleUrlPermissionRepository;
  @Autowired UrlRepository urlRepository;
  @Autowired UserSysroleRepository userSysroleRepository;
  @Autowired SysroleMenuRepository sysroleMenuRepository;
  
  private static Logger logger = LoggerFactory.getLogger(SysroleService.class);
  
  public void removeMenusByMenuId(String sysroleId, List<String> menuIds) {
	  sysroleMenuRepository.deleteByIdSysroleIdAndIdMenuIdIn(sysroleId, menuIds);
  }
  
  public void addMenusByMenuId(String sysroleId, List<String> menuIds) {
    Set<SysroleMenu> sysroleMenus = new HashSet<SysroleMenu>();
    menuIds.forEach(f -> {
    	sysroleMenus.add(new SysroleMenu(new SysroleMenuPK(sysroleId, f)));
    });
    sysroleMenuRepository.saveAll(sysroleMenus);
  }
  
  public void addUrlPermission(String sysroleId, List<String> urlIds) {
	  urlIds.forEach(f->{
		  sysroleUrlPermissionRepository.save(new SysroleUrlPermission(new SysroleUrlPermissionPK(sysroleId, sysroleId)));
	  });
  }
  
  @Transactional
  public void delUrlPermission(String sysroleId, List<String> urlIds) {
	  urlIds.forEach(f->{
		  sysroleUrlPermissionRepository.delete(new SysroleUrlPermission(new SysroleUrlPermissionPK(sysroleId, sysroleId)));
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
}
