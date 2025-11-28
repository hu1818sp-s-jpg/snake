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

  var player1Points = 0
  var player2Points = 0

  val player1 = Player("player1", Player.KeyMap.Letters, snake1, player1Points)
  val player2 = Player("player2", Player.KeyMap.Arrows,  snake2, player2Points)

  def ökaPoäng(): Unit =
    if snake1.eatApple() then 
      player1Points += 1
    if snake2.eatApple() then 
      player2Points += 1

    drawInMessageArea(s"Player1 points: $player1Points", 1, 2)
    drawInMessageArea(s"Player 2 points: $player2Points", 20, 2)

  def play(playerNames: String*): Unit = 
    start(player1, player2)(snake1, snake2, apple1, apple2, monster)
    
    //if playerNames.length != 2 then
    //  pixelWindow.hide()
    
     
