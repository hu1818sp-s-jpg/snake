package snake
import java.awt.Color
import org.w3c.dom.events.MutationEvent

// denna filen beskriver alla default settings som används om man inte vill skapa sina egna i MutableSettings
//vill abstrahera mer så det blir lättare att läsa


class Settings(configs: Map[String, String]):
  def getOrElse[T](key: String, default: T)(using p: Settings.Parser[T]): T = 
    configs.get(key).flatMap(p.fromString).getOrElse(default)

  private def optionalOr(opt: Option[T], default: T): T = opt.getOrElse(default)
  // skickar in opt vilket är i Mutable Settings, annars använd default settings, skickar ut resultat

  var windowTitle: String          = optionalOr(MutableSettings.windowTitel, "Snake")
  var windowSize: (Int, Int)       = optionalOr(MutableSettings.windowSize, (50,30))
  var blockSize: Int               = optionalOr(MutableSettings.blockSize, 15)
  var background: Color            = optionalOr(MutableSettings.background, Colors.Black) 
  var framesPerSecond: Int         = optionalOr(MutableSettings.framesPerSecond, 50)
  var messageAreaHeight: Int       = optionalOr(MutableSettings.messageAreaHeight, 3)
  var messageAreaBackground: Color = optionalOr(MutableSettings.messageAreaBackground, Colors.DarkGray)

  object onePlayer:
    val applesNeededToWin: Int  = optionalOr(MutableSettings.applesNeededToWin, 5) 
  
  object apple:
    val color: Color            = optionalOr(MutableSettings.color, colors.red)
    val teleportAfterSteps: Int = optionalOr(MutableSettings.teleportAfterSteps, 500)
  
  /*object banana:
    val color: Color = MutableSettings.color.getOrElse("banana.color", Colors.Yellow)
    val teleportAfterStepRange: (Int, Int) = 
      MutableSettings.teleportAfterStepRange.getOrElse("banana.teleportAfterStepRange", (500, 1000))*/
  
  object snake:
    val initLength: Int        = optionalOr(MutableSettings.initLength, 18) 
    val growEvery: Int         = optionalOr(MutableSettings. growEvery, 10)
    val startGrowingAfter: Int = optionalOr(MutableSettings.startGrowingAfter, 400)

  object monster: 
    val color: Color = optionalOr(MutableSettings.color, Colors.Pink)

object Settings:
  def configsFromFile(): Map[String, String] = // TODO: read from file
    
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
