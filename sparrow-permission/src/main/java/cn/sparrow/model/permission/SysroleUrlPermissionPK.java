package cn.sparrow.model.permission;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Embeddable
public class SysroleUrlPermissionPK implements Serializable {
  // default serial version id, required for serializable classes.
  private static final long serialVersionUID = 1L;

  @Column(name = "sysrole_id")
  private String sysroleId;

  @Column(name = "url_id")
  private String urlId;

}
