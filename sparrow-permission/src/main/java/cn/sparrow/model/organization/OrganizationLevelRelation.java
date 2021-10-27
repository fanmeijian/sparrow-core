package cn.sparrow.model.organization;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.PrimaryKeyJoinColumns;
import javax.persistence.Table;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import cn.sparrow.model.common.AbstractOperationLog;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
@Entity
@Table(name = "spr_organization_level_relation")
@EntityListeners(AuditingEntityListener.class)
public class OrganizationLevelRelation extends AbstractOperationLog {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private OrganizationLevelRelationPK id;

	// reference
	// columnName反过来的原因是因为OrganizationLevel创建的主键为pkey(organization_id,level_id)但这里创建的默认外键为fk(level_id,organization_id)
	// 会导致插入数据失败，所以应用反过来，
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumns({
			@JoinColumn(name = "organization_id", referencedColumnName = "organization_id", insertable = false, updatable = false),
			@JoinColumn(name = "level_id", referencedColumnName = "level_id", insertable = false, updatable = false) })
	private OrganizationLevel organizationLevel;

	public OrganizationLevelRelation(OrganizationLevelRelationPK id) {
		this.id = id;
	}

//	ALTER TABLE SPR_ORGANIZATION_LEVEL_RELATION DROP FOREIGN KEY FK4MPVTNU937QXM87XGS8JRJOJS;
//	ALTER TABLE SPR_ORGANIZATION_LEVEL_RELATION
//	ADD CONSTRAINT FK4MPVTNU937QXM87XGS8JRJOJS
//	FOREIGN KEY (ORGANIZATION_ID,LEVEL_ID) REFERENCES SPR_ORGANIZATION_LEVEL(ORGANIZATION_ID, LEVEL_ID);

}
