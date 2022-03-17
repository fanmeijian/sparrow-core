package cn.sparrow.permission.mgt;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationConverter;
import org.springframework.security.oauth2.server.resource.authentication.JwtGrantedAuthoritiesConverter;

import cn.sparrow.permission.constant.ApiPermissionEnum;
import cn.sparrow.permission.mgt.api.ApiService;
import cn.sparrow.permission.mgt.service.impl.UrlPermissionService;
import cn.sparrow.permission.model.resource.SparrowApi;

@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

//	@Autowired
//	UrlPermissionService urlPermissionService;

//	@Autowired
//	SparrowService sparrowService;
	
	@Autowired
	ApiService apiService;

	@Autowired
	private ConfigurableApplicationContext appContext;

	@Value("${spring.application.name}")
	String clientId;

	private static Logger logger = LoggerFactory.getLogger(SecurityConfig.class);

	// 用于解决默认只会获取scope的权限，而用户的实际权限在authorities里面
	private JwtAuthenticationConverter jwtAuthenticationConverter() {
		JwtGrantedAuthoritiesConverter jwtGrantedAuthoritiesConverter = new JwtGrantedAuthoritiesConverter();
		jwtGrantedAuthoritiesConverter.setAuthoritiesClaimName("authorities");
		jwtGrantedAuthoritiesConverter.setAuthorityPrefix("ROLE_");
		JwtAuthenticationConverter jwtAuthenticationConverter = new JwtAuthenticationConverter();
		jwtAuthenticationConverter.setJwtGrantedAuthoritiesConverter(jwtGrantedAuthoritiesConverter);
		return jwtAuthenticationConverter;
	}

	/***
	 * 从authority表加载配置的功能权限，将功能权限的id作为spring security的角色名
	 */
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		// get the permission from db

//		sparrowService.init(appContext);

//		 http.csrf().disable().authorizeRequests().anyRequest().permitAll();
		
		SparrowApi exampleApi = new SparrowApi();
		exampleApi.setClientId(clientId);
		exampleApi.setPermission(ApiPermissionEnum.DENY);
		exampleApi.setModelName(null); 
		http.headers().frameOptions().disable(); // use to control the h2 database console not blank
		http.csrf().disable().authorizeRequests().antMatchers("/h2-console/**").permitAll();
		apiService.all(Pageable.unpaged(), exampleApi).forEach(url -> {
			logger.debug(
					"初始化拒绝额访问资源:" + url.getId() + " " + url.getMethod() + " " + url.getName() + " " + url.getUri());

			try {
				http.csrf().disable().authorizeRequests().antMatchers(url.getMethod().toString(), url.getUri()).denyAll();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		});
		
		exampleApi.setPermission(ApiPermissionEnum.ANONYMOUS);
		apiService.all(Pageable.unpaged(), exampleApi).forEach(url -> {
			logger.debug("初始化匿名访问资源:" + url.getId() + " " + url.getMethod() + " " + url.getName() + " " + url.getUri());
			try {
				http.csrf().disable().authorizeRequests().antMatchers(url.getMethod().toString(), url.getUri()).permitAll();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		});
//
		exampleApi.setPermission(ApiPermissionEnum.AUTHENTICATED);
		apiService.all(Pageable.unpaged(), exampleApi).forEach(url -> {
			try {
			  http.csrf().disable()
              .authorizeRequests(
                      (authorizeRequests) -> authorizeRequests.antMatchers(url.getMethod().toString(), url.getUri()).authenticated())
              .oauth2ResourceServer().jwt().jwtAuthenticationConverter(jwtAuthenticationConverter());
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			logger.debug("初始化认证访问资源:" + url.getId() + " " + url.getMethod() + " " + url.getName() + " " + url.getUri());
		});
//
		exampleApi.setPermission(ApiPermissionEnum.RESTRICT);
		apiService.all(Pageable.unpaged(), exampleApi).forEach(url -> {
			logger.debug("初始化受限资源: {} {} {} {} {}", url.getId(), url.getMethod(), url.getName(), url.getUri(),
					apiService.getPermissions(url.getId(), Pageable.unpaged()).toList());
			try {
				http.csrf().disable()
						.authorizeRequests(
								(authorizeRequests) -> authorizeRequests.antMatchers(url.getMethod().toString(), url.getUri())
										.hasAnyRole("SYSADMIN"))
						.oauth2ResourceServer().jwt().jwtAuthenticationConverter(jwtAuthenticationConverter());
			} catch (Exception e) {
				e.printStackTrace();
			}
		});
	}

	@Override
	public void configure(WebSecurity web) throws Exception {
		// Allow OPTIONS calls to be accessed without authentication
		web.ignoring().antMatchers(HttpMethod.OPTIONS, "/**");
	}

}
