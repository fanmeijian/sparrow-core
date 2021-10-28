package cn.sparrow.model.group;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import cn.sparrow.model.common.AbstractSparrowEntity;
import cn.sparrow.model.common.GroupTypeEnum;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "spr_group")
public class Group extends AbstractSparrowEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String code;
	private String name;
	private String owner;
	private String stat;

	@JsonIgnore
	@OneToMany(targetEntity = GroupOrganization.class, cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "group")
	private Set<GroupOrganization> groupOrganizations;
	
	@JsonIgnore
	@OneToMany(targetEntity = GroupRole.class, cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "group")
	private Set<GroupRole> groupRoles;
	
	@JsonIgnore
	@OneToMany(targetEntity = GroupLevel.class, cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "group")
	private Set<GroupLevel> groupLevels;
	
	@JsonIgnore
	@OneToMany(targetEntity = GroupSysrole.class, cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "group")
	private Set<GroupSysrole> groupSysroles;

	@Enumerated
	private GroupTypeEnum type;


}