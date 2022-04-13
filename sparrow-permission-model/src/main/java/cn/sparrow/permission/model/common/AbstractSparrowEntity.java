package cn.sparrow.permission.model.common;

import javax.persistence.Column;
import javax.persistence.JoinColumn;
import javax.persistence.MappedSuperclass;
import javax.persistence.OneToOne;
import javax.persistence.Transient;

import org.hibernate.envers.NotAudited;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import cn.sparrow.permission.model.token.DataPermissionToken;
import lombok.Getter;
import lombok.Setter;

@MappedSuperclass
@Getter
@Setter
@JsonIgnoreProperties(value = { "permissionCheckResult" }, allowGetters = true)
public abstract class AbstractSparrowEntity extends BaseLog {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Transient
	@JsonProperty(access = JsonProperty.Access.READ_ONLY)
	@NotAudited
	private String modelName = this.getClass().getName();

	@JsonProperty(access = JsonProperty.Access.READ_ONLY)
	@Column(name = "data_permission_token_id")
	private String dataPermissionTokenId;

	@OneToOne(targetEntity = DataPermissionToken.class)
	@JoinColumn(name = "data_permission_token_id", insertable = false, updatable = false)
	@NotAudited
	@JsonIgnore
	private DataPermissionToken dataPermissionToken;

	// 用于检查数据字段权限返回检查结果，因为不能直接中断，要正常返回。
	@Transient
	private PermissionCheckResult permissionCheckResult;
}
