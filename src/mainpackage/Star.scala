package mainpackage



class Star ( r : RegexTree) extends RegexTree {
  override def traverseInOrder(currentNumber : Int): (Int,collection.mutable.Map[Int,RegexTree]) = {
    r.traverseInOrder(currentNumber)
  }
  override def empty = true
  override def first() = r.first()
  override def last() = r.last()
   override def next(nextSet : Set[Int]) = {
     nexts = nextSet
     r.next(r.first() ++ nexts)
   }
}