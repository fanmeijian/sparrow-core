package cn.sparrow.group.repository;

import java.util.List;

import javax.validation.constraints.NotBlank;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import cn.sparrow.model.group.GroupLevel;
import cn.sparrow.model.group.GroupLevelPK;

@RepositoryRestResource(exported = false)
public interface GroupLevelRepository extends JpaRepository<GroupLevel, GroupLevelPK> {

	List<GroupLevel> findByIdGroupId(@NotBlank String groupId);

}
