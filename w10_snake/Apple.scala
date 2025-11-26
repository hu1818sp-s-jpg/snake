package snake
//Gabi
import java.awt.Color

class Apple(using ctx: SnakeGame, settings: Settings) //game och set är som variabler som refererar till objekten. Med using så hittar scala själv vilka obejkt som används. 

  def teleportAfterSteps: Int =
    settings.apple.teleportAfterSteps

  def teleport(): Pos =
    ctx.randomFreePos()

  def draw(): Unit =
    game.drawBlock(pos, settings.apple.color)

  def erase(): Unit =
    game.eraseBlock(pos)

  def isOccupyingBlockAt(p: Pos): Boolean =
    p == pos