package cn.sparrow.model.permission;

import java.io.Serializable;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

import cn.sparrow.model.common.AbstractOperationLog;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * Entity implementation class for Entity: UserModelAttributePermission
 *
 */
@Data
@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "spr_user_model_attribute_permission")
public class UserModelAttributePermission extends AbstractOperationLog implements Serializable {

	@EmbeddedId
	private UserModelAttributePermissionPK id;
	private static final long serialVersionUID = 1L;

}
