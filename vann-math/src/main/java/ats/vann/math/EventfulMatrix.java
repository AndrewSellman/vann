package ats.vann.math;

import ats.vann.event.Context;
import ats.vann.event.Event;
import ats.vann.event.EventManager;
import ats.vann.event.matrix.MatrixChangeCellEvent;
import ats.vann.event.matrix.MatrixPollCellEvent;
import ats.vann.event.matrix.MatrixPollColumnCountEvent;
import ats.vann.event.matrix.MatrixPollRowCountEvent;

public class EventfulMatrix implements ReadWriteMatrix {
    private final Matrix matrix;
    private final Context context;
    private final EventManager eventManager;

    protected EventfulMatrix(double[][] data, Context context, EventManager eventManager) {
        matrix = new Matrix(data);
        this.context = context;
        this.eventManager = eventManager;
    }

    @Override
    public int getRowCount() {
        int rowCount = matrix.getRowCount();
        fire(new MatrixPollRowCountEvent(context, rowCount));
        return rowCount;
    }

    @Override
    public int getColumnCount() {
        int columnCount = matrix.getColumnCount();
        fire(new MatrixPollColumnCountEvent(context, columnCount));
        return columnCount;
    }

    @Override
    public double get(int rowIndex, int columnIndex) {
        double currentValue = matrix.get(rowIndex, columnIndex);
        fire(new MatrixPollCellEvent(context, rowIndex, columnIndex, currentValue));
        return currentValue;
    }

    @Override
    public void set(int rowIndex, int columnIndex, double newValue) {
        matrix.set(rowIndex, columnIndex, newValue);
        fire(new MatrixChangeCellEvent(context, rowIndex, columnIndex, newValue));
    }

    @Override
    public String toString() {
        return matrix.toString();
    }

    private void fire(Event event) {
        eventManager.fire(event);
    }

}
