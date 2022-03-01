package cn.sparrow.model.permission;

import java.util.List;

import cn.sparrow.permission.service.PermissionToken;
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
