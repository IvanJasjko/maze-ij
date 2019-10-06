
package object mazeij {

  case class Node(cords: Cords, connected: Seq[Cords] = Seq())
  case class Cords(x: Int, y: Int)
  type Graph = Map[Cords, Seq[Cords]]
  type Nodes = Seq[Node]

}
