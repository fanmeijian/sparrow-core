package cn.sparrow.model.permission;

import java.io.Serializable;

import cn.sparrow.model.common.PermissionEnum;
import cn.sparrow.model.common.PermissionTypeEnum;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Entity implementation class for Entity: AbstractModelAttributePermissionPK
 *
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ModelPermissionPK implements Serializable {

	
	private static final long serialVersionUID = 1L;
	protected String modelName;
	protected PermissionEnum permission;
	protected PermissionTypeEnum permissionType;
   
}
