package cn.sparrow.model.permission;

import java.util.List;

import lombok.Data;

@Data
public class SysrolePermission {
	private List<UserSysrolePK> userSysrolePKs;
}
