package cn.sparrow.common.service;

import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import cn.sparrow.model.common.MyTree;
import cn.sparrow.model.organization.Employee;
import cn.sparrow.model.organization.EmployeeOrganizationLevel;
import cn.sparrow.model.organization.EmployeeOrganizationLevelPK;
import cn.sparrow.model.organization.EmployeeOrganizationRole;
import cn.sparrow.model.organization.EmployeeOrganizationRolePK;
import cn.sparrow.model.organization.EmployeeRelation;
import cn.sparrow.model.organization.EmployeeRelationPK;
import cn.sparrow.organization.repository.EmployeeOrganizationLevelRepository;
import cn.sparrow.organization.repository.EmployeeOrganizationRoleRepository;
import cn.sparrow.organization.repository.EmployeeRelationRepository;
import cn.sparrow.organization.repository.EmployeeRepository;

@Service
public class EmployeeService {
  
  @Autowired EmployeeRelationRepository employeeRelationRepository;
  @Autowired EmployeeRepository employeeRepository;
  @Autowired EmployeeOrganizationRoleRepository employeeOrganizationRoleRepository;
  @Autowired EmployeeOrganizationLevelRepository employeeOrganizationLevelRepository;

  
  public void addRelations(Set<EmployeeRelationPK> ids) {
    ids.forEach(f -> {
      employeeRelationRepository.save(new EmployeeRelation(f));
    });
  }

  public void delRelations(Set<EmployeeRelationPK> ids) {
    ids.forEach(f -> {
      employeeRelationRepository.deleteById(f);
    });
  }
  
  public void addRoles(Set<EmployeeOrganizationRolePK> ids) {
    ids.forEach(f->{
      employeeOrganizationRoleRepository.save(new EmployeeOrganizationRole(f));
    });
  }

  public void delRoles(Set<EmployeeOrganizationRolePK> ids) {
    ids.forEach(f->{
      employeeOrganizationRoleRepository.delete(new EmployeeOrganizationRole(f));
    });
  }
  
  public void addLevels(Set<EmployeeOrganizationLevelPK> ids) {
    ids.forEach(f->{
      employeeOrganizationLevelRepository.save(new EmployeeOrganizationLevel(f));
    });
  }

  public void delLevels(Set<EmployeeOrganizationLevelPK> ids) {
    ids.forEach(f->{
      employeeOrganizationLevelRepository.delete(new EmployeeOrganizationLevel(f));
    });
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
