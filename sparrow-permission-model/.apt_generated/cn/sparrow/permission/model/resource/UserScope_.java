package cn.sparrow.permission.model.resource;

import javax.annotation.processing.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(UserScope.class)
public abstract class UserScope_ extends cn.sparrow.permission.model.common.AbstractSparrowEntity_ {

	public static volatile SingularAttribute<UserScope, Scope> scope;
	public static volatile SingularAttribute<UserScope, UserScopePK> id;

	public static final String SCOPE = "scope";
	public static final String ID = "id";

}

