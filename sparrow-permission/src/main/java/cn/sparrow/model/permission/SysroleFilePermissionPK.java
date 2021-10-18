package cn.sparrow.model.permission;

import java.io.Serializable;
import javax.persistence.Embeddable;
import javax.persistence.Embedded;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Embeddable
public class SysroleFilePermissionPK implements Serializable {
  private static final long serialVersionUID = 1L;
  
  @Embedded
  private FilePermissionPK filePermissionPK;
  private String sysroleId;
}
