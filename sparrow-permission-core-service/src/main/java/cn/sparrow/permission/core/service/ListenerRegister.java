package cn.sparrow.permission.core.service;

import org.hibernate.boot.Metadata;
import org.hibernate.engine.spi.SessionFactoryImplementor;
import org.hibernate.event.service.spi.EventListenerRegistry;
import org.hibernate.event.spi.EventType;
import org.hibernate.integrator.spi.Integrator;
import org.hibernate.service.spi.SessionFactoryServiceRegistry;

public class ListenerRegister implements Integrator {

	@Override
	public void integrate(Metadata metadata, SessionFactoryImplementor sessionFactory,
			SessionFactoryServiceRegistry serviceRegistry) {
//		System.out.println("--------------");
		// As you might expect, an EventListenerRegistry is the thing with which event
		// listeners are registered
		// It is a service so we look it up using the service registry
		final EventListenerRegistry eventListenerRegistry = serviceRegistry.getService(EventListenerRegistry.class);

		// If you wish to have custom determination and handling of "duplicate"
		// listeners,
		// you would have to add an implementation of the
		// org.hibernate.event.service.spi.DuplicationStrategy contract like this

		// EventListenerRegistry defines 3 ways to register listeners:

		// 1) This form overrides any existing registrations with

		// 2) This form adds the specified listener(s) to the beginning of the listener
		// chain
		eventListenerRegistry.prependListeners(EventType.PERSIST, SprEntityListener.class);
		eventListenerRegistry.prependListeners(EventType.PRE_UPDATE, SprEntityListener.class);
		eventListenerRegistry.prependListeners(EventType.PRE_DELETE, SprEntityListener.class);
		eventListenerRegistry.prependListeners(EventType.POST_LOAD, SprEntityListener.class);
		eventListenerRegistry.prependListeners(EventType.POST_DELETE, SprEntityListener.class);

		// 3) This form adds the specified listener(s) to the end of the listener chain
//        eventListenerRegistry.appendListeners( EventType.MERGE,
//                                               DefaultMergeEventListener.class );

	}

	@Override
	public void disintegrate(SessionFactoryImplementor sessionFactory, SessionFactoryServiceRegistry serviceRegistry) {
		// TODO Auto-generated method stub

	}

}
