import java.awt.*;

public class GridCanvas extends Canvas {
  private Cell[][] array;

  public GridCanvas (int rows, int columns, int size) {
    array = new Cell[rows][columns];
    for (int i = 0; i < rows; i++) {
      int x = i * size;
      for (int j = 0; j < columns; j++) {
        int y = j * size;
        array[i][j] = new Cell(x, y, size);
      }
    }

    setSize(columns * size, rows * size);
  }

  public void paint (Graphics g) {
    for (Cell[] row : array) {
      for (Cell cell : row) {
        cell.draw(g);
      }
    }
  }

  public int numRows() {
    return array.length;
  }

  public int numColumns() {
    return array[0].length;
  }

  public Cell getCell(int row, int column) {
    return array[row][column];
  }

  public void turnOn(int row, int column) {
    array[row][column].turnOn();
  }

  public int test (int r, int c) {
    try {
      if (array[r][c].isOn()) {
        return 1;
      }
    } catch (ArrayIndexOutOfBoundsException e) {
      // cell doesn't exist
    }

    return 0;
  }

}