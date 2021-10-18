package cn.sparrow.model.permission;

import java.util.List;

import cn.sparrow.model.group.GroupDataPermissionPK;
import lombok.Data;

@Data
public class DataPermission {
	private List<UserDataPermissionPK> userDataPermissionPKs;
	private List<SysroleDataPermissionPK> sysroleDataPermissionPKs;
	private List<GroupDataPermissionPK> groupDataPermissionPKs;
	private List<OrganizationDataPermissionPK> organizationDataPermissionPKs;
}
