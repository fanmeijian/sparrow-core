package cn.sparrow.permission.service.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import cn.sparrow.permission.model.organization.EmployeeRelation;
import cn.sparrow.permission.model.organization.EmployeeRelationPK;

public interface EmployeeRelationRepository extends JpaRepository<EmployeeRelation, EmployeeRelationPK> {

	List<EmployeeRelation> findByIdParentId(String object);

	@Transactional
	void deleteByIdEmployeeIdInOrIdParentIdIn(String[] ids, String[] ids2);

	long countByIdParentId(String parentId);

	List<EmployeeRelation> findByIdEmployeeId(String employeeId);


}
