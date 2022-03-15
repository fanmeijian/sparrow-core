package cn.sparrow.permission.mgt.service.repository;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.persistence.criteria.Subquery;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.transaction.annotation.Transactional;

import cn.sparrow.permission.model.organization.Employee;
import cn.sparrow.permission.model.organization.EmployeeOrganizationLevel;
import cn.sparrow.permission.model.organization.EmployeeOrganizationLevelPK_;
import cn.sparrow.permission.model.organization.EmployeeOrganizationLevel_;
import cn.sparrow.permission.model.organization.EmployeeOrganizationRole;
import cn.sparrow.permission.model.organization.EmployeeOrganizationRolePK_;
import cn.sparrow.permission.model.organization.EmployeeOrganizationRole_;
import cn.sparrow.permission.model.organization.Employee_;
import cn.sparrow.permission.model.organization.OrganizationPositionLevelPK_;
import cn.sparrow.permission.model.organization.OrganizationRolePK_;

public interface EmployeeRepository extends JpaRepository<Employee, String>, JpaSpecificationExecutor<Employee> {

	Iterable<Employee> findByIsRoot(boolean b);

	@Transactional
	void deleteByIdIn(String[] ids);

	Page<Employee> findByOrganizationId(String organizationId, Pageable pageable);

	default Page<Employee> findAllByOrganizationId(String organizationId, Pageable pageable) {
		Specification<Employee> specification = new Specification<Employee>() {
			private static final long serialVersionUID = 1L;
			List<Predicate> predicates = new ArrayList<Predicate>();

			@Override
			public Predicate toPredicate(Root<Employee> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {

				Subquery<EmployeeOrganizationRole> roleSubquery = query.subquery(EmployeeOrganizationRole.class);
				roleSubquery.select(roleSubquery.from(EmployeeOrganizationRole.class).get(EmployeeOrganizationRole_.id)
						.get(EmployeeOrganizationRolePK_.EMPLOYEE_ID));
				roleSubquery.where(criteriaBuilder.equal(roleSubquery.from(EmployeeOrganizationRole.class)
						.get(EmployeeOrganizationRole_.id).get(EmployeeOrganizationRolePK_.ORGANIZATION_ROLE_ID)
						.get(OrganizationRolePK_.ORGANIZATION_ID), organizationId));

				Subquery<EmployeeOrganizationLevel> levelSubquery = query.subquery(EmployeeOrganizationLevel.class);
				levelSubquery.select(levelSubquery.from(EmployeeOrganizationLevel.class)
						.get(EmployeeOrganizationLevel_.ID).get(EmployeeOrganizationLevelPK_.EMPLOYEE_ID));
				levelSubquery.where(criteriaBuilder.equal(levelSubquery.from(EmployeeOrganizationLevel.class)
						.get(EmployeeOrganizationLevel_.ID).get(EmployeeOrganizationLevelPK_.ORGANIZATION_LEVEL_ID)
						.get(OrganizationPositionLevelPK_.ORGANIZATION_ID), organizationId));

				predicates.add(criteriaBuilder.equal(root.get(Employee_.organizationId), organizationId));
				predicates.add(criteriaBuilder.in(root.get(Employee_.ID)).value(roleSubquery));
				predicates.add(criteriaBuilder.in(root.get(Employee_.ID)).value(levelSubquery));
				query.distinct(true);
				return criteriaBuilder.or(predicates.toArray(new Predicate[0]));
			}

		};
		return findAll(specification, pageable);
	}

	long countByOrganizationId(String id);

	default Page<Employee> search(Employee employee, Pageable pageable) {
		Specification<Employee> specification = new Specification<Employee>() {
			private static final long serialVersionUID = 1L;
			List<Predicate> predicates = new ArrayList<Predicate>();

			@Override
			public Predicate toPredicate(Root<Employee> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
				if (employee.getName() != null) {
					predicates.add(criteriaBuilder.like(root.get(Employee_.name), "%" + employee.getName() + "%"));
				}
				if (employee.getCode() != null) {
					predicates.add(criteriaBuilder.like(root.get(Employee_.code), "%" + employee.getCode() + "%"));
				}

				return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
			}

		};
		return findAll(specification, pageable);
	};

}
