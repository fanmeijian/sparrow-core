package cn.sparrow.permission.model.resource;

import javax.annotation.processing.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(SysroleScope.class)
public abstract class SysroleScope_ extends cn.sparrow.permission.model.common.AbstractSparrowEntity_ {

	public static volatile SingularAttribute<SysroleScope, Sysrole> sysrole;
	public static volatile SingularAttribute<SysroleScope, Scope> scope;
	public static volatile SingularAttribute<SysroleScope, SysroleScopePK> id;

	public static final String SYSROLE = "sysrole";
	public static final String SCOPE = "scope";
	public static final String ID = "id";

}

