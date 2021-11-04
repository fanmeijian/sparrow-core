package cn.sparrow.model.organization;

import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import cn.sparrow.model.common.AbstractSparrowUuidEntity;
import cn.sparrow.model.group.GroupPositionLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@Entity
@NoArgsConstructor
@Table(name = "spr_position_level")
public class PositionLevel extends AbstractSparrowUuidEntity {

	private static final long serialVersionUID = 1L;
	private String code;
	private String name;
	private String stat;
	private boolean root;

	// use for create relation at batch
	@Transient
	@JsonProperty
	private List<String> parentIds;
	
	@Transient
    @JsonProperty
    private List<String> organizationIds;
	
	@JsonIgnore
	@OneToMany(targetEntity = OrganizationPositionLevel.class, cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private Set<OrganizationPositionLevel> organizationPositionLevels;
	
	@JsonIgnore
	@OneToMany(targetEntity = GroupPositionLevel.class, cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private Set<GroupPositionLevel> groupLevels;

}