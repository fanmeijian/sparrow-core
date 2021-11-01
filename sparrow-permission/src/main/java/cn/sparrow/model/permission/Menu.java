package cn.sparrow.model.permission;

import java.io.Serializable;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import cn.sparrow.model.common.AbstractOperationLog;
import cn.sparrow.model.common.AbstractSparrowSortableEntity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Entity
@NoArgsConstructor
@Table(name = "spr_menu")
@EntityListeners(AuditingEntityListener.class)
public class Menu extends AbstractSparrowSortableEntity<String> implements Serializable {
  private static final long serialVersionUID = 1L;

  @Id
  @GenericGenerator(name = "id-generator", strategy = "uuid")
  @GeneratedValue(generator = "id-generator")
  private String id;

  @Column(unique = true)
  private String code;
  private String name;
  private String parentId;
  private String url;
  private boolean isSystem;
//  @Column(name = "previous_node_id")
//  private String previousNodeId;
//  @Column(name = "next_node_id")
//  private String nextNodeId;
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
