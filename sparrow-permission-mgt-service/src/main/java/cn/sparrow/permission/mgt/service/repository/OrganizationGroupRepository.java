package cn.sparrow.permission.mgt.service.repository;

import java.util.List;

import javax.validation.constraints.NotBlank;

import org.springframework.data.jpa.repository.JpaRepository;

import cn.sparrow.permission.model.organization.OrganizationGroup;
import cn.sparrow.permission.model.organization.OrganizationGroupPK;

public interface OrganizationGroupRepository extends JpaRepository<OrganizationGroup, OrganizationGroupPK> {

	long countByIdOrganizationId(String id);

	List<OrganizationGroup> findByIdOrganizationId(@NotBlank String organizationId);
}
