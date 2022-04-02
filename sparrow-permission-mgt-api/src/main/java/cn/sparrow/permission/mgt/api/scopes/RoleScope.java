package cn.sparrow.permission.mgt.api.scopes;

import cn.sparrow.permission.mgt.api.PreserveScope;

public interface RoleScope extends PreserveScope {
	String type="role";
	String admin="admin";
	String SCOPE_ADMIN_CREATE = admin + ":" + type + ":create";
	String SCOPE_ADMIN_READ = admin + ":" + type + ":read";
	String SCOPE_ADMIN_UPDATE = admin + ":" + type + ":update";
	String SCOPE_ADMIN_DELETE = admin + ":" + type + ":delete";
	String SCOPE_ADMIN_LIST = admin + ":" + type + ":list";
	
	String SCOPE_ADMIN_CHILD_LIST = admin + ":" + type + ":child:list";
	String SCOPE_ADMIN_EMP_LIST = admin + ":" + type + ":emp:list";
	
	String SCOPE_ADMIN_PARENT_ADD = admin + ":" + type + ":parent:add";
	String SCOPE_ADMIN_PARENT_REMOVE = admin + ":" + type + ":parent:remove";
	String SCOPE_ADMIN_PARENT_LIST = admin + ":" + type + ":parent:list";
	
	String SCOPE_ADMIN_PARENT_ORG_ADD = admin + ":" + type + ":parent:org:add";
	String SCOPE_ADMIN_PARENT_ORG_REMOVE = admin + ":" + type + ":parent:org:remove";
	String SCOPE_ADMIN_PARENT_ORG_LIST = admin + ":" + type + ":parent:org:list";
}
