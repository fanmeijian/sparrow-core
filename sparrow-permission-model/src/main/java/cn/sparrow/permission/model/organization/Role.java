package cn.sparrow.permission.model.organization;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.envers.Audited;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import cn.sparrow.permission.model.common.AbstractSparrowEntity;
import cn.sparrow.permission.model.group.GroupRole;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@EqualsAndHashCode(callSuper = true, onlyExplicitlyIncluded = true)
@NoArgsConstructor
@Entity
@Table(name = "spr_role")
public class Role extends AbstractSparrowEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@EqualsAndHashCode.Include
	@Id
	@GenericGenerator(name = "id-generator", strategy = "uuid")
	@GeneratedValue(generator = "id-generator")
	@Audited
	@JsonProperty(access = JsonProperty.Access.READ_ONLY)
	private String id;

	@Column(unique = true)
	private String code;
	private String name;
	private Boolean isRoot = true;
	private String stat;

	// use for create relation at batch
//	@Transient
//	@JsonProperty
//	private Set<OrganizationRolePK> parentIds;

	// the role belong to organization
//	@Transient
//	@JsonProperty
//	private Set<String> organizationIds;

	@JsonIgnore
	@OneToMany(targetEntity = OrganizationRole.class, cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "role")
	private Set<OrganizationRole> organizationRoles;

	@JsonIgnore
	@OneToMany(targetEntity = GroupRole.class, cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "role")
	private Set<GroupRole> groupRoles;

	public Role(String name, String code) {
		this.name = name;
		this.code = code;
	}

}