package cosc250.livesessions

import com.wbillingsley.veautiful.html.*
import com.wbillingsley.veautiful.doctacular.*
import cosc250.styleSuite
import cosc250.Common
import cosc250.Common.{marked, willCcBy}
import cosc250.given

import <._
import ^._
import cosc250.Common.imageSlide

val week1 = DeckBuilder(1280, 720) 
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
    |1. Where the content is...
    |2. Where the assessments are...
    |3. The first problem you'll face...
    |
    |---
    |
    |## Where the content is...
    |
    |The Scala ecosystem updates quickly, but the principles remain the same. 
    |To try to work with this...
    |
    |* Where possible, I **pre-record** assessible content for you to watch at your own pace.
    |  This is on **Echo360** in folders matching the Moodle blocks. Though each year
    |  *some of it will need updating*
    |
    |* **Live sessions** (e.g. today) are for
    |  - discussion
    |  - Q&A
    |  - extra examples
    |  - content I haven't had time to record or is experimental
    |
    |In 2023, most of the updates will be focused on the second half of the unit:
    |
    | - Reactive programming
    | - Big and Fast data
    |
    |---
    |
    |## Assessments
    |
    |*Note*: 
    |
    |* What's on handbook.une.edu.au trumps everything
    |* What's on Moodle trumps what's in these slides
    |
    |i.e. this slide deck is an overview of the updates, not a definition of them.
    |
    |---
    |
    |## Assessment structure
    |
    |* Quizzes - 10% of the unit; generally to help you keep up with the content.  
    |  These are untimed and unproctored.
    |
    |* Assessment 1 - 10% of the unit; shot programming tasks to get you over the hurdle of Scala syntax.  
    |  
    |* Assessment 2 - 20% of the unit; a longer programming task but still with quite a lot of structure already provided.
    |
    |* Assessment 3 - 30% of the unit; bigger again & typically includes asynchronous (reactive) programming and streams
    |
    |* Final exam - 30% of the unit. Proctored. Typically short answer and programming tasks. 
    |
    |* Alternative assessment - for students approved for this (apply via AskUNE). Typically **longer than the final exam**.
    |
    |But... we're going to need to make some changes because of Generative AI
    |
    |ChatGPT and Codex (GitHub Copilot) are good at writing short coding snippets. 
    |
    |Codex is especially brilliant at COSC250 tutorial solutions ... 
    | ... because they're in its training set.
    |
    |""".stripMargin)
  .imageSlide("Codex knew it was my imperative week", "images/codex-autocomplete-tutorial1.png")
  .markdownSlides("""
    |
    |## Assessment 1
    |
    |Assessment 1 is a programming task to get you over the hurdle of Scala syntax.
    |
    |* It's short coding snippets. AI is very good at writing short coding snippets...
    |
    |* ... But if you just run the code through Codex, you'll be doing yourself a disservice.
    |
    |  - Assessment 1 is only worth 10% of the unit.
    |  - Its purpose is to get **you** over the hurdle of Scala syntax.
    |
    |* So...
    |
    |  - Try to write the code yourself
    |
    |  - If you are stuck, ChatGPT or Codex can help give you some hints. 
    |    (So can the comp-sci student Slack and Discord. A chat that helps you get over your syntax
    |    problems isn't really that new, but the student chat is better at giving you clues and hints
    |    so you'll learn rather than jumping to code to paste in)
    |
    |  - Note that it is **not permitted** to submit AI-written code unless you **cite the source**.  
    |    (The university considers it contract cheating)
    |
    |---
    |
    |## Evidence of authenticity...
    |
    |- Some of the code sets might make web requests to Assessory.  
    |  Don't remove these requests. (You wouldn't be doing yourself a favour).
    |
    |- A log of **you** developing your code over time is evidence that **you** wrote the code.
    |  (The AI doesn't run tests and often AI-written code just works all at once on the first run) 
    |
    |
    |---
    | 
    |## Assessment 2
    |
    |Assessment 2 is a longer programming task but still with quite a lot of structure already provided.
    |
    |* The code for assessment 2 is being re-visited.  
    |  It used to be boids (a flocking simulation), but this might be too susceptible to AI.
    |
    |* It is under experimental redevelopment  
    |  
    |  - Might use a web UI, collecting some authenticity data from you running it
    |
    |  - Might use my own UI library, which Codex sometimes trips over 
    |    (as it picks up old examples before the API changed slightly)
    |
    |* There will be a forum thread for hints: 
    |  you are encouraged to describe problems you're having, but not to post code.
    |
    |---
    |
    |## Assessment 3
    |
    |Assessment 3 typically includes an async library. In the past, this has been Akka, but 
    |Akka is moving away from open source.
    |
    |The "Reactive Programming" block of the unit will also probably see some updates
    |
    |
    |---
    |
    |## Final exam
    |
    |* In 2023, this will need to move to being **closed book**
    |
    |  - The exam is not intended to be a step-up in difficulty from the assessments
    |  - For short answer understanding questions and short coding problems, AI could write the answers  
    |  - So let's rely on the proctoring to stop AI being used, rather than make the questions more awkward
    |
    |* The alternative assessment will need to get bigger
    |
    |  - The alternative assessment isn't proctored, so we have to do more to defend its integrity
    |  - That probably means it'll be more assignment-like than exam-like
    |  - Consider whether you have the additional time available to you before applying for alt assessment  
    |    (though an SET might also be possible)
    |
    |
    |---
    |
    |## The first problem you'll face
    |
    |Scala is a well designed programming language... with a messy tooling ecosystem.
    |
    |You have several options for how to do your programming, but there are some trade-offs
    |
    |* On **turing** (our student development server)
    |  - Most things are already installed and set up, but...
    |  - You will need to remember to "setproxy" before you *first* run SBT
    |  - You should not use IntelliJ IDEA; its Scala plugin can leak your password
    |  - You can use Visual Studio Code, but don't install the "Metals" plugin yet.  
    |    (It will give you some annoying errors to get past, and you don't need it yet)
    |
    |* On **your own computer**
    |  - There are dozens of different ways of installing the tools!
    |  - Java, sbt, git, and an IDE. 
    |  - Start just by using a text editor and the command line (sbt). Only bring an IDE into it
    |    when you're more comfortable with the language and tools.
    |
    |---
    |
    |## OS choices...
    |
    |* On **Windows**
    |  - Coursier is the recommended installation tool. https://docs.scala-lang.org/getting-started/index.html
    |  - IntelliJ IDEA with the Scala plugin is very nice
    |  - You can trip over permission errors if another process has a file or directory open
    |
    |* On **Mac**
    |  - The installation instructions assume you already have homebrew installed
    |  - Developing on Mac is generally a nicer experience  
    |    It's Unix-like and the permission errors from directories being open elsewhere don't happen
    |
    |* On **Docker**
    |  - I do include "development container" configuration in a lot of repositories as I tend to use this from home
    |  - but we're not going to cover how it works in this unit. (It's in COSC220.)
    |
    |""".stripMargin
  )
  .renderSlides



  


