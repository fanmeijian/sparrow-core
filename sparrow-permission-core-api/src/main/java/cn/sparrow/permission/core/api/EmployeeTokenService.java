package cn.sparrow.permission.core.api;

import cn.sparrow.permission.model.token.EmployeeToken;

public interface EmployeeTokenService {

  // build it from data base, use to get the latest token
  public EmployeeToken buildEmployeeToken(String username);
  /**
   * get it from data base, the token store in database when user login.
   * 
   * @param username
   * @return
   */

  public EmployeeToken getEmployeeToken(String username);
}
