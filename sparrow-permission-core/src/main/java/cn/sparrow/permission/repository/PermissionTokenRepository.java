package cn.sparrow.permission.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import cn.sparrow.permission.model.SparrowPermissionToken;

@RepositoryRestResource(exported = false)
public interface PermissionTokenRepository extends JpaRepository<SparrowPermissionToken, String> {

}
