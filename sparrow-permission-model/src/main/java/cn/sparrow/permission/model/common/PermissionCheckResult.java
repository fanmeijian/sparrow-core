package cn.sparrow.permission.model.common;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class PermissionCheckResult implements Serializable{
	private static final long serialVersionUID = 1L;
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
