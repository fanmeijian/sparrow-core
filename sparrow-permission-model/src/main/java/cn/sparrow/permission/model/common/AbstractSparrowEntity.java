package cn.sparrow.permission.model.common;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.JoinColumn;
import javax.persistence.MappedSuperclass;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;
import javax.validation.constraints.Size;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.hibernate.envers.NotAudited;

import com.fasterxml.jackson.annotation.JsonProperty;

import cn.sparrow.permission.model.token.DataPermissionToken;
import lombok.Data;
import lombok.EqualsAndHashCode;

@MappedSuperclass
@Data
@EqualsAndHashCode(callSuper = false, onlyExplicitlyIncluded = true)
public abstract class AbstractSparrowEntity implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Transient
	@JsonProperty
	protected String modelName = this.getClass().getName();
	
	@Column(name = "created_date", insertable = true, updatable = false)
	@CreationTimestamp
	@Temporal(TemporalType.TIMESTAMP)
	private Date createdDate; // 创建时间
	
	@Column(name = "modified_date", insertable = true, updatable = true)
	@UpdateTimestamp
	@Temporal(TemporalType.TIMESTAMP)
	private Date modifiedDate; // 最后更新时间

//  @Transient
//  private Model model;

	@OneToOne(targetEntity = DataPermissionToken.class)
	@JoinColumn(name = "data_permission_token_id")
	protected DataPermissionToken dataPermissionToken;

	@Transient
	@Size(max = 0)
	@NotAudited
	@JsonProperty
	private List<String> errorMessage = new ArrayList<String>();

}
