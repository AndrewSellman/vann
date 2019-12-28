package ats.vann.math;

public class Matrix implements ReadWriteMatrix {
	private final double[][] data;

	protected Matrix(double[][] data) {
		this.data = data;
	}

	protected Matrix(int rowCount, int columnCount) {
		this(new double[rowCount][columnCount]);
	}

	@Override
	public int getRowCount() {
		return data.length;
	}

	@Override
	public int getColumnCount() {
		return data[0].length;
	}

	@Override
	public double get(int rowIndex, int columnIndex) {
		return data[rowIndex][columnIndex];
	}

	@Override
	public void set(int rowIndex, int columnIndex, double newValue) {
		data[rowIndex][columnIndex] = newValue;
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder("[");
		sb.append(getRowCount());
		sb.append("x");
		sb.append(getColumnCount());
		sb.append("]\n");
		for (int r = 0; r < getRowCount(); r++) {
			for (int c = 0; c < getColumnCount(); c++) {
				sb.append(get(r, c));
				sb.append(" ");
			}
			sb.append("\n");
		}
		return sb.toString();
	}

}
