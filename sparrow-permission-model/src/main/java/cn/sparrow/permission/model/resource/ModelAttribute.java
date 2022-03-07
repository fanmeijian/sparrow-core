package cn.sparrow.permission.model.resource;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import cn.sparrow.permission.model.common.AbstractSparrowEntity;
import cn.sparrow.permission.model.token.SparrowPermissionToken;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false, onlyExplicitlyIncluded = true)
@Entity
@Table(name = "spr_model_attribute")
public class ModelAttribute extends AbstractSparrowEntity {
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
