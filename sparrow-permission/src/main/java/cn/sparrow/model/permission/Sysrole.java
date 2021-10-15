package cn.sparrow.model.permission;

import java.util.Set;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import com.fasterxml.jackson.annotation.JsonInclude;

import cn.sparrow.model.organization.Group;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@EqualsAndHashCode(callSuper = false)
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "spr_sysrole")
@NamedQuery(name = "Sysrole.findAll", query = "SELECT s FROM Sysrole s")
public class Sysrole extends AbstractSparrowEntity {
	private static final long serialVersionUID = 1L;

	private String name;

	// uni-directional many-to-many association to SwdAuthority
//	@JsonInclude(JsonInclude.Include.NON_EMPTY)
//	@ManyToMany(fetch = FetchType.LAZY)
//	@JoinTable(name = "spr_sysrole_authority", joinColumns = {
//			@JoinColumn(name = "SYSROLE_ID") }, inverseJoinColumns = { @JoinColumn(name = "AUTHORITY_ID") })
//	private Set<SparrowUrl> urls;

	// uni-directional many-to-many association to SwdMenu
	@JsonInclude(JsonInclude.Include.NON_EMPTY)
	@ManyToMany
	@JoinTable(name = "spr_sysrole_menu", joinColumns = { @JoinColumn(name = "SYSROLE_ID") }, inverseJoinColumns = {
			@JoinColumn(name = "MENU_ID") })
	private Set<Menu> menus;
	
	@ManyToMany(mappedBy = "sysroles")
	private Set<Group> groups;

}
