package cn.sparrow.permission.model.resource;

import javax.annotation.processing.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(SysroleApi.class)
public abstract class SysroleApi_ extends cn.sparrow.permission.model.common.AbstractSparrowEntity_ {

	public static volatile SingularAttribute<SysroleApi, Sysrole> sysrole;
	public static volatile SingularAttribute<SysroleApi, SysroleApiPK> id;
	public static volatile SingularAttribute<SysroleApi, SparrowApi> sparrowApi;

	public static final String SYSROLE = "sysrole";
	public static final String ID = "id";
	public static final String SPARROW_API = "sparrowApi";

}

