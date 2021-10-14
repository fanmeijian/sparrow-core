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
import cn.sparrow.common.service.EmployeeService;
import cn.sparrow.model.common.MyTree;
import cn.sparrow.model.organization.Employee;
import cn.sparrow.model.organization.EmployeeOrganizationLevelPK;
import cn.sparrow.model.organization.EmployeeOrganizationRolePK;
import cn.sparrow.model.organization.EmployeeRelationPK;
import cn.sparrow.model.organization.GroupRelationPK;
import cn.sparrow.organization.service.OrganizationService;

@RestController
public class EmployeeController {
  
  @Autowired OrganizationService organizationService;
  @Autowired EmployeeService employeeService;
  
  @GetMapping("/employees/getTreeByParentId")
  public MyTree<Employee> tree(@Nullable @RequestParam("parentId") String parentId){
    return employeeService.getTree(parentId);
  }
  
  @PostMapping("/employees/addRoles")
  public void addRoles(Set<EmployeeOrganizationRolePK> ids) {
    employeeService.addRoles(ids);
  }
  
  @DeleteMapping("/employees/delRoles")
  public void delRoles(Set<EmployeeOrganizationRolePK> ids) {
    employeeService.delRoles(ids);
  }
  
  @PostMapping("/employees/addLevels")
  public void addLevels(Set<EmployeeOrganizationLevelPK> ids) {
    employeeService.addLevels(ids);
  }
  
  @DeleteMapping("/employees/delLevels")
  public void delLevels(Set<EmployeeOrganizationLevelPK> ids) {
    employeeService.delLevels(ids);
  }
  
  @PostMapping("/employees/relations")
  public void addRelations(@NotNull @RequestBody Set<EmployeeRelationPK> ids) {
    employeeService.addRelations(ids);
  }
  
  @DeleteMapping("/employees/relations")
  public void delRelations(@NotNull @RequestBody Set<EmployeeRelationPK> ids) {
    employeeService.delRelations(ids);
  }
  
}
