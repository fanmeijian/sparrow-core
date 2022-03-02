package cn.sparrow.permission.repository.organization;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.transaction.annotation.Transactional;

import cn.sparrow.permission.model.organization.EmployeeRelation;
import cn.sparrow.permission.model.organization.EmployeeRelationPK;

@RepositoryRestResource(exported = false)
public interface EmployeeRelationRepository extends JpaRepository<EmployeeRelation, EmployeeRelationPK> {

	List<EmployeeRelation> findByIdParentId(String object);

	@Transactional
	void deleteByIdEmployeeIdInOrIdParentIdIn(String[] ids, String[] ids2);

	long countByIdParentId(String parentId);

	List<EmployeeRelation> findByIdEmployeeId(String employeeId);


}
