package cn.sparrow.model.permission.token;

import java.util.List;

import cn.sparrow.model.permission.SparrowFunctionPK;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class FuncPermissionToken {
	private SparrowFunctionPK functionPK;
	private List<PermissionExpression> permissionExpressions;
	
	public FuncPermissionToken(SparrowFunctionPK functionPK) {
		this.functionPK = functionPK;
	}
}
