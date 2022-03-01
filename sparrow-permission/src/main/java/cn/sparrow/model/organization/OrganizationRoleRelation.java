package cn.sparrow.model.organization;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.data.rest.core.RepositoryConstraintViolationException;

import cn.sparrow.model.common.AbstractOperationLog;
import cn.sparrow.permission.listener.RepositoryErrorFactory;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@EqualsAndHashCode(callSuper = false, onlyExplicitlyIncluded = true)
@NoArgsConstructor
@Entity
@Table(name = "spr_organization_role_relation")
@EntityListeners(AuditingEntityListener.class	)
public class OrganizationRoleRelation extends AbstractOperationLog{

  /**
   * 
   */
  private static final long serialVersionUID = 1L;

  @EqualsAndHashCode.Include
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
  
  @PrePersist
  @PreUpdate
  private void preSave() {
    if (id.getId().equals(id.getParentId())) {
      throw new RepositoryConstraintViolationException(
          RepositoryErrorFactory.getErros(this, "", "can not add relation to self"));
    }
  }

}
