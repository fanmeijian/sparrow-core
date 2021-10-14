package cn.sparrow.organization.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import cn.sparrow.model.organization.EmployeeRelation;
import cn.sparrow.model.organization.EmployeeRelationPK;

public interface EmployeeRelationRepository extends JpaRepository<EmployeeRelation, EmployeeRelationPK> {

  Iterable<EmployeeRelation> findByIdParentId(String object);

}
