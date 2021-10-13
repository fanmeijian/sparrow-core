package cn.sparrow.model.organization;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Embeddable
@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrganizationRelationPK implements Serializable{
  /**
   * 
   */
  private static final long serialVersionUID = 1L;
  @Column(name = "organization_id")
  private String organizationId;
  @Column(name = "parent_id")
  private String parentId;
}   
