package cosc250.livesessions

import com.wbillingsley.veautiful.html.*
import com.wbillingsley.veautiful.doctacular.*
import cosc250.styleSuite
import cosc250.Common
import cosc250.Common.{marked, willCcBy}
import cosc250.given

import <._
import ^._

val week1 = DeckBuilder(1920, 1080) 
  .markdownSlide(
    """
      |# COSC250 Functional and Reactive Programming
      |
      |## Live session 1
      |
      |*Monday 27 Feb 2023*
      |
      |""".stripMargin
  ).withClass("center middle")
  .markdownSlides(
    """
    |## Agenda
    |
    |1. Introduction
    |
    |""".stripMargin
  )
  .renderSlides



  


