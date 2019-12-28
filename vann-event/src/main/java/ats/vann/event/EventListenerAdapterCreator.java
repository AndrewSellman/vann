package ats.vann.event;

public interface EventListenerAdapterCreator {

    EventListenerAdapter createFor(Listener listener);
}
