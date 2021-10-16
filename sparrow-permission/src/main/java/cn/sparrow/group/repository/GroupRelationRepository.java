package cn.sparrow.group.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import cn.sparrow.model.group.GroupRelation;
import cn.sparrow.model.group.GroupRelationPK;

@RepositoryRestResource(exported = false)
public interface GroupRelationRepository extends JpaRepository<GroupRelation, GroupRelationPK> {

  Iterable<GroupRelation> findByIdParentId(String object);


}
