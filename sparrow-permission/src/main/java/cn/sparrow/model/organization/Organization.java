package cn.sparrow.model.organization;

import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.fasterxml.jackson.annotation.JsonProperty;

import cn.sparrow.model.common.AbstractSparrowEntity;
import cn.sparrow.model.common.OrganizationTypeEnum;
import cn.sparrow.model.group.Group;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
@Entity
@Table(name = "spr_organization")
public class Organization extends AbstractSparrowEntity {

	private static final long serialVersionUID = 1L;
	private String code;
	private String name;
	private String stat;
	private boolean root;
	// use for create relation at batch
	@Transient
	@JsonProperty
	private List<String> parentIds;
	@Enumerated(EnumType.STRING)
	private OrganizationTypeEnum type; // 公司还是部门
	
	@Transient
    @JsonProperty
    private boolean hasChildren;

	@ManyToMany(targetEntity = Role.class, cascade = CascadeType.ALL)
	@JoinTable(name = "spr_organization_role", joinColumns = {
			@JoinColumn(name = "organization_id") }, inverseJoinColumns = { @JoinColumn(name = "role_id") })
	private Set<Role> roles;

	@ManyToMany(targetEntity = Level.class, cascade = CascadeType.ALL)
	@JoinTable(name = "spr_organization_level", joinColumns = {
			@JoinColumn(name = "organization_id") }, inverseJoinColumns = { @JoinColumn(name = "level_id") })
	private Set<Level> levels;
	
	@ManyToMany(targetEntity = Group.class, cascade = CascadeType.ALL)
	@JoinTable(name = "spr_organization_group", joinColumns = {
			@JoinColumn(name = "organization_id") }, inverseJoinColumns = { @JoinColumn(name = "group_id") })
	private Set<Group> groups;
	
	@ManyToMany(mappedBy = "containOrganizations")
	private Set<Group> inGroups;
}