package cn.sparrow.model.permission;

import java.io.Serializable;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * Entity implementation class for Entity: SysroleDataPermission
 *
 */
@Data
@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "spr_sysrole_data_field_permission")
public class SysroleDataFieldPermission extends AbstractOperationLog implements Serializable {

	@EmbeddedId
	private SysroleDataFieldPermissionPK id;
	private static final long serialVersionUID = 1L;

}
