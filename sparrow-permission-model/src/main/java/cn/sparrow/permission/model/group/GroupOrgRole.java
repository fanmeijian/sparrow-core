package cn.sparrow.permission.model.group;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import org.hibernate.envers.Audited;

import com.fasterxml.jackson.annotation.JsonIgnore;

import cn.sparrow.permission.model.common.AbstractSparrowEntity;
import cn.sparrow.permission.model.organization.Organization;
import cn.sparrow.permission.model.organization.Role;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@EqualsAndHashCode(callSuper = false, onlyExplicitlyIncluded = true)
@NoArgsConstructor
@Entity
@Table(name = "spr_group_org_role")
@NamedQueries({
		@NamedQuery(name = "GroupOrgRole.findByOrgRoleId", query = "SELECT gor FROM GroupOrgRole gor WHERE gor.id.roleId=:roleId AND gor.id.orgId=:orgId") })
public class GroupOrgRole extends AbstractSparrowEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@EqualsAndHashCode.Include
	@EmbeddedId
	@Audited
	private GroupOrgRolePK id;

	@JsonIgnore
	@ManyToOne
	@JoinColumn(name = "group_id", insertable = false, updatable = false)
	private Group group;

	@JsonIgnore
	@ManyToOne
	@JoinColumn(name = "role_id", insertable = false, updatable = false)
	private Role role;

	@JsonIgnore
	@ManyToOne
	@JoinColumn(name = "org_id", insertable = false, updatable = false)
	private Organization organization;

	public GroupOrgRole(GroupOrgRolePK groupOrgRolePK) {
		this.id = groupOrgRolePK;
	}

	public GroupOrgRole(String groupId, String roleId, String orgId) {
		this.id = new GroupOrgRolePK(groupId, roleId, orgId);
	}

}
