package cn.sparrow.permission.mgt.service.repository;

import java.util.List;

import javax.validation.constraints.NotBlank;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import cn.sparrow.permission.model.organization.Organization;
import cn.sparrow.permission.model.organization.OrganizationGroup;
import cn.sparrow.permission.model.organization.OrganizationGroupPK;

public interface OrganizationGroupRepository extends JpaRepository<OrganizationGroup, OrganizationGroupPK> {

	long countByIdOrganizationId(String id);

	Page<OrganizationGroup> findByIdOrganizationId(String organizationId, Pageable pageable);

    List<OrganizationGroup> findByIdGroupId(String groupId);
}
