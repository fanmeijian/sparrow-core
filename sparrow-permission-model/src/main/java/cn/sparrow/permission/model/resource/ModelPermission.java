package cn.sparrow.permission.model.resource;

import java.util.List;

import cn.sparrow.permission.model.token.PermissionToken;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ModelPermission {
	private List<String> modelName;
	private PermissionToken permissionToken;
}
