package cn.sparrow.model.group;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
@Data
@AllArgsConstructor
@NoArgsConstructor
public class GroupRelationPK implements Serializable{
  /**
   * 
   */
  private static final long serialVersionUID = 1L;
  @Column(name = "group_id")
  private String groupId;
  @Column(name = "parent_id")
  private String parentId;
}
