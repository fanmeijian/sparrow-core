package cn.sparrow.permission.model.resource;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

import cn.sparrow.permission.model.app.SparrowApp;
import cn.sparrow.permission.model.common.AbstractSparrowEntity;
import cn.sparrow.permission.model.token.SparrowPermissionToken;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@EqualsAndHashCode(callSuper = false, onlyExplicitlyIncluded = true)
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "spr_model")
@JsonIgnoreProperties(value = {"modelAttributes","sparrowPermissionToken","dataPermissionToken"},allowGetters = true)
public class Model extends AbstractSparrowEntity {

	private static final long serialVersionUID = 1L;

	@EqualsAndHashCode.Include
	@Id
	private String name;
	private String nameTxt;
	private String remark;
	private Boolean isSystem;
	@Column(name = "app_id")
	private String appId;

	// @ManyToOne
	// @JoinColumn(name = "catalog_id")
	// private Catalog catalog;

	@JsonIgnore
	@ManyToOne
	@JoinColumn(name = "app_id", insertable = false, updatable = false)
	private SparrowApp sparrowApp;

	@JsonIgnore
	@OneToMany(targetEntity = ModelAttribute.class, cascade = CascadeType.ALL, mappedBy = "model")
	private List<ModelAttribute> modelAttributes;

	@OneToOne(cascade = CascadeType.ALL,optional=false)
	@JoinColumn(name = "permission_token_id")
	private SparrowPermissionToken sparrowPermissionToken;

	public Model(String name) {
		this.name = name;
	}

	public Model(String name, boolean isSystem) {
		this.name = name;
		this.isSystem = isSystem;
	}

}
