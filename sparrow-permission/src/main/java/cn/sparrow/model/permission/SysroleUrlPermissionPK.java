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
public class SysroleUrlPermissionPK implements Serializable {
  // default serial version id, required for serializable classes.
  private static final long serialVersionUID = 1L;

  @Column(name = "sysrole_id")
  private String sysroleId;

  @Column(name = "url_id")
  private String urlId;



  public SysroleUrlPermissionPK(String sysroleId, String urlId) {
    super();
    this.sysroleId = sysroleId;
    this.urlId = urlId;
  }


  public SysroleUrlPermissionPK() {
    super();
  }


  @Override
  public int hashCode() {
    return Objects.hash(sysroleId, urlId);
  }


  @Override
  public boolean equals(Object obj) {
    if (this == obj)
      return true;
    if (obj == null)
      return false;
    if (getClass() != obj.getClass())
      return false;
    SysroleUrlPermissionPK other = (SysroleUrlPermissionPK) obj;
    return Objects.equals(sysroleId, other.sysroleId) && Objects.equals(urlId, other.urlId);
  }



}
