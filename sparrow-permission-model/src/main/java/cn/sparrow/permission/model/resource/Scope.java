package cn.sparrow.permission.model.resource;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.envers.Audited;
import org.hibernate.envers.NotAudited;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import cn.sparrow.permission.model.common.AbstractSparrowEntity;
import cn.sparrow.permission.model.token.SparrowPermissionToken;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/*
 * 功能权限表，相当于oauth2的scope范围，可用于细粒度的控制
 * 
 */
@Data
@EqualsAndHashCode(callSuper = true, onlyExplicitlyIncluded = true)
@Entity
@Table(name = "spr_scope")
@Audited
@NoArgsConstructor
@NamedQueries({@NamedQuery(name = "Scope.findByCode", query = "SELECT s FROM Scope s WHERE s.code = :code")})
public class Scope extends AbstractSparrowEntity {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@JsonProperty(access = JsonProperty.Access.READ_ONLY)
	@EqualsAndHashCode.Include
	@Id
	@GenericGenerator(name = "id-generator", strategy = "uuid")
	@GeneratedValue(generator = "id-generator")
	private String id;

	private String name;
	@Column(unique = true, nullable = false)
	private String code; // app:module:action
	
	@Column(name = "permission_token_id")
	private String permissionTokenId;

	@JsonIgnore
	@NotAudited
	@OneToOne
	@JoinColumn(name = "permission_token_id", insertable = false, updatable = false)
	private SparrowPermissionToken sparrowPermissionToken;

	@NotAudited
	@JsonIgnore
	@OneToMany(cascade = CascadeType.REMOVE, mappedBy = "scope")
	private Set<SysroleScope> sysroleScopes;
	
	@NotAudited
	@JsonIgnore
	@OneToMany(cascade = CascadeType.REMOVE, mappedBy = "scope")
	private Set<ScopeApi> scopeApis;
	
	public Scope(String name, String code) {
		this.name = name;
		this.code = code;
	}
	
}
