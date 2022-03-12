package cn.sparrow.permission.model.resource;

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

  public UserMenuPK(String username, String menuId) {
    super();
    this.username = username;
    this.menuId = menuId;
  }

  public UserMenuPK() {
    super();
  }


}
