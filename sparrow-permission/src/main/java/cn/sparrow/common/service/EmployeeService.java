package cn.sparrow.common.service;

import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import cn.sparrow.model.common.MyTree;
import cn.sparrow.model.organization.Employee;
import cn.sparrow.model.organization.EmployeeOrganizationLevelPK;
import cn.sparrow.model.organization.EmployeeOrganizationRolePK;
import cn.sparrow.model.organization.EmployeeRelationPK;
import cn.sparrow.model.organization.Organization;
import cn.sparrow.model.organization.OrganizationRelationPK;
import cn.sparrow.organization.repository.EmployeeRelationRepository;
import cn.sparrow.organization.repository.EmployeeRepository;

@Service
public class EmployeeService {
  
  @Autowired EmployeeRelationRepository employeeRelationRepository;
  @Autowired EmployeeRepository employeeRepository;

  public void addRoles(Set<EmployeeOrganizationRolePK> ids) {

  }

  public void delRoles(Set<EmployeeOrganizationRolePK> ids) {

  }
  
  public void addLevels(Set<EmployeeOrganizationLevelPK> ids) {

  }

  public void delLevels(Set<EmployeeOrganizationLevelPK> ids) {

  }
  
  public MyTree<Employee> getTree(String parentId) {
    MyTree<Employee> myTree = new MyTree<Employee>(parentId==null?null:employeeRepository.findById(parentId).orElse(null));
    buildTree(myTree);
    return myTree;
  }

  public void buildTree(MyTree<Employee> myTree) {
    employeeRelationRepository.findByIdParentId(myTree.getMe()==null?null:myTree.getMe().getId()).forEach(f -> {
      MyTree<Employee> leaf = new MyTree<Employee>(f.getEmployee());
      // 防止死循环
      if(employeeRelationRepository.findById(new EmployeeRelationPK(f.getId().getParentId(), f.getId().getUsername())).orElse(null)==null)
        buildTree(leaf);
      myTree.getChildren().add(leaf);
    });
  }
}
