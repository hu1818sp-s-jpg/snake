package snake
//denna filen beskriver settings utan implementation, dvs. man kan själv redigera dem som man vill --julia

import java.awt.Color

object MutableSettings:
    
    var windowTitle: Option[String] = None
    var windowSize: Option[(Int, Int)] = None
    var blockSize: Option[Int] = None
    var background: Option[Color] = None

    var framesPerSecond: Option[Int] = None
    var messageAreaHeight: Option[Int] = None
    var messageAreaBackground: Option[Color] = None

    var applesNeededToWin: Option[Int] = None
    var color: Option[Color] = None
    var teleportAfterSteps: Option[Int] = None

    var initLength: Option[Int]= None
    var growEvery: Option[Int] = None
    var startGrowingAfter: Option[Int] = None



