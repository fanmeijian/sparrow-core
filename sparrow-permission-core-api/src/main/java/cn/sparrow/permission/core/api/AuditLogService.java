package cn.sparrow.permission.core.api;

import java.util.List;
/**
 * 
 * @author fansword
 * 日志服务，采用hibernate的 auditlog 实现
 */
public interface AuditLogService {
	// 获取条数据的操作日志
	public List<?> getLog(Class<?> c, Object id);
	
	// only return delete log
	public List<?> getLog(Class<?> c);
}
