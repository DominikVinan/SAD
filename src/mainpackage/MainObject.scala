package mainpackage

object MainObject {
  def main(args: Array[String]) {
    val drawer = new NFADrawer()
    println(drawer.makeStates)
  }
}