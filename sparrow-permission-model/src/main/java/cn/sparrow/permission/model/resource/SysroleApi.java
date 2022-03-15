package cn.sparrow.permission.model.resource;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.envers.Audited;

import cn.sparrow.permission.model.common.AbstractSparrowEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@EqualsAndHashCode(callSuper = true, onlyExplicitlyIncluded = true)
@NoArgsConstructor
@Entity
@Table(name = "spr_sysrole_api")
public class SysroleApi extends AbstractSparrowEntity {
	public SysroleApi(SysroleApiPK id) {
		this.id = id;
	}

	public SysroleApi(String apiId, String sysroleId) {
		this.id = new SysroleApiPK(sysroleId, apiId);
	}

	private static final long serialVersionUID = 1L;

	@EqualsAndHashCode.Include
	@EmbeddedId
	@Audited
	private SysroleApiPK id;

	@ManyToOne
	@JoinColumn(name = "api_id", insertable = false, updatable = false)
	private SparrowApi sparrowApi;

	@ManyToOne
	@JoinColumn(name = "sysrole_id", insertable = false, updatable = false)
	private Sysrole sysrole;

}
