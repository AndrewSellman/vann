package ats.vann.event;

import ats.vann.concurrent.AbstractTask;

import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.concurrent.Semaphore;
import java.util.concurrent.atomic.AtomicBoolean;

class EventDispatcher extends AbstractTask {
    private final Map<Class<? extends Event>, List<EventListenerAdapter>> registeredEventListenerAdapters;
    private final Queue<? extends Event> pendingEvents;
    private final AtomicBoolean keepRunning;
    private final Semaphore arbitrator;

    public EventDispatcher(Map<Class<? extends Event>,
            List<EventListenerAdapter>> registeredEventListenerAdapters,
                           Queue<Event> pendingEvents,
                           AtomicBoolean keepRunning,
                           Semaphore arbitrator) {
        this.registeredEventListenerAdapters = registeredEventListenerAdapters;
        this.pendingEvents = pendingEvents;
        this.keepRunning = keepRunning;
        this.arbitrator = arbitrator;
    }

    @Override
    public void execute() {
        while (keepRunning.get()) {
            try {
                arbitrator.acquire();
                dispatch();
            } catch (Exception e) {
                // TODO log the exception?
            }
        }
    }

    private void dispatch() {
        Event event = pendingEvents.poll();
        if (event == null) {
            return;
        }

        dispatch(event);
    }

    private void dispatch(Event event) {
        List<? extends EventListenerAdapter> adaptersForEvent = registeredEventListenerAdapters.get(event.getClass());
        if (adaptersForEvent == null) {
            return;
        }

        dispatch(event, adaptersForEvent);
    }

    private void dispatch(Event event, List<? extends EventListenerAdapter> adaptersForEvent) {
        synchronized (adaptersForEvent) {
            for (EventListenerAdapter adapter : adaptersForEvent) {
                adapter.onEvent(event);
            }
        }
    }

}
