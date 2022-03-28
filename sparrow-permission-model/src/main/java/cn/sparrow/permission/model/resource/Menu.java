package cn.sparrow.permission.model.resource;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.envers.Audited;
import org.hibernate.envers.NotAudited;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import cn.sparrow.permission.model.common.AbstractSparrowEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@EqualsAndHashCode(callSuper = true, onlyExplicitlyIncluded = true)
@Entity
@NoArgsConstructor
@Table(name = "spr_menu")
@JsonIgnoreProperties(value = "dataPermissionToken", allowGetters = true)
@Audited
public class Menu extends AbstractSparrowEntity {

	private static final long serialVersionUID = 1L;

	@JsonProperty(access = JsonProperty.Access.READ_ONLY)
	@EqualsAndHashCode.Include
	@Id
	@GenericGenerator(name = "id-generator", strategy = "uuid")
	@GeneratedValue(generator = "id-generator")
	private String id;

	@Column(unique = true, nullable = false)
	private String code;
	private String name;
	private String parentId;
	private String url;
	private Boolean isSystem;
	private String previousNodeId;
	private String nextNodeId;
	private String icon;

	@NotAudited
	@JsonIgnore
	@OneToMany(mappedBy = "menu")
	private Set<UserMenu> userMenus;

	@NotAudited
	@JsonIgnore
	@OneToMany(mappedBy = "menu", cascade = CascadeType.REMOVE)
	private Set<SysroleMenu> sysroleMenus;

	public Menu(String code, String parentId) {
		this.code = code;
		this.parentId = parentId;
	}

}
