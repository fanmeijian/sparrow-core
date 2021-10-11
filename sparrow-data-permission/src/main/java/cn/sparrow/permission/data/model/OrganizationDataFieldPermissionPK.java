package cn.sparrow.permission.data.model;

import java.io.Serializable;

import javax.persistence.Embeddable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
@Embeddable
public class OrganizationDataFieldPermissionPK extends AbstractDataFieldPermissionPK implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String organizationId;
}
