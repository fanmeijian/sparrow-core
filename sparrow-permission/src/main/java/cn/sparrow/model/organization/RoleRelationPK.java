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
public class RoleRelationPK implements Serializable{
  
  /**
   * 
   */
  private static final long serialVersionUID = 1L;
  @Column(name = "role_id")
  private String roleId;
  private String parentId;
}
