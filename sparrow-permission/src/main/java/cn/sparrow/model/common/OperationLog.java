package cn.sparrow.model.common;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import org.hibernate.annotations.GenericGenerator;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Entity
@Table(name = "spr_operation_log")
@NamedQuery(name = "OperationLog.findAll", query = "SELECT s FROM OperationLog s")
public class OperationLog implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GenericGenerator(name = "id-generator", strategy = "uuid")
	@GeneratedValue(generator = "id-generator")
	private String id;

	@Lob
	@Column(name = "MODEL_BYTEARRAY")
	private byte[] modelBytearray;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "OP_TIME")
	private Date opTime;

	private String uri;

	private String username;

	private String ip;

	@Transient
	private Object params;

	public OperationLog() {
	}

}