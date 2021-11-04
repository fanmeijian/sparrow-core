package cn.sparrow.model.organization;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.springframework.data.domain.Persistable;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.fasterxml.jackson.annotation.JsonIgnore;

import cn.sparrow.model.common.AbstractOperationLog;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false,onlyExplicitlyIncluded = true)
@Entity
@Table(name = "spr_employee_organization_position_level")
@EntityListeners(AuditingEntityListener.class)
public class EmployeeOrganizationLevel extends AbstractOperationLog
		implements Persistable<EmployeeOrganizationLevelPK> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@EqualsAndHashCode.Include
	@EmbeddedId
	private EmployeeOrganizationLevelPK id;
	private String stat;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumns({
			@JoinColumn(name = "organization_id", referencedColumnName = "organization_id", insertable = false, updatable = false),
			@JoinColumn(name = "position_level_id", referencedColumnName = "position_level_id", insertable = false, updatable = false) })
	private OrganizationPositionLevel organizationLevel;

	@JsonIgnore
	@ManyToOne
	@JoinColumn(name = "employee_id", insertable = false, updatable = false)
	private Employee employee;

	public EmployeeOrganizationLevel() {

	}

	public EmployeeOrganizationLevel(EmployeeOrganizationLevelPK f) {
		this.id = f;
	}

	@Override
	public boolean isNew() {
		return true;
	}

}