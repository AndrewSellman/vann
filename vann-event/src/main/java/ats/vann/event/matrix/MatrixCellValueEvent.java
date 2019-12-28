package ats.vann.event.matrix;

import ats.vann.event.Context;
import ats.vann.event.DoubleValueEvent;

abstract class MatrixCellValueEvent extends DoubleValueEvent {
	private final int rowIndex;
	private final int columnIndex;

	public MatrixCellValueEvent(Context context, int rowIndex, int columnIndex, double value) {
		super(context, value);
		this.rowIndex = rowIndex;
		this.columnIndex = columnIndex;
	}

	public final int getRowIndex() {
		return rowIndex;
	}

	public final int getColumnIndex() {
		return columnIndex;
	}

	public double getValue() {
		return getEventValue();
	}

	@Override
	public String toString() {
		return "Matrix value is " + getValue() + " for rowIndex " + getRowIndex() + " and columnIndex " + getColumnIndex() + " " + super.toString();
	}

}
