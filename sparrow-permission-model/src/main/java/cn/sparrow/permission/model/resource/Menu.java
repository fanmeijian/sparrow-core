package cn.sparrow.permission.model.resource;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import cn.sparrow.permission.model.common.AbstractSparrowUuidEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@EqualsAndHashCode(callSuper = true, onlyExplicitlyIncluded = true)
@Entity
@NoArgsConstructor
@Table(name = "spr_menu")
public class Menu extends AbstractSparrowUuidEntity implements Serializable {
  private static final long serialVersionUID = 1L;

  @Column(unique = true)
  private String code;
  private String name;
  private String parentId;
  private String url;
  private Boolean isSystem;
  private String previousNodeId;
  private String nextNodeId;
  private String icon;

  @JsonIgnore
  @OneToMany(mappedBy = "menu")
  private Set<UserMenu> userMenus;
  
  @JsonIgnore
  @OneToMany(mappedBy = "menu")
  private Set<SysroleMenu> sysroleMenus;

  public Menu(String id, String parentId) {
	  this.setId(id);
	  this.parentId = parentId;
  }

}
