package mainpackage



class NFADrawer {
  
  var tree :RegexTree = concat(star(or(letter('a'), letter('b'))),concat(letter('a'),or(letter('a'), letter('b'))))

  private def epsilon() : RegexTree = new Epsilon()
  private def letter (l : Char) : RegexTree = new Letter(l)
  private def concat( l: RegexTree, r: RegexTree) : RegexTree = new Concat(l,r)
  private def or(l : RegexTree, r : RegexTree) : RegexTree = new Or(l,r)
  private def star(r : RegexTree) : RegexTree = new Star(r)
  
  def makeStates = {

    val (numberOfStates,stateMap) = tree.traverseInOrder(1)
    //println("numberOfStates: "+numberOfStates+ "map:"+stateMap)
    val states = numberOfStates
    val eEmpty = tree.empty
    val firstStates = tree.first 
    val finalStates = if(eEmpty) tree.last + 0 else tree.last
    tree.next(Set())
    stateMap.foreach(f => println(f._1 + " "+f._2.nexts ))
    printDot(firstStates, stateMap, finalStates, 0)
  }
  
  def printDot(firsts : Set[Int], stateMap : collection.mutable.Map[Int,RegexTree], endStates : Set[Int] , start : Int) = {

    var transString = ""
    firsts.foreach(f => {
      stateMap.get(f) match{
            case Some(x) => x match{
              case  letter : Letter => transString += addTransition(0,f,letter.l)
              case _ => throw new Exception("RegexTree is not a letter!")
            }
            case None => throw new Exception("State not found! Something went wrong!")
          }
    })
    stateMap.foreach(f =>{
      f._2.nexts.foreach(c =>{
          stateMap.get(c) match {
            case Some(state) => state match{
                case  letter : Letter => transString += addTransition(f._1,c,letter.l)
                case _ => throw new Exception("RegexTree is not a letter!")
              }
            case None => throw new Exception("State not found! Something went wrong!")
          }
        })
    })
    
    var code = "digraph nfa {\n"
    code += "\trankdir=LR;\n"
    code += "\tsize=\"8,5\"\n"
	  code += "\tnode [shape=none,width=0,height=0,margin=0]; start [label=\"\"];\n"
		code += "\tnode [shape=doublecircle];\n"
	  code += addEndStates(endStates)
    code += "\tnode [shape=circle];\n"
    code += transString
    code += "}\n"
    code  
  }
  
  def addEndStates(set : Set[Int]) : String = {
    var output = "\t"
    set.foreach(f => output += f+";")
    output += "\n"
    output
  }
  def addTransition(a : Int, b : Int, label : Char) :String = {
    "\t"+a+" -> "+b +" [label=\""+label+"\"];\n"
  }
  
  def addStart(a : Int) : String = {
    "\tstart -> "+a+"\n"
  }
}