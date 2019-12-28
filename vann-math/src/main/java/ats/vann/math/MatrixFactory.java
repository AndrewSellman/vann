package ats.vann.math;

import ats.vann.event.Context;
import ats.vann.event.EventManager;

public class MatrixFactory {

    ReadWriteMatrix create(int rowCount, int columnCount) {
        return create(new double[rowCount][columnCount]);
    }

    ReadWriteMatrix create(double[][] data) {
        return new Matrix(data);
    }

    ReadWriteMatrix create(int rowCount, int columnCount, Context context, EventManager eventManager) {
        return create(new double[rowCount][columnCount], context, eventManager);
    }

    ReadWriteMatrix create(double[][] data, Context context, EventManager eventManager) {
        return new EventfulMatrix(data, context, eventManager);
    }

}
