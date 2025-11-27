package snake
//Gabi
import java.awt.Color

class Apple(using ctx: SnakeGame, settings: Settings) extends CanTeleport: //game och set är som variabler som refererar till objekten. Med using så hittar scala själv vilka obejkt som används. 

  def teleportAfterSteps: Int =
    settings.apple.teleportAfterSteps

  def teleport(): Pos =
    ctx.randomFreePos()

  def draw(): Unit = ctx.drawBlock(pos.x, pos.y, settings.apple.color)
  
  def erase(): Unit = ctx.eraseBlock(pos.x, pos.y)

  def isOccupyingBlockAt(p: Pos): Boolean =
    p == pos