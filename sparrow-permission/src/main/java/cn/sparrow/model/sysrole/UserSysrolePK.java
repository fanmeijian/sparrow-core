package cn.sparrow.model.sysrole;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import lombok.Data;

@Data
@Embeddable
public class UserSysrolePK implements Serializable {
  // default serial version id, required for serializable classes.
  private static final long serialVersionUID = 1L;

  @Column(insertable = false, updatable = false)
  private String username;

  @Column(name = "SYSROLE_ID", insertable = false, updatable = false)
  private String sysroleId;


  public boolean equals(Object other) {
    if (this == other) {
      return true;
    }
    if (!(other instanceof UserSysrolePK)) {
      return false;
    }
    UserSysrolePK castOther = (UserSysrolePK) other;
    return this.username.equals(castOther.username) && this.sysroleId.equals(castOther.sysroleId);
  }

  public int hashCode() {
    final int prime = 31;
    int hash = 17;
    hash = hash * prime + this.username.hashCode();
    hash = hash * prime + this.sysroleId.hashCode();

    return hash;
  }
}
