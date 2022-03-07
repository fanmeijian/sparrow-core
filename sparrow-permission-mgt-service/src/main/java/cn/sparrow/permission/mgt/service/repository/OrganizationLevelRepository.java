package cn.sparrow.permission.mgt.service.repository;

import java.util.List;

import javax.validation.constraints.NotBlank;

import org.springframework.data.jpa.repository.JpaRepository;

import cn.sparrow.permission.model.organization.OrganizationPositionLevel;
import cn.sparrow.permission.model.organization.OrganizationPositionLevelPK;

public interface OrganizationLevelRepository extends JpaRepository<OrganizationPositionLevel, OrganizationPositionLevelPK> {

	List<OrganizationPositionLevel> findByIdOrganizationId(@NotBlank String organizationId);

	long countByIdOrganizationId(String id);

	Iterable<OrganizationPositionLevel> findByIdPositionLevelId(@NotBlank String positionLevelId);

}
