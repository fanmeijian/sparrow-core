package cn.sparrow.group.repository;

import java.util.List;

import javax.validation.constraints.NotBlank;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import cn.sparrow.model.group.GroupPositionLevel;
import cn.sparrow.model.group.GroupPositionLevelPK;

@RepositoryRestResource(exported = false)
public interface GroupLevelRepository extends JpaRepository<GroupPositionLevel, GroupPositionLevelPK> {

	List<GroupPositionLevel> findByIdGroupId(@NotBlank String groupId);

}
