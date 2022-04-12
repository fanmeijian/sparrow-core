package cn.sparrow.permission.model.common;

import javax.persistence.EntityManager;

public class CurrentEntityManager {
//	public static final CurrentUser INSTANCE = new CurrentUser();

	private static final ThreadLocal<EntityManager> storage = new ThreadLocal<>();

	public static void set(EntityManager entityManager) {
		storage.set(entityManager);
	}

	public static void remove() {
		storage.remove();
	}

	public static EntityManager get() {
		return storage.get();
	}
}
