package cn.sparrow.model.common;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityListeners;
import javax.persistence.JoinColumn;
import javax.persistence.MappedSuperclass;
import javax.persistence.OneToOne;
import javax.persistence.Transient;
import javax.validation.constraints.Size;

import org.hibernate.envers.NotAudited;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.data.rest.core.annotation.RestResource;

import com.fasterxml.jackson.annotation.JsonProperty;

import cn.sparrow.model.permission.SparrowFieldPermissionToken;
import cn.sparrow.model.permission.SparrowPermissionToken;
import cn.sparrow.permission.listener.AuditLogListener;
import cn.sparrow.permission.listener.AuthorPermissionListener;
import cn.sparrow.permission.listener.DeleterPermissionListener;
import cn.sparrow.permission.listener.EditorPermissionListener;
import cn.sparrow.permission.listener.ReadPermissionListener;
import lombok.Data;
import lombok.EqualsAndHashCode;

@MappedSuperclass
@EntityListeners({ReadPermissionListener.class, AuditLogListener.class,
    AuditingEntityListener.class, AuthorPermissionListener.class, EditorPermissionListener.class,
    DeleterPermissionListener.class})
@Data
@EqualsAndHashCode(callSuper = false, onlyExplicitlyIncluded = true)
public abstract class AbstractSparrowEntity extends AbstractOperationLog {
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
  @RestResource(exported = false)
  protected SparrowPermissionToken sparrowDataPermissionToken;
  
  @OneToOne
  @JoinColumn(name = "field_permission_token_id")
  @RestResource(exported = false)
  protected SparrowFieldPermissionToken sparrowFieldPermissionToken;

  @Transient
  @Size(max = 0)
  @NotAudited
  @JsonProperty
  private List<String> errorMessage = new ArrayList<String>();
  


}
