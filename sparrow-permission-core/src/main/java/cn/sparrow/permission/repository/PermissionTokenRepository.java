package cn.sparrow.permission.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import cn.sparrow.permission.model.SparrowPermissionToken;

public interface PermissionTokenRepository extends JpaRepository<SparrowPermissionToken, String> {

}
