package snake
import java.awt.Color

class Monster(using ctx: SnakeGame, settings: Settings) extends CanTeleport:


  def teleportAfterSteps: Int =
    settings.monster.teleportAfterSteps

  def teleport(): Pos =
    ctx.randomFreePos()

  def draw(): Unit = ctx.drawBlock(pos.x, pos.y, settings.monster.color)
  
  def erase(): Unit = ctx.eraseBlock(pos.x, pos.y)


  def isOccupyingBlockAt(p: Pos): Boolean =
    p == pos