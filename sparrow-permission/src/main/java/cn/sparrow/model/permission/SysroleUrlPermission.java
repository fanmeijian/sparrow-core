package cn.sparrow.model.permission;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import cn.sparrow.model.common.AbstractOperationLog;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;


@Data
@EqualsAndHashCode(callSuper = false)
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "spr_sysrole_url_permission")
public class SysroleUrlPermission extends AbstractOperationLog {
  public SysroleUrlPermission(SysroleUrlPermissionPK f) {
		this.id = f;
	}

private static final long serialVersionUID = 1L;

  @EmbeddedId
  private SysroleUrlPermissionPK id;

  @ManyToOne
  @JoinColumn(name = "url_id", insertable = false, updatable = false)
  private SparrowUrl sparrowUrl;


}
