package mainpackage


class Epsilon extends RegexTree{
  var _number : Option[Int] = None
  def number = {
    _number match{
      case Some(leafNumber) => leafNumber
      case None => throw new Exception("No number was set for epsilon")
    }
  }
  override def traverseInOrder(currentNumber : Int): (Int,collection.mutable.Map[Int,RegexTree]) = {
    println("Set number for epsilon")
    _number = Some(currentNumber)
    (currentNumber + 1, collection.mutable.Map((currentNumber,this)))
  }
  
  override def empty = true
  override def first = Set()
  override def last = Set()
  override def next(nextSet : Set[Int]) = nexts = nextSet
     
  
}