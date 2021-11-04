package cn.sparrow.organization.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.transaction.annotation.Transactional;

import cn.sparrow.model.organization.PositionLevel;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "level-controller")
@RepositoryRestResource(exported = false)
public interface PositionLevelRepository extends JpaRepository<PositionLevel, String> {

	@Transactional
	void deleteByIdIn(String[] ids);
//	Page<Level> findByOrganizationId(String organizationId, Pageable p);

	Iterable<PositionLevel> findByRoot(boolean b);
}
