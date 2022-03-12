package cn.sparrow.permission.authorization.server.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import cn.sparrow.permission.authorization.server.model.LoginLog;

public interface LoginLogRepository extends JpaRepository<LoginLog, String> {

}
