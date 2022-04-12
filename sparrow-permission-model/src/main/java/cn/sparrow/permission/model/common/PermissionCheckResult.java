package cn.sparrow.permission.model.common;

import java.util.List;

import lombok.Data;

@Data
public class PermissionCheckResult {
	// 没有读数据权限
	private boolean noDataRead = false;
	private boolean denyDataRead = false;

	private List<String> noReadAttrs;
	private List<String> noReadFields;
	private List<String> noUpdateFields;
	
	private List<String> denyReadAttrs;
	private List<String> denyReadFields;
	private List<String> denyUpdateFields;
}
