package cn.sparrow.permission.model.resource;

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
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;
import org.hibernate.envers.Audited;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

import cn.sparrow.permission.model.common.AbstractSparrowEntity;
import cn.sparrow.permission.model.group.GroupSysrole;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@EqualsAndHashCode(callSuper = false, onlyExplicitlyIncluded = true)
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "spr_sysrole")
@JsonIgnoreProperties(value = {"dataPermissionToken","id"},allowGetters = true)
public class Sysrole extends AbstractSparrowEntity {
	private static final long serialVersionUID = 1L;

	@EqualsAndHashCode.Include
	@Id
	@GenericGenerator(name = "id-generator", strategy = "uuid")
	@GeneratedValue(generator = "id-generator")
	@Audited
	@JsonProperty(access = Access.READ_ONLY)
	private String id;

	@Audited
	private String name;
	@Audited
	@Column(unique = true)
	private String code;
	@Audited
	private Boolean isSystem;

	@JsonIgnore
	@OneToMany(fetch = FetchType.LAZY, targetEntity = SysroleMenu.class, cascade = CascadeType.ALL, mappedBy = "sysrole")
	private Set<SysroleMenu> sysroleMenus;

	@JsonIgnore
	@OneToMany(targetEntity = GroupSysrole.class, cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "sysrole")
	private Set<GroupSysrole> groupSysroles;

	@JsonIgnore
	@OneToMany(mappedBy = "sysrole", cascade = CascadeType.ALL)
	private Set<UserSysrole> userSysroles;

	@JsonIgnore
	@OneToMany
	@LazyCollection(LazyCollectionOption.TRUE)
	private Set<SysroleApi> sysroleApiPermissions;

	public Sysrole(String name, String code) {
		super();
		this.name = name;
		this.code = code;
	}
}
