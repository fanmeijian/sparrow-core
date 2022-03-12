package cn.sparrow.permission.mgt.service;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.data.domain.Pageable;

import cn.sparrow.permission.constant.OrganizationTypeEnum;
import cn.sparrow.permission.core.api.PermissionService;
import cn.sparrow.permission.core.service.PermissionServiceImpl;
import cn.sparrow.permission.mgt.service.impl.EmployeeServiceImpl;
import cn.sparrow.permission.mgt.service.impl.GroupServiceImpl;
import cn.sparrow.permission.mgt.service.impl.OrganizationServiceImpl;
import cn.sparrow.permission.mgt.service.impl.PositionLevelServiceImpl;
import cn.sparrow.permission.mgt.service.impl.RoleServiceImpl;
import cn.sparrow.permission.mgt.service.repository.EmployeeRepository;
import cn.sparrow.permission.mgt.service.repository.GroupRepository;
import cn.sparrow.permission.mgt.service.repository.OrganizationRelationRepository;
import cn.sparrow.permission.mgt.service.repository.OrganizationRoleRelationRepository;
import cn.sparrow.permission.mgt.service.repository.PositionLevelRepository;
import cn.sparrow.permission.mgt.service.repository.RoleRepository;
import cn.sparrow.permission.model.group.Group;
import cn.sparrow.permission.model.group.GroupEmployee;
import cn.sparrow.permission.model.group.GroupEmployeePK;
import cn.sparrow.permission.model.group.GroupMember;
import cn.sparrow.permission.model.organization.Employee;
import cn.sparrow.permission.model.organization.Organization;
import cn.sparrow.permission.model.organization.OrganizationRelation;
import cn.sparrow.permission.model.organization.OrganizationRolePK;
import cn.sparrow.permission.model.organization.OrganizationRoleRelationPK;
import cn.sparrow.permission.model.organization.PositionLevel;
import cn.sparrow.permission.model.organization.Role;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@SpringBootTest
public class OrganizationTest {

	@Autowired
	private static TestEntityManager entityManager;

	@TestConfiguration
	static class PermissionServiceImplTestContextConfiguration {
		@Bean
		public PermissionService permissionService() {
			return new PermissionServiceImpl((EntityManager) entityManager);
		}
	}

	@Autowired
	OrganizationServiceImpl organizationServiceImpl;
	@Autowired
	OrganizationRelationRepository organizationRelationRepository;
	@Autowired
	EmployeeRepository employeeRepository;
	@Autowired
	RoleRepository roleRepository;
	@Autowired
	PositionLevelRepository positionLevelRepository;
	@Autowired
	GroupRepository groupRepository;

	@Autowired
	RoleServiceImpl roleServiceImpl;
	@Autowired
	PositionLevelServiceImpl positionLevelServiceImpl;
	@Autowired
	GroupServiceImpl groupService;
	@Autowired
	EmployeeServiceImpl employeeServiceImpl;

	@Test
	@Transactional
	void orgTest() {
		String prev = null;

		for (int i = 0; i < 10; i++) {
			Organization parent = organizationServiceImpl
					.create(new Organization("o" + i, i + "", OrganizationTypeEnum.ORGANIZATION));
			prev = parent.getId();

			List<String> ids = new ArrayList<>();
			ids.add(parent.getId());

			for (int j = 0; j < 10; j++) {
				// 创建1级公司部门
				Organization dept1 = organizationServiceImpl.create(
						new Organization("d" + i + "" + j, "o" + i + "d" + i + "" + j, OrganizationTypeEnum.UNIT));

				// 测试组织关系服务
				organizationServiceImpl.addParent(dept1.getId(), ids);

				// 创建2级公司
				Organization org1 = organizationServiceImpl.create(new Organization("o" + i + "" + j,
						"o" + i + "o" + i + "" + j, OrganizationTypeEnum.ORGANIZATION));
				organizationRelationRepository.save(new OrganizationRelation(org1.getId(), parent.getId()));

				Employee employee2 = new Employee("e2" + i + "" + j, "o" + i + "e2" + i + "" + j, parent.getId());
				employeeRepository.save(employee2);

				// 创建岗位
				Role role = new Role(j + "r1" + i, j + "o" + i + "r1" + i);
				roleServiceImpl.create(role);
				ids = new ArrayList<>();
				ids.add(parent.getId());
				roleServiceImpl.setParentOrg(role.getId(), ids);

				// 创建级别
				PositionLevel positionLevel = new PositionLevel(j + "p" + i, j + "o" + i + "p" + i);
				positionLevelServiceImpl.create(positionLevel);
				positionLevelServiceImpl.setParentOrg(positionLevel.getId(), ids);

				// 创建群组
				Group group = new Group(j + "g" + i, j + "o" + i + "g" + i);
				groupService.create(group);
				groupService.setParentOrgs(group.getId(), ids);

				for (int n = 0; n < 10; n++) {
					Organization dept2 = organizationServiceImpl.create(new Organization("d1" + i + "" + j + "" + n,
							"o" + i + "d1" + i + "" + j + "" + n, OrganizationTypeEnum.UNIT));
					organizationRelationRepository.save(new OrganizationRelation(dept2.getId(), dept1.getId()));

					Organization dept3 = organizationServiceImpl.create(new Organization("d2" + i + "" + j + "" + n,
							"o" + i + "d2" + i + "" + j + "" + n, OrganizationTypeEnum.UNIT));
					organizationRelationRepository.save(new OrganizationRelation(dept3.getId(), org1.getId()));
				}

			}
		}

		// log.info("{}", organizationServiceImpl.getChildren(prev).size());
		assertEquals(20, organizationServiceImpl.getChildren(prev).size());
		assertEquals(10, organizationServiceImpl.getEmployees(prev, Pageable.unpaged()).getTotalElements());
		assertEquals(10, organizationServiceImpl.getRoles(prev, Pageable.unpaged()).getTotalElements());
		assertEquals(10, organizationServiceImpl.getLevels(prev, Pageable.unpaged()).getTotalElements());
		assertEquals(10, organizationServiceImpl.getGroups(prev, Pageable.unpaged()).getTotalElements());
		// log.info("{}", organizationServiceImpl.getTreeByParentId(prev));
		// assertEquals(110, menuServiceImpl.all(Pageable.unpaged(),
		// null).getTotalElements());

	}

	@Autowired
	OrganizationRoleRelationRepository organizationRoleRelationRepository;

	@Test
	@Transactional
	void roleTest() {

		Role role1 = new Role("", "rrrrrrrr");
		roleServiceImpl.create(role1);
		String orgid = "";
		for (int i = 0; i < 10; i++) {
			Organization organization = new Organization("" + i, "oo" + i, OrganizationTypeEnum.UNIT);
			organizationServiceImpl.create(organization);
			orgid = organization.getId();
			List<String> ids = new ArrayList<>();
			ids.add(organization.getId());

			Role role = new Role("" + i, "rrr" + i);
			roleServiceImpl.create(role);
			roleServiceImpl.setParentOrg(role1.getId(), ids);
			roleServiceImpl.setParentOrg(role.getId(), ids);
			List<OrganizationRoleRelationPK> organizationRoleRelationPKs = new ArrayList<>();
			organizationRoleRelationPKs
					.add(new OrganizationRoleRelationPK(new OrganizationRolePK(organization.getId(), role.getId()),
							new OrganizationRolePK(organization.getId(), role1.getId())));
			roleServiceImpl.addRelations(organizationRoleRelationPKs);

		}

		// log.info("------------------------{} {} {}" ,
		// organizationRoleRelationRepository.findAll(),role1.getId(),orgid);

		// 设置员工岗位
		Employee employee2 = new Employee("sdfafd", "e333332", orgid);
		employeeServiceImpl.create(employee2);
		List<OrganizationRolePK> organizationRolePKs = new ArrayList<>();
		organizationRolePKs.add(new OrganizationRolePK(orgid, role1.getId()));
		employeeServiceImpl.addRole(employee2.getId(), organizationRolePKs);

		assertEquals(1, roleServiceImpl.getChildren(orgid, role1.getId()).size());
		assertEquals(1, roleServiceImpl.getEmployees(orgid, role1.getId()).size());
	}

	@Test
	void leveTest() {

	}

	@Test
	void employeeTest() {

	}

	@Test
	@Transactional
	void groupTest() {
		Group group1 = new Group("g1", "g1");
		groupService.create(group1);

		Group group2 = new Group("g2", "g2");
		groupService.create(group2);

		Organization organization = new Organization("ooooooo1", "ooooooo1", OrganizationTypeEnum.ORGANIZATION);
		organizationServiceImpl.create(organization);
		List<String> orgs = new ArrayList<>();
		orgs.add(organization.getId());

		// 设置所属组织
		groupService.setParentOrgs(group1.getId(), orgs);
		groupService.setParentOrgs(group2.getId(), orgs);

		GroupMember groupMember = null;
		// 设置员工
		Employee employee1 = new Employee("eeeeee1", "eeeeeee1", organization.getId());
		Employee employee2 = new Employee("eeeeee2", "eeeeeee2", organization.getId());
		employeeServiceImpl.create(employee1);
		employeeServiceImpl.create(employee2);
		List<GroupEmployee> groupEmployees = new ArrayList<>();
		groupEmployees.add(new GroupEmployee(new GroupEmployeePK(group1.getId(), employee1.getId())));
		groupEmployees.add(new GroupEmployee(new GroupEmployeePK(group2.getId(), employee2.getId())));
		groupMember = new GroupMember();
		groupMember.setGroupEmployees(groupEmployees);
		groupService.addMembers(groupMember);

		log.info("+++++++++++{}", groupService.getGroupMember(group1.getId()));

		// 设置子组
	}
}
