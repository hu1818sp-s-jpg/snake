package snake

import java.awt.Color
import introprog.PixelWindow


class Snake (
  val initPos: Pos,
  val initDir: Dir,
  val headColor: Color,
  val tailColor: Color,
)(using ctx: SnakeGame, settings: Settings) extends CanMove: 
  var dir: Dir = initDir
  val initBody: List[Pos] = List(initPos + initDir, initPos)
  val body: scala.collection.mutable.Buffer[Pos] = initBody.toBuffer

  val initLength: Int = settings.snake.initLength
  val growEvery: Int = settings.snake.growEvery
  val startGrowingAfter: Int = settings.snake.startGrowingAfter

  private var _nbrOfSteps = 0
  def nbrOfSteps: Int = _nbrOfSteps

  private var _nbrOfApples = 0
  def nbrOfApples: Int = _nbrOfApples

  def reset(): Unit =   // återställ starttillstånd, ge rätt svanslängd
    dir = initDir
    _nbrOfSteps = 0
    _nbrOfApples = 0
    _isEatenByMonster = false
    body.clear()
    val head =initPos + initDir
    body += head
    var p = head 
    for _ <- 1 until initLength do 
      p = p - initDir
      body +=p


  def grow(): Unit =  
    body.length match
      case 0 =>
        body += initPos
      case 1 =>
        body += body.last
      case _ =>
        val tail = body.last
        val pretail = body(body.length - 2)
        body += tail + (tail - pretail)  
      

  def shrink(): Unit =  // krymp svansen om kroppslängden är större än 2
    if body.length > 2 then 
      body.remove(body.length-1)
  def isOccupyingBlockAt(p: Pos): Boolean =  // kolla om p finns i kroppen
    body.contains(p)

  def isHeadCollision(other: Snake): Boolean =  // kolla om huvudena krockar
    val myHead = body.head
    val otherHead = other.body.head
    myHead == otherHead

  def isTailCollision(other: Snake): Boolean =  // mitt huvud i annans svans
    val myTail = body.tail
    val otherTail = other.body.tail
    myTail ==otherTail

  private var _isEatenByMonster: Boolean = false
  def isEatenByMonster: Boolean = _isEatenByMonster

  def eatenByMonster(): Unit = 
    _isEatenByMonster = true
  
    

  def move(): Unit =  
    // väx och krymp enl. regler
    // åtgärder om äter frukt eller blir uppäten av monster
    if _isEatenByMonster then return
    _nbrOfSteps +=1
    val newHead = body.head + dir
    body.prepend(newHead)

    val shouldGrow = 
      _nbrOfSteps >= startGrowingAfter &&
      ((_nbrOfSteps - startGrowingAfter) % growEvery == 0)
    
    if shouldGrow then grow()
    else shrink()

  override def toString = // bra vid println-debugging
    body.map(p => (p.x, p.y)).mkString(">:)", "~", s" going $dir")

  def draw(): Unit =
    if !_isEatenByMonster && body.nonEmpty then
      ctx.drawBlock(body.head.x, body.head.y, headColor)
      body.tail.foreach(p => ctx.drawBlock(p.x, p.y, tailColor))

  def erase(): Unit =
    if body.nonEmpty then
      ctx.eraseBlock(body.head.x, body.head.y)
      body.tail.foreach(p => ctx.eraseBlock(p.x, p.y))

    
      
    
