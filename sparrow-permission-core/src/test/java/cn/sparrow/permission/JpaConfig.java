package cn.sparrow.permission;

import java.util.Optional;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;

@Configuration
@EnableJpaAuditing(auditorAwareRef = "auditorAware")
public class JpaConfig {
	
//	@Autowired
//    private ConfigurableListableBeanFactory beanFactory;
//
//
//	protected JpaConfig(DataSource dataSource, JpaProperties properties,
//			ObjectProvider<JtaTransactionManager> jtaTransactionManager) {
//		super(dataSource, properties, jtaTransactionManager);
//	}
//
//	@Override
//	protected AbstractJpaVendorAdapter createJpaVendorAdapter() {
//		return new HibernateJpaVendorAdapter();
//	}
//
//	@Override
//	protected Map<String, Object> getVendorProperties() {
//		Map<String, Object> props = new HashMap<>();
//
//        // configure use of SpringBeanContainer
//        props.put(org.hibernate.cfg.AvailableSettings.BEAN_CONTAINER, 
//            new SpringBeanContainer(beanFactory));
//        return props;
//	}
	
	@Bean
    public AuditorAware<String> auditorAware() {
        return () -> Optional.of(((User)     SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getUsername());
    }

}
