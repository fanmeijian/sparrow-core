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
public class ScopeGroupPemPK extends BasePermission {

	private static final long serialVersionUID = 1L;

	private String scopeGroupId;

	public ScopeGroupPemPK(String scopeGroupId, PermissionTargetEnum target, String targetId, AllowOrDeny type) {
		this.scopeGroupId = scopeGroupId;
		this.target = target;
		this.targetId = targetId;
		this.type = type;
	}

}
