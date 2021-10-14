package cn.sparrow.model.organization;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Embeddable
public class LevelRelationPK implements Serializable{
  
  /**
   * 
   */
  private static final long serialVersionUID = 1L;
  @Column(name = "level_id")
  private String levelId;
  private String parentId;
}
