package cn.sparrow.model.permission;

import java.util.List;

import cn.sparrow.model.group.GroupModelPermissionPK;
import lombok.Data;

@Data
public class ModelPermission {
	private List<UserModelPermissionPK> userModelPermissionPKs;
	private List<SysroleModelPermissionPK> sysroleModelPermissionPKs;
	private List<GroupModelPermissionPK> groupModelPermissionPKs;
	private List<OrganizationModelPermissionPK> organizationModelPermissionPKs;
}
