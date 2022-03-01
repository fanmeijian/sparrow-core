package cn.sparrow.permission.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import cn.sparrow.permission.model.AuditLog;

@RepositoryRestResource(exported = false)
public interface AuditLogRepository extends JpaRepository<AuditLog, String> {

}
