package cn.sparrow.permission.model.organization;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.envers.Audited;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import cn.sparrow.permission.constant.OrganizationTypeEnum;
import cn.sparrow.permission.model.common.AbstractSparrowEntity;
import cn.sparrow.permission.model.group.GroupOrganization;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@EqualsAndHashCode(callSuper = true, onlyExplicitlyIncluded = true)
@NoArgsConstructor
@Entity
@Table(name = "spr_organization")
@JsonIgnoreProperties(value={"children","dataPermissionToken"}, allowGetters=true)
public class Organization extends AbstractSparrowEntity {

	/**
	* 
	*/
	@EqualsAndHashCode.Include
	@Id
	@GenericGenerator(name = "id-generator", strategy = "uuid")
	@GeneratedValue(generator = "id-generator")
	@Audited
	@JsonProperty(access = JsonProperty.Access.READ_ONLY)
	private String id;
	
	private static final long serialVersionUID = 8581950429388182649L;
	@Column(unique = true)
	@Audited
	private String code;
	@Audited
	private String name;
	@Audited
	private String stat;
	@Audited
	private Boolean isRoot = true;
	// use for create relation at batch
//  @Transient
//  @JsonProperty
//  private List<String> parentIds;
	@Enumerated(EnumType.STRING)
	private OrganizationTypeEnum type; // 公司还是部门

	@Transient
	@JsonProperty(access = JsonProperty.Access.READ_ONLY)
	private long parentCount;

	@Transient
	@JsonProperty(access = JsonProperty.Access.READ_ONLY)
	private long childCount;

	@Transient
	@JsonProperty(access = JsonProperty.Access.READ_ONLY)
	private long levelCount;

	@Transient
	@JsonProperty(access = JsonProperty.Access.READ_ONLY)
	private long groupCount;

	@Transient
	@JsonProperty(access = JsonProperty.Access.READ_ONLY)
	private long roleCount;

	@Transient
	@JsonProperty(access = JsonProperty.Access.READ_ONLY)
	private long employeeCount;

	@OneToMany(mappedBy = "parent", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private Set<OrganizationRelation> children;

	@JsonIgnore
	@OneToMany(mappedBy = "organization", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private Set<OrganizationRelation> parents;

	@JsonIgnore
	@OneToMany(targetEntity = OrganizationRole.class, cascade = CascadeType.ALL, mappedBy = "organization", fetch = FetchType.LAZY)
	private Set<OrganizationRole> organizationRoles;

	@JsonIgnore
	@OneToMany(targetEntity = OrganizationPositionLevel.class, cascade = CascadeType.ALL, mappedBy = "organization", fetch = FetchType.LAZY)
	private Set<OrganizationPositionLevel> organizationLevels;

	@JsonIgnore
	@OneToMany(targetEntity = OrganizationGroup.class, cascade = CascadeType.ALL, mappedBy = "organization", fetch = FetchType.LAZY)
	private Set<OrganizationGroup> organizationGroups;

	@JsonIgnore
	@OneToMany(targetEntity = GroupOrganization.class, cascade = CascadeType.ALL, mappedBy = "organization", fetch = FetchType.LAZY)
	private Set<GroupOrganization> groupOrganizations;

	public Organization(String name, String code, OrganizationTypeEnum type) {
		this.code = code;
		this.name = name;
		this.type = type;
	}
}
