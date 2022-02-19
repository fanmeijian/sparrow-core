package cn.sparrow.permission.service;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import cn.sparrow.model.common.PermissionEnum;
import cn.sparrow.model.common.PermissionTargetEnum;
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
