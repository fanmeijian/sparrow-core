package cn.sparrow.permission.model.organization;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.envers.Audited;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import cn.sparrow.permission.model.common.AbstractSparrowEntity;
import cn.sparrow.permission.model.group.GroupPositionLevel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@EqualsAndHashCode(callSuper = true, onlyExplicitlyIncluded = true)
@Entity
@NoArgsConstructor
@Table(name = "spr_position_level")
public class PositionLevel extends AbstractSparrowEntity {

	private static final long serialVersionUID = 1L;

	@EqualsAndHashCode.Include
	@Id
	@GenericGenerator(name = "id-generator", strategy = "uuid")
	@GeneratedValue(generator = "id-generator")
//	@Audited
	@JsonProperty(access = JsonProperty.Access.READ_ONLY)
	private String id;

	@Column(unique = true)
	private String code;
	private String name;
	private String stat;
	private Boolean isRoot;

	// use for create relation at batch
//	@Transient
//	@JsonProperty
//	private List<String> parentIds;

//	@Transient
//	@JsonProperty
//	private List<String> organizationIds;

	@JsonIgnore
	@OneToMany(targetEntity = OrganizationPositionLevel.class, cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "positionLevel")
	private Set<OrganizationPositionLevel> organizationPositionLevels;

	@JsonIgnore
	@OneToMany(targetEntity = GroupPositionLevel.class, cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "positionLevel")
	private Set<GroupPositionLevel> groupLevels;

	public PositionLevel(String name, String code) {
		this.name = name;
		this.code = code;
	}

}
