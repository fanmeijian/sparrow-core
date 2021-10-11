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
public class AbstractDataFieldPermissionPK extends AbstractModelAttributePermissionPK implements Serializable {

	private static final long serialVersionUID = 1L;
	protected String dataId;

	public AbstractDataFieldPermissionPK() {
		super();
	}

}
