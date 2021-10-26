package cn.sparrow.model.organization;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import cn.sparrow.model.common.AbstractOperationLog;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
@Entity
@Table(name = "spr_organization_role_relation")
public class OrganizationRoleRelation extends AbstractOperationLog{

  /**
   * 
   */
  private static final long serialVersionUID = 1L;

  @EmbeddedId
  private OrganizatioinRoleRelationPK id;

  @ManyToOne(fetch = FetchType.EAGER)
  @JoinColumns({
      @JoinColumn(name = "organization_id", referencedColumnName = "organization_id",
          insertable = false, updatable = false),
      @JoinColumn(name = "role_id", referencedColumnName = "role_id", insertable = false,
          updatable = false)})
  private OrganizationRole organizationRole;

  public OrganizationRoleRelation(OrganizatioinRoleRelationPK id) {
    this.id = id;
  }

}
