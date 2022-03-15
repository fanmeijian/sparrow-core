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
import cn.sparrow.permission.model.resource.Sysrole;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@EqualsAndHashCode(callSuper = false, onlyExplicitlyIncluded = true)
@NoArgsConstructor
@Entity
@Table(name = "spr_group_sysrole")
public class GroupSysrole extends AbstractSparrowEntity {


  private static final long serialVersionUID = 1L;

  @EqualsAndHashCode.Include
  @EmbeddedId
  @Audited
  private GroupSysrolePK id;
  
  @JsonIgnore
  @ManyToOne(fetch = FetchType.EAGER)
  @JoinColumn(name = "group_id", insertable = false, updatable = false)
  private Group group;
  
  @JsonIgnore
  @ManyToOne(fetch = FetchType.EAGER)
  @JoinColumn(name = "sysrole_id", insertable = false, updatable = false)
  private Sysrole sysrole;

  public GroupSysrole(GroupSysrolePK f) {
    this.id = f;
  }
  
  public GroupSysrole(String groupId, String sysroleId) {
	    this.id = new GroupSysrolePK(groupId, sysroleId);
	  }

}
