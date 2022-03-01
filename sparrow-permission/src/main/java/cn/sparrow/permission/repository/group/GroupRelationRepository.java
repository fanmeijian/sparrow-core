package cn.sparrow.permission.repository.group;

import java.util.List;

import javax.validation.constraints.NotBlank;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import cn.sparrow.permission.model.group.GroupRelation;
import cn.sparrow.permission.model.group.GroupRelationPK;

@RepositoryRestResource(exported = false)
public interface GroupRelationRepository extends JpaRepository<GroupRelation, GroupRelationPK> {

  List<GroupRelation> findByIdParentId(String object);

}
