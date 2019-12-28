package ats.vann.event.matrix;

import ats.vann.event.Context;
import ats.vann.event.DoubleValueEvent;

public class MatrixPollRowCountEvent extends DoubleValueEvent {

    public MatrixPollRowCountEvent(final Context context, double rowCount) {
        super(context, rowCount);
    }

}
