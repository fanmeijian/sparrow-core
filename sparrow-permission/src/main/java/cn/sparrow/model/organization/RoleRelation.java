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
@Table(name = "spr_role_relation")
public class RoleRelation {
  public RoleRelation(RoleRelationPK f) {
    this.id = f;
  }

  @EmbeddedId
  private RoleRelationPK id;
  
  @ManyToOne
  @JoinColumn(name = "role_id", insertable = false, updatable = false)
  private Role role;
}
