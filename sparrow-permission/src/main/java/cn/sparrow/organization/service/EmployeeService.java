package cn.sparrow.organization.service;

import java.util.Set;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

	@Autowired
	EmployeeRelationRepository employeeRelationRepository;
	@Autowired
	EmployeeRepository employeeRepository;
	@Autowired
	EmployeeOrganizationRoleRepository employeeOrganizationRoleRepository;
	@Autowired
	EmployeeOrganizationLevelRepository employeeOrganizationLevelRepository;

	public Employee save(Employee employee) {
		Employee r = employeeRepository.save(employee);
		if(employee.getParentIds()!=null) {
			employee.getParentIds().forEach(f->{
				employeeRelationRepository.save(new EmployeeRelation(new EmployeeRelationPK(employee.getId(),f)));
	    	});
	    }
		return r;
	}

	public void addRelations(Set<EmployeeRelation> ids) {
		employeeRelationRepository.saveAll(ids);
	}

	public void delRelations(Set<EmployeeRelationPK> ids) {
		ids.forEach(f -> {
			employeeRelationRepository.deleteById(f);
		});
	}

	public void addRoles(Set<EmployeeOrganizationRole> ids) {
      employeeOrganizationRoleRepository.saveAll(ids);
	}

	public void delRoles(Set<EmployeeOrganizationRolePK> ids) {
		ids.forEach(f -> {
			employeeOrganizationRoleRepository.delete(new EmployeeOrganizationRole(f));
		});
	}

	public void addLevels(List<EmployeeOrganizationLevel> ids) {
		employeeOrganizationLevelRepository.saveAll(ids);
	}

	public void delLevels(Set<EmployeeOrganizationLevelPK> ids) {
		ids.forEach(f -> {
			employeeOrganizationLevelRepository.delete(new EmployeeOrganizationLevel(f));
		});
	}

	public MyTree<Employee> getTree(String parentId) {
		if (parentId == null) {
			MyTree<Employee> rootTree = new MyTree<Employee>(null);
			employeeRepository.findByRoot(true).forEach(f -> {
				MyTree<Employee> myTree = new MyTree<Employee>(f);
				buildTree(myTree);
				rootTree.getChildren().add(myTree);
			});

			return rootTree;
		} else {
			MyTree<Employee> myTree = new MyTree<Employee>(employeeRepository.findById(parentId).orElse(null));
			buildTree(myTree);
			return myTree;
		}
	}

	public void buildTree(MyTree<Employee> myTree) {
		employeeRelationRepository.findByIdParentId(myTree.getMe() == null ? null : myTree.getMe().getId())
				.forEach(f -> {
					MyTree<Employee> leaf = new MyTree<Employee>(f.getEmployee());
					// 防止死循环
					if (employeeRelationRepository
							.findById(new EmployeeRelationPK(f.getId().getParentId(), f.getId().getEmployeeId()))
							.orElse(null) == null)
						buildTree(leaf);
					myTree.getChildren().add(leaf);
				});
	}

	  @Transactional
	  public void delBatch(String[] ids) {
		  employeeRelationRepository.deleteByIdEmployeeIdInOrIdParentIdIn(ids,ids);
		  employeeRepository.deleteByIdIn(ids);
	  }

	public List<EmployeeRelation> getChildren(String parentId) {
		List<EmployeeRelation> employeeRelation = employeeRelationRepository.findByIdParentId(parentId);
		employeeRelation.forEach(f->{
			f.getEmployee().setChildCount(this.getChildCount(f.getEmployee().getId()));
		});
		return employeeRelation;
	}
	
	public long getChildCount(String parentId) {
		return employeeRelationRepository.countByIdParentId(parentId);
	}

	public List<EmployeeRelation> getParents(String employeeId) {
		return employeeRelationRepository.findByIdEmployeeId(employeeId);
	}
	
}
