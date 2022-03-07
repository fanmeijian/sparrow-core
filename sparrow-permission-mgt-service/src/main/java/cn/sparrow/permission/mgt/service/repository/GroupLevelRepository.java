package cn.sparrow.permission.mgt.service.repository;

import java.util.List;

import javax.validation.constraints.NotBlank;

import org.springframework.data.jpa.repository.JpaRepository;

import cn.sparrow.permission.model.group.GroupPositionLevel;
import cn.sparrow.permission.model.group.GroupPositionLevelPK;

public interface GroupLevelRepository extends JpaRepository<GroupPositionLevel, GroupPositionLevelPK> {

	List<GroupPositionLevel> findByIdGroupId(@NotBlank String groupId);

}
