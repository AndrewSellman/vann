package ats.vann.event;

public abstract class AbstractEventListenerAdapterFactory<E extends Event, L extends Listener, A extends EventListenerAdapter> implements EventListenerAdapterCreator {
	private final Class<E> supportedEventClass;
	private final Class<L> supportedListenerClass;
	private final Class<A> supportedAdapterClass;

	public AbstractEventListenerAdapterFactory(Class<E> supportedEventClass, Class<L> supportedListenerClass, Class<A> supportedAdapterClass) {
		this.supportedEventClass = supportedEventClass;
		this.supportedListenerClass = supportedListenerClass;
		this.supportedAdapterClass = supportedAdapterClass;
	}

	public final EventListenerAdapter createFor(final Listener listener) {
		if (!isCompatibleListener(listener)) {
			throw new IllegalStateException(getAdapterClassToBeCreated().toString() + " cannot adapt listener of type " + listener.getClass().toString());
		}

		return create(listener);
	}

	protected abstract A create(Listener listener);

	protected final Class<E> getSupportedEventClass() {
		return supportedEventClass;
	}

	protected final L getCompatibleListener(Listener listener) {
		return getSupportedListenerClass().cast(listener);
	}

	private boolean isCompatibleListener(final Listener listener) {
		return getSupportedListenerClass().isAssignableFrom(listener.getClass());
	}

	private Class<L> getSupportedListenerClass() {
		return supportedListenerClass;
	}

	private Class<A> getAdapterClassToBeCreated() {
		return supportedAdapterClass;
	}

}
