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
public class UserDataPermissionPK extends AbstractDataPermissionPK implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String username;
	
	public UserDataPermissionPK(String modelName, PermissionEnum permission, PermissionTypeEnum permissionType,
			String dataId, String username) {
		this.modelName = modelName;
		this.permission = permission;
		this.permissionType = permissionType;
		this.dataId = dataId;
		this.username = username;
	}

}
