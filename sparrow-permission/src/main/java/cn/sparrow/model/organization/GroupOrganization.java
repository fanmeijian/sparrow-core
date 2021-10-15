package cn.sparrow.model.organization;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;
import org.springframework.data.domain.Persistable;

import cn.sparrow.model.common.AbstractOperationLog;
import lombok.Getter;
import lombok.Setter;

/**
 * @author fansword
 * @version 1.0
 * @created 28-Sep-2021 4:57:09 PM
 */

@Getter
@Setter
@Entity
@Table(name = "spr_group_organization")
public class GroupOrganization extends AbstractOperationLog
    implements Persistable<GroupOrganizationPK> {

  private static final long serialVersionUID = 1L;
  @EmbeddedId
  private GroupOrganizationPK id;

  public GroupOrganization() {

  }

  public GroupOrganization(GroupOrganizationPK f) {
    this.id = f;
  }

  @Override
  public boolean isNew() {
    return true;
  }

}
