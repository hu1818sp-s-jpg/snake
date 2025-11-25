package snake

class Player(
  var name: String, 
  var keyMap: Player.KeyMap, 
  val snake: Snake,
  var points: Int = 0,    // TODO: count points when eating apple & counting the number of steps, använd _nbrOfSteps & _nbrOfApples & print on screen points
):

  def eatsApple(): Unit =
    snake._nbrOfApples += 1
    points += 10

  def takesStep(): Unit =
    snake._nbrOfSteps += 1
    points += 1
      

  def handleKey(key: String): Unit = 
    // om key ingår i keyMap så uppdatera snake.dir
    def update = 
      if keyMap.dir.isDefinedAt(key) then { key match

        case keyMap.dir.left => if snake.dir != East then snake.dir = West //kolla i Player.KeyMap dir (nyckel värde tabell) och uppdatera värdet i snake.dir om current direction != opposite

        case keyMap.dir.right =>  if snake.dir != West then snake.dir = East

        case keyMap.dir.up => if snake.dir != South then snake.dir = North
        
        case keyMap.dir.down => if snake.dir != North then snake.dir = South

        case _ => ()
      }
    
    update


object Player:
  enum KeyMap(left: String, right: String, up: String, down: String):
    val dir = Map(left -> West, right -> East, up -> North, down -> South) 
    case Letters extends KeyMap("a", "d", "w", "s")
    case Arrows extends KeyMap("Left", "Right", "Up", "Down")