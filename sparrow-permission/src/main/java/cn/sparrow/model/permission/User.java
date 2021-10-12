package cn.sparrow.model.permission;

import java.io.Serializable;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Transient;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonProperty;
import cn.sparrow.model.menu.Menu;
import cn.sparrow.model.sysrole.Sysrole;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "spr_user")
@NamedQuery(name = "User.findAll", query = "SELECT s FROM User s")
public class User implements Serializable {
	private static final long serialVersionUID = 1L;

//@GenericGenerator(name = "id-generator", strategy = "uuid")
//@GeneratedValue(generator = "id-generator")
	@Id
	private String username;

	@JsonProperty(value = "password", access = JsonProperty.Access.WRITE_ONLY)
	private String password;

	@Transient
	private String oldPassword;
	@Transient
	private String secondPassword;

	private byte enabled;

	// uni-directional many-to-many association to SwdAuthority
	// @LazyCollection(LazyCollectionOption.FALSE)
//	@JsonIgnoreProperties("users") // 用来防止无限循环，因为menu里面含user的列表
//	@ManyToMany(fetch = FetchType.LAZY)
//	@JoinTable(name = "spr_user_url", joinColumns = { @JoinColumn(name = "username") }, inverseJoinColumns = {
//			@JoinColumn(name = "url_id") })
//	private Set<SparrowUrl> urls;

	@JsonManagedReference
//	@JsonIgnoreProperties("users") // 用来防止无限循环，因为menu里面含user的列表
	@ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinTable(name = "spr_user_menu", joinColumns = { @JoinColumn(name = "username") }, inverseJoinColumns = {
			@JoinColumn(name = "menu_id") })
	private Set<Menu> menus;

	// uni-directional many-to-many association to SwdSysrole
	@JsonIgnoreProperties("users") // 用来防止无限循环，因为menu里面含user的列表
	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(name = "spr_user_sysrole", joinColumns = { @JoinColumn(name = "username") }, inverseJoinColumns = {
			@JoinColumn(name = "sysrole_id") })
	private Set<Sysrole> sysroles;
//
//  public void setMenus(Set<Menu> menus) {
//    this.menus.addAll(menus);
//  }
//
//  public void setAuthorities(Set<Authority> authorities) {
//    this.authorities.addAll(authorities);
//  }
//
//  public void setSysroles(Set<Sysrole> sysroles) {
//    this.sysroles.addAll(sysroles);
//  }

}
