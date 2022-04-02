package cn.sparrow.permission.mgt.api.scopes;

import cn.sparrow.permission.mgt.api.PreserveScope;

public interface OrgScope extends PreserveScope{
	String SCOPE_ADMIN_ORG_CREATE = "admin:org:create";
	String SCOPE_ADMIN_ORG_UPDATE = "admin:org:update";
}
