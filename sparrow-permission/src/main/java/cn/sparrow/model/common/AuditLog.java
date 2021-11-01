package cn.sparrow.model.common;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.GenericGenerator;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "spr_audit_log")
public class AuditLog {

	@Id
	@GenericGenerator(name = "id-generator", strategy = "uuid")
	@GeneratedValue(generator = "id-generator")
	protected String rev;

	private String modelName;

	@Column(length = 2000)
	private String objectId;

	@Column(length = 1)
	private String revtype;

	private Date timestamp;
	
	private String username;
	
	@Lob
	@Column(name="object_bytearray")
	private byte[] objectBytearray;
	
	@Transient
	@JsonSerialize
	private Object sourceObject;

}
