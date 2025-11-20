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
    // väx och krymp enl. regler
    // åtgärder om äter frukt eller blir uppäten av monster

  override def toString = // bra vid println-debugging
    body.map(p => (p.x, p.y)).mkString(">:)", "~", s" going $dir")

  def draw(): Unit = ??? //Hugo

  def erase(): Unit = ??? //Hugo
