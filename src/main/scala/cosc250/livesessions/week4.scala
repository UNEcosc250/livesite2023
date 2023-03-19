package cosc250.livesessions

import com.wbillingsley.veautiful.html.*
import com.wbillingsley.veautiful.doctacular.*
import cosc250.styleSuite
import cosc250.{Common, MermaidDiagram}
import cosc250.Common.{marked, willCcBy}
import cosc250.given

import <._
import ^._
import cosc250.Common.imageSlide
import cosc250.wordgame.embedWordle

val week4 = DeckBuilder(1280, 720) 
  .markdownSlide(
    """
      |# Live Session 4: Wordle part 1
      |
      |## COSC250 Functional and Reactive Programming
      |
      |""".stripMargin
  ).withClass("center middle")
  .markdownSlides(
    """
    |## Wordle
    |
    |Let's start to live-code a version of the game "Wordle" for the command-line
    |
    |There's a version of the game built into this deck as well
    |
    |""".stripMargin
  )
  .veautifulSlide(<.div(
      embedWordle
    ),
  )
  .markdownSlides(
    """
    |## Scastie
    |
    |We'll live code this mostly online, using Scastie
    |
    |https://scastie.scala-lang.org/
    |
    |
    |
    |""".stripMargin
  )
  .renderSlides



  


