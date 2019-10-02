package mazeij



object Main {

  def main(args: Array[String]): Unit = {
    val grid = new Grid(5)
    val maze = new MazeBuilder(grid)
    Printer.printGrid(grid, maze)

  }
}
