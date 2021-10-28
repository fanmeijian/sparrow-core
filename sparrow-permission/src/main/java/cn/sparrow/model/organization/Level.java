package cn.sparrow.model.organization;

import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.fasterxml.jackson.annotation.JsonProperty;

import cn.sparrow.model.common.AbstractSparrowEntity;
import cn.sparrow.model.group.GroupLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@Entity
@NoArgsConstructor
@Table(name = "spr_level")
public class Level extends AbstractSparrowEntity {

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
	
	@OneToMany(targetEntity = OrganizationLevel.class, cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private Set<OrganizationLevel> organizationLevels;
	
	@OneToMany(fetch = FetchType.EAGER)
	private Set<GroupLevel> groupLevels;

}