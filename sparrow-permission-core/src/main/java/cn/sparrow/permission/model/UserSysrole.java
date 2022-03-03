package cn.sparrow.permission.model;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@EqualsAndHashCode(callSuper = false, onlyExplicitlyIncluded = true)
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "spr_user_sysrole")
@NamedQueries({
	@NamedQuery(name = "UserSysrole.findByUsername", query = "SELECT o FROM UserSysrole o WHERE o.id.username = :username") })
public class UserSysrole extends AbstractOperationLog {
  private static final long serialVersionUID = 1L;

  @EqualsAndHashCode.Include
  @EmbeddedId
  private UserSysrolePK id;
  
  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "sysrole_id", insertable = false, updatable = false)
  private Sysrole sysrole;
  
//  @ManyToOne
//  @JoinColumn(name = "menu_id", insertable = false, updatable = false)
//  private Menu menu;
  
  public UserSysrole(UserSysrolePK id) {
    this.id = id;
  }

}
