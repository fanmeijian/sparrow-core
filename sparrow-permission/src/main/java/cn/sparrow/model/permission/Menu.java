package cn.sparrow.model.permission;

import java.io.Serializable;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import cn.sparrow.model.common.AbstractSparrowUuidEntity;
import cn.sparrow.permission.listener.AuthorPermissionListener;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
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
  private boolean isSystem;
  private String previousNodeId;
  private String nextNodeId;
  private String icon;

  // @JsonIgnore
  // @ManyToOne
  // @JoinColumn(name = "app_id")
  // private SparrowApp sparrowApp;

  @OneToMany(mappedBy = "menu")
  private Set<UserMenu> userMenus;

  public Menu(String id, String parentId) {
	  this.id = id;
	  this.parentId = parentId;
  }

}
