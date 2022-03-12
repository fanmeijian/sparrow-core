package cn.sparrow.permission.mgt.service.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import cn.sparrow.permission.model.group.GroupRelation;
import cn.sparrow.permission.model.group.GroupRelationPK;

public interface GroupRelationRepository extends JpaRepository<GroupRelation, GroupRelationPK> {

  Page<GroupRelation> findByIdParentId(String groupId, Pageable pageable);

}
