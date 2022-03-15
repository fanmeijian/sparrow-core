package cn.sparrow.permission.model.resource;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import org.hibernate.envers.Audited;

import cn.sparrow.permission.model.common.AbstractSparrowEntity;
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
public class UserSysrole extends AbstractSparrowEntity {
  private static final long serialVersionUID = 1L;

  @EqualsAndHashCode.Include
  @EmbeddedId
  @Audited
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
  
  public UserSysrole(String sysroleId, String username) {
	    this.id = new UserSysrolePK(username, sysroleId);
	  }

}
