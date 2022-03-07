package cn.sparrow.permission.model.token;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.OneToOne;
import javax.persistence.PostLoad;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.apache.commons.lang3.SerializationUtils;
import org.hibernate.annotations.GenericGenerator;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import cn.sparrow.permission.model.resource.Model;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@EqualsAndHashCode(callSuper = false, onlyExplicitlyIncluded = true)
@NoArgsConstructor
@Entity
@Table(name = "spr_permission_token")
public class SparrowPermissionToken implements Serializable {
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
	@Column(name = "permission_token", nullable = false)
	private byte[] permissionTokenByteArray;

	@OneToOne(mappedBy = "sparrowPermissionToken")
	private Model model;
	
	@Transient
	@JsonProperty
	private PermissionToken permissionToken;

	@PostLoad
	private void postLoad() {
		this.permissionToken = (PermissionToken) SerializationUtils.deserialize(permissionTokenByteArray);
	}

	@PreUpdate
	@PrePersist
	private void beforeSave() {
		this.permissionTokenByteArray = SerializationUtils.serialize(permissionToken);
	}

	public void setPermissionToken(PermissionToken permissionToken) {
		this.permissionToken = permissionToken;
		this.permissionTokenByteArray = SerializationUtils.serialize(permissionToken);
	}

	public SparrowPermissionToken(PermissionToken permissionToken) {
		this.permissionToken = permissionToken;
		this.permissionTokenByteArray = SerializationUtils.serialize(permissionToken);
	}

}
