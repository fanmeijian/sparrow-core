package cn.sparrow.model.common;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import javax.persistence.Transient;
import javax.validation.constraints.Size;
import org.hibernate.envers.NotAudited;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import com.fasterxml.jackson.annotation.JsonProperty;
import cn.sparrow.permission.listener.AuditLogListener;
import cn.sparrow.permission.listener.ReadPermissionListener;
import lombok.Data;
import lombok.EqualsAndHashCode;

@MappedSuperclass
@EntityListeners({ ReadPermissionListener.class, AuditLogListener.class, AuditingEntityListener.class })
@Data
@EqualsAndHashCode(callSuper = false, onlyExplicitlyIncluded = true)
public class AbstractSparrowEntity extends AbstractOperationLog {
  /**
   * 
   */
  private static final long serialVersionUID = 1L;

  @Transient
  @JsonProperty
  protected String modelName = this.getClass().getName();
  
  @Transient
  @Size(max = 0)
  @NotAudited
  private List<String> errorMessage = new ArrayList<String>();
}
