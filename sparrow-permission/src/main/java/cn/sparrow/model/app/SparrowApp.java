package cn.sparrow.model.app;

import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import cn.sparrow.model.common.AbstractSparrowUuidEntity;
import cn.sparrow.model.permission.Model;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true, onlyExplicitlyIncluded = true)
@Entity
@Table(name = "spr_app")
public class SparrowApp extends AbstractSparrowUuidEntity {

  /**
   * 
   */
  private static final long serialVersionUID = 1L;

  private String name;
  private String code;
  private String remark;
  private String deployId;

  @OneToMany(targetEntity = Model.class, cascade = CascadeType.ALL, fetch = FetchType.LAZY,
      mappedBy = "sparrowApp")
  private List<Model> models;

}
