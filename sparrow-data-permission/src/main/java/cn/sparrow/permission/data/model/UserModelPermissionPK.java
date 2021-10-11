package cn.sparrow.permission.data.model;

import java.io.Serializable;

import javax.persistence.Embeddable;

import cn.sparrow.permission.data.repository.PermissionEnum;
import cn.sparrow.permission.data.repository.PermissionTypeEnum;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
@Embeddable
public class UserModelPermissionPK extends AbstractModelPermissionPK implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String username;
	
	public UserModelPermissionPK(String modelName, PermissionEnum permission, PermissionTypeEnum permissionType, String username) {
		this.modelName = modelName;
		this.permission = permission;
		this.permissionType = permissionType;
		this.username = username;
	}
}
