package cn.sparrow.permission.model.resource;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.envers.Audited;

import com.fasterxml.jackson.annotation.JsonIgnore;

import cn.sparrow.permission.model.common.AbstractSparrowEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.EqualsAndHashCode.Include;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "spr_scope_api")
@EqualsAndHashCode(callSuper = false, onlyExplicitlyIncluded = true)
@Data
@NoArgsConstructor
public class ScopeApi extends AbstractSparrowEntity{
	
	private static final long serialVersionUID = 1L;
	@EmbeddedId
	@Audited
	@Include
	private ScopeApiPK id;
	
	@JsonIgnore
	@ManyToOne
	@JoinColumn(name = "scope_id", insertable = false, updatable = false)
	private Scope scope;
	
	@JsonIgnore
	@ManyToOne
	@JoinColumn(name = "api_id", insertable = false, updatable = false)
	private SparrowApi sparrowApi;
	
	public ScopeApi(String scopeId, String apiId) {
		this.id =  new ScopeApiPK(scopeId, apiId);
	}
}
