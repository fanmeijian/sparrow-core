package cn.sparrow.model.permission;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import lombok.Getter;
import lombok.Setter;

/**
 * Entity implementation class for Entity: AbstractModelAttributePermissionPK
 *
 */
@MappedSuperclass
@Getter
@Setter
public class AbstractDataFieldPermissionPK extends AbstractModelAttributePermissionPK implements Serializable {

	private static final long serialVersionUID = 1L;
	@Column(length = 32)
	protected String dataId;

	public AbstractDataFieldPermissionPK() {
		super();
	}

}
