package cn.sparrow.organization.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import cn.sparrow.model.organization.GroupLevel;
import cn.sparrow.model.organization.GroupLevelPK;

@RepositoryRestResource(exported = false)
public interface GroupLevelRepository extends JpaRepository<GroupLevel, GroupLevelPK> {

}
