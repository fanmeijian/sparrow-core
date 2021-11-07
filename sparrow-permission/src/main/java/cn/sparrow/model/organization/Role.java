package cn.sparrow.model.organization;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import cn.sparrow.model.common.AbstractSparrowUuidEntity;
import cn.sparrow.model.group.GroupRole;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "spr_role")
public class Role extends AbstractSparrowUuidEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String code;
	private String name;
	private boolean isRoot;

	
	// use for create relation at batch
	@Transient
    @JsonProperty
    private Set<OrganizationRolePK> parentIds;
	
	// the role belong to organization
	@Transient
    @JsonProperty
    private Set<String> organizationIds;
	

	@JsonIgnore
	@OneToMany(targetEntity = OrganizationRole.class, cascade = CascadeType.ALL,fetch = FetchType.LAZY, mappedBy = "role")
	private Set<OrganizationRole> organizationRoles;

	@JsonIgnore
	@OneToMany(targetEntity = GroupRole.class, cascade = CascadeType.ALL,fetch = FetchType.LAZY, mappedBy = "role")
	private Set<GroupRole> groupRoles;

	private String stat;


}