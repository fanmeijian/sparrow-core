package cn.sparrow.permission.data.model;

import java.io.Serializable;

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
public class AbstractModelAttributePermissionPK extends AbstractModelPermissionPK implements Serializable {

	private static final long serialVersionUID = 1L;
	protected String attributeName;

	public AbstractModelAttributePermissionPK() {
		super();
	}

}
