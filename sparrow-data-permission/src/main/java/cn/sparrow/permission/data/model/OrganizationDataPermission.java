package cn.sparrow.permission.data.model;

import java.io.Serializable;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * Entity implementation class for Entity: OrganizationDataPermission
 *
 */
@Data
@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "spr_organization_data_permission")

public class OrganizationDataPermission extends AbstractOperationLog implements Serializable {

	@EmbeddedId
	private OrganizationDataPermissionPK id;
	private static final long serialVersionUID = 1L;

}
