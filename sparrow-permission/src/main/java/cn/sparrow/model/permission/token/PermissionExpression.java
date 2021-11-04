package cn.sparrow.model.permission.token;

import cn.sparrow.model.common.PermissionExpressionEnum;
import cn.sparrow.model.common.PermissionTargetEnum;
import cn.sparrow.model.common.PermissionTypeEnum;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class PermissionExpression {
	private PermissionTypeEnum permissionType;
	private PermissionTargetEnum target;
	private PermissionExpressionEnum expression;
}
