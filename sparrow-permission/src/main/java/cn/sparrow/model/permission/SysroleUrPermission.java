package cn.sparrow.model.permission;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import cn.sparrow.model.permission.AbstractOperationLog;
import cn.sparrow.model.url.SparrowUrl;
import lombok.Data;


@Data
@Entity
@Table(name = "spr_sysrole_url_permission")
public class SysroleUrPermission extends AbstractOperationLog {
  private static final long serialVersionUID = 1L;

  @EmbeddedId
  private SysroleUrlPermissionPK id;

  @ManyToOne
  @JoinColumn(name = "url_id", insertable = false, updatable = false)
  private SparrowUrl sparrowUrl;


}
