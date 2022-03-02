package cn.sparrow.permission.authorization.server;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerSecurityConfiguration;

@Configuration
class JwkSetEndpointConfiguration extends AuthorizationServerSecurityConfiguration {
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        super.configure(http);
        http
        .requestMatchers()
        .mvcMatchers("/.well-known/jwks.json","/oauth/token","/userinfo")
        .and()
        .authorizeRequests()
        .mvcMatchers("/.well-known/jwks.json","/oauth/token","/userinfo").permitAll();
    }
}
