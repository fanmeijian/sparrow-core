package cn.sparrow.model.menu;

import java.io.Serializable;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import org.hibernate.annotations.GenericGenerator;
import com.fasterxml.jackson.annotation.JsonBackReference;
import cn.sparrow.model.app.SparrowApp;
import cn.sparrow.model.permission.AbstractOperationLog;
import cn.sparrow.model.permission.User;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "spr_menu")
@NamedQuery(name = "Menu.findAll", query = "SELECT s FROM Menu s")
public class Menu extends AbstractOperationLog implements Serializable {
  private static final long serialVersionUID = 1L;

  @Id
  @GenericGenerator(name = "id-generator", strategy = "uuid")
  @GeneratedValue(generator = "id-generator")
  private String id;

  private String name;

  @Column(name = "parent_id")
  private String parentId;

  private Long sort;

  private String url;

  // @JsonIgnore
  @ManyToOne
  @JoinColumn(name = "app_id")
  private SparrowApp sparrowApp;

  @JsonBackReference
  @ManyToMany(fetch = FetchType.EAGER, mappedBy = "menus")
  private Set<User> users;

}
