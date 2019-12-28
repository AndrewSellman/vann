package ats.vann.event.matrix;

import ats.vann.event.Context;
import ats.vann.event.DoubleValueEvent;

public class MatrixPollColumnCountEvent extends DoubleValueEvent {

    public MatrixPollColumnCountEvent(final Context context, double columnCount) {
        super(context, columnCount);
    }

}
