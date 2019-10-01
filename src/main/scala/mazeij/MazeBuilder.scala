package mazeij

import scala.annotation.tailrec
import scala.collection.mutable.ListBuffer
import scala.util.Random

class MazeBuilder(grid: Grid) {

  private val gridSeq = grid.gridSeq
  private val gridMap = grid.gridMap

  def traverseGrid(): Seq[(Cords, Cords)] = {
    //Seed cell
    val startNode = gridSeq(Random.nextInt(gridSeq.size))

    val queue = ListBuffer(startNode.cords)
    val visited = ListBuffer(startNode.cords)
    val wallsToKeep: ListBuffer[(Cords, Cords)] = ListBuffer()

    while (visited.size != gridSeq.size) {
      val pathNode = makeNode(visited.last)
      val cordPair = popUntilFound(pathNode, queue, visited)

      val prevCords = cordPair._1
      val nextNode = makeNode(cordPair._2)

      updateBuffer(visited, nextNode.cords)
      updateBuffer(queue, nextNode.cords)
      wallsToKeep += Tuple2(prevCords, nextNode.cords)
    }
    wallsToKeep
  }

  private def updateBuffer(visitedCords: ListBuffer[Cords], cords: Cords): ListBuffer[Cords] = {
    if (visitedCords.contains(cords))
      visitedCords
    else
      visitedCords += cords
  }

  @tailrec
  private def popUntilFound(node: Node, queue: ListBuffer[Cords], visited: ListBuffer[Cords]): (Cords, Cords) = {
    val availableNodes = node.connected diff visited
    if (availableNodes.nonEmpty)
      (node.cords, availableNodes(Random.nextInt(availableNodes.length)))
    else
      popUntilFound(makeNode(queue.head), queue.tail, visited)
  }

  private def makeNode(cords: Cords): Node = {
    Node(cords, gridMap(cords))
  }
}
