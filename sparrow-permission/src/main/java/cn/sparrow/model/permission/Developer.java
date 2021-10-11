package cn.sparrow.model.permission;

import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import org.hibernate.annotations.GenericGenerator;
import cn.sparrow.model.app.SparrowApp;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Entity
@Table(name = "spr_developer")
public class Developer extends AbstractOperationLog {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GenericGenerator(name = "id-generator", strategy = "uuid")
	@GeneratedValue(generator = "id-generator")
	private String id;

	private String name;

	private String license;

	@OneToMany(targetEntity = SparrowApp.class, cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private List<SparrowApp> sparrowApps;

}
