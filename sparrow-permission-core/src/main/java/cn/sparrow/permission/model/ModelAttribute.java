package cn.sparrow.permission.model;

import java.io.Serializable;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import cn.sparrow.permission.listener.AuditLogListener;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EntityListeners({  AuditLogListener.class })
@Data
@EqualsAndHashCode(callSuper = false, onlyExplicitlyIncluded = true)
@Entity
@Table(name = "spr_model_attribute")
public class ModelAttribute extends AbstractOperationLog implements Serializable {
  /**
   * 
   */
  private static final long serialVersionUID = 1L;

  @EqualsAndHashCode.Include
  @EmbeddedId
  ModelAttributePK id;

  private String type;
  private String nameTxt;
  private String remark;
  
  @OneToOne
  @JoinColumn(name = "permission_token_id")
  private SparrowPermissionToken permissionToken;
  
  @JsonIgnore
  @ManyToOne
  @JoinColumn(name = "model_name", referencedColumnName = "name", insertable = false, updatable = false)
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
