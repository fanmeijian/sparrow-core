package cn.sparrow.permission.model.common;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.GenerationTime;
import org.hibernate.annotations.GeneratorType;
import org.hibernate.annotations.UpdateTimestamp;
import org.hibernate.envers.NotAudited;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;

@MappedSuperclass
@Getter
public abstract class BaseLog implements Serializable {
	private static final long serialVersionUID = 1L;

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
}
