package cn.sparrow.model.organization;

import java.util.Set;
import javax.persistence.Entity;
import javax.persistence.Enumerated;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import cn.sparrow.model.permission.AbstractSparrowEntity;
import cn.sparrow.model.sysrole.Sysrole;
import lombok.Getter;
import lombok.Setter;

/**
 * @author fansword
 * @version 1.0
 * @created 28-Sep-2021 4:56:06 PM
 */

@Getter
@Setter
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
	private String parentId;
	private String stat;

	@ManyToMany(mappedBy = "groups")
	private Set<Organization> organizations;
	
	@ManyToMany
	@JoinTable(name = "spr_group_organization", joinColumns = {
			@JoinColumn(name = "group_id") }, inverseJoinColumns = { @JoinColumn(name = "organization_id") })
	private Set<Organization> containOrganizations;
	
	@ManyToMany
	@JoinTable(name = "spr_group_role", joinColumns = {
			@JoinColumn(name = "group_id") }, inverseJoinColumns = { @JoinColumn(name = "role_id") })
	private Set<Role> roles;
	
	@ManyToMany
	@JoinTable(name = "spr_group_level", joinColumns = {
			@JoinColumn(name = "group_id") }, inverseJoinColumns = { @JoinColumn(name = "level_id") })
	private Set<Level> levels;
	
	@ManyToMany
	@JoinTable(name = "spr_group_sysrole", joinColumns = {
			@JoinColumn(name = "group_id") }, inverseJoinColumns = { @JoinColumn(name = "sysrole_id") })
	private Set<Sysrole> sysroles;

	@Enumerated
	private GroupTypeEnum type;

	public Group() {

	}

}