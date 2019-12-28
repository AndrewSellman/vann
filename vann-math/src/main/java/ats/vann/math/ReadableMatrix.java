package ats.vann.math;

public interface ReadableMatrix {

    int getRowCount();

    int getColumnCount();

    double get(int rowIndex, int columnIndex);

}
