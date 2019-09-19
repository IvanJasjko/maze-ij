package mazeij

import scala.collection.mutable.ListBuffer
import scala.util.Random

object MazeBuilder {

  def traverseGrid(grid: Grid) = {
    val gridSeq = grid.emptyGrid
    val gridMap = gridSeq.map(node => node.cords -> node.connected).toMap

    val startCell = gridSeq(Random.nextInt(gridSeq.size))
    val visited = new ListBuffer[Cords] += startCell.cords
    val queue = new ListBuffer[Cords] += startCell.cords

    while (visited.size != gridSeq.size) {
      val nextCell = selectNext(startCell, gridMap, visited)
      visited += nextCell.cords
    }

    visited.foreach(println)




  }

  private def selectNext(node: Node, gridMap: Map[Cords, Seq[Cords]], visited: ListBuffer[Cords]): Node = {
    if ((node.connected diff visited).nonEmpty) {
      val cords = (node.connected diff visited) (Random.nextInt((node.connected diff visited).size))
     println( Node(cords, gridMap(cords)))
      Node(cords, gridMap(cords))
    }
    node
  }


}
