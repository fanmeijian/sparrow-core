package cn.sparrow.permission.mgt.common.configuration;

import org.springframework.boot.autoconfigure.security.oauth2.resource.OAuth2ResourceServerProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.jwt.NimbusJwtDecoder;


@Configuration
public class MyJwtDecoder {

	@Bean
	public JwtDecoder customDecoder(OAuth2ResourceServerProperties properties) {
	    NimbusJwtDecoder jwtDecoder = NimbusJwtDecoder.withJwkSetUri(
	      properties.getJwt().getJwkSetUri()).build();
	    
	    jwtDecoder.setClaimSetConverter(new UsernameSubClaimAdapter());
	    
	    return jwtDecoder;
	}

}
