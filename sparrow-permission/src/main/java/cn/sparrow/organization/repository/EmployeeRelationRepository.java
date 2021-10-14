package cn.sparrow.organization.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import cn.sparrow.model.organization.EmployeeRelation;
import cn.sparrow.model.organization.EmployeeRelationPK;

@RepositoryRestResource(exported = false)
public interface EmployeeRelationRepository extends JpaRepository<EmployeeRelation, EmployeeRelationPK> {

  Iterable<EmployeeRelation> findByIdParentId(String object);

}
