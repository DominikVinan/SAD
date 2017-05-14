package mainpackage

trait RegexTree {
  var nexts = Set[Int]()
  def traverseInOrder(currentNumber : Int) : (Int,collection.mutable.Map[Int,RegexTree])
  def empty() : Boolean 
  def first() : Set[Int]
  def last()  : Set[Int]
  def next(nextSet : Set[Int]) : Unit 
}
