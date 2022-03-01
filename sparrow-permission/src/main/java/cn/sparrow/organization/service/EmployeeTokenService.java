package cn.sparrow.organization.service;

import org.springframework.stereotype.Service;

@Service
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
