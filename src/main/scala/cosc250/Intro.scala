package cosc250

import com.wbillingsley.veautiful.html.*

val frontPage = div(
  div(^.cls := "lead",
    markdown.div(
      """
        |# COSC250 Live sessions 2025
        |
        |As well as the content slides, sometimes we have things wew need to say about this year's class.
        |This site holds those decks.
        |
        |It'll update as trimester goes on (and will often look quite scratchy)
        |""".stripMargin
    ),
  )
)
