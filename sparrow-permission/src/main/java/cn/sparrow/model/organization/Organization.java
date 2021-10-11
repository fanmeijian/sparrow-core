package cn.sparrow.model.organization;

import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import cn.sparrow.model.permission.AbstractSparrowEntity;
import lombok.Data;

/**
 * ��֯ʵ�壬
 * 
 * @author fansword
 * @version 1.0
 * @created 28-Sep-2021 4:56:07 PM
 */
@Data
@Entity
@Table(name = "spr_organization")
public class Organization extends AbstractSparrowEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String code;
	private String name;
	private String parentId;
	private String stat;
	@ManyToOne
	@JoinColumn(name = "type")
	private OrganizationType type; // 公司还是部门

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

	public Organization() {

	}

}