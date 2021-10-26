package cn.sparrow.model.organization;

import java.io.Serializable;
import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Embedded;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Embeddable
public class OrganizatioinRoleRelationPK implements Serializable {
  /**
   * 
   */
  private static final long serialVersionUID = 1L;
  @Embedded
  private OrganizationRolePK id;

  @Embedded
  @AttributeOverrides({
      @AttributeOverride(name = "organizationId", column = @Column(name = "parent_organization_id")),
      @AttributeOverride(name = "roleId", column = @Column(name = "parent_role_id"))})
  private OrganizationRolePK parentId;
}
