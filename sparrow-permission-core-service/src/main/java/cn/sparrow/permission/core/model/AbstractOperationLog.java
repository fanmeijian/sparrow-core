package cn.sparrow.permission.core.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@MappedSuperclass
public abstract class AbstractOperationLog implements Serializable {
	private static final long serialVersionUID = 1L;

	@Column(name = "created_by", insertable = true, updatable = false)
	private String createdBy; // 创建者

	@Column(name = "created_date", insertable = true, updatable = false)
	@CreationTimestamp
	@Temporal(TemporalType.TIMESTAMP)
	private Date createdDate; // 创建时间

	@Column(name = "modified_by", insertable = true, updatable = true)
	private String modifiedBy; // 最后更新者

	@Column(name = "modified_date", insertable = true, updatable = true)
	@UpdateTimestamp
	@Temporal(TemporalType.TIMESTAMP)
	private Date modifiedDate; // 最后更新时间
}
