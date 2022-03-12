package cn.sparrow.permission.model.resource;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
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

/*
 * 功能权限表，相当于oauth2的scope范围，可用于细粒度的控制
 * 
 */
@Data
@EqualsAndHashCode(callSuper = true, onlyExplicitlyIncluded = true)
@Entity
@Table(name = "spr_scope")
@Audited
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
	
	@JsonIgnore
	@NotAudited
	@OneToOne
	@JoinColumn(name = "permission_token_id")
	private SparrowPermissionToken sparrowPermissionToken;
}
