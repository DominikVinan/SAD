package mainpackage



class Letter (val l : Char) extends RegexTree {
  var _number : Option[Int] = None
  def number :Int = {
    _number match{
      case Some(leafNumber) => leafNumber
      case None => throw new Exception("No number was set for letter: "+l)
    }
  }
  override def traverseInOrder(currentNumber : Int): (Int,collection.mutable.Map[Int,RegexTree]) = {
    println("setnumbe for leaf")
    _number = Some(currentNumber)
    println("number "+l+" :"+_number + ":"+number)
    (currentNumber + 1, collection.mutable.Map((currentNumber,this)))
  }
  override def empty = false
  override def first = Set(number)
  override def last = Set(number)
  override def next(nextSet : Set[Int]) = nexts = nextSet
}