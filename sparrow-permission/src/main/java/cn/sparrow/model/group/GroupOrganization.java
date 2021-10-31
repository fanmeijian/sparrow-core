package cn.sparrow.model.group;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.springframework.data.domain.Persistable;

import cn.sparrow.model.common.AbstractOperationLog;
import cn.sparrow.model.organization.Organization;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.EqualsAndHashCode.Exclude;
import lombok.NoArgsConstructor;


@Data
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
@Entity
@Table(name = "spr_group_organization")
public class GroupOrganization extends AbstractOperationLog
    implements Persistable<GroupOrganizationPK> {

  private static final long serialVersionUID = 1L;
  @EmbeddedId
  private GroupOrganizationPK id;

  @Exclude
  @ManyToOne
  @JoinColumn(name = "group_id", insertable = false, updatable = false)
  private Group group;
  
  @Exclude
  @ManyToOne
  @JoinColumn(name = "organization_id", insertable = false, updatable = false)
  private Organization organization;

  public GroupOrganization(GroupOrganizationPK f) {
    this.id = f;
  }

  @Override
  public boolean isNew() {
    return true;
  }

}
