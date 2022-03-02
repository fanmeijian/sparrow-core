package cn.sparrow.permission.model;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;


@Data
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
@Entity
@Table(name = "spr_sysrole_api")
public class SysroleApiPermission extends AbstractOperationLog {
  public SysroleApiPermission(SysroleApiPK f) {
		this.id = f;
	}

private static final long serialVersionUID = 1L;

  @EmbeddedId
  @NotNull
  private SysroleApiPK id;

  @EqualsAndHashCode.Exclude
  @ManyToOne(fetch = FetchType.EAGER)
  @JoinColumn(name = "api_id", insertable = false, updatable = false)
  private SparrowApi sparrowApi;
  
  @EqualsAndHashCode.Exclude
  @ManyToOne(fetch = FetchType.EAGER)
  @JoinColumn(name = "sysrole_id", insertable = false, updatable = false)
  private Sysrole sysrole;

  

}
