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
      |# COSC250 Programming Paradigms
      |
      |## Welcome
      |
      |*Monday 24 Feb 2025*
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
    |* **Lecture sessions** (e.g. today) are for
    |  - discussion
    |  - Q&A
    |  - extra examples
    |  - content I haven't had time to record or is experimental
    |
    |---
    |
    |## A way to think about it 
    |
    |One of the things people find hard to keep error-free in programming is
    |
    |> shared mutable data
    |
    |We're going to end up seeing 3 different ways programming languages avoid this problem:
    |
    |* Functional programming (avoid "mutable")
    |* Concurrency-oriented programming (avoid "shared")
    |* Rust (disallow things being both mutable and shared at the same time - by having the compiler track ownership and borrowing)
    |
    |That's not all we'll see in these different paradigms, but it can be a handy mnemonic
    |
    |---
    |
    |## Structure of the course
    |
    |I try to keep most of the content in Scala. Scala is a "multi-paradigm language", which means I can show you a lot of things in it.
    |
    |It's used by some well known places (e.g. Disney+, The Guardian, ITV+, LinkedIn) 
    |
    |* Imperative programming
    |  Scala syntax and also learn about how compilers change our code
    |
    |* Functional programming
    |  Basic functional programming, like you could do in Haskell, Scheme, Lisp, etc
    |
    |* Type systems
    |  Ways we can make compiler check our code more tightly, so it can catch more bugs
    |
    |* Effects
    |  Tracking when our code changes things and talks to the outside world
    |
    |* Reactive programming
    |  Everything from button clicks, to async, to processing fast streams of data
    |
    |* Rust
    |  Automatic memory management without garbage collection, but with a new set of compiler rules
    |
    |---
    |
    |## Assessments
    |
    |*Note*: 
    |
    |* What's on handbook.une.edu.au trumps everything
    |* What's on myLearn trumps what's in these slides
    |
    |i.e. this slide deck is an overview of the updates, not a definition of them.
    |
    |---
    |
    |## Assessment structure
    |
    |* Tour via handbook and myLearn
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
    |Assessment 3 typically includes an async library. 
    |
    |It's also taking the place of the exam & may include some of the features of the old "alternative assessment"
    |
    |
    |---
    |
    |## Final exam
    |
    |* In 2024, there is no final exam
    |
    |  - Although the *exam* would have fit in the compressed exam period, I'm not sure the alt assessment would have
    |  - The short timeframes of an exam are dissatisfying
    |
    |* Assessment 3 will need to get bigger
    |
    |  - The alternative assessment isn't proctored, so we have to do more to defend its integrity
    |  - video
    |  - data trails from some of your work
    |
    |---
    |
    |## Scala's tooling system
    |
    |Scala is a well designed programming language and the tools are getting simple to use... but under the hood, they do a lot
    |
    |scala (or scala-cli) can do much more than just compile your code:
    |
    |* Package management
    |* Unit testing
    |* Talking to your IDE
    |* Cross-compiling to JavaScript
    |
    |""".stripMargin
  )
  .renderSlides



  


