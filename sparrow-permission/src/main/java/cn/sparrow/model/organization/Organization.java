package cn.sparrow.model.organization;

import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import cn.sparrow.model.common.AbstractSparrowUuidEntity;
import cn.sparrow.model.common.OrganizationTypeEnum;
import cn.sparrow.model.group.GroupOrganization;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@EqualsAndHashCode(callSuper = true, onlyExplicitlyIncluded = true)
@NoArgsConstructor
@Entity
@Table(name = "spr_organization")
public class Organization extends AbstractSparrowUuidEntity {

  /**
  * 
  */
  private static final long serialVersionUID = 8581950429388182649L;
  private String code;
  private String name;
  private String stat;
  private boolean isRoot;
  // use for create relation at batch
  @Transient
  @JsonProperty
  private List<String> parentIds;
  @Enumerated(EnumType.STRING)
  private OrganizationTypeEnum type; // 公司还是部门

  @Transient
  @JsonProperty
  private boolean hasChildren;

  @Transient
  @JsonProperty
  private long childCount;

  @Transient
  @JsonProperty
  private long levelCount;

  @Transient
  @JsonProperty
  private long groupCount;

  @Transient
  @JsonProperty
  private long roleCount;

  @Transient
  @JsonProperty
  private long employeeCount;

  @JsonIgnore
  @OneToMany(targetEntity = OrganizationRole.class, cascade = CascadeType.ALL,
      mappedBy = "organization", fetch = FetchType.LAZY)
  private Set<OrganizationRole> organizationRoles;

  @JsonIgnore
  @OneToMany(targetEntity = OrganizationLevel.class, cascade = CascadeType.ALL,
      mappedBy = "organization", fetch = FetchType.LAZY)
  private Set<OrganizationLevel> organizationLevels;

  @JsonIgnore
  @OneToMany(targetEntity = OrganizationGroup.class, cascade = CascadeType.ALL,
      mappedBy = "organization", fetch = FetchType.LAZY)
  private Set<OrganizationGroup> organizationGroups;

  @JsonIgnore
  @OneToMany(targetEntity = GroupOrganization.class, cascade = CascadeType.ALL,
      mappedBy = "organization", fetch = FetchType.LAZY)
  private Set<GroupOrganization> groupOrganizations;
}
