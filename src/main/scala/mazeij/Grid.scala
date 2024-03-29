package mazeij

class Grid(size: Int) {

  val gridSize: Int = size

  def makeGraph(wallsToRemove: Seq[(Cords, Cords)] = Seq()): Graph = {
    buildNodeGrid()
      .map(node => node.cords -> (node.connected diff wallsToRemove
        .filter(_.productIterator.toList.contains(node.cords))
        .map(_.productIterator.toList.filter(_ != node.cords).head)
        )
      ).toMap
  }

  private def buildNodeGrid(): Nodes = {
    val cordList = buildGrid(1, size)
    cordList map { cord => Node(cord, makeConnections(cord)) }
  }

  private def makeConnections(cords: Cords): Seq[Cords] = {
    val connections = Seq(
      Cords(cords.x - 1, cords.y),
      Cords(cords.x + 1, cords.y),
      Cords(cords.x, cords.y + 1),
      Cords(cords.x, cords.y - 1)
    )
    connections.filter(isEdge)
  }

  private def isEdge(cords: Cords): Boolean = {
    val outOfBounds = Seq(0, size+1)
    !outOfBounds.contains(cords.x) && !outOfBounds.contains(cords.y)
  }

  private def buildGrid(start: Int, finish: Int): Seq[Cords] = {
    for (
      x <- start to finish;
      y <- start to finish
    ) yield Cords(x, y)
  }
}
