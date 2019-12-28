package ats.vann.event.matrix;

import ats.vann.event.Context;

public class MatrixChangeCellEvent extends MatrixCellValueEvent {

	public MatrixChangeCellEvent(final Context context, int rowIndex, int columnIndex, double newValue) {
		super(context, rowIndex, columnIndex, newValue);
	}

}
