package cn.sparrow.permission.model.resource;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import cn.sparrow.permission.model.common.AbstractSparrowUuidEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@EqualsAndHashCode(callSuper = true, onlyExplicitlyIncluded = true)
@Entity
@NoArgsConstructor
@Table(name = "spr_op_menu")
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
	  this.setId(id);
	  this.parentId = parentId;
  }

}
