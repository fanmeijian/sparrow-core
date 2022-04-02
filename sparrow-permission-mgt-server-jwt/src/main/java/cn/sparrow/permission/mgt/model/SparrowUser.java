package cn.sparrow.permission.mgt.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.envers.Audited;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "spr_user")
@Audited
public class SparrowUser implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private String username;

	@JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
	private String password;

	@Transient
	private String oldPassword;
	@Transient
	private String secondPassword;
	
	private boolean accountExpired;
	private boolean accountLocked;
	private boolean credentialsExpired;
	private boolean disabled;

	public SparrowUser(String username, String password) {
		this.username = username;
		this.password = password;
	}

}
