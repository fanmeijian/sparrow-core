package cn.sparrow.permission.model.group;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.envers.Audited;

import com.fasterxml.jackson.annotation.JsonIgnore;

import cn.sparrow.permission.model.common.AbstractSparrowEntity;
import cn.sparrow.permission.model.organization.Role;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@EqualsAndHashCode(callSuper = false, onlyExplicitlyIncluded = true)
@NoArgsConstructor
@Entity
@Table(name = "spr_group_role")
public class GroupRole extends AbstractSparrowEntity {

  /**
   * 
   */
  private static final long serialVersionUID = 1L;
  @EqualsAndHashCode.Include
  @EmbeddedId
  @Audited
  private GroupRolePK id;
//  @Audited
  private String stat;

  @JsonIgnore
  @ManyToOne(fetch = FetchType.EAGER)
  @JoinColumn(name = "group_id", insertable = false, updatable = false)
  private Group group;
  
  @JsonIgnore
  @ManyToOne(fetch = FetchType.EAGER)
  @JoinColumn(name = "role_id", insertable = false, updatable = false)
  private Role role;

  public GroupRole(GroupRolePK f) {
    this.id = f;
  }
  
  public GroupRole(String groupId, String roleId) {
	    this.id = new GroupRolePK(groupId, roleId);
	  }

}
