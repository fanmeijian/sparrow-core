package cn.sparrow.model.group;

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
@Table(name = "spr_group_relation")
public class GroupRelation {

  @EmbeddedId
  private GroupRelationPK id;
  
  
  @ManyToOne
  @JoinColumn(name = "group_id", insertable = false, updatable = false)
  private Group group;
  
  
  public GroupRelation(GroupRelationPK id) {
    this.id = id;
  }
  
  
}
