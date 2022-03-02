package cn.sparrow.permission.model.organization;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Embeddable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Embeddable
@AllArgsConstructor
@NoArgsConstructor
public class EmployeeUserPK implements Serializable{
  /**
   * 
   */
  private static final long serialVersionUID = 1L;
  private String username;
  @Column(name = "employee_id")
  private String employeeId;
}
