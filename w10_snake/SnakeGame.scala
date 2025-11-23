package snake

object SnakeGame:
  enum State:
    case Starting, Playing, GameOver, Quitting
  export State.*
  
abstract class SnakeGame(settings: Settings) extends introprog.BlockGame(
  title                 = settings.windowTitle,
  dim                   = settings.windowSize,
  blockSize             = settings.blockSize,
  background            = settings.background,
  framesPerSecond       = settings.framesPerSecond,
  messageAreaHeight     = settings.messageAreaHeight,
  messageAreaBackground = settings.messageAreaBackground
):
  // exempel på olika synlighet (diskutera val av synlighet utifrån användning)
  var entities: Vector[Entity] = Vector.empty
  protected var players: Vector[Player] = Vector.empty
  private var isPaused = false

  import SnakeGame.*  

  protected var state: State = Starting
  private var _iterationsSinceStart = 0
  def iterationsSinceStart = _iterationsSinceStart

  def enterStartingState(): Unit = //sudda, meddela "tryck space för start"
    clear()
    clearMessageArea()
    drawTextInMessageArea("Tryck SPACE för att starta spelet", 10, 2) //Osäker över hur stor texten ska vara, återkom hit!!
    state = Starting

  def enterPlayingState(): Unit = //sudda, för varje entitet: nollställ & rita
    clear()
    clearMessageArea()

    for e <- entities do //För varje entitet i spelet
      e.reset()
      e.draw()

    _iterationsSinceStart = 0 //Nollställ räknaren
    state = Playing

  def enterGameOverState(): Unit = // meddela "game over"
    clearMessageArea()
    drawInMessageArea("GAME OVER - Tryck SPACE för att starta igen", 10, 2)
    state = GameOver

  def enterQuittingState(): Unit = 
    println("Goodbye!")
    pixelWindow.hide()
    state = Quitting

  def randomFreePos(): Pos = // dra slump-pos tills ledig plats, används av frukt, monster
    var p: Pos = null

    var free = false

    while !free do //sålänge platsen INTE är ledig slumpa ny
      val x = util.Random.nextInt(dim.width) //next.Int ger ett heltal från 1 till n-1 (n-1 = dim.width -1)
      val y = util.Random.nextInt(dim.height)
      p = Pos(x, y) //Slumpar fram random x och y kordinater inom förstrets dimensioners gränser
      
      free = entities.forall(e => !e.isOccupyingBlockAt (p))

    p

  override def onKeyDown(key: String): Unit = 
    println(s"""key "$key" pressed""")
    state match 
      case Starting => if key == " " then enterPlayingState()

      case Playing =>
        if key == "Esc" then
          println(s"Toggle pause: isPaused == $isPaused")
          isPaused = !isPaused
        else
          players.foreach(_.handleKey(key))

      case GameOver =>
        if key == " " then enterPlayingState()
        else if key == "Esc" then enterQuittingState()

      case _ =>

  override def onClose(): Unit = 
    println("Window Closed!")
    enterQuittingState()

  /** Implement this with logic for when to end the game */ 
  def isGameOver: Boolean //Hugo

  /** Override this if you want to add game-logic in gameLoopAction
   *  Call super.onIteration() if you want to keep the step counter.
   */
  def onIteration(): Unit = 
    clearMessageArea()
    drawTextInMessageArea(s"Number of steps: $iterationsSinceStart", 10, 2)

  override def gameLoopAction(): Unit = 
    if state == Playing && !isPaused then
      _iterationsSinceStart += 1
      entities.foreach(_.erase())
      entities.foreach(_.update())
      entities.foreach(_.draw())
      onIteration()
      if isGameOver then enterGameOverState()

  final def start(ps: Player*)(es: Entity*): Unit = 
    players = ps.toVector
    entities = es.toVector
    isPaused = false
    pixelWindow.show()  // möjliggör omstart även om fönstret stängts...
    enterStartingState()
    gameLoop(stopWhen = state == Quitting)

  /** Implement this with a call to start with specific players and entities. */
  def play(playerNames: String*): Unit //Gabi

