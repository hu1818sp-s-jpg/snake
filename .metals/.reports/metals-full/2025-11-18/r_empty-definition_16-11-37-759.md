error id: file:///C:/Users/gabri/Documents/GitHub/snake/w10_snake/Snake.scala:scala/Boolean#
file:///C:/Users/gabri/Documents/GitHub/snake/w10_snake/Snake.scala
empty definition using pc, found symbol in pc: scala/Boolean#
empty definition using semanticdb
empty definition using fallback
non-local guesses:
	 -Boolean#
	 -scala/Predef.Boolean#
offset: 1242
uri: file:///C:/Users/gabri/Documents/GitHub/snake/w10_snake/Snake.scala
text:
```scala
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

  def reset(): Unit = ???  // återställ starttillstånd, ge rätt svanslängd

  def grow(): Unit = ??? // väx i rätt riktning med extra svansposition

  def shrink(): Unit = ??? // krymp svansen om kroppslängden är större än 2

  def isOccupyingBlockAt(p: Pos): Boolean = ??? // kolla om p finns i kroppen

  def isHeadCollision(other: Snake): Boolean = ??? // kolla om huvudena krockar

  def isTailCollision(other: Snake): Boolean = ??? // mitt huvud i annans svans

  private var _isEatenByMonster: Boolean = false
  def isEatenByMonster: Boolea@@n = _isEatenByMonster
  def eatenByMonster(): Unit = ???

  def move(): Unit = ??? 
    // väx och krymp enl. regler
    // åtgärder om äter frukt eller blir uppäten av monster

  override def toString = // bra vid println-debugging
    body.map(p => (p.x, p.y)).mkString(">:)", "~", s" going $dir")

  def draw(): Unit = ???

  def erase(): Unit = ???

```


#### Short summary: 

empty definition using pc, found symbol in pc: scala/Boolean#