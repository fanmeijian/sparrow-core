package cn.sparrow.model.organization;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import lombok.Data;

@Data
@Embeddable
public class EmployeeUserPK implements Serializable{
  /**
   * 
   */
  private static final long serialVersionUID = 1L;
  private String username;
  @Column(name = "employee_id")
  private String employeeId;
}
