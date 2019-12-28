package ats.vann.event;

interface EventListenerAdapter {

    void onEvent(Event event);

    Class<? extends Listener> getListenerClass();

}
