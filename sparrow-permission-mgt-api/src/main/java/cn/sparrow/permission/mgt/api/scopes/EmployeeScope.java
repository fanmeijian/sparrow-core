package cn.sparrow.permission.mgt.api.scopes;

import cn.sparrow.permission.mgt.api.PreserveScope;

public interface EmployeeScope extends PreserveScope {
	String type="emp";
	String admin="admin";
	String SCOPE_ADMIN_CREATE = admin + ":" + type + ":create";
	String SCOPE_ADMIN_READ = admin + ":" + type + ":read";
	String SCOPE_ADMIN_UPDATE = admin + ":" + type + ":update";
	String SCOPE_ADMIN_DELETE = admin + ":" + type + ":delete";
	String SCOPE_ADMIN_LIST = admin + ":" + type + ":list";
	
	String SCOPE_ADMIN_CHILD_LIST = admin + ":" + type + ":child:list";
	String SCOPE_ADMIN_TREE = admin + ":" + type + ":tree";
	
	String SCOPE_ADMIN_ROLE_ADD = admin + ":" + type + ":role:add";
	String SCOPE_ADMIN_ROLE_REMOVE = admin + ":" + type + ":role:remove";
	String SCOPE_ADMIN_ROLE_LIST = admin + ":" + type + ":role:list";
	
	String SCOPE_ADMIN_LEVEL_ADD = admin + ":" + type + ":level:add";
	String SCOPE_ADMIN_LEVEL_REMOVE = admin + ":" + type + ":level:remove";
	String SCOPE_ADMIN_LEVEL_LIST = admin + ":" + type + ":level:list";
	
	String SCOPE_ADMIN_PARENT_ADD = admin + ":" + type + ":parent:add";
	String SCOPE_ADMIN_PARENT_REMOVE = admin + ":" + type + ":parent:remove";
	String SCOPE_ADMIN_PARENT_LIST = admin + ":" + type + ":parent:list";
}
