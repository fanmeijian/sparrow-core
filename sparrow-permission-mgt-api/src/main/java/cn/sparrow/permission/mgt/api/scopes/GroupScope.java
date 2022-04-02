package cn.sparrow.permission.mgt.api.scopes;

import cn.sparrow.permission.mgt.api.PreserveScope;

public interface GroupScope extends PreserveScope {
	String type="group";
	String admin="admin";
	String SCOPE_ADMIN_CREATE = admin + ":" + type + ":create";
	String SCOPE_ADMIN_READ = admin + ":" + type + ":read";
	String SCOPE_ADMIN_UPDATE = admin + ":" + type + ":update";
	String SCOPE_ADMIN_DELETE = admin + ":" + type + ":delete";
	String SCOPE_ADMIN_LIST = admin + ":" + type + ":list";
	
	String SCOPE_ADMIN_TREE = admin + ":" + type + ":tree";
	
	String SCOPE_ADMIN_MEMBER_ADD = admin + ":" + type + ":member:add";
	String SCOPE_ADMIN_MEMBER_REMOVE = admin + ":" + type + ":member:remove";
	String SCOPE_ADMIN_MEMBER_LIST = admin + ":" + type + ":member:list";
	
	String SCOPE_ADMIN_PARENT_ORG_ADD = admin + ":" + type + ":parent:org:add";
	String SCOPE_ADMIN_PARENT_ORG_REMOVE = admin + ":" + type + ":parent:org:remove";
	String SCOPE_ADMIN_PARENT_ORG_LIST = admin + ":" + type + ":parent:org:list";
}
