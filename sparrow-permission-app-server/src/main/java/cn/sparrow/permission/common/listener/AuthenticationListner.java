package cn.sparrow.permission.common.listener;

import org.springframework.context.event.EventListener;
import org.springframework.security.authentication.event.AbstractAuthenticationFailureEvent;
import org.springframework.security.authentication.event.AuthenticationSuccessEvent;
import org.springframework.stereotype.Component;

@Component
public class AuthenticationListner {

  @EventListener
  public void onSuccess(AuthenticationSuccessEvent success) {
    System.out.println(success);
    CurrentUser.INSTANCE.logIn(success.getAuthentication().getName());
  }

  @EventListener
  public void onFailure(AbstractAuthenticationFailureEvent failures) {
    System.out.println(failures);
  }
}
