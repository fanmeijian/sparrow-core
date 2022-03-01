package cn.sparrow.model.group;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

import org.springframework.data.domain.Persistable;

import cn.sparrow.model.common.AbstractOperationLog;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@EqualsAndHashCode(callSuper = false, onlyExplicitlyIncluded = true)
@NoArgsConstructor
@Entity
@Table(name = "spr_group_user")
public class GroupUser extends AbstractOperationLog implements Persistable<GroupUserPK> {

  private static final long serialVersionUID = 1L;
  @EqualsAndHashCode.Include
  @EmbeddedId
  private GroupUserPK id;
  private String stat;


  public GroupUser(GroupUserPK f) {
    this.id = f;
  }

  @Override
  public boolean isNew() {
    return true;
  }

}
