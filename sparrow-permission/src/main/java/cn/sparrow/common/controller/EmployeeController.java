package cn.sparrow.common.controller;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.Nullable;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.sun.istack.NotNull;

import cn.sparrow.model.common.MyTree;
import cn.sparrow.model.organization.Employee;
import cn.sparrow.model.organization.EmployeeOrganizationLevelPK;
import cn.sparrow.model.organization.EmployeeOrganizationRolePK;
import cn.sparrow.model.organization.EmployeeRelationPK;
import cn.sparrow.organization.service.EmployeeService;
import cn.sparrow.organization.service.OrganizationService;

/**
 * 
 * @author fansword
 *
 */

@RestController
public class EmployeeController {
  
  @Autowired OrganizationService organizationService;
  @Autowired EmployeeService employeeService;
  
  @GetMapping("/employees/getTreeByParentId")
  public MyTree<Employee> tree(@Nullable @RequestParam("parentId") String parentId){
    return employeeService.getTree(parentId);
  }
  
  @PostMapping("/employees/roles/batch")
  public void addRoles(@NotNull @RequestBody Set<EmployeeOrganizationRolePK> ids) {
    employeeService.addRoles(ids);
  }
  
  @DeleteMapping("/employees/roles/batch")
  public void delRoles(@NotNull @RequestBody Set<EmployeeOrganizationRolePK> ids) {
    employeeService.delRoles(ids);
  }
  
  /**
   * 	
   * @param ids
   */
  @PostMapping("/employees/levels/batch")
  public void addLevels(@NotNull @RequestBody Set<EmployeeOrganizationLevelPK> ids) {
    employeeService.addLevels(ids);
  }
  
  @DeleteMapping("/employees/levels/batch")
  public void delLevels(@NotNull @RequestBody Set<EmployeeOrganizationLevelPK> ids) {
    employeeService.delLevels(ids);
  }
  
  /**
   * 
   * @param ids
   */
  @PostMapping("/employees/relations/batch")
  public void addRelations(@NotNull @RequestBody Set<EmployeeRelationPK> ids) {
    employeeService.addRelations(ids);
  }
  
  @DeleteMapping("/employees/relations/batch")
  public void delRelations(@NotNull @RequestBody Set<EmployeeRelationPK> ids) {
    employeeService.delRelations(ids);
  }
  
}
