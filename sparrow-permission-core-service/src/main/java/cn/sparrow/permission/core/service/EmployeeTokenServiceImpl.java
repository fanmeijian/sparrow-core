package cn.sparrow.permission.core.service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;

import cn.sparrow.permission.core.api.EmployeeTokenService;
import cn.sparrow.permission.model.group.GroupEmployee;
import cn.sparrow.permission.model.group.GroupOrgJobLevel;
import cn.sparrow.permission.model.group.GroupOrgRole;
import cn.sparrow.permission.model.group.GroupOrganization;
import cn.sparrow.permission.model.group.GroupPositionLevel;
import cn.sparrow.permission.model.group.GroupRole;
import cn.sparrow.permission.model.group.GroupSysrole;
import cn.sparrow.permission.model.group.GroupUser;
import cn.sparrow.permission.model.organization.Employee;
import cn.sparrow.permission.model.organization.EmployeeOrganizationLevel;
import cn.sparrow.permission.model.organization.EmployeeOrganizationRole;
import cn.sparrow.permission.model.organization.OrganizationPositionLevelPK;
import cn.sparrow.permission.model.organization.OrganizationRolePK;
import cn.sparrow.permission.model.resource.UserSysrole;
import cn.sparrow.permission.model.token.EmployeeToken;
import cn.sparrow.permission.model.token.SparrowEmployeeToken;

public class EmployeeTokenServiceImpl implements EmployeeTokenService {
	private EntityManager entityManager;

	@Override
	// build it from data base, use to get the latest token
	public EmployeeToken buildEmployeeTokenWithUsername(String username) {
		Employee employee = entityManager.createNamedQuery("Employee.findByUsername", Employee.class)
				.setParameter("username", username).getSingleResult();
		return buildEmployeeTokenWithEmployeeId(employee.getId());
	}

	/**
	 * get it from data base, the token store in database when user login.
	 * 
	 * @param username
	 * @return
	 */

	@Override
	public EmployeeToken getEmployeeTokenByUsername(String username) {
		try {
			Employee employee = entityManager.createNamedQuery("Employee.findByUsername", Employee.class)
					.setParameter("username", username).getSingleResult();
			SparrowEmployeeToken sparrowEmployeeToken = entityManager.find(SparrowEmployeeToken.class,
					employee.getId());
			if (sparrowEmployeeToken != null && sparrowEmployeeToken.getEmployeeToken() != null)
				return sparrowEmployeeToken.getEmployeeToken();
			else {
				return buildEmployeeTokenWithUsername(username);
			}
		} catch (NoResultException e) {
			return null;
		}
	}

	public EmployeeTokenServiceImpl(EntityManager entityManager) {
		this.entityManager = entityManager;
	}

	public EmployeeTokenServiceImpl() {
	}

	@Override
	public EmployeeToken getEmployeeTokenByEmployeeId(String employeeId) {
		SparrowEmployeeToken sparrowEmployeeToken = entityManager.find(SparrowEmployeeToken.class, employeeId);
		if (sparrowEmployeeToken != null && sparrowEmployeeToken.getEmployeeToken() != null)
			return sparrowEmployeeToken.getEmployeeToken();
		else {
			return buildEmployeeTokenWithEmployeeId(employeeId);
		}
	}

	@Override
	public EmployeeToken buildEmployeeTokenWithEmployeeId(String employeeId) {

		Employee employee = entityManager.find(Employee.class, employeeId);
		List<String> sysroles = new ArrayList<String>();
		Set<String> sysroleGroups = new HashSet<>();
		List<String> organizations = new ArrayList<String>();
		List<OrganizationRolePK> orgRoleIds = new ArrayList<OrganizationRolePK>();
		List<OrganizationPositionLevelPK> orgJobLevelIds = new ArrayList<OrganizationPositionLevelPK>();
		List<String> empGroups = new ArrayList<String>();
		List<String> userGroups = new ArrayList<>();

		if (employee.getUsername() != null) {
			// 员工所在的用户组
			entityManager.createNamedQuery("GroupUser.findByUsername", GroupUser.class)
					.setParameter("username", employee.getUsername()).getResultList().forEach(groupUser -> {
						userGroups.add(groupUser.getId().getGroupId());
					});

			// 员工拥有的角色
			entityManager.createNamedQuery("UserSysrole.findByUsername", UserSysrole.class)
					.setParameter("username", employee.getUsername()).getResultList().forEach(us -> {
						sysroles.add(us.getId().getSysroleId());
						// 角色所在的组

						entityManager.createNamedQuery("GroupSysrole.findBySysroleId", GroupSysrole.class)
								.setParameter("sysroleId", us.getId().getSysroleId()).getResultList()
								.forEach(groupSysrole -> {
									sysroleGroups.add(groupSysrole.getId().getGroupId());
								});
					});
		}

		// 员工所在组织列表
		organizations.add(employee.getOrganizationId());

		// 员工担任的岗位列表
		entityManager.createNamedQuery("EmployeeOrganizationRole.findByEmployeeId", EmployeeOrganizationRole.class)
				.setParameter("employeeId", employeeId).getResultList().forEach(f -> {
					orgRoleIds.add(f.getId().getOrganizationRoleId());
					organizations.add(f.getId().getOrganizationRoleId().getOrganizationId());
				});

		// 员工的级别
		entityManager.createNamedQuery("EmployeeOrganizationLevel.findByEmployeeId", EmployeeOrganizationLevel.class)
				.setParameter("employeeId", employeeId).getResultList().forEach(f -> {
					orgJobLevelIds.add(f.getId().getOrganizationLevelId());
//			organizations.add(f.getId().getOrganizationLevelId().getOrganizationId());
				});

		// 员工所在的组
		entityManager.createNamedQuery("GroupEmployee.findByEmployeeId", GroupEmployee.class)
				.setParameter("employeeId", employeeId).getResultList().forEach(f -> {
					empGroups.add(f.getId().getGroupId());
				});

		EmployeeToken employeeToken = new EmployeeToken();
		employeeToken.setUsername(employee.getUsername());
		employeeToken.setUserGroups(userGroups);
		employeeToken.setSysroles(sysroles);
		employeeToken.setSysroleGroups(sysroleGroups);

		employeeToken.setEmployeeId(employeeId);
		employeeToken.setEmpGroups(empGroups);
		employeeToken.setOrganizationIds(organizations);
		employeeToken.setOrgRoleIds(orgRoleIds);
		employeeToken.setOrgJobLevelIds(orgJobLevelIds);

		// 初始化组
		Set<String> orgGroups = new HashSet<String>();
		Set<String> roleGroups = new HashSet<String>();
		Set<String> jobLevelGroups = new HashSet<String>();

		organizations.forEach(orgId -> {
			// 获取用户所在组织的归属所有组
			entityManager.createNamedQuery("GroupOrganization.findByOrgId", GroupOrganization.class)
					.setParameter("orgId", orgId).getResultList().forEach(f -> {
						orgGroups.add(f.getId().getGroupId());
					});
		});

		orgRoleIds.forEach(orgRoleId -> {
			// 获取用户所在组织的归属所有组
			entityManager.createNamedQuery("GroupOrgRole.findByOrgRoleId", GroupOrgRole.class)
					.setParameter("roleId", orgRoleId.getRoleId()).setParameter("orgId", orgRoleId.getOrganizationId())
					.getResultList().forEach(f -> {
						roleGroups.add(f.getId().getGroupId());

						// 再检查是否有对不分组织的岗位进行的分组
						entityManager.createNamedQuery("GroupRole.findByRoleId", GroupRole.class)
								.setParameter("roleId", orgRoleId.getRoleId()).getResultList().forEach(groupRole -> {
									roleGroups.add(groupRole.getId().getGroupId());
								});
					});
		});

		orgJobLevelIds.forEach(orgJobLevelId -> {
			// 获取用户所在工作级别的归属所有组
			entityManager.createNamedQuery("GroupOrgJobLevel.findByOrgJobLevelId", GroupOrgJobLevel.class)
					.setParameter("jobLevelId", orgJobLevelId.getPositionLevelId())
					.setParameter("orgId", orgJobLevelId.getOrganizationId()).getResultList().forEach(f -> {
						jobLevelGroups.add(f.getId().getGroupId());

						// 再检查是否有对不分组织的工作级别进行的分组
						entityManager.createNamedQuery("GroupPositionLevel.findByJobLevelId", GroupPositionLevel.class)
								.setParameter("roleId", orgJobLevelId.getPositionLevelId()).getResultList()
								.forEach(groupJobLevel -> {
									jobLevelGroups.add(groupJobLevel.getId().getGroupId());
								});
					});
		});

		employeeToken.setOrgGroups(orgGroups);
		employeeToken.setRoleGroups(roleGroups);
		employeeToken.setJobLevelGroups(jobLevelGroups);

		return employeeToken;
	}

}
