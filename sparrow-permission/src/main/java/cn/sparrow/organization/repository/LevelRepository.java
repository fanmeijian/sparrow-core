package cn.sparrow.organization.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import cn.sparrow.model.organization.Level;

public interface LevelRepository extends JpaRepository<Level, String> {
//	Page<Level> findByOrganizationId(String organizationId, Pageable p);
}
