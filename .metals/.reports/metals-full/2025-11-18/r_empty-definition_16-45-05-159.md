error id: file:///C:/Users/gabri/Documents/GitHub/snake/w10_snake/Main.scala:Dialog.
file:///C:/Users/gabri/Documents/GitHub/snake/w10_snake/Main.scala
empty definition using pc, found symbol in pc: Dialog.
empty definition using semanticdb
empty definition using fallback
non-local guesses:
	 -introprog/Dialog.
	 -scala/Predef.introprog.Dialog.
offset: 402
uri: file:///C:/Users/gabri/Documents/GitHub/snake/w10_snake/Main.scala
text:
```scala
package snake

//def createOnePlayerGame(): Unit = 
  //Settings.default.windowTitle = "Snake: One Player"
  //OnePlayerGame().play()

def createTwoPlayerGame(): Unit = 
  Settings.default.windowTitle = "Snake: Two Player"
  TwoPlayerGame().play()

@main
def run: Unit = 
  val buttons = 
    Seq("One", "Two", "Competition", "Tournament", "Cancel")
  val selected = 
    introprog.Dialo@@g.select("Number of players?", buttons, "Snake")
  selected match 
    case "One"         => createOnePlayerGame()
    case "Two"         => createTwoPlayerGame()
    case "Competition" => println(s"TODO: $selected") 
    case "Tournament"  => println(s"TODO: $selected") 
    case  _            => println("Goodbye!")
```


#### Short summary: 

empty definition using pc, found symbol in pc: Dialog.