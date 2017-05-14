package mainpackage



class Or(left : RegexTree, right : RegexTree) extends RegexTree{
  override def traverseInOrder(currentNumber : Int): (Int,collection.mutable.Map[Int,RegexTree]) = {
    val (newNumber,map) =  left.traverseInOrder(currentNumber)
    val (incNumber, newMap) = right.traverseInOrder(newNumber)
    (incNumber,newMap ++ map)
  }
  override def empty = left.empty || right.empty
  override def first() = left.first() ++ right.first()
  override def last() = left.last() ++ right.last()
  
   override def next(nextSet : Set[Int]) = {
     nexts = nextSet
     left.next(nexts)
     right.next(nexts)
   }
}