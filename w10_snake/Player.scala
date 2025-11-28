package snake

class Player(
  var name: String,
  var keyMap: Player.KeyMap,
  val snake: Snake,
  var points: Int = 0
) {
  def handleKey(key: String): Unit = {
    keyMap.dir.get(key) match {
      case Some(direction) if direction.reversed() != snake.dir =>
        snake.dir = direction
      case _ => ()
    }
  }
}

object Player {
  enum KeyMap(left: String, right: String, up: String, down: String) {
    val dir = Map(left -> West, right -> East, up -> North, down -> South)
    case Letters extends KeyMap("a", "d", "w", "s")
    case Arrows  extends KeyMap("Left", "Right", "Up", "Down")
  }
}

  /*def ökaPoäng(): Int =
    if Snake.eatApple() then points += 1
    window.write() //tänker vi ska skriva ut poäng i pixelWindow någonstans i TwoPlayerGame?
    */
    

      
   


