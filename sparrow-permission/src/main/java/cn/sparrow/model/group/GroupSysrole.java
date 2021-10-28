package cn.sparrow.model.group;

import java.io.Serializable;
import javax.persistence.*;

import cn.sparrow.model.permission.Sysrole;
import lombok.Data;
import lombok.EqualsAndHashCode.Exclude;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity
@Table(name = "spr_group_sysrole")
public class GroupSysrole implements Serializable {


  private static final long serialVersionUID = 1L;

  @EmbeddedId
  private GroupSysrolePK id;
  
  @Exclude
  @ManyToOne(fetch = FetchType.EAGER)
  @JoinColumn(name = "group_id", insertable = false, updatable = false)
  private Group group;
  
  @Exclude
  @ManyToOne(fetch = FetchType.EAGER)
  @JoinColumn(name = "sysrole_id", insertable = false, updatable = false)
  private Sysrole sysrole;

  public GroupSysrole(GroupSysrolePK f) {
    this.id = f;
  }

}
