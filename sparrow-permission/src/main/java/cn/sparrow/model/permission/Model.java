package cn.sparrow.model.permission;

import java.io.Serializable;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import org.springframework.util.SerializationUtils;
import com.fasterxml.jackson.annotation.JsonIgnore;
import cn.sparrow.model.app.SparrowApp;
import cn.sparrow.model.common.AbstractOperationLog;
import cn.sparrow.model.common.AbstractSparrowEntity;
import cn.sparrow.model.permission.token.ModelPermissionToken;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@EqualsAndHashCode(callSuper = false, onlyExplicitlyIncluded = true)
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "spr_model")
public class Model extends AbstractOperationLog implements Serializable {

  private static final long serialVersionUID = 1L;

  @EqualsAndHashCode.Include
  @Id
  private String name;
  private String nameTxt;
  private String remark;
  private boolean isSystem;
  @Column(name = "app_id")
  private String appId;

  // @ManyToOne
  // @JoinColumn(name = "catalog_id")
  // private Catalog catalog;

  @ManyToOne
  @JoinColumn(name = "app_id", insertable = false, updatable = false)
  private SparrowApp sparrowApp;

  @JsonIgnore
  @OneToMany(targetEntity = ModelAttribute.class, cascade = CascadeType.ALL, mappedBy = "model")
  private List<ModelAttribute> modelAttributes;

  @Lob
  @Column(name = "model_permission_token")
  private byte[] modelPermissionTokenByteArray;

  public ModelPermissionToken getModelPermissionToken() {
    return (ModelPermissionToken) SerializationUtils.deserialize(modelPermissionTokenByteArray);
  }

  public void setModelPermissionToken(ModelPermissionToken modelPermissionToken) {
    this.modelPermissionTokenByteArray =
        SerializationUtils.serialize(modelPermissionTokenByteArray);
  }

  public Model(String name) {
    this.name = name;
  }

  public Model(String name, boolean isSystem) {
    this.name = name;
    this.isSystem = isSystem;
  }

}
