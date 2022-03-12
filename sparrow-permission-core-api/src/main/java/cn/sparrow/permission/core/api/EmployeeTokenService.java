package cn.sparrow.permission.core.api;

import cn.sparrow.permission.model.token.EmployeeToken;

public interface EmployeeTokenService {

  // build it from data base, use to get the latest token
  public EmployeeToken buildEmployeeTokenWithUsername(String username);
  public EmployeeToken buildEmployeeTokenWithEmployeeId(String employeeId);
  /**
   * get it from data base, the token store in database when user login.
   * 
   * @param username
   * @return
   */

  public EmployeeToken getEmployeeTokenByUsername(String username);
  public EmployeeToken getEmployeeTokenByEmployeeId(String employeeId);
}
