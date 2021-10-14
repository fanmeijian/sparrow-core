package cn.sparrow.organization.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import cn.sparrow.model.organization.LevelRelation;
import cn.sparrow.model.organization.LevelRelationPK;

public interface LevelRelationRepository extends JpaRepository<LevelRelation, LevelRelationPK> {

}
