package cn.sparrow.permission.mgt.service.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import cn.sparrow.permission.model.token.SparrowPermissionToken;

public interface SparrowPermissionTokenRepository extends JpaRepository<SparrowPermissionToken, String>{
    
}
