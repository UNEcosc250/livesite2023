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
      |*Monday 23 Feb 2026*
      |
      |""".stripMargin
  ).withClass("center middle")
  .markdownSlides(
    """
    |## Agenda
    |
    |0. This unit and AI...
    |1. Where the content is...
    |2. Where the assessments are...
    |3. The first problem you'll face...
    |
    |---
    |
    |## COSC250 and AI
    |
    |* AI can generate code really fast, in lots of languages. Not just by you...
    |
    |* The more and faster AI can generate code, the more code you have to deal with in the same amount of time...
    |
    |I have a hunch that the shift towards AI will create a move towards languages with tighter compilers
    |
    |* LLMs can be sloppy; they're text-based and general purpose and generate code linearly token-by-token
    |
    |* Pairing that with a precise compiler that can catch more of its errors sounds (to me) like a good idea
    |
    |In which case -
    |
    |* You're likely to have to work with code created by AI in many languages in your career
    |
    |* Expressive languages that can describe code with good structure and a strong type system to catch errors will probably be a good thing
    |
    |* Static analysis, tracking of memory safety, and other ways of inspecting the quality of code are going to be important too. 
    |
    |---
    |
    |## So...
    |
    |* Gradually, COSC250's going to move to getting you to know what's going on in code quickly, rather than expecting it all to be hand-written
    |
    |* A1, A2, and A3 are all "Learning Tasks". You are permitted to use AI, but I very much recommend
    |
    |  - have a go at it manually *first* because you need to get your brain to grapple with the task for you to understand it
    |  - but feel free to ask an LLM to explain what's going wrong or how to improve the code. Give it the error messages and the differences in output too.
    |  - Cite the LLM where you've used its code. Some of it'll be conversational, in which case save the conversation out as a text/markdown file and include it in the zip (referencing it from comments in your code)
    |
    |---
    |
    |## There is an exam ...
    |
    |  - There's a 50% exam that you'll need to pass.
    |  - Given you will probably be used to coding with an LLM, I'm not going to make you write long stretches of code without one (but might ask you to write a few fragments up to about 5 lines long)
    |  - I will ask about concepts
    |  - I will give you AI generated code and ask you about it
    |  - Some questions from the quizzes may be repeated on the exam
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
    |
    |""".stripMargin)
  .imageSlide("Codex knew it was my imperative week", "images/codex-autocomplete-tutorial1.png")
  .markdownSlides("""
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



  


