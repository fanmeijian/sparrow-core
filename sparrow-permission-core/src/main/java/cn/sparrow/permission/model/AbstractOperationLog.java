package cn.sparrow.permission.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import cn.sparrow.permission.listener.AuditLogListener;
import cn.sparrow.permission.listener.CurrentUser;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@MappedSuperclass
@EntityListeners({ AuditLogListener.class })
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
	
	@PrePersist
	private void preCreate() {
		this.createdBy = CurrentUser.INSTANCE.get();
		this.modifiedBy = CurrentUser.INSTANCE.get();
	}
	
	@PreUpdate
	private void preUpdate() {
		this.modifiedBy = CurrentUser.INSTANCE.get();
	}

}
