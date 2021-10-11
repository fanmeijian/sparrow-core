package cn.sparrow.permission.data.model;

import java.io.Serializable;

import javax.persistence.MappedSuperclass;

import cn.sparrow.permission.data.repository.PermissionEnum;
import cn.sparrow.permission.data.repository.PermissionTypeEnum;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * Entity implementation class for Entity: AbstractModelAttributePermissionPK
 *
 */
@Data
@EqualsAndHashCode(callSuper = true)
@MappedSuperclass
@AllArgsConstructor
@NoArgsConstructor
public class AbstractDataPermissionPK extends AbstractModelPermissionPK implements Serializable {

	private static final long serialVersionUID = 1L;
	protected String dataId;

	public AbstractDataPermissionPK(String modelName, PermissionEnum permission, PermissionTypeEnum permissionType,
			String dataId) {
		this.modelName = modelName;
		this.permission = permission;
		this.permissionType = permissionType;
		this.dataId = dataId;
	}

}
