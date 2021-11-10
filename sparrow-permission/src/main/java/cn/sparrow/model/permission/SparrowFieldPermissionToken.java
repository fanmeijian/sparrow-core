package cn.sparrow.model.permission;

import java.util.Map;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.PostLoad;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;
import javax.persistence.Transient;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.util.SerializationUtils;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import cn.sparrow.model.common.AbstractOperationLog;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@EqualsAndHashCode(callSuper = false, onlyExplicitlyIncluded = true)
@NoArgsConstructor
@Entity
@Table(name = "spr_field_permission_token")
public class SparrowFieldPermissionToken extends AbstractOperationLog {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@EqualsAndHashCode.Include
	@Id
	@GenericGenerator(name = "id-generator", strategy = "uuid")
	@GeneratedValue(generator = "id-generator")
	protected String id;

	@JsonIgnore
	@Lob
	@Column(name = "permission_token")
	private byte[] permissionTokenByteArray;

	@Transient
	@JsonProperty
	private Map<ModelAttributePK, PermissionToken> permissionToken;

	@SuppressWarnings("unchecked")
	@PostLoad
	private void postLoad() {
		this.permissionToken = (Map<ModelAttributePK, PermissionToken>) SerializationUtils
				.deserialize(permissionTokenByteArray);
	}

	@PreUpdate
	@PrePersist
	private void beforeSave() {
		this.permissionTokenByteArray = SerializationUtils.serialize(permissionToken);
	}

	public SparrowFieldPermissionToken(Map<ModelAttributePK, PermissionToken> permissionToken) {
		this.permissionToken = permissionToken;
	}

}
