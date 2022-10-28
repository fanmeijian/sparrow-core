package cn.sparrow.permission.mgt.model;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(SparrowUser.class)
public abstract class SparrowUser_ {

	public static volatile SingularAttribute<SparrowUser, Boolean> accountLocked;
	public static volatile SingularAttribute<SparrowUser, String> password;
	public static volatile SingularAttribute<SparrowUser, Boolean> accountExpired;
	public static volatile SingularAttribute<SparrowUser, Boolean> disabled;
	public static volatile SingularAttribute<SparrowUser, Boolean> credentialsExpired;
	public static volatile SingularAttribute<SparrowUser, String> username;

	public static final String ACCOUNT_LOCKED = "accountLocked";
	public static final String PASSWORD = "password";
	public static final String ACCOUNT_EXPIRED = "accountExpired";
	public static final String DISABLED = "disabled";
	public static final String CREDENTIALS_EXPIRED = "credentialsExpired";
	public static final String USERNAME = "username";

}

