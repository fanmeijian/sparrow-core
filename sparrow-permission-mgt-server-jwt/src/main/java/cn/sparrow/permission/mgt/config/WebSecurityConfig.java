package cn.sparrow.permission.mgt.config;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import cn.sparrow.permission.constant.ApiPermissionEnum;
import cn.sparrow.permission.mgt.api.ApiService;
import cn.sparrow.permission.mgt.service.repository.ApiRepository;
import cn.sparrow.permission.model.resource.SparrowApi;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	private JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint;

//	@Autowired
//	private UserDetailsService jwtUserDetailsService;

	@Autowired
	private JwtRequestFilter jwtRequestFilter;

	@Autowired
	ApiService apiService;
	
	@Autowired
	ApiRepository apiRepository;

//	@Autowired
//	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
//		// configure AuthenticationManager so that it knows from where to load
//		// user for matching credentials
//		// Use BCryptPasswordEncoder
//		auth.userDetailsService(jwtUserDetailsService).passwordEncoder(passwordEncoder());
//	}

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Bean
	@Override
	public AuthenticationManager authenticationManagerBean() throws Exception {
		return super.authenticationManagerBean();
	}

	@Override
	protected void configure(HttpSecurity httpSecurity) throws Exception {
		httpSecurity.headers().frameOptions().disable(); // use to control the h2 database console not blank
		httpSecurity.csrf().disable().authorizeRequests().antMatchers("/h2-console/**").permitAll();

		// We don't need CSRF for this example
		httpSecurity.csrf().disable()
				// dont authenticate this particular request
				.authorizeRequests().antMatchers("/authenticate", "/swagger-ui/**", "/v3/**").permitAll().
				// all other requests need to be authenticated
				anyRequest().authenticated().and().
				// make sure we use stateless session; session won't be used to
				// store user's state.
				exceptionHandling().authenticationEntryPoint(jwtAuthenticationEntryPoint).and().sessionManagement()
				.sessionCreationPolicy(SessionCreationPolicy.STATELESS);
//
//		SparrowApi exampleApi = new SparrowApi();
//		exampleApi.setClientId("sparrow");
//		exampleApi.setPermission(ApiPermissionEnum.DENY);
//		exampleApi.setModelName(null);
//
//		apiService.all(Pageable.unpaged(), exampleApi).forEach(url -> {
//			log.debug("初始化拒绝额访问资源:" + url.getId() + " " + url.getMethod() + " " + url.getName() + " " + url.getUri());
//
//			try {
//				httpSecurity.csrf().disable().authorizeRequests().antMatchers(url.getMethod().toString(), url.getUri())
//						.denyAll();
//			} catch (Exception e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
//		});
//
//		exampleApi.setPermission(ApiPermissionEnum.ANONYMOUS);
//		apiService.all(Pageable.unpaged(), exampleApi).forEach(url -> {
//			log.debug("初始化匿名访问资源:" + url.getId() + " " + url.getMethod() + " " + url.getName() + " " + url.getUri());
//			try {
//				httpSecurity.csrf().disable().authorizeRequests().antMatchers(url.getMethod().toString(), url.getUri())
//						.permitAll();
//			} catch (Exception e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
//		});
////
//		exampleApi.setPermission(ApiPermissionEnum.AUTHENTICATED);
//		apiService.all(Pageable.unpaged(), exampleApi).forEach(url -> {
//			try {
//				httpSecurity.csrf().disable().authorizeRequests((authorizeRequests) -> {
//					try {
//						authorizeRequests
//								.antMatchers(url.getMethod().toString(), url.getUri()).authenticated().and().
//						// make sure we use stateless session; session won't be used to
//						// store user's state.
//								exceptionHandling().authenticationEntryPoint(jwtAuthenticationEntryPoint).and()
//								.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
//					} catch (Exception e) {
//						// TODO Auto-generated catch block
//						e.printStackTrace();
//					}
//				});
//			} catch (Exception e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
//			log.debug("初始化认证访问资源:" + url.getId() + " " + url.getMethod() + " " + url.getName() + " " + url.getUri());
//		});
////
//		exampleApi.setPermission(ApiPermissionEnum.RESTRICT);
//		List<SparrowApi> apis= apiRepository.findAll();
//		log.info("{}",apis.size());
//		apiService.all(Pageable.unpaged(), exampleApi).forEach(url -> {
//			log.debug("初始化受限资源: {} {} {} {} {}", url.getId(), url.getMethod(), url.getName(), url.getUri(), "");
//			try {
//				httpSecurity.csrf().disable().authorizeRequests((authorizeRequests) -> {
//					try {
//						authorizeRequests
//								.antMatchers(url.getMethod().toString(), url.getUri()).hasAnyRole("SYSADMIN").and().
//						// make sure we use stateless session; session won't be used to
//						// store user's state.
//								exceptionHandling().authenticationEntryPoint(jwtAuthenticationEntryPoint).and()
//								.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
//					} catch (Exception e) {
//						// TODO Auto-generated catch block
//						e.printStackTrace();
//					}
//				});
//			} catch (Exception e) {
//				e.printStackTrace();
//			}
//		});

		// Add a filter to validate the tokens with every request
		httpSecurity.addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class);
	}
}
