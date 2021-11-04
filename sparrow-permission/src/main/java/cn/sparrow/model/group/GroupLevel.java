package cn.sparrow.model.group;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.springframework.data.domain.Persistable;

import cn.sparrow.model.common.AbstractOperationLog;
import cn.sparrow.model.organization.PositionLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "spr_group_level")
public class GroupLevel extends AbstractOperationLog implements Persistable<GroupLevelPK> {

  /**
   * 
   */
  private static final long serialVersionUID = 1L;
  @EmbeddedId
  private GroupLevelPK id;
  private String stat;

  @ManyToOne(fetch = FetchType.EAGER)
  @JoinColumn(name = "group_id", insertable = false, updatable = false)
  private Group group;
  
  @ManyToOne(fetch = FetchType.EAGER)
  @JoinColumn(name = "level_id", insertable = false, updatable = false)
  private PositionLevel level;

  public GroupLevel(GroupLevelPK f) {
    this.id = f;
  }

  @Override
  public boolean isNew() {
    return true;
  }

}
