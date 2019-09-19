package object mazeij {

  case class Node(cords: Cords, connected: Seq[Cords] = Seq())
  case class Cords(x: Int, y: Int)
  type Nodes = Seq[Node]

}
