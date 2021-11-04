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
@Table(name = "spr_group_position_level")
public class GroupPositionLevel extends AbstractOperationLog implements Persistable<GroupPositionLevelPK> {

  /**
   * 
   */
  private static final long serialVersionUID = 1L;
  @EmbeddedId
  private GroupPositionLevelPK id;
  private String stat;

  @ManyToOne(fetch = FetchType.EAGER)
  @JoinColumn(name = "group_id", insertable = false, updatable = false)
  private Group group;
  
  @ManyToOne(fetch = FetchType.EAGER)
  @JoinColumn(name = "position_level_id", insertable = false, updatable = false)
  private PositionLevel positionLevel;

  public GroupPositionLevel(GroupPositionLevelPK f) {
    this.id = f;
  }

  @Override
  public boolean isNew() {
    return true;
  }

}
