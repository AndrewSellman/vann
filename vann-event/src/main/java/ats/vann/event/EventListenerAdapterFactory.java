package ats.vann.event;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class EventListenerAdapterFactory {
	private Map<Class<? extends Event>, AbstractEventListenerAdapterFactory<? extends Event, ? extends Listener, ? extends EventListenerAdapter>> eventListenerAdapaterFactories;

	public EventListenerAdapterFactory() {
		eventListenerAdapaterFactories = new ConcurrentHashMap<>();
	}

	public void register(AbstractEventListenerAdapterFactory<? extends Event, ? extends Listener, ? extends EventListenerAdapter> factory) {
		eventListenerAdapaterFactories.put(factory.getSupportedEventClass(), factory);
	}

	public EventListenerAdapter createFor(final Class<? extends Event> eventClass, Listener listener) {
		EventListenerAdapterCreator creator = eventListenerAdapaterFactories.get(eventClass);
		if (creator == null) {
			throw new IllegalStateException("No EventListenerAdapterCreator found for event type " + eventClass.toString());
		}

		return creator.createFor(listener);
	}

}
