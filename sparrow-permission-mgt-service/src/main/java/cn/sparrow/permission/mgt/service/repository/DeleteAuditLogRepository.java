package cn.sparrow.permission.mgt.service.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import cn.sparrow.permission.model.common.DeleteAuditLog;

public interface DeleteAuditLogRepository extends JpaRepository<DeleteAuditLog, String> {

}
