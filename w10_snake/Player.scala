package snake

class Player(
  var name: String, 
  var keyMap: Player.KeyMap, 
  val snake: Snake,
  var points: Int = 0):    // TODO: count points when eating apple

  /*def ökaPoäng(): Int =
    if Snake.eatApple() then points += 1
    window.write() //tänker vi ska skriva ut poäng i pixelWindow någonstans i TwoPlayerGame?
    */
    

      
  def handleKey(key: String): Unit = 
    // om key ingår i keyMap så uppdatera snake.dir
    keyMap.dir.get(key) match                                         // get kollar att key ingår i vår keyMap och returnerar Some om det finns, None om inte
      case Some(direction) if direction.reversed() != snake.dir =>    // match case nedan om värdet finns eller inte
        snake.dir = direction
      case _ =>


object Player
  enum KeyMap(left: String, right: String, up: String, down: String):
    val dir = Map(left -> West, right -> East, up -> North, down -> South) 
    case Letters extends KeyMap("a", "d", "w", "s")
    case Arrows extends KeyMap("Left", "Right", "Up", "Down")