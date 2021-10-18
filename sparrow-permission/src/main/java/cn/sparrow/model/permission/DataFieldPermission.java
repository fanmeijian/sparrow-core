package cn.sparrow.model.permission;

import java.util.List;

import cn.sparrow.model.group.GroupDataFieldPermissionPK;
import lombok.Data;

@Data
public class DataFieldPermission {
	private List<UserDataFieldPermissionPK> userDataFieldPermissionPKs;
	private List<SysroleDataFieldPermissionPK> sysroleDataFieldPermissionPKs;
	private List<GroupDataFieldPermissionPK> groupDataFieldPermissionPKs;
	private List<OrganizationDataFieldPermissionPK> organizationDataFieldPermissionPKs;
}
