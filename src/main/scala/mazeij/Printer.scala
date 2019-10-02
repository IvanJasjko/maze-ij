package mazeij

import PrinterUtils._

object Printer {

  def printGrid(grid: Grid, maze: MazeBuilder): Unit = {
    val wallsToRemove = maze.wallsToRemove()
    val size = grid.gridSize
    for (j <- 1 to size) {
      println()
      for (i <- 1 to size) {
        if (Cords(i, j) == maze.startNode.cords) {
          printOpenBoth()
        } else if (Cords(i, j) == Cords(size, 1)) {
          printTopLeft()
        } else if (j == 1) {
          printTop()
        } else if (i == size) {
          printRight()
        } else {
          wallRemovalParser(Cords(i, j), wallsToRemove)
        }
      }
    }
    println("\n")
  }

  private def wallRemovalParser(node: Cords, walls: Seq[(Cords, Cords)]): Unit = {
    val wallConnectionsToBreak = walls.filter(x => x.productIterator.contains(node))
      .flatMap(_.productIterator.toList diff List(node))

    if (wallConnectionsToBreak.isEmpty) {
      printConnectedNode()
    } else if (wallConnectionsToBreak.contains(Cords(node.x - 1, node.y + 1))) {
      printOpenBoth()
    } else if (wallConnectionsToBreak.contains(Cords(node.x - 1, node.y))) {
      printOpenLeft()
    } else if (wallConnectionsToBreak.contains(Cords(node.x, node.y + 1))) {
      printOpenBottom()
    } else {
      printConnectedNode()
    }
  }
}
