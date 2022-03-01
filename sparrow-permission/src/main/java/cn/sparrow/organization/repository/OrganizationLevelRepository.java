package cn.sparrow.organization.repository;

import java.util.List;

import javax.validation.constraints.NotBlank;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import cn.sparrow.model.organization.OrganizationPositionLevel;
import cn.sparrow.model.organization.OrganizationPositionLevelPK;

@RepositoryRestResource(exported = false)
public interface OrganizationLevelRepository extends JpaRepository<OrganizationPositionLevel, OrganizationPositionLevelPK> {

	List<OrganizationPositionLevel> findByIdOrganizationId(@NotBlank String organizationId);

	long countByIdOrganizationId(String id);

	Iterable<OrganizationPositionLevel> findByIdPositionLevelId(@NotBlank String positionLevelId);

}
