package cn.sparrow.organization.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import cn.sparrow.model.organization.Employee;
import cn.sparrow.model.organization.EmployeeToken;
import cn.sparrow.model.organization.EmployeeUser;
import cn.sparrow.organization.repository.EmployeeRepository;
import cn.sparrow.organization.repository.EmployeeTokenRepository;
import cn.sparrow.organization.repository.EmployeeUserRepository;

@Service
public class EmployeeTokenServiceImpl implements EmployeeTokenService {

  @Autowired
  EmployeeTokenRepository employeeTokenRepository;

  @Autowired
  EmployeeRepository employeeRepository;
  
  @Autowired
  EmployeeUserRepository employeeUserRepository;

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
    EmployeeUser employeeUser = employeeUserRepository.findOneByIdUsername(username);
    if (employeeUser != null)
      return employeeTokenRepository.findById(employeeUser.getEmployee().getId())
          .get().getEmployeeToken();
    else
      return null;

  }
}
