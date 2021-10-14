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
@Table(name = "spr_level_relation")
public class LevelRelation {
  public LevelRelation(LevelRelationPK f) {
    this.id = f;
  }

  @EmbeddedId
  private LevelRelationPK id;
  
  @ManyToOne
  @JoinColumn(name = "level_id", insertable = false, updatable = false)
  private Level level;
}
