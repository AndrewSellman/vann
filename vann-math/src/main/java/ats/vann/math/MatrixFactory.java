package ats.vann.math;

public class MatrixFactory {

	ReadWriteMatrix create(int rowCount, int columnCount) {
		return create(new double[rowCount][columnCount]);
	}

	ReadWriteMatrix create(double[][] data) {
		return new Matrix(data);
	}

}
