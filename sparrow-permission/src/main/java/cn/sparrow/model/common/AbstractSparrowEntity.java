package cn.sparrow.model.common;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.Transient;
import javax.validation.constraints.Size;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.envers.NotAudited;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.fasterxml.jackson.annotation.JsonProperty;

import cn.sparrow.permission.listener.AuditLogListener;
import cn.sparrow.permission.listener.ReadPermissionListener;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@EntityListeners({ ReadPermissionListener.class, AuditLogListener.class, AuditingEntityListener.class })
@MappedSuperclass
@Data
@EqualsAndHashCode(callSuper = false, onlyExplicitlyIncluded = true)
@AllArgsConstructor
@NoArgsConstructor
public abstract class AbstractSparrowEntity extends AbstractOperationLog implements Serializable {
	private static final long serialVersionUID = 1L;

	@EqualsAndHashCode.Include
	@Id
	@GenericGenerator(name = "id-generator", strategy = "uuid")
	@GeneratedValue(generator = "id-generator")
	protected String id;
	
	@Transient
	@JsonProperty
	protected String modelName = this.getClass().getName();
	
	@Transient
	@Size(max = 0)
	@NotAudited
	private List<String> errorMessage = new ArrayList<String>();
	
//	@Transient
//	private Model model;
	
}
