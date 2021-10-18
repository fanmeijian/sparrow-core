package cn.sparrow.model.permission;

import java.util.List;

import cn.sparrow.model.group.GroupModelAttributePermissionPK;
import lombok.Data;

@Data
public class ModelAttributePermission {
	private List<UserModelAttributePermissionPK> userModelAttributePermissionPKs;
	private List<SysroleModelAttributePermissionPK> sysroleModelAttributePermissionPKs;
	private List<GroupModelAttributePermissionPK> groupModelAttributePermissionPKs;
	private List<OrganizationModelAttributePermissionPK> organizationModelAttributePermissionPKs;
}
