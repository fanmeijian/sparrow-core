package cn.sparrow.permission.portal.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import cn.sparrow.permission.model.resource.Workspace;

public interface WorkspaceRepository extends JpaRepository<Workspace, String> {

}
