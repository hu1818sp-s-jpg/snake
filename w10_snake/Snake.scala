package snake

import java.awt.Color

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

<<<<<<< HEAD
  def reset(): Unit =   // återställ starttillstånd, ge rätt svanslängd
    dir = initDir
    _nbrOfSteps = 0
    _nbrOfApples = 0
    _isEatenByMonster = false
  

    val startHead = initPos +dir
    val startBody = 

  def grow(): Unit = 
    if body.isEmpty then  // väx i rätt riktning med extra svansposition
      body += initPos
    else if body.length == 1 then 
      body += body.last
    else 
      val tail = body.last
      val preTail = body(body.length - 2)
      val dim = tail.dim
      
      val stepX = (tail.x -preTail.x + dim.x) % dim.x match
        case 1 => 1
        case n if n == dim.x-1 = -1
        case_ => 0
      
      val stepY = (tail.y - preTail.y + dim.y) % dim.y match
        case 1 => 1
        case n if n == dim.y-1 => -1
        case_ => 0

      val newTail = Pos(tail.x + stepX, tail.y +stepY, dim)
      body += newTail
      
      

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
=======
  def reset(): Unit = ??? //Hugo // återställ starttillstånd, ge rätt svanslängd

  def grow(): Unit =  //Hugo // väx i rätt riktning med extra svansposition
    val tail = body.last //last innebär sista elementet (pos) på ormen dvs svansen
    body.append(tail) //append: lägg till ett element i slutet av ormen

  def shrink(): Unit = // krymp svansen om kroppslängden är större än 2
    if body.length > 2 then
      body.remove(body.length - 1)

  def isOccupyingBlockAt(p: Pos): Boolean =  // kolla om p finns i kroppen
    body.contains(p)

  def isHeadCollision(other: Snake): Boolean = // kolla om huvudena krockar
    this.body.head == other.body.head

  def isTailCollision(other: Snake): Boolean = // mitt huvud i annans svans
    val myHead = this.body.head
    other.body.tail.contains(myHead)

  private var _isEatenByMonster: Boolean = false
  def isEatenByMonster: Boolean = _isEatenByMonster
  def eatenByMonster(): Unit = 
    _isEatenByMonster = true

  def move(): Unit = 
    if !_isEatenByMonster then
      val newHead = body.head + dir //Pos + direction
      body.prepend(newHead)
      _nbrOfSteps += 1

      val shouldGrow =
        _nbrOfSteps > startGrowingAfter && //Ormen ska ju inte växa i början utan efter 400 steg
        (_nbrOfSteps - startGrowingAfter) % growEvery == 0 //Jämnt delbart med settings growEvery, subtraktion pga annars växer den på steg 10 ist för 410!

      if !shouldGrow then
        shrink()
      else 
        () //ingenting händer

      //TODO behövs växande och krympande detta är endast så att den rör sig
>>>>>>> 68fe814757d12352fdce3fdf27f2893e666b24dd
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

<<<<<<< HEAD
  def draw(): Unit = 
    ctx.pixelWindow.drawBlock(body.head.x, body.head.y, headColor)
    body.tail.foreach(p => ctx.pixelWindow.drawBlock(p.x, p.y, tailColor)

  def erase(): Unit = 
    body.foreach(p => ctx.pixelWindow.clearBlock(p.x, p.y))
=======
  def draw(): Unit = ??? //Hugo

  def erase(): Unit = ??? //Hugo
>>>>>>> 68fe814757d12352fdce3fdf27f2893e666b24dd
