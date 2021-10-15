package cn.sparrow.model.organization;

import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import cn.sparrow.model.common.AbstractSparrowEntity;
import cn.sparrow.model.common.Group;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * ��֯ʵ�壬
 * 
 * @author fansword
 * @version 1.0
 * @created 28-Sep-2021 4:56:07 PM
 */
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
	@Enumerated(EnumType.STRING)
	private OrganizationTypeEnum type; // 公司还是部门

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