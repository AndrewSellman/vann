package ats.vann.event.matrix;

import ats.vann.event.Context;

public class MatrixPollCellEvent extends MatrixCellValueEvent {

	public MatrixPollCellEvent(final Context context, int rowIndex, int columnIndex, double currentValue) {
		super(context, rowIndex, columnIndex, currentValue);
	}

}
