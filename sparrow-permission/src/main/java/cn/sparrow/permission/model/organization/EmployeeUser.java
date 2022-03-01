package cn.sparrow.permission.model.organization;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
@Table(name = "spr_employee_user")
public class EmployeeUser {
  
  @EqualsAndHashCode.Include
  @EmbeddedId
  private EmployeeUserPK id;
  
  @JsonIgnore
  @ManyToOne
  @JoinColumn(name = "employee_id", insertable = false, updatable = false)
  private Employee employee;
}
