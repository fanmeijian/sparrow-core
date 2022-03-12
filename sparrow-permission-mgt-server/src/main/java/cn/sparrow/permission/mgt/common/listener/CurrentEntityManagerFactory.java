package cn.sparrow.permission.mgt.common.listener;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

public class CurrentEntityManagerFactory {
	public static final CurrentEntityManagerFactory INSTANCE = new CurrentEntityManagerFactory();

	private static final ThreadLocal<EntityManagerFactory> storage = new ThreadLocal<>();

	public void set(EntityManagerFactory entityManager) {
		storage.set(entityManager);
	}

	public void remove() {
		storage.remove();
	}

	public EntityManagerFactory get() {
		return storage.get();
	}
	
	public EntityManager getEntityManager() {
		return storage.get().createEntityManager();
	}
}
