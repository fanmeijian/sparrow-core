package cn.sparrow.model.app;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import cn.sparrow.model.app.SparrowApp;
import cn.sparrow.model.common.AbstractSparrowUuidEntity;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "spr_flow_no")
@NamedQuery(name = "FlowNo.findAll", query = "SELECT s FROM FlowNo s")
public class FlowNo extends AbstractSparrowUuidEntity {
	private static final long serialVersionUID = 1L;

	private String code;

	@Column(name = "app_id", insertable = false, updatable = false)
	private String appId;

	private String el;

	private String remark;

	private int reset;

	@Column(name = "sequence_length")
	private int sequenceLength;

	@Column(name = "sequence_no")
	private Long sequenceNo;

	// uni-directional many-to-one association to SwdApp
	@ManyToOne
	@JoinColumn(name = "app_id", insertable = false, updatable = false)
	private SparrowApp swdApp;

	public FlowNo() {
	}

}