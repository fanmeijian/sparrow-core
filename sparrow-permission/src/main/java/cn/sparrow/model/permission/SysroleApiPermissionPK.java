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
public class SysroleApiPermissionPK implements Serializable {
  // default serial version id, required for serializable classes.
  private static final long serialVersionUID = 1L;

  @Column(name = "sysrole_id")
  private String sysroleId;

  @Column(name = "api_id")
  private String apiId;

}
