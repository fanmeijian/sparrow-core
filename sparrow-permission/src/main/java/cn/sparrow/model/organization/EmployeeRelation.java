package cn.sparrow.model.organization;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "spr_employee_relation")
public class EmployeeRelation {
  @EmbeddedId
  private EmployeeRelationPK id;
  
  @ManyToOne
  @JoinColumn(name = "username", insertable = false, updatable = false)
  private Employee employee;
}
