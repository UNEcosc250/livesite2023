package cosc250

import com.wbillingsley.veautiful.html.*

val frontPage = div(
  div(^.cls := "lead",
    markdown.div(
      """
        |# COSC250 Live sessions 2023
        |
        |This is a site for holding the slides for the live sessions in 2023.
        |It'll update as trimester goes on (and will often look quite scratchy)
        |""".stripMargin
    ),
  )
)
