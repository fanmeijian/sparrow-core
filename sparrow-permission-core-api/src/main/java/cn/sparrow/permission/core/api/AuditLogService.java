package cn.sparrow.permission.core.api;

import java.util.List;

public interface AuditLogService {
	public List<?> getLog(Class<?> c, Object id);
}
