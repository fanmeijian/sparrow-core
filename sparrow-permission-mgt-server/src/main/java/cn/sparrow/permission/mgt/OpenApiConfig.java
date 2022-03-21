package cn.sparrow.permission.mgt;

import java.util.ArrayList;
import java.util.List;

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
import io.swagger.v3.oas.models.tags.Tag;

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
		
//		List<Tag> tags = new ArrayList<Tag>();
//		for (int i = 0; i < 12; i++) {
//			tags.add(new Tag());
//		}
//		
//		tags.get(0).setName("sprtoken");//令牌管理服务
//		tags.get(0).setDescription("令牌管理服务");
//		tags.get(1).setName("auditlog");//审计日志服务
//		tags.get(1).setDescription("审计日志服务");
//		tags.get(2).setName("organization");//组织服务
//		tags.get(2).setDescription("组织服务");
//		tags.get(3).setName("role");//岗位服务
//		tags.get(3).setDescription("岗位服务");
//		tags.get(4).setName("joblevel");//职级服务
//		tags.get(4).setDescription("职级服务");
//		tags.get(5).setName("group");//群组服务
//		tags.get(5).setDescription("群组服务");
//		tags.get(6).setName("employee");//人员服务
//		tags.get(6).setDescription("人员服务");
//		tags.get(7).setName("sprapi");//接口服务
//		tags.get(7).setDescription("接口服务");
//		tags.get(8).setName("menu");//菜单服务
//		tags.get(8).setDescription("菜单服务");
//		tags.get(9).setName("opscope");//功能服务
//		tags.get(9).setDescription("功能服务");
//		tags.get(10).setName("sysrole");//角色服务
//		tags.get(10).setDescription("角色服务");
//		tags.get(11).setName("datamodel");//数据模型服务
//		tags.get(11).setDescription("数据模型服务");
		
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
