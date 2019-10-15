package mazeij

import MazeBuilder._

object Main {

  def main(args: Array[String]): Unit = {
    val grid = new Grid(10)
    makeMaze(grid)
  }
}
