package cn.sparrow.common.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;
import org.springframework.data.rest.core.mapping.ExposureConfiguration;

//@Configuration
//public class RestRepositoryGloalConfig {
//
//  @Autowired RepositoryRestConfiguration repositoryRestConfiguration;
//  
//  ExposureConfiguration config = repositoryRestConfiguration.getExposureConfiguration();
//  config.withItemExposure((metadata, httpMethods) -> httpMethods.disable(HttpMethod.PATCH));
//}
