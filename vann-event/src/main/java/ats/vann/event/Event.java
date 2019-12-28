package ats.vann.event;

public abstract class Event {
    private final Context context;

    public Event(final Context context) {
        this.context = context;
    }

    public final String getNetworkName() {
        return context.getNetworkName();
    }

    public final int getNetworkLayerIndex() {
        return context.getNetworkLayerIndex();
    }

    public final Representation getRepresentation() {
        return context.getRepresents();
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(" representing ");
        sb.append(getRepresentation());

        sb.append(" for network layer index ");
        sb.append(getNetworkLayerIndex());

        sb.append(" of network ");
        sb.append(getNetworkName());

        return sb.toString();
    }

}
