package cn.sparrow.permission.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.Table;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@EqualsAndHashCode(callSuper = true, onlyExplicitlyIncluded = true)
@Entity
@NoArgsConstructor
@Table(name = "spr_op_menu")
@EntityListeners({AuditingEntityListener.class})
public class OpMenu extends AbstractSparrowUuidEntity implements Serializable {
  private static final long serialVersionUID = 1L;

  @Column(unique = true)
  private String code;
  private String name;
  private String parentId;
  private String url;
  private Boolean isSystem;
  private String previousNodeId;
  private String nextNodeId;
  private String icon;

  public OpMenu(String id, String parentId) {
	  this.id = id;
	  this.parentId = parentId;
  }

}
