package cn.sparrow.model.permission;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.MappedSuperclass;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Entity implementation class for Entity: AbstractModelAttributePermissionPK
 *
 */
@Data
@MappedSuperclass
@AllArgsConstructor
@NoArgsConstructor
public class AbstractModelPermissionPK implements Serializable {

	
	private static final long serialVersionUID = 1L;
	
	protected String modelName;
	@Enumerated(EnumType.STRING)
	@Column(length = 10)
	protected PermissionEnum permission;
	@Enumerated(EnumType.STRING)
	@Column(length = 10)
	protected PermissionTypeEnum permissionType;
   
}
