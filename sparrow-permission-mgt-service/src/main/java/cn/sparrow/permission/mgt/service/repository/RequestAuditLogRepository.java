package cn.sparrow.permission.mgt.service.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import cn.sparrow.permission.model.common.RequestAuditLog;

public interface RequestAuditLogRepository extends JpaRepository<RequestAuditLog, String> {

}
