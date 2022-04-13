package cn.sparrow.permission.model.token;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import com.fasterxml.jackson.annotation.JsonIgnore;

import cn.sparrow.permission.model.resource.ModelAttribute;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@EqualsAndHashCode(callSuper = false, onlyExplicitlyIncluded = true)
@NoArgsConstructor
@Entity
@Table(name = "spr_field_permission_token")
public class FieldPermissionToken implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@EqualsAndHashCode.Include
	@Id
	@GenericGenerator(name = "id-generator", strategy = "uuid")
	@GeneratedValue(generator = "id-generator")
	protected String id;

	@OneToOne
	@JoinColumns({ @JoinColumn(name = "model_id", referencedColumnName = "model_id"),
			@JoinColumn(name = "attribute_id", referencedColumnName = "attribute_id") })
	private ModelAttribute modelAttribute;

	@Column(name = "permission_token_id", nullable = false)
	private String permissionTokenId;

	@OneToOne
	@JoinColumn(name = "permission_token_id", insertable = false, updatable = false)
	private SparrowPermissionToken sparrowPermissionToken;

	@JsonIgnore
	@ManyToOne
	@JoinColumn(name = "data_permission_token_id")
	private DataPermissionToken dataPermissionToken;

}
