package cn.sparrow.permission.mgt.service.impl;

import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.sparrow.permission.mgt.api.EmployeeService;
import cn.sparrow.permission.mgt.service.repository.EmployeeOrganizationLevelRepository;
import cn.sparrow.permission.mgt.service.repository.EmployeeOrganizationRoleRepository;
import cn.sparrow.permission.mgt.service.repository.EmployeeRelationRepository;
import cn.sparrow.permission.mgt.service.repository.EmployeeRepository;
import cn.sparrow.permission.model.organization.Employee;
import cn.sparrow.permission.model.organization.EmployeeOrganizationLevel;
import cn.sparrow.permission.model.organization.EmployeeOrganizationLevelPK;
import cn.sparrow.permission.model.organization.EmployeeOrganizationRole;
import cn.sparrow.permission.model.organization.EmployeeOrganizationRolePK;
import cn.sparrow.permission.model.organization.EmployeeRelation;
import cn.sparrow.permission.model.organization.EmployeeRelationPK;
import cn.sparrow.permission.model.resource.SparrowTree;

@Service
public class EmployeeServiceImpl implements EmployeeService{

	@Autowired
	EmployeeRelationRepository employeeRelationRepository;
	@Autowired
	EmployeeRepository employeeRepository;
	@Autowired
	EmployeeOrganizationRoleRepository employeeOrganizationRoleRepository;
	@Autowired
	EmployeeOrganizationLevelRepository employeeOrganizationLevelRepository;

	@Transactional
	public void saveMasterOrganization(String employeeId, String organizationId) {
		Employee employee = employeeRepository.getOne(employeeId);
		employee.setOrganizationId(organizationId);
		employeeRepository.save(employee);
	}
	
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

	public SparrowTree<Employee, String> getTree(String parentId) {
		if (parentId == null) {
			SparrowTree<Employee, String> rootTree = new SparrowTree<Employee, String>(null);
			employeeRepository.findByIsRoot(true).forEach(f -> {
				SparrowTree<Employee, String> myTree = new SparrowTree<Employee, String>(f);
				buildTree(myTree);
				rootTree.getChildren().add(myTree);
			});

			return rootTree;
		} else {
			SparrowTree<Employee, String> myTree = new SparrowTree<Employee, String>(employeeRepository.findById(parentId).orElse(null));
			buildTree(myTree);
			return myTree;
		}
	}

	public void buildTree(SparrowTree<Employee, String> myTree) {
		employeeRelationRepository.findByIdParentId(myTree.getMe() == null ? null : myTree.getMe().getId())
				.forEach(f -> {
					SparrowTree<Employee, String> leaf = new SparrowTree<Employee, String>(f.getEmployee());
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

	public List<EmployeeOrganizationLevel> getLevels(String employeeId) {
		return employeeOrganizationLevelRepository.findByIdEmployeeId(employeeId);
	}

	public List<EmployeeOrganizationRole> getRoles(String employeeId) {
		return employeeOrganizationRoleRepository.findByIdEmployeeId(employeeId);
	}

	@Override
	public void updateOrganization(String employeeId, String organizationId) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public SparrowTree<Employee, String> tree(String parentId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void add(List<Employee> employees) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void update(List<Employee> employees) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(String[] ids) {
		// TODO Auto-generated method stub
		
	}
	
}
