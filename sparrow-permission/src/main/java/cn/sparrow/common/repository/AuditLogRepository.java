package cn.sparrow.common.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import cn.sparrow.model.common.AuditLog;

@RepositoryRestResource(exported = false)
public interface AuditLogRepository extends JpaRepository<AuditLog, String> {

}
