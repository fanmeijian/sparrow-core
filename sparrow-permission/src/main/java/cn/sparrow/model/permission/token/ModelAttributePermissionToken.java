package cn.sparrow.model.permission.token;

import java.util.List;

import cn.sparrow.model.permission.ModelAttributePK;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ModelAttributePermissionToken {
	private ModelAttributePK modelAttributePK;
	private List<PermissionExpression> permissionExpressions;

	public ModelAttributePermissionToken(ModelAttributePK modelAttributePK) {
		this.modelAttributePK = modelAttributePK;
	}
}
