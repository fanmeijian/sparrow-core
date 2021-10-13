package cn.sparrow.organization.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import cn.sparrow.model.organization.Level;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "level-controller")
public interface LevelRepository extends JpaRepository<Level, String> {
//	Page<Level> findByOrganizationId(String organizationId, Pageable p);
}
