package cn.sparrow.model.organization;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.io.Serializable;
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
  private String groupId;
  private String parentId;
}
