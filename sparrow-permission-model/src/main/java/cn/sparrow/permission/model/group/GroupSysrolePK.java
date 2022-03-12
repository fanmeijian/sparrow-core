package cn.sparrow.permission.model.group;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Embeddable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Embeddable
@NoArgsConstructor
@AllArgsConstructor
public class GroupSysrolePK implements Serializable{
  /**
   * 
   */
  private static final long serialVersionUID = 1L;
  @Column(name = "group_id")
  private String groupId;
  @Column(name = "sysrole_id")
  private String sysroleId;
}
