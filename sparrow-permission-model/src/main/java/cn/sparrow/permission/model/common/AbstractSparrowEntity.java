package cn.sparrow.permission.model.common;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.JoinColumn;
import javax.persistence.MappedSuperclass;
import javax.persistence.OneToOne;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.GenerationTime;
import org.hibernate.annotations.GeneratorType;
import org.hibernate.annotations.UpdateTimestamp;
import org.hibernate.envers.NotAudited;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import cn.sparrow.permission.model.token.DataPermissionToken;
import lombok.Data;
import lombok.EqualsAndHashCode;

@MappedSuperclass
@Data
@EqualsAndHashCode(callSuper = false, onlyExplicitlyIncluded = true)
public abstract class AbstractSparrowEntity implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Transient
	@JsonProperty(access = JsonProperty.Access.READ_ONLY)
	@NotAudited
	protected String modelName = this.getClass().getName();

	@Column(name = "created_date", insertable = true, updatable = false)
	@CreationTimestamp
	@Temporal(TemporalType.TIMESTAMP)
	@JsonProperty(access = JsonProperty.Access.READ_ONLY)
	@NotAudited
	private Date createdDate; // 创建时间

	@Column(name = "modified_date", insertable = true, updatable = true)
	@UpdateTimestamp
	@Temporal(TemporalType.TIMESTAMP)
	@JsonProperty(access = JsonProperty.Access.READ_ONLY)
	@NotAudited
	private Date modifiedDate; // 最后更新时间

	@GeneratorType(type = LoggedUserGenerator.class, when = GenerationTime.INSERT)
	@Column(name = "created_by", insertable = true, updatable = false)
	@JsonProperty(access = JsonProperty.Access.READ_ONLY)
	private String createdBy;

	@GeneratorType(type = LoggedUserGenerator.class, when = GenerationTime.ALWAYS)
	@Column(name = "modified_by", insertable = true, updatable = true)
	@JsonProperty(access = JsonProperty.Access.READ_ONLY)
	private String modifiedBy;

	@JsonProperty(access = JsonProperty.Access.READ_ONLY)
	@Column(name = "data_permission_token_id")
	private String dataPermissionTokenId;

	@OneToOne(targetEntity = DataPermissionToken.class)
	@JoinColumn(name = "data_permission_token_id", insertable = false, updatable = false)
	@NotAudited
	@JsonIgnore
	private DataPermissionToken dataPermissionToken;

}
