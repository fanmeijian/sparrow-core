package cn.sparrow.group.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import cn.sparrow.model.group.GroupSysrole;
import cn.sparrow.model.group.GroupSysrolePK;

@RepositoryRestResource(exported = false)
public interface GroupSysroleRepository extends JpaRepository<GroupSysrole, GroupSysrolePK> {

}
