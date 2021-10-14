package cn.sparrow.organization.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import cn.sparrow.model.organization.RoleRelation;
import cn.sparrow.model.organization.RoleRelationPK;

public interface RoleRelationRepository extends JpaRepository<RoleRelation, RoleRelationPK> {

}
