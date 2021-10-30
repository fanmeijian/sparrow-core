package cn.sparrow.model.permission;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.fasterxml.jackson.annotation.JsonIgnore;

import cn.sparrow.model.common.AbstractOperationLog;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "spr_menu")
@EntityListeners(AuditingEntityListener.class)
public class Menu extends AbstractOperationLog implements Serializable {
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
  @Column(name = "previous_node_id")
  private String previousNodeId;
  @Column(name = "next_node_id")
  private String nextNodeId;

//  @JsonIgnore
//  @OneToOne(fetch = FetchType.LAZY)
//  @JoinColumn(name = "previou_node_id", insertable = false, updatable = false)
//  private Menu previousNode;
//  
//  @JsonIgnore
//  @OneToOne(fetch = FetchType.LAZY)
//  @JoinColumn(name = "next_node_id", insertable = false, updatable = false)
//  private Menu nextNode;
  
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
