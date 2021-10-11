package cn.sparrow.permission.data.model;

import java.io.Serializable;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;

@Embeddable
@Getter
@Setter
public class ModelAttributePK implements Serializable {

  /**
   * 
   */
  private static final long serialVersionUID = 1L;

  private String name;
  @Column(name = "model_name")
  private String modelName;



  public ModelAttributePK() {
    super();
  }



  @Override
  public int hashCode() {
    return Objects.hash(modelName, name);
  }



  @Override
  public boolean equals(Object obj) {
    if (this == obj)
      return true;
    if (obj == null)
      return false;
    if (getClass() != obj.getClass())
      return false;
    ModelAttributePK other = (ModelAttributePK) obj;
    return Objects.equals(modelName, other.modelName) && Objects.equals(name, other.name);
  }



  public ModelAttributePK(String name, String modelName) {
    super();
    this.name = name;
    this.modelName = modelName;
  }




}
