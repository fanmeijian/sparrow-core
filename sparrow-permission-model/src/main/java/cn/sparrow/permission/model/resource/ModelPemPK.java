package cn.sparrow.permission.model.resource;

import javax.persistence.Embeddable;

import cn.sparrow.permission.constant.AllowOrDeny;
import cn.sparrow.permission.constant.PermissionTargetEnum;
import cn.sparrow.permission.model.common.BasePermission;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Embeddable
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class ModelPemPK extends BasePermission {

	private static final long serialVersionUID = 1L;

	private String modelId;

	public ModelPemPK(String modelId, PermissionTargetEnum target, String targetId, AllowOrDeny type) {
		this.modelId = modelId;
		this.target = target;
		this.targetId = targetId;
		this.type = type;
	}

}
