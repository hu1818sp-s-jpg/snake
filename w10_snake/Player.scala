package snake

class Player(
  var name: String, 
  var keyMap: Player.KeyMap, 
  val snake: Snake,
  var points: Int = 0,    // TODO: count points when e.g. eating apple
):
  def handleKey(key: String): Unit = 
    // om key ingår i keyMap så uppdatera snake.dir
    def update = 
      key match

        case keyMap.left => if current.dir != keyMap.right then snake.dir(West) //kolla i PlayerKeyMap dir (nyckel värde tabell) och uppdatera värdet i snake.dir

        case keyMap.right =>  if current.dir != keyMap.lift then snake.dir(East)

        case keyMap.up => if current.dir != keyMap.down then snake.dir(North)
        
        case keyMap.down => if current.dir != keyMap.up then snake.dir(South)

        case _ => None


    if key => key.KeyMap then update.snake.dir() 


object Player:
  enum KeyMap(left: String, right: String, up: String, down: String):
    val dir = Map(left -> West, right -> East, up -> North, down -> South) 
    case Letters extends KeyMap("a", "d", "w", "s")
    case Arrows extends KeyMap("Left", "Right", "Up", "Down")