package mazeij

import scala.annotation.tailrec
import scala.collection.mutable.ListBuffer
import scala.util.Random

class MazeBuilder(grid: Grid) {

  private val gridSeq = grid.gridSeq
  private val gridMap = grid.gridMap
  private val size = grid.gridSize
  //Seed cell
  val startNode: Node = chooseStart()

  def wallsToRemove(): Seq[(Cords, Cords)] = {
    val queue = ListBuffer(startNode.cords)
    val visited = ListBuffer(startNode.cords)
    val wallsForRemoval: ListBuffer[(Cords, Cords)] = ListBuffer()

    while (visited.size != gridSeq.size) {
      val prevNode = makeNode(visited.last)
      val newCordPair = popUntilFound(prevNode, queue, visited)

      val nextNode = makeNode(newCordPair._2)

      updateBuffer(visited, nextNode.cords)
      updateBuffer(queue, nextNode.cords)

      wallsForRemoval += removeWall(newCordPair._1, newCordPair._2)
    }
    wallsForRemoval
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

  private def chooseStart(): Node = {
    val xChoices = (for (y <- 2 until size) yield List(Cords(1, y), Cords(size, y))).flatten
    val yChoices = (for (x <- 2 until size) yield List(Cords(x, 1), Cords(x, size))).flatten
    val edges = xChoices ++ yChoices
    makeNode(edges(Random.nextInt(edges.length)))
  }

  private def makeNode(cords: Cords): Node = {
    Node(cords, gridMap(cords))
  }

  private def removeWall(start: Cords, finish: Cords): (Cords, Cords) = {
    if (start.y == finish.y) {
      (finish, Cords(finish.x + 1, finish.y))
    } else {
      (finish, Cords(finish.x, finish.y + 1))
    }
  }
}
