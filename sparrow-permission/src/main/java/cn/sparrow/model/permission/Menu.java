package cn.sparrow.model.permission;

import java.io.Serializable;
import java.util.Set;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import org.hibernate.annotations.GenericGenerator;
import cn.sparrow.model.app.SparrowApp;
import cn.sparrow.model.common.AbstractOperationLog;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "spr_menu")
public class Menu extends AbstractOperationLog implements Serializable {
  private static final long serialVersionUID = 1L;

  @Id
  @GenericGenerator(name = "id-generator", strategy = "uuid")
  @GeneratedValue(generator = "id-generator")
  private String id;

  private String name;

  private String parentId;

  private Long sort;

  private String url;

  // @JsonIgnore
//  @ManyToOne
//  @JoinColumn(name = "app_id")
//  private SparrowApp sparrowApp;
  
  @OneToMany(mappedBy = "menu")
  private Set<UserMenu> userMenus;

//  @JsonBackReference
//  @ManyToMany(fetch = FetchType.EAGER, mappedBy = "menus")
//  private Set<User> users;

}
