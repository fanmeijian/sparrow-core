package cn.sparrow.model.organization;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import cn.sparrow.model.common.AbstractOperationLog;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
@Entity
@Table(name = "spr_organization_role_relation")
@EntityListeners(AuditingEntityListener.class	)
public class OrganizationRoleRelation extends AbstractOperationLog{

  /**
   * 
   */
  private static final long serialVersionUID = 1L;

  @EmbeddedId
  private OrganizationRoleRelationPK id;

  @ManyToOne(fetch = FetchType.EAGER)
  @JoinColumns({
      @JoinColumn(name = "organization_id", referencedColumnName = "organization_id",
          insertable = false, updatable = false),
      @JoinColumn(name = "role_id", referencedColumnName = "role_id", insertable = false,
          updatable = false)})
  private OrganizationRole organizationRole;
  
  @ManyToOne(fetch = FetchType.EAGER)
  @JoinColumns({
      @JoinColumn(name = "parent_organization_id", referencedColumnName = "organization_id",
          insertable = false, updatable = false),
      @JoinColumn(name = "parent_role_id", referencedColumnName = "role_id", insertable = false,
          updatable = false)})
  private OrganizationRole parentOrganizationRole;

  public OrganizationRoleRelation(OrganizationRoleRelationPK id) {
    this.id = id;
  }

}
