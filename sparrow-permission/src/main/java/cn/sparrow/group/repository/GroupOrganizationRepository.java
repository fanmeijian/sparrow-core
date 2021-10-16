package cn.sparrow.group.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import cn.sparrow.model.group.GroupOrganization;
import cn.sparrow.model.group.GroupOrganizationPK;

@RepositoryRestResource(exported = false)
public interface GroupOrganizationRepository extends JpaRepository<GroupOrganization, GroupOrganizationPK> {

}
