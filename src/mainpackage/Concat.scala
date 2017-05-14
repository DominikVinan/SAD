package mainpackage;


class Concat(val left : RegexTree, val right : RegexTree) extends RegexTree {
  override def traverseInOrder(currentNumber : Int): (Int,collection.mutable.Map[Int,RegexTree]) = {
    val (newNumber,map) =  left.traverseInOrder(currentNumber)
    val (incNumber,newMap) = right.traverseInOrder(newNumber)
    (incNumber,newMap ++ map)
  }
   override def empty = left.empty && right.empty
   override def first () = {
     if(left.empty) left.first() ++ right.first()
     else left.first()
   }
   
   override def last() = {
     if(right.empty) left.last() ++ right.last()
     else right.last()
   }
   
   override def next(nextSet : Set[Int]) = {
     nexts = nextSet
     if(right.empty)
       left.next(right.first() ++ nexts)
     else
       left.next(right.first())
     right.next(nexts)
   }
   
}

