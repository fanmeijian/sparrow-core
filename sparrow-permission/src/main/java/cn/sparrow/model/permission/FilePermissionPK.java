package cn.sparrow.model.permission;

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
public class FilePermissionPK extends AbstractPermission {

	protected String fileId;

	public FilePermissionPK(String fileId, PermissionEnum permission, PermissionTypeEnum permissionType) {
		this.permission = permission;
		this.permissionType = permissionType;
		this.fileId = fileId;
	}
}
