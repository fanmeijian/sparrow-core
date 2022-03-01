package cn.sparrow.permission.repository.organization;

import java.util.List;

import javax.validation.constraints.NotBlank;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import cn.sparrow.permission.model.organization.OrganizationGroup;
import cn.sparrow.permission.model.organization.OrganizationGroupPK;

@RepositoryRestResource(exported = false)
public interface OrganizationGroupRepository extends JpaRepository<OrganizationGroup, OrganizationGroupPK> {

	long countByIdOrganizationId(String id);

	List<OrganizationGroup> findByIdOrganizationId(@NotBlank String organizationId);
}
