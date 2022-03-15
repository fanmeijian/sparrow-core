package cn.sparrow.permission.model.group;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.PrePersist;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.envers.Audited;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import cn.sparrow.permission.constant.GroupTypeEnum;
import cn.sparrow.permission.model.common.AbstractSparrowEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@EqualsAndHashCode(callSuper = true, onlyExplicitlyIncluded = true)
@NoArgsConstructor
@Entity
@Table(name = "spr_group")
public class Group extends AbstractSparrowEntity {

	/**
	 * 
	 */
	@EqualsAndHashCode.Include
	@Id
	@GenericGenerator(name = "id-generator", strategy = "uuid")
	@GeneratedValue(generator = "id-generator")
	@Audited
	@JsonProperty(access = JsonProperty.Access.READ_ONLY)
	private String id;
	private static final long serialVersionUID = 1L;
	@Column(unique = true)
	@Audited
	private String code;
	@Audited
	private String name;
	@Audited
	private String owner;
	@Audited
	private String stat;
	@Audited
	private Boolean isRoot;
	@Audited
	@Enumerated
	private GroupTypeEnum type;

//	@Transient
//	@JsonProperty
//	private List<String> organizationIds;
	
//	@JsonIgnore
//	@OneToMany(targetEntity = GroupRelation.class, cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "group")
//	private Set<GroupRelation> groupRelations;

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

	public Group(String name, String code){
		this.name = name;
		this.code = code;
	}
	
	@PrePersist
	private void preSave() {
		if (isRoot == null) {
			isRoot = true;
		}
	}
}