package cn.sparrow.permission.mgt.api.scopes;

import cn.sparrow.permission.mgt.api.PreserveScope;

public interface ApiScope extends PreserveScope {
	String SCOPE_ADMIN_API_LIST = "admin:api:list";
	String SCOPE_ADMIN_API_CREATE = "admin:api:create";
	String SCOPE_ADMIN_API_READ = "admin:api:read";
	String SCOPE_ADMIN_API_UPDATE = "admin:api:update";
	String SCOPE_ADMIN_API_DELETE = "admin:api:delete";
	
	String SCOPE_ADMIN_API_PEM_LIST = "admin:api:pem:list";
	String SCOPE_ADMIN_API_PEM_ADD = "admin:api:pem:add";
	String SCOPE_ADMIN_API_PEM_REMOVE = "admin:api:pem:remove";
	
	String SCOPE_ADMIN_API_SCOPE_ADD = "admin:api:scope:add";
	String SCOPE_ADMIN_API_SCOPE_LIST = "admin:api:scope:list";
	String SCOPE_ADMIN_API_SCOPE_REMOVE = "admin:api:scope:remove";
}
