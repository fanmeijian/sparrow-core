package cn.sparrow.permission.model.group;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import cn.sparrow.permission.model.AbstractOperationLog;
import cn.sparrow.permission.model.organization.Organization;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;


@Data
@EqualsAndHashCode(callSuper = false, onlyExplicitlyIncluded = true)
@NoArgsConstructor
@Entity
@Table(name = "spr_group_organization")
public class GroupOrganization extends AbstractOperationLog{

  private static final long serialVersionUID = 1L;
  @EqualsAndHashCode.Include
  @EmbeddedId
  private GroupOrganizationPK id;

  @ManyToOne
  @JoinColumn(name = "group_id", insertable = false, updatable = false)
  private Group group;
  
  @ManyToOne
  @JoinColumn(name = "organization_id", insertable = false, updatable = false)
  private Organization organization;

  public GroupOrganization(GroupOrganizationPK f) {
    this.id = f;
  }

}
