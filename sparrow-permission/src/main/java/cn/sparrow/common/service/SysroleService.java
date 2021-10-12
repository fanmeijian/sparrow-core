package cn.sparrow.common.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import cn.sparrow.common.repository.SysroleRepository;
import cn.sparrow.common.repository.SysroleUrlPermissionRepository;
import cn.sparrow.common.repository.UrlRepository;
import cn.sparrow.common.repository.UserSysroleRepository;
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
  
  private static Logger logger = LoggerFactory.getLogger(SysroleService.class);
  
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
