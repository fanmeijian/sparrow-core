package cn.sparrow.permission.data.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;

import org.springframework.security.core.context.SecurityContextHolder;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@MappedSuperclass
public class AbstractOperationLog implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Column(name = "create_time",insertable = false, updatable = false)
	private Date createTime; // 创建时间
	@Column(name = "last_update_time", insertable = false, updatable = false)
	private Date lastUpdateTime; // 最后更新时间
	@Column(insertable = false, updatable = false)
	private String author; // 创建者
	@Column(name = "last_update_user", insertable = false, updatable = false)
	private String lastUpdateUser; // 最后更新者

	@PrePersist
	public void prePresist() {
		String username = SecurityContextHolder.getContext().getAuthentication().getName();
		author = username;
		lastUpdateUser = username;
		createTime = new Date();
		lastUpdateTime = new Date();
	}

	@PreUpdate
	public void preUpdate() {
		String username = SecurityContextHolder.getContext().getAuthentication().getName();
		lastUpdateUser = username;
		lastUpdateTime = new Date();
	}
}
