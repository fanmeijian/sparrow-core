package cn.sparrow.permission.model;

import java.io.Serializable;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import com.fasterxml.jackson.annotation.JsonIgnore;

import cn.sparrow.permission.listener.AuthorPermissionListener;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@EqualsAndHashCode(callSuper = true, onlyExplicitlyIncluded = true)
@Entity
@NoArgsConstructor
@Table(name = "spr_menu")
@EntityListeners({AuditingEntityListener.class, AuthorPermissionListener.class})
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
	  this.id = id;
	  this.parentId = parentId;
  }

}
