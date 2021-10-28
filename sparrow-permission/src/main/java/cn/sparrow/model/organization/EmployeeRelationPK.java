package cn.sparrow.model.organization;

import java.io.Serializable;

import javax.persistence.Column;
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

  @Column(name = "employee_id")
  private String employeeId;
  @Column(name = "parent_id")
  private String parentId;

}
