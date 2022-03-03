package cn.sparrow.permission.service.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import cn.sparrow.permission.model.group.GroupRelation;
import cn.sparrow.permission.model.group.GroupRelationPK;

public interface GroupRelationRepository extends JpaRepository<GroupRelation, GroupRelationPK> {

  List<GroupRelation> findByIdParentId(String object);

}
