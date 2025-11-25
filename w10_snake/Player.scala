package snake

class Player(
  var name: String, 
  var keyMap: Player.KeyMap, 
  val snake: Snake,
  var points: Int = 0,    // TODO: count points when eating apple & counting the number of steps, använd _nbrOfSteps & _nbrOfApples & print on screen points
):

  def eatsApple(): Unit =
    if Apple.erase() then snake._nbrOfApples += 1 && points += 10

      

  def handleKey(key: String): Unit = 
    // om key ingår i keyMap så uppdatera snake.dir
    def update = 
      if keyMap.dir.get(key) then { key match

        case Some(newDir) => if snake.dir != key && snake.dir.opposite != key then
          snake.dir = snake.copy(dir = newDir)
          
           //kolla i Player.KeyMap dir (nyckel värde tabell) och uppdatera värdet i snake.dir om current direction != opposite



        case _ => ()
      }
    
    update


object Player:
  enum KeyMap(left: String, right: String, up: String, down: String):
    val dir = Map(left -> West, right -> East, up -> North, down -> South) 
    case Letters extends KeyMap("a", "d", "w", "s")
    case Arrows extends KeyMap("Left", "Right", "Up", "Down")