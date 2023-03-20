package cosc250

import com.wbillingsley.veautiful.html._
import com.wbillingsley.veautiful.doctacular._
import Medium._
import org.scalajs.dom

import Common._

val site = Site()

object Main {

  val scaleChallengesToWindow:Boolean = {
    !dom.window.location.search.contains("scale=off")
  }

  def main(args:Array[String]): Unit = {
    val n = dom.document.getElementById("render-here")
    n.innerHTML = ""

    styleSuite.install()

    import site.given 
        
    site.toc = site.Toc(
      "Home" -> site.HomeRoute,
      
      "Weekly live sessions" -> site.Toc(
        "Wk 1: Orientation" -> site.addDeck("week1", livesessions.week1),
        "Wk 2: Tooling" -> site.addDeck("week2", livesessions.week2),
        "Wk 4: Wordle, part 1" -> site.addDeck("week4", livesessions.week4),
        /*"Intro to Scala syntax" -> site.add("introScala", 
          Alternative("Slide deck", Deck(() => imperative.introScala)),
          Alternative("Watch the video", Video(() => Echo360Video("92548534-cc2c-4661-99d4-f2dfc1e26309"))),
        ),*/
      ),
    )
    
    site.home = () => site.renderPage(frontPage)
    site.attachTo(n)

  }

}
