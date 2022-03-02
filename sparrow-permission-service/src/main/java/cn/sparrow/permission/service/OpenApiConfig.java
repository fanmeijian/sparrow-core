package cn.sparrow.permission.service;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import io.swagger.v3.oas.models.ExternalDocumentation;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;

/**
 * 
 * @author fansword
 * 用于设置api文档的说明配置
 *
 */
@Configuration
public class OpenApiConfig {

	/**
	 * 
	 * @return OpenApi
	 */
	@Bean
	public OpenAPI springShopOpenAPI() {
		return new OpenAPI()
				.info(new Info().title("Sparrow Permission API")
						.description(
								"An integration API for enterprise organization management and permission management.")
						.version("v0.0.1").license(new License().name("Apache 2.0").url("https://github.com/fanmeijian/sparrow/blob/main/LICENSE")))
				.externalDocs(new ExternalDocumentation().description("Sparrow Permission Wiki Documentation")
						.url("https://github.com/fanmeijian/sparrow/wiki"));
	}
}
