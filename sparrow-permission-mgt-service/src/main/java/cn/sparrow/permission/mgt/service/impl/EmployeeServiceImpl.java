package cn.sparrow.permission.mgt.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

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
import cn.sparrow.permission.model.organization.OrganizationPositionLevelPK;
import cn.sparrow.permission.model.organization.OrganizationRolePK;
import cn.sparrow.permission.model.organization.PositionLevel;
import cn.sparrow.permission.model.organization.Role;
import cn.sparrow.permission.model.resource.SparrowTree;

@Service
public class EmployeeServiceImpl implements EmployeeService {

	@Autowired
	EmployeeRelationRepository employeeRelationRepository;
	@Autowired
	EmployeeRepository employeeRepository;
	@Autowired
	EmployeeOrganizationRoleRepository employeeOrganizationRoleRepository;
	@Autowired
	EmployeeOrganizationLevelRepository employeeOrganizationLevelRepository;

	@Override
	@Transactional
	public Employee update(String employeeId, Map<String, Object> map) {
		Employee source = employeeRepository.getById(employeeId);
		PatchUpdateHelper.merge(source, map);
		return employeeRepository.save(source);
	}

	@Override
	@Transactional
	public Employee create(Employee employee) {
		return employeeRepository.save(employee);
	}

	@Override
	@Transactional
	public void addParent(String employeeId, List<String> parentIds) {
		parentIds.forEach(f -> {
			employeeRelationRepository.save(new EmployeeRelation(employeeId, f));
		});
	}

	@Override
	@Transactional
	public void removeParent(String employeeId, List<String> parentIds) {
		parentIds.forEach(f -> {
			employeeRelationRepository.deleteById(new EmployeeRelationPK(employeeId, f));
		});
	}

	@Override
	@Transactional
	public void addRole(String employeeId, List<OrganizationRolePK> ids) {
		ids.forEach(f -> {
			employeeOrganizationRoleRepository.save(new EmployeeOrganizationRole(employeeId, f));
		});
	}

	@Override
	@Transactional
	public void removeRole(String employeeId, List<OrganizationRolePK> ids) {
		ids.forEach(f -> {
			employeeOrganizationRoleRepository.deleteById(new EmployeeOrganizationRolePK(f, employeeId));
		});
	}

	@Override
	@Transactional
	public void addLevel(String employeeId, List<OrganizationPositionLevelPK> ids) {
		ids.forEach(f -> {
			employeeOrganizationLevelRepository.save(new EmployeeOrganizationLevel(employeeId, f));
		});
	}

	@Override
	@Transactional
	public void removeLevel(String employeeId, List<OrganizationPositionLevelPK> ids) {
		ids.forEach(f -> {
			employeeOrganizationLevelRepository.deleteById(new EmployeeOrganizationLevelPK(f, employeeId));
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
			SparrowTree<Employee, String> myTree = new SparrowTree<Employee, String>(
					employeeRepository.findById(parentId).orElse(null));
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
		employeeRelationRepository.deleteByIdEmployeeIdInOrIdParentIdIn(ids, ids);
		employeeRepository.deleteByIdIn(ids);
	}

	@Override
	public List<Employee> getChildren(String employeeId) {
		List<Employee> employees = new ArrayList<Employee>();
		employeeRelationRepository.findByIdParentId(employeeId).forEach(f -> {
			f.getEmployee().setChildCount(this.getChildCount(f.getEmployee().getId()));
			employees.add(f.getEmployee());
		});
		return employees;
	}

	public long getChildCount(String parentId) {
		return employeeRelationRepository.countByIdParentId(parentId);
	}

	@Override
	public List<Employee> getParents(String employeeId) {
		List<Employee> employees = new ArrayList<Employee>();
		employeeRelationRepository.findByIdEmployeeId(employeeId).forEach(f -> {
			employees.add(f.getEmployee());
		});
		return employees;
	}

	@Override
	public List<PositionLevel> getLevels(String employeeId) {
		List<PositionLevel> positionLevels = new ArrayList<PositionLevel>();
		employeeOrganizationLevelRepository.findByIdEmployeeId(employeeId).forEach(f -> {
			positionLevels.add(f.getOrganizationLevel().getPositionLevel());
		});
		return positionLevels;
	}

	@Override
	public List<Role> getRoles(String employeeId) {
		List<Role> roles = new ArrayList<Role>();
		employeeOrganizationRoleRepository.findByIdEmployeeId(employeeId).forEach(f -> {
			roles.add(f.getOrganizationRole().getRole());
		});
		return roles;
	}

	@Override
	public SparrowTree<Employee, String> tree(String parentId) {
		return getTree(parentId);
	}

	@Override
	@Transactional
	public void delete(String[] ids) {
		employeeRepository.deleteByIdIn(ids);
	}

}
