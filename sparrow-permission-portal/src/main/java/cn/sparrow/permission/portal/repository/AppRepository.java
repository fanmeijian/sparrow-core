package cn.sparrow.permission.portal.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import cn.sparrow.permission.model.resource.App;

public interface AppRepository extends JpaRepository<App, String> {
    Page<App> findByWorkspaceId(String workspaceId, Pageable pageable);
}
