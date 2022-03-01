package cn.sparrow.model.permission;

import java.io.Serializable;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.PostLoad;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;
import javax.persistence.Transient;
import org.springframework.util.SerializationUtils;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import cn.sparrow.model.app.SparrowApp;
import cn.sparrow.model.common.AbstractOperationLog;
import cn.sparrow.permission.service.PermissionToken;
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
public class Model extends AbstractOperationLog implements Serializable {

	private static final long serialVersionUID = 1L;

	@EqualsAndHashCode.Include
	@Id
	private String name;
	private String nameTxt;
	private String remark;
	private boolean isSystem;
	@Column(name = "app_id")
	private String appId;

	// @ManyToOne
	// @JoinColumn(name = "catalog_id")
	// private Catalog catalog;

	@ManyToOne
	@JoinColumn(name = "app_id", insertable = false, updatable = false)
	private SparrowApp sparrowApp;

	@JsonIgnore
	@OneToMany(targetEntity = ModelAttribute.class, cascade = CascadeType.ALL, mappedBy = "model")
	private List<ModelAttribute> modelAttributes;

	@Lob
	@Column(name = "model_permission_token")
	private byte[] modelPermissionTokenByteArray;

	@Transient
	@JsonProperty
	private PermissionToken modelPermissionToken;

	@PostLoad
	private void postLoad() {
		if (modelPermissionTokenByteArray != null)
			this.modelPermissionToken = (PermissionToken) SerializationUtils.deserialize(modelPermissionTokenByteArray);
	}

	@PreUpdate
	@PrePersist
	private void beforeSave() {
		this.modelPermissionTokenByteArray = SerializationUtils.serialize(modelPermissionToken);
	}

	public Model(String name) {
		this.name = name;
	}

	public Model(String name, boolean isSystem) {
		this.name = name;
		this.isSystem = isSystem;
	}

}
