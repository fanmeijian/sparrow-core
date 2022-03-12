package cn.sparrow.permission.authorization.server.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.GenericGenerator;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "spr_login_log")
public class LoginLog implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GenericGenerator(name = "id-generator", strategy = "uuid")
	@GeneratedValue(generator = "id-generator")
	private String id;
	private String ip;
	@Temporal(TemporalType.TIMESTAMP)
	private Date loginTime;
	private String username;

	public LoginLog(String username, String ip) {
		this.username = username;
		this.ip = ip;
		this.loginTime = new Date();
	}
}