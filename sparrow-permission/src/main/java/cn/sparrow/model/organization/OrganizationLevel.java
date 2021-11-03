package cn.sparrow.model.organization;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.springframework.data.domain.Persistable;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import cn.sparrow.model.common.AbstractOperationLog;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.EqualsAndHashCode.Exclude;
import lombok.NoArgsConstructor;

@Data
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
@Entity
@Table(name = "spr_organization_level")
@EntityListeners(AuditingEntityListener.class)
public class OrganizationLevel extends AbstractOperationLog implements Persistable<OrganizationLevelPK> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@EmbeddedId
	private OrganizationLevelPK id;
	private String stat;

	@Transient
	@JsonProperty
	private boolean hasChildren;

	@Exclude
	@JsonIgnore
	@OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.REFRESH)
	@JoinColumns({ @JoinColumn(name = "organization_id", referencedColumnName = "organization_id"),
			@JoinColumn(name = "level_id", referencedColumnName = "level_id") })
	private List<EmployeeOrganizationLevel> employeeOrganizationLevels;

	@Exclude
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "level_id", insertable = false, updatable = false)
	private Level level;

	@JsonIgnore
	@Exclude
	@ManyToOne
	@JoinColumn(name = "organization_id", insertable = false, updatable = false)
	private Organization organization;

	public OrganizationLevel(OrganizationLevelPK f) {
		this.id = f;
	}

	@Override
	public boolean isNew() {
		return true;
	}

}
