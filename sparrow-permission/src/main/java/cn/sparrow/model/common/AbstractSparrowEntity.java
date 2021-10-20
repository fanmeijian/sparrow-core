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
import cn.sparrow.model.permission.Model;
import cn.sparrow.permission.listener.AuditLogListener;
import cn.sparrow.permission.listener.ReadPermissionListener;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@EntityListeners({ ReadPermissionListener.class, AuditLogListener.class, AuditingEntityListener.class })
@MappedSuperclass
@Data
@EqualsAndHashCode(callSuper = false)
@AllArgsConstructor
@NoArgsConstructor
public class AbstractSparrowEntity extends AbstractOperationLog implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GenericGenerator(name = "id-generator", strategy = "uuid")
	@GeneratedValue(generator = "id-generator")
	protected String id;
	
	@Transient
	@Size(max = 0)
	@NotAudited
	private List<String> errorMessage = new ArrayList<String>();
	
//	@Transient
//	private Model model;
	
}
