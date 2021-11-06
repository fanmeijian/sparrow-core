package cn.sparrow.organization.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import cn.sparrow.model.organization.EmployeeToken;
import cn.sparrow.organization.repository.EmployeeRepository;
import cn.sparrow.organization.repository.EmployeeTokenRepository;

@Service
public class EmployeeTokenServiceImpl implements EmployeeTokenService {

  @Autowired
  EmployeeTokenRepository employeeTokenRepository;

  @Autowired
  EmployeeRepository employeeRepository;

  @Override
  // build it from data base, use to get the latest token
  public EmployeeToken buildEmployeeToken(String username) {
    return null;

  }

  /**
   * get it from data base, the token store in database when user login.
   * 
   * @param username
   * @return
   */

  @Override
  public EmployeeToken getEmployeeToken(String username) {
    if (employeeRepository.findByUsername(username) != null)
      return employeeTokenRepository.findById(employeeRepository.findByUsername(username).getId())
          .get().getEmployeeToken();
    else
      return null;

  }
}
