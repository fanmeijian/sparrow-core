package cn.sparrow.permission.mgt.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.ExternalDocumentation;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import io.swagger.v3.oas.models.security.SecurityScheme.Type;

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
		SecurityScheme securityScheme = new SecurityScheme();
		securityScheme.setScheme("bearer");
		securityScheme.setType(Type.HTTP);
		securityScheme.setBearerFormat("JWT");
		
		OpenAPI openAPI = new OpenAPI()
				.components(new Components().addSecuritySchemes("bearerAuth",securityScheme ))
				.addSecurityItem(new SecurityRequirement().addList("bearerAuth"))
				.info(new Info().title("Sparrow Permission API")
						.description(
								"An integration API for enterprise organization management and permission management.")
						.version("v0.0.1").license(new License().name("Apache 2.0").url("https://github.com/fanmeijian/sparrow/blob/main/LICENSE")))
				.externalDocs(new ExternalDocumentation().description("Sparrow Permission Wiki Documentation")
						.url("https://github.com/fanmeijian/sparrow/wiki"));
//		openAPI.setTags(tags);
		return openAPI;
	}
}
