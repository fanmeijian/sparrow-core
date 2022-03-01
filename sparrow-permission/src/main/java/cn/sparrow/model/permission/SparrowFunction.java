package cn.sparrow.model.permission;

import java.util.List;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Lob;
import javax.persistence.PostLoad;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;
import javax.persistence.Transient;
import org.springframework.util.SerializationUtils;
import com.fasterxml.jackson.annotation.JsonProperty;
import cn.sparrow.model.common.AbstractSparrowEntity;
import cn.sparrow.permission.service.PermissionToken;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false, onlyExplicitlyIncluded = true)
@Entity
@Table(name = "spr_function")
public class SparrowFunction extends AbstractSparrowEntity {
  /**
   * 
   */
  private static final long serialVersionUID = 1L;

  @EqualsAndHashCode.Include
  @EmbeddedId
  private SparrowFunctionPK id;


  @Lob
  @Column(name = "func_permission_token")
  private byte[] funcPermissionTokenByteArray;

  @Transient
  private List<SparrowFunctionParameter> parameters;

  @Transient
  @JsonProperty
  private PermissionToken funcPermissionToken;
  
  @PostLoad
  private void postLoad() {
    this.funcPermissionToken = (PermissionToken) SerializationUtils.deserialize(funcPermissionTokenByteArray);
  }
  
  @PreUpdate
  @PrePersist
  private void beforeSave() {
    this.funcPermissionTokenByteArray = SerializationUtils.serialize(funcPermissionToken);
  }

}
