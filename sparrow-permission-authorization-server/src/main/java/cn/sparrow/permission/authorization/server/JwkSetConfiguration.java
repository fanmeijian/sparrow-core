package cn.sparrow.permission.authorization.server;

import java.security.KeyPair;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerEndpointsConfiguration;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;

@Import(AuthorizationServerEndpointsConfiguration.class)
@Configuration
public class JwkSetConfiguration extends AuthorizationServerConfigurerAdapter {

//	@Autowired
    AuthenticationManager authenticationManager;
    KeyPair keyPair;
    PasswordEncoder passwordEncoder;
    UserDetailsService userDetailsService;
    MyClientDetailsService clientDetailsService;

    public JwkSetConfiguration(AuthenticationConfiguration authenticationConfiguration, KeyPair keyPair, PasswordEncoder passwordEncoder, UserDetailsService userDetailsService, MyClientDetailsService clientDetailsService) throws Exception {
        this.authenticationManager = authenticationConfiguration.getAuthenticationManager();
        this.keyPair = keyPair;
        this.passwordEncoder = passwordEncoder;
        this.userDetailsService = userDetailsService;
        this.clientDetailsService = clientDetailsService;
    }

    @Override
    public void configure(AuthorizationServerSecurityConfigurer security) {
        security
          .tokenKeyAccess("permitAll()")
          .checkTokenAccess("isAuthenticated()")
          .allowFormAuthenticationForClients() //Adds form capability
        ;
    }

    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
        clients.withClientDetails(clientDetailsService);    
    }

    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) {
        endpoints.authenticationManager(this.authenticationManager)
            .accessTokenConverter(accessTokenConverter())
            .userDetailsService(userDetailsService)
            .tokenStore(tokenStore());
    }

    @Bean
    public TokenStore tokenStore() {
        return new JwtTokenStore(accessTokenConverter());
    }

    @Bean
    public JwtAccessTokenConverter accessTokenConverter() {
        JwtAccessTokenConverter converter = new JwtAccessTokenConverter();
        converter.setKeyPair(this.keyPair);
        return converter;
    }
}
