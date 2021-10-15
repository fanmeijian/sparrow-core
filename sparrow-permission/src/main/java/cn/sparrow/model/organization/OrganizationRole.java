package cn.sparrow.model.organization;

import java.util.Set;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import org.springframework.data.domain.Persistable;

import cn.sparrow.model.common.AbstractOperationLog;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "spr_organization_role")
public class OrganizationRole extends AbstractOperationLog
    implements Persistable<OrganizationRolePK> {

  private static final long serialVersionUID = 1L;
  @EmbeddedId
  private OrganizationRolePK id;
  private String stat;

  @ManyToMany(mappedBy = "organizationRoles")
  private Set<Employee> employees;

  public OrganizationRole() {

  }

  public OrganizationRole(OrganizationRolePK f) {
    this.id = f;
  }

  @Override
  public boolean isNew() {
    return true;
  }

}
