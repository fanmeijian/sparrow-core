package cn.sparrow.organization.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import cn.sparrow.model.organization.LevelRelation;
import cn.sparrow.model.organization.LevelRelationPK;

@RepositoryRestResource(exported = false)
public interface LevelRelationRepository extends JpaRepository<LevelRelation, LevelRelationPK> {
  List<LevelRelation> findByIdParentId(String parentId);
}
