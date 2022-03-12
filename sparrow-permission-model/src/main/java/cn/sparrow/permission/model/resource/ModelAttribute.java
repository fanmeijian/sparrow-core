package cn.sparrow.permission.model.resource;

import javax.persistence.CascadeType;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.envers.Audited;
import org.hibernate.envers.NotAudited;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import cn.sparrow.permission.model.common.AbstractSparrowEntity;
import cn.sparrow.permission.model.token.SparrowPermissionToken;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false, onlyExplicitlyIncluded = true)
@Entity
@Table(name = "spr_model_attribute")
@Audited
@JsonIgnoreProperties(value = { "sparrowPermissionToken", "dataPermissionToken" }, allowGetters = true)
public class ModelAttribute extends AbstractSparrowEntity {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@EqualsAndHashCode.Include
	@EmbeddedId
	ModelAttributePK id;

	private String type;
	private String name;
	private String remark;

	@NotAudited
	@OneToOne(cascade = CascadeType.PERSIST)
	@JoinColumn(name = "permission_token_id")
	private SparrowPermissionToken sparrowPermissionToken;

	@NotAudited
	@JsonIgnore
	@ManyToOne
	@JoinColumn(name = "model_id", referencedColumnName = "id", insertable = false, updatable = false)
	private Model model;

	public ModelAttribute(ModelAttributePK id) {
		super();
		this.id = id;
	}

	public ModelAttribute(ModelAttributePK id, String type) {
		super();
		this.id = id;
		this.type = type;
	}

	public ModelAttribute() {
		super();
	}
}
