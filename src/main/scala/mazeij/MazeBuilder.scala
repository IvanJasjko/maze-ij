package mazeij

import scala.annotation.tailrec
import scala.collection.mutable.ListBuffer
import scala.util.Random
import PrinterUtils._

object MazeBuilder {

  def makeMaze(grid: Grid): Unit = {
    val start = Cords(1,1)
    val finish = Cords(grid.gridSize, grid.gridSize)
    val paths = Seq(
      (Cords(1,1), Cords(1,2)),
      (Cords(1,1), Cords(2,1))
    )

    printGraph(grid.makeGraph(paths), start, finish)
  }


}
