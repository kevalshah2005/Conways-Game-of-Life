import java.awt.*;
import javax.swing.JFrame;

class Main {
  private GridCanvas grid;

  public Main() {
    grid = new GridCanvas(10, 10, 40);
    grid.turnOn(4, 3);
    grid.turnOn(5, 1);
    grid.turnOn(6, 3);
    grid.turnOn(5, 4);
    grid.turnOn(6, 5);
    grid.turnOn(4, 2);
    grid.turnOn(8, 2);
    grid.turnOn(8, 3);
    grid.turnOn(8, 4);
  }

  public static void main(String[] args) {
    String title = "Conway's Game of Life";
    Main game = new Main();
    JFrame frame = new JFrame(title);
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.setResizable(false);
    frame.add(game.grid);
    frame.pack();
    frame.setVisible(true);
    game.mainLoop();
  }

  private void mainLoop() {
    while (true) {
      this.update();
      grid.repaint();
      try {
        Thread.sleep(500);
      } catch (InterruptedException e) {
        // do nothing
      }
    }
  }

  public void update() {
    int[][] counts = countNeighbors();
    updateGrid(counts);
  }

  private int countAlive(int r, int c) {
    int count = 0;
    count += grid.test(r - 1, c - 1);
    count += grid.test(r - 1, c);
    count += grid.test(r - 1, c + 1);
    count += grid.test(r, c + 1);
    count += grid.test(r, c - 1);
    count += grid.test(r + 1, c + 1);
    count += grid.test(r + 1, c);
    count += grid.test(r + 1, c - 1);
    return count;
  }

  private int[][] countNeighbors() {
    int rows = grid.numRows();
    int columns = grid.numColumns();

    int[][] counts = new int[rows][columns];
    for (int r = 0; r < rows; r++) {
      for (int c = 0; c < columns; c++) {
        counts[r][c] = countAlive(r, c);
      }
    }
    return counts;
  }

  private void updateGrid(int[][] counts) {
    int rows = grid.numRows();
    int columns = grid.numColumns();

    for (int r = 0; r < rows; r++) {
      for (int c = 0; c < columns; c++) {
        Cell cell = grid.getCell(r, c);
        updateCell(cell, counts[r][c]);
      }
    }
  }

  private static void updateCell(Cell cell, int count) {
    if (cell.isOn()) {
      if (count < 2 || count > 3) {
        cell.turnOff();
      }
    } else {
      if (count == 3) {
        cell.turnOn();
      }
    }
  }
}