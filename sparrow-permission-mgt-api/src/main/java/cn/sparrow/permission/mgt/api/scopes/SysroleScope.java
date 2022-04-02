package cn.sparrow.permission.mgt.api.scopes;

import cn.sparrow.permission.mgt.api.PreserveScope;

public interface SysroleScope extends PreserveScope {
	String SCOPE_ADMIN_SYSROLE_CREATE = "admin:sysrole:create";
	String SCOPE_ADMIN_SYSROLE_READ = "admin:sysrole:read";
	String SCOPE_ADMIN_SYSROLE_UPDATE = "admin:sysrole:update";
	String SCOPE_ADMIN_SYSROLE_DELETE = "admin:sysrole:delete";
	String SCOPE_ADMIN_SYSROLE_LIST = "admin:sysrole:list";
	
	String SCOPE_ADMIN_SYSROLE_USER_ADD = "admin:sysrole:user:add";
	String SCOPE_ADMIN_SYSROLE_USER_REMOVE = "admin:sysrole:user:remove";
	String SCOPE_ADMIN_SYSROLE_USER_LIST = "admin:sysrole:user:list";
}
