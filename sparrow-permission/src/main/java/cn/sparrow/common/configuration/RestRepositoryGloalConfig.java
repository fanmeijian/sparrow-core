package cn.sparrow.common.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;

//@Configuration
public class RestRepositoryGloalConfig {

  @Autowired RepositoryRestConfiguration repositoryRestConfiguration;
  
//  ExposureConfiguration config = repositoryRestConfiguration.getExposureConfiguration();
//
//  config.forDomainType(User.class).disablePutForCreation(); 
//  config.withItemExposure((metadata, httpMethods) -> httpMethods.disable(HttpMethod.PATCH));
}
