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

object Settings:
  def configsFromFile(): Map[String, String] = 
    val register = collection.mutable.Map.empty[String, String] // skapar en mutable map som heter register
    val lines = scala.io.Source.fromFile("settings").getLines() // tar alla rader från settings filen
    for line <- lines do                                        // splittar alla lines vid = och skapar nyckel värden par och stoppar in i mappen register
      val splitLines = line.split("=")
      register.addOne(splitLines(0)->splitLines(1))
    register.toMap                                               // gör mappen oföränderlig


  given default: Settings = Settings(configsFromFile())


  trait Parser[T]:
    def fromString(value: String): Option[T]

  object Parser:
    given intParser: Parser[Int] with
      def fromString(value: String): Option[Int] = value.toIntOption   

    given stringParser: Parser[String] with
      def fromString(value: String): Option[String] = Some(value)   

    given intPairParser: Parser[(Int, Int)] with
      def fromString(value: String): Option[(Int, Int)] = 
        value.split(",") match
          case Array(a, b) =>
            for 
              x <- a.toIntOption
              y <- b.toIntOption
            yield (x, y)
          case _ => None

    given colorParser: Parser[Color] with 
      def fromString(value: String): Option[Color] = 
        value match
          case Red => Some(Colors.Red)
          case Green => Some(Colors.Green)
          case Yellow => Some(Colors.Yellow)
          case DarkGreen => Some(Colors.DarkGreen)
          case Blue => Some(Colors.DarkBlue)
          case DarkBlue => Some(Colors.DarkBlue)
          case Purple => Some(Colors.Purple)
          case DarkPurple => Some(Colors.DarkPurple)
          case Pink => Some(Colors.Pink)
          case DarkPink => Some(Colors.DarkPink)
          case Black => Some(Colors.Black)
          case DarkGray => Some(Colors.DarkGray)
          case _ => None