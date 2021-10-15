package cn.sparrow.model.organization;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;
import org.springframework.data.domain.Persistable;

import cn.sparrow.model.common.AbstractOperationLog;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "spr_organization_group")
public class OrganizationGroup extends AbstractOperationLog
    implements Persistable<OrganizationGroupPK> {
  private static final long serialVersionUID = 1L;
  @EmbeddedId
  private OrganizationGroupPK id;
  private String stat;

  public OrganizationGroup() {

  }

  public OrganizationGroup(OrganizationGroupPK f) {
    this.id = f;
  }

  @Override
  public boolean isNew() {
    return true;
  }
}
