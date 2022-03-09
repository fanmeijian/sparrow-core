package cn.sparrow.permission.mgt.service.repository;

import org.springframework.data.rest.core.config.RepositoryRestConfiguration;
import org.springframework.data.rest.core.mapping.ExposureConfiguration;
import org.springframework.data.rest.webmvc.config.RepositoryRestConfigurer;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;

import cn.sparrow.permission.model.resource.Menu;

@Component
public class RestRepositoryConfig implements RepositoryRestConfigurer {

	@Override
	public void configureRepositoryRestConfiguration(RepositoryRestConfiguration restConfig) {
		ExposureConfiguration config = restConfig.getExposureConfiguration().disablePutForCreation();
		config.forDomainType(Menu.class);
//		config.withCollectionExposure((metadata, httpMethods) -> httpMethods.disable(HttpMethod.GET));
		config.withItemExposure(
				(metadata, httpMethods) -> httpMethods.disable(HttpMethod.GET));

	}
}
