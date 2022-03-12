package cn.sparrow.permission.authorization.server.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name = "spr_sysrole")
public class Sysrole implements Serializable {
  private static final long serialVersionUID = 1L;

  
  private String name;
  @Id
  private String code;

}
