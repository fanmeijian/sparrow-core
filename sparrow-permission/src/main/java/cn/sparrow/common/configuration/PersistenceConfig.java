package cn.sparrow.common.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.transaction.annotation.EnableTransactionManagement;


/**
 * 
 * @author fansword
 *
 */
@Configuration
@EnableTransactionManagement
@EnableJpaAuditing(auditorAwareRef = "auditorProvider")
public class PersistenceConfig {
	/**
	 * 
	 * @return AuditorAware<String>
	 */
	@Bean
	AuditorAware<String> auditorProvider() {
		return new AuditorAwareImpl();
	}
}
