package cn.sparrow.model.permission;

import java.util.List;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Lob;
import javax.persistence.Table;
import javax.persistence.Transient;
import org.springframework.util.SerializationUtils;
import cn.sparrow.model.common.AbstractSparrowEntity;
import cn.sparrow.model.permission.token.ModelPermissionToken;
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

  public ModelPermissionToken getModelPermissionToken() {
    return (ModelPermissionToken) SerializationUtils.deserialize(funcPermissionTokenByteArray);
  }

  public void setModelPermissionToken(ModelPermissionToken modelPermissionToken) {
    this.funcPermissionTokenByteArray = SerializationUtils.serialize(funcPermissionTokenByteArray);
  }

}
