package cn.sparrow.model.permission;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import cn.sparrow.model.common.AbstractOperationLog;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;


@EntityListeners({  AuditingEntityListener.class })
@Data
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
@Entity
@Table(name = "spr_sysrole_url_permission")
public class SysroleUrlPermission extends AbstractOperationLog {
  public SysroleUrlPermission(SysroleUrlPermissionPK f) {
		this.id = f;
	}

private static final long serialVersionUID = 1L;

  @EmbeddedId
  @NotNull
  private SysroleUrlPermissionPK id;

  @EqualsAndHashCode.Exclude
  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "url_id", insertable = false, updatable = false)
  private SparrowUrl sparrowUrl;
  
  @EqualsAndHashCode.Exclude
  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "sysrole_id", insertable = false, updatable = false)
  private Sysrole sysrole;

  

}
