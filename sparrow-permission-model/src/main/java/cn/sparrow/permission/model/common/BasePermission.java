package cn.sparrow.permission.model.common;

import java.io.Serializable;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.MappedSuperclass;

import cn.sparrow.permission.constant.AllowOrDeny;
import cn.sparrow.permission.constant.PermissionTargetEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@MappedSuperclass
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public abstract class BasePermission implements Serializable {

	private static final long serialVersionUID = 1L;
	@Enumerated(EnumType.STRING)
	protected PermissionTargetEnum target;
	protected String targetId;
	@Enumerated(EnumType.STRING)
	protected AllowOrDeny type;
}
