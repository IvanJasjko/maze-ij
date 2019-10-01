package mazeij



object Main {

  def main(args: Array[String]): Unit = {

    val grid = new Grid(10)
    val maze = new MazeBuilder(grid)
    maze.traverseGrid()
    Printer.printGrid(grid, maze)

  }
}
