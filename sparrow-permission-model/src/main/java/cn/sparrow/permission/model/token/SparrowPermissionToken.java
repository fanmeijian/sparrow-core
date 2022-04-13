package cn.sparrow.permission.model.token;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.OneToOne;
import javax.persistence.PostLoad;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.GenericGenerator;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

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
	private String id;
	private String modelId;

	@JsonIgnore
	@OneToOne(mappedBy = "sparrowPermissionToken")
	@JoinColumn(name = "model_id", insertable = false, updatable = false)
	private Model model;

	@Transient
	@JsonProperty
	private PermissionToken permissionToken;

	@Lob
	@JsonProperty(access = Access.WRITE_ONLY)
	private String permissonTokenString;

	@PostLoad
	private void postLoad() {
		try {
			this.permissionToken = new ObjectMapper().readValue(permissonTokenString, PermissionToken.class);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
	}

	@PreUpdate
	@PrePersist
	private void beforeSave() {
		try {
			this.permissonTokenString = new ObjectMapper().writeValueAsString(this.permissionToken);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
	}

	public void setPermissionToken(PermissionToken permissionToken) {
		this.permissionToken = permissionToken;
		try {
			this.permissonTokenString = new ObjectMapper().writeValueAsString(this.permissionToken);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
	}

	public SparrowPermissionToken(PermissionToken permissionToken) {
		this.permissionToken = permissionToken;
		try {
			this.permissonTokenString = new ObjectMapper().writeValueAsString(this.permissionToken);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
	}

}
