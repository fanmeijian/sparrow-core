package cn.sparrow.model.permission;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Embeddable
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ModelAttributePK implements Serializable {

  /**
   * 
   */
  private static final long serialVersionUID = 1L;

  @Column(name = "attribute_name")
  protected String attributeName;
  @Column(name = "model_name")
  protected String modelName;

}
