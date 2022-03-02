package cn.sparrow.permission.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import cn.sparrow.permission.model.AuditLog;

public interface AuditLogRepository extends JpaRepository<AuditLog, String> {

}
