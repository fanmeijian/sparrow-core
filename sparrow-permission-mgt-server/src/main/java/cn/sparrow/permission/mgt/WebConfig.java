package cn.sparrow.permission.mgt;

import java.text.SimpleDateFormat;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.hibernate5.Hibernate5Module;

@Configuration
public class WebConfig {
//	@Bean
//	public FilterRegistrationBean<CorsFilter> corsFilter() {
//		UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
//		CorsConfiguration config = new CorsConfiguration();
//		config.setAllowCredentials(true);
//		config.addAllowedOrigin("http://localhost:4200");
//		config.addAllowedOrigin("http://localhost:8091");
//		config.addAllowedOrigin("http://1.12.232.226:8091");
//		config.addAllowedHeader("*");
//		config.addAllowedMethod("*");
//		source.registerCorsConfiguration("/**", config);
//		FilterRegistrationBean<CorsFilter> bean = new FilterRegistrationBean<CorsFilter>(new CorsFilter(source));
//		bean.setOrder(0);
//		return bean;
//	}

	@Bean
	public WebMvcConfigurer corsConfigurer() {
		return new WebMvcConfigurer() {
			@Override
			public void addCorsMappings(CorsRegistry registry) {
				registry.addMapping("/**").allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS").allowedHeaders("*")
						.allowedOriginPatterns("http://localhost:8091","http://localhost:4200","http://1.12.232.226:8091").allowCredentials(true);
			}
		};
	}

	@Bean
	public MappingJackson2HttpMessageConverter mappingJackson2HttpMessageConverter() {
		MappingJackson2HttpMessageConverter converter = new MappingJackson2HttpMessageConverter();
		ObjectMapper mapper = converter.getObjectMapper();
		Hibernate5Module hibernate5Module = new Hibernate5Module();
		mapper.registerModule(hibernate5Module);
		mapper.setDateFormat(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"));
		return converter;
	}

	@Bean
	public ObjectMapper includeTransientObjectMapper() {
		Hibernate5Module hibernate5Module = new Hibernate5Module();
		hibernate5Module.disable(Hibernate5Module.Feature.USE_TRANSIENT_ANNOTATION);
		ObjectMapper mapper = new ObjectMapper();
		mapper.registerModule(hibernate5Module);
		return mapper;
	}
}
