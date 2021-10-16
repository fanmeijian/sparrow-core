package cn.sparrow.group.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import cn.sparrow.model.group.GroupLevel;
import cn.sparrow.model.group.GroupLevelPK;

@RepositoryRestResource(exported = false)
public interface GroupLevelRepository extends JpaRepository<GroupLevel, GroupLevelPK> {

}
