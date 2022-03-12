package cn.sparrow.permission.model.resource;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

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
	private String id;
	private String name;
	private String remark;
	private Boolean isSystem;

	@JsonIgnore
	@OneToMany(targetEntity = ModelAttribute.class, cascade = CascadeType.ALL, mappedBy = "model")
	private List<ModelAttribute> modelAttributes;

	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "permission_token_id")
	private SparrowPermissionToken sparrowPermissionToken;

	public Model(String id) {
		this.id = id;
	}

	public Model(String id, Boolean isSystem) {
		this.id = id;
		this.isSystem = isSystem;
	}

}
