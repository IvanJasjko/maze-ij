package mazeij

import PrinterUtils._

object Printer {

  def printGrid(grid: Grid, maze: MazeBuilder): Unit = {
    val nodesToUpdate = WallRemovalParser(maze.traverseGrid())
    val size = grid.gridSize
    for (j <- 1 to size) {
      println()
      for (i <- 1 to size) {
        if (Cords(i, j) == Cords(size, 1)) {
          printTopLeft()
        } else if (j == 1) {
          printTop()
        } else if (i == size) {
          printRight()
        } else {
          if (!(maze.traverseGrid().map(_._1) contains Cords(i, j))) {
            printOpenBottom()
          } else {
            printConnectedNode()
          }
        }
      }
    }
    println("\n")
  }

  private def WallRemovalParser(walls: Seq[(Cords, Cords)]): Seq[Cords] = {
    List(Cords(1, 1))
  }
}
