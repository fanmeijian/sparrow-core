package cn.sparrow.permission.model.resource;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

import cn.sparrow.permission.constant.ApiPermissionEnum;
import cn.sparrow.permission.constant.HttpMethodEnum;
import cn.sparrow.permission.model.common.AbstractSparrowEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@EqualsAndHashCode(callSuper = false, onlyExplicitlyIncluded = true)
@NoArgsConstructor
@Entity
@Table(name = "spr_api")
public class SparrowApi extends AbstractSparrowEntity {
	private static final long serialVersionUID = 1L;

	@JsonProperty(access = Access.READ_ONLY)
	@EqualsAndHashCode.Include
	@Id
	@GenericGenerator(name = "id-generator", strategy = "uuid")
	@GeneratedValue(generator = "id-generator")
	private String id;
	private String name;
	private String uri;
	private Boolean isSystem;

	@Enumerated(EnumType.STRING)
	private HttpMethodEnum method;

	@Enumerated(EnumType.STRING)
	private ApiPermissionEnum permission;

	@Column(name = "client_id")
	private String clientId;

//  @OneToMany(mappedBy = "sparrowUrl")
//  Set<UserUrlPermission> userUrlPermissions;

//  @EqualsAndHashCode.Exclude
//  @OneToMany(mappedBy = "sparrowUrl",cascade = CascadeType.ALL, fetch = FetchType.LAZY)
//  Set<SysroleUrlPermission> sysroleUrPermissions;

//  @ManyToOne
//  @JoinColumn(name = "app_id")
//  private SparrowApp sparrowApp;

	public SparrowApi(String name, String uri, HttpMethodEnum method, String clientId, ApiPermissionEnum permission) {
		this.name = name;
		this.uri = uri;
		this.method = method;
		this.clientId = clientId;
		this.permission = permission;
	}

}
