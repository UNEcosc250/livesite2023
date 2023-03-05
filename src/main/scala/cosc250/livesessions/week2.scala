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

val week2 = DeckBuilder(1280, 720) 
  .markdownSlide(
    """
      |# Live Session 2: Tooling
      |
      |## COSC250 Functional and Reactive Programming
      |
      |""".stripMargin
  ).withClass("center middle")
  .markdownSlides(
    """
    |## Tooling
    |
    |Scala is (in my view) a tidy and conceptually clean language ... with a messy tooling ecosystem.
    |
    |A lot of the tooling is hidden behind the scenes, but let's go through what things are...
    |
    |---
    |
    |### Scala packages
    |
    |Most modern languages have online repositories where libraries are published.
    |(This is covered more in COSC220)
    |
    |Scala's pacakages are generally published to Maven Central
    |
    |* https://central.sonatype.com/?smo=true  
    |
    |But you'll notice something odd about the naming - the packages have underscores, e.g.
    |
    |* [munit_3](https://central.sonatype.com/artifact/org.scalameta/munit_3/1.0.0-M7)
    |* [veautiful_sjs1_3](https://central.sonatype.com/artifact/com.wbillingsley/veautiful_sjs1_3/0.3.0) 
    |
    |This is because libraries are published for each Scala version (e.g. 2.12, 2.13, 3)
    |
    |You can get a better index across Scala packages from the Scala Library Index
    |
    |* https://index.scala-lang.org/
    |
    |---
    |
    |### Coursier
    |
    |Coursier is an artifact manager for Scala. 
    |
    |It downloads and fetches libraries from Maven Central (and can also install applications if you want).
    |
    |* https://get-coursier.io/
    |
    |However, you often won't interact with it directly. It gets called by the build tool.
    |
    |---
    |
    |### SBT
    |
    |SBT is the build tool for Scala. We started using it in week 1's tutorial
    |
    |It can 
    |
    |* Call coursier to download your libraries
    |* Compile your code
    |* Run your code
    |* Run tests
    |* Any other tasks we define for it
    |
    |A project's build definition:
    |
    |* [`project/project.properties`](https://github.com/UNEcosc250/livesite2023/blob/main/project/build.properties) specifies what version of SBT to use
    |* [`project/plugins.sbt`](https://github.com/UNEcosc250/livesite2023/blob/main/project/plugins.sbt) specifies any plugins to use
    |* [`build.sbt`](https://github.com/UNEcosc250/livesite2023/blob/main/build.sbt) specifies the build definition
    |
    |We can have other build files, but those are the usual three.
    |We start `sbt` in the directory containing `build.sbt`
    |
    |We could call sbt just with one command (`sbt compile`) 
    |but often we'll want to do more than one thing, so we'll start it in interactive mode (`sbt`). 
    |
    |""".stripMargin
  )
  .veautifulSlide(<.div(
    markdown.div(
        """
        |
        |### Build Servers
        |
        |Suppose we want to run sbt interactively, so it can build our code quickly.
        |
        |Suppose we also want to edit our code in an IDE and have it auto-compile our code when we make changes.
        |
        |We'd need two processes connected to our build: our command-line sbt, and our IDE.
        |
        |""".stripMargin
    ),
    MermaidDiagram(
        """
        |graph TD
        |  IDE --- buildServer["Build Server"]
        |  CLI["Command Line"] --- buildServer
        |
        |""".stripMargin
    ),
    markdown.div(
        """
        |
        |So we need a build server, which will run sbt and allow multiple processes to connect to it.
        |
        |""".stripMargin
    ),   
  ))
  .veautifulSlide(<.div(
    markdown.div(
        """
        |
        |### SBT
        |
        |By default, the `sbt` command will try to create a new server, and connect to it.
        |
        |However, if your IDE has already created one, you can tell sbt to connect to the one that's
        |already started:
        |
        |* `sbt --client` will start a Command-Line-Interface and try to connect to an existing build server
        |* `sbt --java-client` does the same thing, but using a Java version of the CLI (needed for ARM-based Macs)
        |
        |""".stripMargin
    ),
    MermaidDiagram(
        """
        |graph TD
        |  IDE --- buildServer["SBT build server"]
        |  CLI["sbt --java-client"] --- buildServer
        |
        |""".stripMargin
    ),   
  ))
  .veautifulSlide(<.div(
    markdown.div(
        """
        |### Bloop
        |
        |Bloop is *another* build server. It's a bit like sbt, but it's designed to be used by IDEs.
        |
        |Generally, it works in the same way
        |
        |""".stripMargin
    ),
    MermaidDiagram(
        """
        |graph TD
        |  IDE --- buildServer["Bloop server"]
        |  CLI["bloop cli"] --- buildServer
        |
        |""".stripMargin
    ),
    markdown.div(
        """
          |However, it has two problems (from my perspective)
          |
          |1. It doesn't have a good CLI, so working from the command-line is painful
          |2. The standard version of bloop has problems on multi-user machines: you can accidentally
          |   connect to *someone else's* bloop server
          |
          |It is (perhaps unfortunately) the default build server that "Metals" uses
          |""".stripMargin
    )   
  ))
  .veautifulSlide(<.div(
    markdown.div(
        """
        |### Metals
        |
        |Metals is a plugin for Visual Studio Code. It tries to make VS Code work cleverly with
        |Scala projects.
        |
        |By default it uses bloop as its build server, but you can configure it to use sbt instead.
        |
        |* View -> Command Pallette... -> "Metals: Switch build server"
        |
        |""".stripMargin
    ),
    MermaidDiagram(
        """
        |graph TD
        |  IDE["VS Code with Metals"] --- buildServer["SBT server"]
        |  CLI["sbt --java-client"] --- buildServer
        |
        |""".stripMargin
    ),
    markdown.div(
        """
          |When it works, it's nice and smooth. When it doesn't, it's painful to get it working again.
          |
          |(**Advice: Don't use it until you're comfortable with Scala. You don't really need it**)
          |
          |
          |
          |""".stripMargin
    )   
  ))
  .markdownSlides(
    """
    |### Fixing Metals:
    |
    |Typically:
    |
    |* Make sure you switch the build server to sbt **before** you import the project
    |
    |* Sometimes the IDE seems to get out of sync and an `sbt clean` or even:
    |  - closing the IDE
    |  - stopping SBT
    |  - `git clean -Xfd` (which deletes all the files that aren't in version control)
    |  to get it back into a known-working state
    |
    |---
    |
    |### Scala-CLI
    |
    |Scala-CLI is a new project that aims to make it easy to write command-line applications in Scala.
    |
    |It has a much simpler interface ... but uses bloop behind the scenes (so we need to test it!)
    |
    |* Unlike SBT, *there is no build definition file*
    |
    |* Intead, build directives are specified in the source code
    |
    |e.g.
    |
    |```scala
    |//> using dep "com.wbillingsley::doctacular::0.3.0"
    |
    |import com.wbillingsley.veautiful.html.*
    |import com.wbillingsley.veautiful.doctacular.*
    |```
    |
    |---
    |
    |### Scala-CLI and Scala.js
    |
    |Scala-CLI can be used to build Scala.js applications.
    |
    |Though this is a work in progress, it looks like it will become a very easy way of doing
    |JS development
    |
    |```sh
    |scala-cli --js --power package . -o main.js
    |```
    |
    |(Compile everything in this directory, and output it as a single file `main.js`)
    |
    |""".stripMargin
  )
  .renderSlides



  


