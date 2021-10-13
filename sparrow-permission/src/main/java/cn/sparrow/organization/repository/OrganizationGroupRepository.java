package cn.sparrow.organization.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import cn.sparrow.model.organization.OrganizationGroup;
import cn.sparrow.model.organization.OrganizationGroupPK;

@RepositoryRestResource(exported = false)
public interface OrganizationGroupRepository extends JpaRepository<OrganizationGroup, OrganizationGroupPK> {

}
