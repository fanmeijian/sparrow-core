package cn.sparrow.permission.service;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import cn.sparrow.permission.constant.PermissionEnum;
import cn.sparrow.permission.constant.PermissionTargetEnum;
import lombok.Data;

@Data
public class PermissionToken implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Map<PermissionEnum, Map<PermissionTargetEnum, List<PermissionExpression<?>>>> allowPermissions;
	private Map<PermissionEnum, Map<PermissionTargetEnum, List<PermissionExpression<?>>>> denyPermissions;

}
