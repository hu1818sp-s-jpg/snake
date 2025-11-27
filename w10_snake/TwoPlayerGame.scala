package snake

class TwoPlayerGame(using settings: Settings) extends SnakeGame(settings):  
  private given SnakeGame = this
  private val windowDim = Dim(settings.windowSize)


  // ormar och ev. äpple, bananer etc
  private val snake1 = Snake(
    Pos(5, windowDim.y / 2, windowDim),
    East,
    Colors.Green,
    Colors.DarkGreen
  )
  private val snake2 = Snake(
    Pos(windowDim.x - 6, windowDim.y / 2, windowDim),
    West,
    Colors.Blue,
    Colors.DarkBlue
  )

  private val player1 = Player("player1", Player.KeyMap.Letters, snake1)
  private val player2 = Player("player2", Player.KeyMap.Arrows,  snake2)

  def play(playerNames: String*): Unit = 
    start(player1, player2)(snake1, snake2)
    
      // ska överskugga play i SnakeGame
