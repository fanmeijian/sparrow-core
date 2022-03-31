package cn.sparrow.permission.model.resource;

import javax.annotation.processing.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(ScopeApi.class)
public abstract class ScopeApi_ extends cn.sparrow.permission.model.common.AbstractSparrowEntity_ {

	public static volatile SingularAttribute<ScopeApi, Scope> scope;
	public static volatile SingularAttribute<ScopeApi, ScopeApiPK> id;
	public static volatile SingularAttribute<ScopeApi, SparrowApi> sparrowApi;

	public static final String SCOPE = "scope";
	public static final String ID = "id";
	public static final String SPARROW_API = "sparrowApi";

}

