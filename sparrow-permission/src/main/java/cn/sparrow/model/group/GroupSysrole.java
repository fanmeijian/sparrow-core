package cn.sparrow.model.group;

import java.io.Serializable;
import javax.persistence.*;

/**
 * Entity implementation class for Entity: GroupSysrole
 *
 */
@Entity
@Table(name = "spr_group_sysrole")
public class GroupSysrole implements Serializable {


  private static final long serialVersionUID = 1L;

  @EmbeddedId
  private GroupSysrolePK id;

  public GroupSysrole() {
    super();
  }

  public GroupSysrole(GroupSysrolePK f) {
    this.id = f;
  }

}
