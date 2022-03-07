package cn.sparrow.permission.mgt.common.validation;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.rest.core.event.ValidatingRepositoryEventListener;
import org.springframework.data.rest.webmvc.config.RepositoryRestConfigurer;
import org.springframework.validation.Validator;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;

@Configuration
public class MySpringDataRestValidationConfiguration implements RepositoryRestConfigurer {

  @Bean
  @Primary
  /**
   * Create a validator to use in bean validation - primary to be able to autowire without qualifier
   */
  Validator validator() {
    return new LocalValidatorFactoryBean();
  }

//  @Autowired
//  ModelAuthorPermissionValidator modelAuthorPermissionValidator;
//  @Autowired
//  ModelEditorPermissionValidator modelEditorPermissionValidator;
//  @Autowired
//  ModelDeleterPermissionValidator modelDeleterPermissionValidator;

  @Override
  public void configureValidatingRepositoryEventListener(ValidatingRepositoryEventListener v) {
//    v.addValidator("beforeCreate", modelAuthorPermissionValidator);
//    v.addValidator("beforeSave", modelEditorPermissionValidator);
//    v.addValidator("beforeDelete", modelDeleterPermissionValidator);
  }
}
