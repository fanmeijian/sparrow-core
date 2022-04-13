package cn.sparrow.permission.model.common;

import java.util.ArrayList;
import java.util.List;

import lombok.Data;

@Data
public class PermissionCheckResult {
	// 没有读数据权限
	private boolean noDataRead = false;
	private boolean denyDataRead = false;

	private List<String> noReadAttrs = new ArrayList<>();
	private List<String> noReadFields = new ArrayList<>();
	private List<String> noUpdateFields = new ArrayList<>();

	private List<String> denyReadAttrs = new ArrayList<>();
	private List<String> denyReadFields = new ArrayList<>();
	private List<String> denyUpdateFields = new ArrayList<>();
}
