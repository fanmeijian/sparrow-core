package cn.sparrow.permission.model.resource;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.envers.Audited;
import org.hibernate.envers.NotAudited;

import com.fasterxml.jackson.annotation.JsonIgnore;

import cn.sparrow.permission.model.common.AbstractSparrowEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@EqualsAndHashCode(callSuper = false, onlyExplicitlyIncluded = true)
@NoArgsConstructor
@Entity
@Table(name = "spr_user_scope")
@Audited
public class UserScope extends AbstractSparrowEntity {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	@Audited
	@JoinColumns({ @JoinColumn(name = "username", insertable = false, updatable = false),
			@JoinColumn(name = "scope_id", insertable = false, updatable = false) })
	private UserScopePK id;

	@JsonIgnore
	@NotAudited
	@ManyToOne
	@JoinColumn(name = "scope_id", insertable = false, updatable = false)
	private Scope scope;

	public UserScope(String username, String scopeId) {
		this.id = new UserScopePK(username, scopeId);
	}

}
