package cn.sparrow.model.permission;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class FilePermission {
  private List<UserFilePermissionPK> userFilePermissionPKs;
  private List<SysroleFilePermissionPK> sysroleFilePermissionPKs;
}
