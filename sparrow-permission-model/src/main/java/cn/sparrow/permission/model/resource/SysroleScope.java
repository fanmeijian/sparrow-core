package cn.sparrow.permission.model.resource;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.envers.Audited;

import com.fasterxml.jackson.annotation.JsonIgnore;

import cn.sparrow.permission.model.common.AbstractSparrowEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@EqualsAndHashCode(callSuper = false, onlyExplicitlyIncluded = true)
@NoArgsConstructor
@Entity
@Table(name = "spr_sysrole_scope")
public class SysroleScope extends AbstractSparrowEntity {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Audited
	@EmbeddedId
	@JoinColumns({ @JoinColumn(name = "sysrole_id", insertable = false, updatable = false),
			@JoinColumn(name = "scope_id", insertable = false, updatable = false) })
	private SysroleScopePK id;

	@JsonIgnore
	@ManyToOne
	@JoinColumn(name = "scope_id", insertable = false, updatable = false)
	private Scope scope;

	@JsonIgnore
	@ManyToOne
	@JoinColumn(name = "sysrole_id", insertable = false, updatable = false)
	private Sysrole sysrole;

	public SysroleScope(String sysroleId, String scopeId) {
		this.id = new SysroleScopePK(sysroleId, scopeId);
	}
}
