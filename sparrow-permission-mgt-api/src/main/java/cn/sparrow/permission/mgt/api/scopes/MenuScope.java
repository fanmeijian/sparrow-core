package cn.sparrow.permission.mgt.api.scopes;

import cn.sparrow.permission.mgt.api.PreserveScope;

public interface MenuScope extends PreserveScope{
	String type="menu";
	String admin="admin";
	String SCOPE_ADMIN_CREATE = admin + ":" + type + ":create";
	String SCOPE_ADMIN_READ = admin + ":" + type + ":read";
	String SCOPE_ADMIN_UPDATE = admin + ":" + type + ":update";
	String SCOPE_ADMIN_DELETE = admin + ":" + type + ":delete";
	String SCOPE_ADMIN_LIST = admin + ":" + type + ":list";
	String SCOPE_ADMIN_SORT = admin + ":" + type + ":sort";
	String SCOPE_ADMIN_TREE = admin + ":" + type + ":tree";
	
	String SCOPE_ADMIN_PEM_ADD = admin + ":" + type + ":pem:add";
	String SCOPE_ADMIN_PEM_REMOVE = admin + ":" + type + ":pem:remove";
	String SCOPE_ADMIN_PEM_LIST = admin + ":" + type + ":pem:list";
	
}
