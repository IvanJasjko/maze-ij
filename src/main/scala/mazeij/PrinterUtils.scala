package mazeij

object PrinterUtils {

  def printGraph(graph: Graph, start: Cords, finish: Cords): Unit = {

    val size = math.sqrt(graph.size).toInt
    var botRowBuffer = ""

    //Newline to tackle my OCD
    println()
    for (y <- 1 to size) {
      val nodes = for (x <- 1 to size) yield {
        val node = Cords(x, y)

        if (node == start) initNode(node, graph(node), size, "S")
        else if (node == finish) initNode(node, graph(node), size, "F")
        else initNode(node, graph(node), size, " ")
      }
      val nodeTriples = nodes.map(_.split("\n"))

      val topRow = nodeTriples.map(_(0)).mkString("\b")
      val midRow = nodeTriples.map(_(1)).mkString("\b")
      val botRow = nodeTriples.map(_(2)).mkString("\b")

      println(topRow)
      println(midRow)
      botRowBuffer = botRow
    }
    println(botRowBuffer)
  }

  private def initNode(node: Cords, connections: Seq[Cords], size: Int, symbol: String): String = {
    val nodeStr = s"""*****
       || $symbol |
       |*****""".stripMargin

    if (noTopConn(node, connections) && noLeftConn(node, connections))
      return s"""*   *
                |${" "} $symbol |
                |*****""".stripMargin

    if (noLeftConn(node, connections))
      return s"""*****
                |${" "} $symbol |
                |*****""".stripMargin

    if (noTopConn(node, connections))
      return s"""*   *
                || $symbol |
                |*****""".stripMargin
    nodeStr
  }

  private def noTopConn(node: Cords, connections: Seq[Cords]): Boolean = {
    !connections.contains(Cords(node.x, node.y - 1)) && node.y != 1
  }

  private def noLeftConn(node: Cords, connections: Seq[Cords]): Boolean = {
    !connections.contains(Cords(node.x - 1, node.y)) && node.x != 1
  }
}