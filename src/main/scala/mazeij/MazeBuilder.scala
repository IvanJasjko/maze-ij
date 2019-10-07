package mazeij

import scala.annotation.tailrec
import scala.util.Random
import PrinterUtils._

object MazeBuilder {

  def makeMaze(grid: Grid): Graph = {
    val size = grid.gridSize
    val fullGraph = grid.makeGraph()
    val start = chooseStartNode(fullGraph)
    val finish = Cords(size - start.x + 1, size - start.y + 1)

    val traversal = traverseGraph(fullGraph, start)

    val pathWalls = addPaths(traversal)
      .flatMap {case (a, b) => List((a, b), (b, a)) }

    val extraWalls = traversal.sliding(2).toList
      .flatMap { case List(a, b) => List((a, b), (b, a)) }

    val maze = grid.makeGraph(pathWalls ++ extraWalls)
    printGraph(maze, start, finish)
    fullGraph
  }

  private def chooseStartNode(graph: Graph): Cords = {
    val keys = graph.keys
    val size = graph.keys.map(_.x).max
    val edges = Seq(1, size)
    val sides = keys.filter(key => edges.contains(key.x) || edges.contains(key.y)).toSeq
    sides(Random.nextInt(sides.length))
  }

  @tailrec
  private def traverseGraph(graph: Graph, start: Cords, visited: List[Cords] = List()): Seq[Cords] = {
    val newVisited = visited :+ start
    if (newVisited.length == graph.keys.size)
      return newVisited
    val nextNode = getNextNode(graph, start, newVisited, newVisited)
    traverseGraph(graph, nextNode, newVisited)
  }

  @tailrec
  private def getNextNode(graph: Graph, node: Cords, visited: List[Cords], stack: List[Cords]): Cords = {
    val availableNodes = graph(node) diff visited
    if (availableNodes.nonEmpty)
      return availableNodes(Random.nextInt(availableNodes.length))
    getNextNode(graph, stack.last, visited, stack.reverse.tail)
  }

  private def addPaths(traversal: Seq[Cords]): Seq[(Cords, Cords)] = {
    for {
      i <- 1 until traversal.length if !isConnected(traversal(i), traversal(i - 1))
    } yield (traversal(i), traversal.take(i).filter(isConnected(_, traversal(i))).last)
  }

  private def isConnected(node1: Cords, node2: Cords): Boolean = {
    (Math.abs(node1.x - node2.x) == 1 && node1.y == node2.y) ||
      (Math.abs(node1.y - node2.y) == 1 && node1.x == node2.x)
  }
}