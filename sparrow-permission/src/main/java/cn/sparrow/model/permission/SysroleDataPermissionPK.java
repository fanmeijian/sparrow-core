package cn.sparrow.model.permission;

import java.io.Serializable;
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
public class SysroleDataPermissionPK extends AbstractDataPermissionPK implements Serializable {

	private static final long serialVersionUID = 1L;
	private String sysroleId;

	public SysroleDataPermissionPK(String modelName, PermissionEnum permission, PermissionTypeEnum permissionType,
			String dataId, String sysroleId) {
		this.modelName = modelName;
		this.permission = permission;
		this.permissionType = permissionType;
		this.dataId = dataId;
		this.sysroleId = sysroleId;
	}
}
