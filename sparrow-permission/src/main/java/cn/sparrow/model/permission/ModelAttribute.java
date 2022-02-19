package cn.sparrow.model.permission;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.PostLoad;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.springframework.data.rest.core.annotation.RestResource;
import org.springframework.util.SerializationUtils;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import cn.sparrow.model.common.AbstractOperationLog;
import cn.sparrow.permission.listener.AuditLogListener;
import cn.sparrow.permission.service.PermissionToken;
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
  
  @JsonIgnore
  @Lob
  @Column(name = "model_attribute_permission_token")
  private byte[] modelAttributePermissionTokenByteArray;
  
  @Transient
  @JsonProperty
  private PermissionToken modelAttributePermissionToken;
  
  @PostLoad
  private void postLoad() {
    this.modelAttributePermissionToken = (PermissionToken) SerializationUtils.deserialize(modelAttributePermissionTokenByteArray);
  }
  
  @PreUpdate
  @PrePersist
  private void beforeSave() {
    this.modelAttributePermissionTokenByteArray = SerializationUtils.serialize(modelAttributePermissionToken);
  }

  @RestResource(exported = false)
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
