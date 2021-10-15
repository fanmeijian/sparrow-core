package cn.sparrow.model.permission;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@EqualsAndHashCode(callSuper = false)
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "spr_user_sysrole")
@NamedQuery(name = "UserSysrole.findAll", query = "SELECT s FROM UserSysrole s")
public class UserSysrole extends AbstractOperationLog {
  private static final long serialVersionUID = 1L;

  @EmbeddedId
  private UserSysrolePK id;
  
//  @ManyToOne
//  @JoinColumn(name = "menu_id", insertable = false, updatable = false)
//  private Menu menu;

}
