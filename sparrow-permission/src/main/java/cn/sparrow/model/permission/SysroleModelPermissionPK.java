package cn.sparrow.model.permission;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

import cn.sparrow.model.common.PermissionEnum;
import cn.sparrow.model.common.PermissionTypeEnum;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
@Embeddable
public class SysroleModelPermissionPK extends AbstractModelPermissionPK implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Column(name = "sysrole_id")
	private String sysroleId;
	
	public SysroleModelPermissionPK(String modelName, PermissionEnum permission, PermissionTypeEnum permissionType, String sysroleId) {
		this.modelName = modelName;
		this.permission = permission;
		this.permissionType = permissionType;
		this.sysroleId = sysroleId;
	}

}
