package cn.sparrow.permission.model.organization;

import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import cn.sparrow.permission.model.AbstractSparrowUuidEntity;
import cn.sparrow.permission.model.group.GroupEmployee;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@EqualsAndHashCode(callSuper = true, onlyExplicitlyIncluded = true)
@NoArgsConstructor
@Entity
@Table(name = "spr_employee")
public class Employee extends AbstractSparrowUuidEntity {

  /**
   * 
   */
  private static final long serialVersionUID = 1L;

  private String name;
  @Column(unique = true)
  private String code;
  private Boolean isRoot;

  // use for create relation at batch
  @Transient
  @JsonProperty
  private List<String> parentIds;

  @Column(name = "organization_id")
  private String organizationId;

  @Transient
  @JsonProperty
  private long childCount;

  @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL, mappedBy = "employee")
  private Set<EmployeeUser> employeeUsers;

  @ManyToOne(fetch = FetchType.EAGER)
  @JoinColumn(name = "organization_id", insertable = false, updatable = false)
  private Organization organization;

  @JsonIgnore
  @OneToMany(cascade = CascadeType.ALL, mappedBy = "employee", fetch = FetchType.LAZY)
  private Set<EmployeeOrganizationRole> employeeOrganizationRoles;

  @JsonIgnore
  @OneToMany(cascade = CascadeType.ALL, mappedBy = "employee", fetch = FetchType.LAZY)
  private Set<EmployeeOrganizationLevel> employeeOrganizationLevels;

  @JsonIgnore
  @OneToMany(cascade = CascadeType.ALL, mappedBy = "employee", fetch = FetchType.LAZY)
  private Set<GroupEmployee> groupEmployees;
  
  public Employee(String name, String code,String organizationId) {
	  this.name = name;
	  this.code = code;
	  this.organizationId = organizationId;
  }

}
