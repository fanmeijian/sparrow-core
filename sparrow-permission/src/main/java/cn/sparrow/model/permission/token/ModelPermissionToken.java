package cn.sparrow.model.permission.token;

import java.util.List;
import java.util.Map;

import cn.sparrow.model.permission.ModelAttributePK;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ModelPermissionToken {
	private String modelName;
	private List<PermissionExpression> permissionExpressions;
	private Map<ModelAttributePK, ModelAttributePermissionToken> modelAttributePermissionTokens;

	public ModelPermissionToken(String modelName) {
		this.modelName = modelName;
	}
}