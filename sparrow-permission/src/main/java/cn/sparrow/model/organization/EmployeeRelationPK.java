package cn.sparrow.model.organization;

import java.io.Serializable;
import javax.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Embeddable
public class EmployeeRelationPK implements Serializable {
  /**
  * 
  */
  private static final long serialVersionUID = 1L;

  private String username;
  private String parentId;

}
