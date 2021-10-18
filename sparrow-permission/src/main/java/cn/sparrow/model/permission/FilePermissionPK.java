package cn.sparrow.model.permission;

import javax.persistence.Embeddable;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
@Embeddable
public class FilePermissionPK extends AbstractPermission {
  protected String fileId;
}
