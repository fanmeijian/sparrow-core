package cn.sparrow.model.common;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;

import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@MappedSuperclass
public abstract class AbstractOperationLog implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Column(name = "created_by", insertable = true, updatable = false)
	@CreatedBy
	private String createdBy; // 创建者
	
	@Column(name = "created_date", insertable = true, updatable = false)
	@CreatedDate
	private Date createdDate; // 创建时间
	
	@Column(name = "modified_by", insertable = true, updatable = true)
	@LastModifiedBy
	private String modifiedBy; // 最后更新者

	@Column(name = "modified_date", insertable = true, updatable = true)
	@LastModifiedDate
	private Date modifiedDate; // 最后更新时间

}
