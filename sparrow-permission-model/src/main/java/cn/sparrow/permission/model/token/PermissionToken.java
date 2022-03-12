package cn.sparrow.permission.model.token;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import cn.sparrow.permission.constant.PermissionEnum;
import cn.sparrow.permission.constant.PermissionTargetEnum;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PermissionToken implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Map<PermissionEnum, Map<PermissionTargetEnum, List<PermissionExpression<?>>>> allowPermissions;
	private Map<PermissionEnum, Map<PermissionTargetEnum, List<PermissionExpression<?>>>> denyPermissions;

}
