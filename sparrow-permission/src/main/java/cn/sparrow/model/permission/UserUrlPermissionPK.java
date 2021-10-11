package cn.sparrow.model.permission;

import java.io.Serializable;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Embeddable
public class UserUrlPermissionPK implements Serializable {
  // default serial version id, required for serializable classes.
  private static final long serialVersionUID = 1L;

  @Column
  private String username;

  @Column(name = "url_id")
  private String urlId;


  public UserUrlPermissionPK(String username, String urlId) {
    super();
    this.username = username;
    this.urlId = urlId;
  }

  public UserUrlPermissionPK() {
    super();
  }

  @Override
  public int hashCode() {
    return Objects.hash(urlId, username);
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj)
      return true;
    if (obj == null)
      return false;
    if (getClass() != obj.getClass())
      return false;
    UserUrlPermissionPK other = (UserUrlPermissionPK) obj;
    return Objects.equals(urlId, other.urlId) && Objects.equals(username, other.username);
  }



}
