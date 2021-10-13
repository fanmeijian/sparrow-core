package cn.sparrow.organization.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import cn.sparrow.model.organization.GroupSysrole;
import cn.sparrow.model.organization.GroupSysrolePK;

@RepositoryRestResource(exported = false)
public interface GroupSysroleRepository extends JpaRepository<GroupSysrole, GroupSysrolePK> {

}
