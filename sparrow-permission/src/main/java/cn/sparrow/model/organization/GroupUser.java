package cn.sparrow.model.organization;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;
import org.springframework.data.domain.Persistable;
import cn.sparrow.model.permission.AbstractOperationLog;
import lombok.Getter;
import lombok.Setter;

/**
 * @author fansword
 * @version 1.0
 * @created 28-Sep-2021 4:56:32 PM
 */

@Getter
@Setter
@Entity
@Table(name = "spr_group_user")
public class GroupUser extends AbstractOperationLog implements Persistable<GroupUserPK> {

  private static final long serialVersionUID = 1L;
  @EmbeddedId
  private GroupUserPK id;
  private String stat;

  public GroupUser() {

  }

  public GroupUser(GroupUserPK f) {
    this.id = f;
  }

  @Override
  public boolean isNew() {
    return true;
  }

}
