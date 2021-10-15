package cn.sparrow.common.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.stereotype.Service;

import cn.sparrow.permission.service.ModelService;
import cn.sparrow.permission.service.SysroleService;
import cn.sparrow.permission.service.UrlPermissionService;

@Service
public class SparrowService {

  private static Logger logger = LoggerFactory.getLogger(UrlPermissionService.class);
  
  @Autowired SysroleService sysroleService;
  @Autowired UrlPermissionService urlPermissionService;
  @Autowired ModelService modelService;
  
  @Autowired
  private ConfigurableApplicationContext appContext;
  
  public void init() {
	  
    urlPermissionService.init();
    logger.info("finish url init.");
    sysroleService.init();
    logger.info("finish sysrole init.");
    modelService.init();
    logger.info("finish model init.");

  }
}
