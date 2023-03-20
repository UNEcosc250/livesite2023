package cosc250.wordgame

import com.wbillingsley.veautiful.*
import com.wbillingsley.veautiful.html.* 
import org.scalajs.dom.Fetch
import scala.concurrent.{Future, ExecutionContext}
import scala.util.{Try, Success, Failure, Random}

given ec:ExecutionContext = scala.scalajs.concurrent.JSExecutionContext.queue

import cosc250.styleSuite

lazy val wordList = 
    for 
        words <- Fetch.fetch("resources/words.txt").toFuture
        text <- words.text().toFuture
    yield text.split("\r\n")


class FutureComponent(f:Future[VHtmlContent]) extends DHtmlComponent with Keep(f) {

    f.onComplete { _ => rerender() }

    def render = <.div(
        f.value match {
            case None => <.div("Loading...")
            case Some(Failure(x)) => <.div("Error: ", x.getMessage)
            case Some(Success(x)) => x
        }
    )

}

def embedWordle = FutureComponent(for w <- wordList yield WordGame(w))

val worldeStyle = Styling(
        "font-size: 36px; display: inline-block; text-align: center; width: 350px;" 
    )
    .modifiedBy(
        " .grid" -> "width: 250px; margin: auto; display: grid; grid-template-rows: repeat(6, 1fr); grid-gap: 5px;",
        " .row" -> "display: grid; grid-template-columns: repeat(5, 1fr); grid-gap: 5px;",
        " .letter" -> "line-height: 1; border: 1px solid black; display: inline-block;",
        " .letter::before" -> "content: ''; padding-bottom: 100%; display: inline-block;",
        " .letter.Absent" -> "background: #777; color: white;",
        " .letter.RightPlace" -> "background: #484; color: white;",
        " .letter.WrongPlace" -> "background: #ecb115; color: white;",
        " .key" -> "font-size: 21px; min-width: 1em; color: white; background: #aaa; margin: 0.1em; padding: 0.2em; border-radius: 10px; display: inline-block;",
        " .key.RightPlace" -> "background: #484;",
        " .key.WrongPlace" -> "background: #ecb115;",
        " .key.Absent" -> "background: #777;",
        " .key:disabled" -> "opacity: 0.5;"
    )
    .register()

class WordGame(wordList:Seq[String]) extends DHtmlComponent {

    def randomWord = wordList(Random.nextInt(wordList.length))

    def newGame = Game(randomWord, Seq())

    val game = stateVariable(newGame)

    val typing = stateVariable("")

    def validGuess = typing.value.length == 5 && wordList.contains(typing.value.toUpperCase())

    def keyboard = {
        def letterKey(letter:Char) =
            val fb = maxColour(letter, game.value.guesses).map(_.toString).getOrElse("")
            <.button(^.cls := ("key", fb), ^.onClick --> guessType(letter), letter.toString)

        def guessType(letter:Char) = 
            if typing.value.length < 5 then typing.value = typing.value + letter

        def guessBackspace() = typing.value = typing.value.dropRight(1)

        def guessEnter() = 
            if validGuess then
                game.value = game.value.copy(guesses = game.value.guesses :+ compare(game.value.target, typing.value))
                typing.value = ""


        <.div(
            <.div(for c <- "QUERTYUIOP" yield letterKey(c)),
            <.div(for c <- "ASDFGHJKL" yield letterKey(c)),
            <.div(
                <.button(^.cls := "key", ^.onClick --> guessEnter(), 
                    if !validGuess then ^.attr.disabled := "disabled" else Seq(), 
                    "ENTER"
                ),
                for c <- "ZXCVBNM" yield letterKey(c),
                <.button(^.cls := "key", ^.onClick --> guessBackspace(), "⌫")
            )
        )
    }

    def render = <.div(^.cls := worldeStyle,
        <.h3("Scaladle"),
        <.div(^.cls := "grid",
            for 
                guess <- game.value.guesses 
            yield <.div(^.cls := "row",
                for (c, feedback) <- guess yield <.span(^.cls := "letter " + feedback.toString(), c.toString)
            ),

            if !game.value.over then 
                <.div(^.cls := "row",
                    for 
                        letter <- (typing.value ++ "      ").take(5)
                    yield <.span(^.cls := "letter", letter.toString)
                )
            else 
                <.div(
                    if game.value.lost then 
                        for c <- game.value.target yield <.span(^.cls := "letter", c.toString)
                    else Seq(),
                    <.button(^.onClick --> { game.value = newGame }, "New game")
                )
            ,

            if (!game.value.over) then 
                for  _ <- game.value.guesses.length until 5 yield <.div(^.cls := "row",
                    for  _ <- 0 until 5 yield <.span(^.cls := "letter", " ")        
                )
            else Seq()
        ),

        if game.value.over then Seq() else Seq(keyboard)
    )

}

