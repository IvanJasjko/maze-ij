package mazeij

object PrinterUtils {

  def printConnectedNode() {
    print("|_")
  }

  def printTop(): Unit = {
    print("__")
  }

  def printRight(): Unit = {
    print("|")
  }

  def printTopLeft(): Unit = {
    print("_")
  }

  def printOpenLeft(): Unit = {
    print(" _")
  }

  def printOpenBottom(): Unit = {
    print("| ")
  }

  def printOpenBoth(): Unit = {
    print("  ")
  }
}