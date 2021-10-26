package cn.sparrow.model.organization;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "spr_organization_relation")
public class OrganizationRelation {

  @EmbeddedId
  private OrganizationRelationPK id;

  @ManyToOne(fetch = FetchType.EAGER)
  @JoinColumn(name = "organization_id", insertable = false, updatable = false)
  private Organization organization;

  @ManyToOne
  @JoinColumn(name = "parent_id", insertable = false, updatable = false)
  private Organization parent;
  
  public OrganizationRelation(OrganizationRelationPK id) {
    this.id = id;
  }

  public OrganizationRelation(Organization f) {
    this.organization = f;
  }

}
