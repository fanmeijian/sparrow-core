package cn.sparrow.organization.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import cn.sparrow.model.organization.GroupOrganization;
import cn.sparrow.model.organization.GroupOrganizationPK;

@RepositoryRestResource(exported = false)
public interface GroupOrganizationRepository extends JpaRepository<GroupOrganization, GroupOrganizationPK> {

}
