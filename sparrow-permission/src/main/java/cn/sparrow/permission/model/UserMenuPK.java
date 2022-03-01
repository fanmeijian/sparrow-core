package cn.sparrow.permission.model;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import lombok.Data;

@Data
@Embeddable
public class UserMenuPK implements Serializable {
  // default serial version id, required for serializable classes.
  private static final long serialVersionUID = 1L;

  private String username;
  @Column(name = "menu_id")
  private String menuId;



  public boolean equals(Object other) {
    if (this == other) {
      return true;
    }
    if (!(other instanceof UserMenuPK)) {
      return false;
    }
    UserMenuPK castOther = (UserMenuPK) other;
    return this.username.equals(castOther.username) && this.menuId.equals(castOther.menuId);
  }

  public int hashCode() {
    final int prime = 31;
    int hash = 17;
    hash = hash * prime + this.username.hashCode();
    hash = hash * prime + this.menuId.hashCode();

    return hash;
  }

  public UserMenuPK(String username, String menuId) {
    super();
    this.username = username;
    this.menuId = menuId;
  }

  public UserMenuPK() {
    super();
  }


}
