package cn.sparrow.common.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SparrowService {

  private static Logger logger = LoggerFactory.getLogger(UrlPermissionService.class);
  
  @Autowired SysroleService sysroleService;
  @Autowired UrlPermissionService urlPermissionService;
  @Autowired ModelService modelService;
  
  public void init() {
    
    urlPermissionService.init();
    logger.info("finish url init.");
    sysroleService.init();
    logger.info("finish sysrole init.");
    modelService.init();
    logger.info("finish model init.");

  }
}
