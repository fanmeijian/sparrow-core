package cn.sparrow.permission.mgt.service.repository;

import javax.validation.constraints.NotBlank;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import cn.sparrow.permission.model.organization.OrganizationPositionLevel;
import cn.sparrow.permission.model.organization.OrganizationPositionLevelPK;

public interface OrganizationLevelRepository extends JpaRepository<OrganizationPositionLevel, OrganizationPositionLevelPK> {

	Page<OrganizationPositionLevel> findByIdOrganizationId(String organizationId, Pageable pageable);

	long countByIdOrganizationId(String id);

	Iterable<OrganizationPositionLevel> findByIdPositionLevelId(@NotBlank String positionLevelId);

}
