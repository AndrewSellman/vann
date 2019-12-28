package ats.vann.event;

public abstract class DoubleValueEvent extends Event {
	private final double value;

	public DoubleValueEvent(Context context, double value) {
		super(context);
		this.value = value;
	}

	protected double getEventValue() {
		return value;
	}

}
