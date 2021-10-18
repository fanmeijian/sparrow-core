package cn.sparrow.model.permission;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;
import cn.sparrow.model.common.AbstractOperationLog;

@Entity
@Table(name = "spr_user_file_permission")
public class UserFilePermission extends AbstractOperationLog {

  private static final long serialVersionUID = 1L;
  @EmbeddedId
  private UserFilePermissionPK id;

}
