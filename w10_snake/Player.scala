package snake

class Player(
  var name: String, 
  var keyMap: Player.KeyMap, 
  val snake: Snake,
  var points: Int = 0,    // TODO: count points when eating apple & counting the number of steps, använd _nbrOfSteps & _nbrOfApples & print on screen points
):

  //def eatsApple(): Unit =
   

      

  def handleKey(key: String): Unit =
  keyMap.dir.get(key).foreach { dir =>
    if dir != snake.dir then snake.dir = dir
  }


    
  /*def handleKey(key: String): Unit = 
    // om key ingår i keyMap så uppdatera snake.dir
    keyMap.dir.get(key) match
      case Some(direction) if direction.reversed() != snake.dir => 
        snake.dir = direction
      case _ =>

    val hejsan = "a"
    hejsan match  
      case "b" => println("B")
      case "c" => println("C")
      case "d" => println("D")
      case "e" => println("E")
      case "f" => println("F")
      case rest => 
        println(rest)

    
    
    
    
    
    if keyMap.dir.contains(key) then
      val dir: Dir = keyMap.dir.get(key).get
      if dir.reversed() =! snake.dir then
        snake.dir = dir
        


object Player:
  enum KeyMap(left: String, right: String, up: String, down: String):
    val dir = Map(left -> West, right -> East, up -> North, down -> South) 
    case Letters extends KeyMap("a", "d", "w", "s")
    case Arrows extends KeyMap("Left", "Right", "Up", "Down")*/