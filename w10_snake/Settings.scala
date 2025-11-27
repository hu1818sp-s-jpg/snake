package snake
import java.awt.Color
//import org.w3c.dom.events.MutationEvent

// denna filen beskriver alla default settings som används om man inte vill skapa sina egna i MutableSettings


class Settings(configs: Map[String, String]):
  def getOrElse[T](key: String, default: T)(using p: Settings.Parser[T]): T = 
    configs.get(key).flatMap(p.fromString).getOrElse(default)


  var windowTitle: String          = getOrElse("windowTitle", "Snake")
  var windowSize: (Int, Int)       = getOrElse("windowSize", (50,30))
  var blockSize: Int               = getOrElse("blockSize", 15)
  var background: Color            = getOrElse("backgroundColor", Colors.Black) 
  var framesPerSecond: Int         = getOrElse("framesPerSecond", 50)
  var messageAreaHeight: Int       = getOrElse("messageAreaHeight", 3)
  var messageAreaBackground: Color = getOrElse("messageAreaBackground", Colors.DarkGray)

  object onePlayer:
    val applesNeededToWin: Int  = getOrElse("applesNeededToWin", 5) 
  
  object apple:
    val color: Color            = getOrElse("color", Colors.Red)
    val teleportAfterSteps: Int = getOrElse("teleportAfterSteps", 500)
  
  /*object banana:
    val color: Color = MutableSettings.color.getOrElse("banana.color", Colors.Yellow)
    val teleportAfterStepRange: (Int, Int) = 
      MutableSettings.teleportAfterStepRange.getOrElse("banana.teleportAfterStepRange", (500, 1000))*/
  
  object snake:
    val initLength: Int        = getOrElse("initLength", 18) 
    val growEvery: Int         = getOrElse("growEvery", 10)
    val startGrowingAfter: Int = getOrElse("startGrowingAfter", 400)

  object monster: 
    val color: Color = getOrElse("color", Colors.Pink)
    val teleportAfterSteps: Int = getOrElse("teleportAfterSteps", 500)


object Settings:
  def configsFromFile(): Map[String, String] = Map.empty // TODO: read from file
  given default: Settings = Settings(configsFromFile())


  trait Parser[T]:
    def fromString(value: String): Option[T]

  object Parser:
    given intParser: Parser[Int] with
      def fromString(value: String): Option[Int] = value.toIntOption   

    given stringParser: Parser[String] with
      def fromString(value: String): Option[String] = Some(value)   

    given intPairParser: Parser[(Int, Int)] with
      def fromString(value: String): Option[(Int, Int)] = None // TODO

    given colorParser: Parser[Color] with 
      def fromString(value: String): Option[Color] = None // TODO
