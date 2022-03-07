package cn.sparrow.permission.model.common;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.JoinColumn;
import javax.persistence.MappedSuperclass;
import javax.persistence.OneToOne;
import javax.persistence.Transient;
import javax.validation.constraints.Size;

import org.hibernate.boot.model.IdentifierGeneratorDefinition.Builder;
import org.hibernate.envers.NotAudited;

import com.fasterxml.jackson.annotation.JsonProperty;

import cn.sparrow.permission.model.token.DataPermissionToken;
import lombok.Data;
import lombok.EqualsAndHashCode;

@MappedSuperclass
@Data
@EqualsAndHashCode(callSuper = false, onlyExplicitlyIncluded = true)
public abstract class AbstractSparrowEntity extends Builder implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Transient
	@JsonProperty
	protected String modelName = this.getClass().getName();

//  @Transient
//  private Model model;

	@OneToOne
	@JoinColumn(name = "data_permission_token_id")
	protected DataPermissionToken dataPermissionToken;

	@Transient
	@Size(max = 0)
	@NotAudited
	@JsonProperty
	private List<String> errorMessage = new ArrayList<String>();

}
