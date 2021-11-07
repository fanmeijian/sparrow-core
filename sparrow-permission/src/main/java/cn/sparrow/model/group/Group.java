package cn.sparrow.model.group;

import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import cn.sparrow.model.common.AbstractSparrowUuidEntity;
import cn.sparrow.model.common.GroupTypeEnum;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "spr_group")
public class Group extends AbstractSparrowUuidEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String code;
	private String name;
	private String owner;
	private String stat;
	private boolean isRoot;
	@Enumerated
	private GroupTypeEnum type;

	@Transient
	@JsonProperty
	private List<String> organizationIds;
	
	@JsonIgnore
	@OneToMany(targetEntity = GroupRelation.class, cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "group")
	private Set<GroupRelation> groupRelations;

	@JsonIgnore
	@OneToMany(targetEntity = GroupOrganization.class, cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "group")
	private Set<GroupOrganization> groupOrganizations;

	@JsonIgnore
	@OneToMany(targetEntity = GroupRole.class, cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "group")
	private Set<GroupRole> groupRoles;

	@JsonIgnore
	@OneToMany(targetEntity = GroupPositionLevel.class, cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "group")
	private Set<GroupPositionLevel> groupLevels;

	@JsonIgnore
	@OneToMany(targetEntity = GroupSysrole.class, cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "group")
	private Set<GroupSysrole> groupSysroles;

}