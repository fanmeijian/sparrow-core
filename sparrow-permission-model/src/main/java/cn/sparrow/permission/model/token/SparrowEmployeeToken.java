package cn.sparrow.permission.model.token;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.PostLoad;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.apache.commons.lang3.SerializationUtils;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
@Entity
@Table(name = "spr_employee_token")
public class SparrowEmployeeToken implements Serializable{
  /**
   * 
   */
  private static final long serialVersionUID = 1L;

  @EqualsAndHashCode.Include
  @Id
  private String employeeId;
  
//  @OneToOne
//  @MapsId
//  private Employee employee;
  
  @Lob
  @Column(name = "employee_token")
  private byte[] employeeTokenByteArray;
  
  @Transient
  private EmployeeToken employeeToken;
  
  @PrePersist
  @PreUpdate
  private void preSave() {
    this.employeeTokenByteArray = SerializationUtils.serialize(employeeToken);
  }
  
  @PostLoad
  private void postLoad() {
    this.employeeToken = (EmployeeToken) SerializationUtils.deserialize(employeeTokenByteArray);
  }
  
}
