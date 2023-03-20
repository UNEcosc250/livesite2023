package cosc250.wordgame

type Guess = Seq[(Char, Feedback)]

type CharLocations = Seq[(Char, Int)]

enum Feedback:
    case RightPlace
    case WrongPlace
    case Absent

case class Game(target:String, guesses:Seq[Guess]):
    def turnsLeft = 6 - guesses.size
    def won = guesses.lastOption.contains(for c <- target yield (c, Feedback.RightPlace))
    def lost = turnsLeft <= 0 && !won
    def over = won || lost

def greenLetters(target:String, source:String):CharLocations = 
    for ((a, b), i) <- target.zip(source).zipWithIndex if a == b yield (a, i)

def orangeLetters(target:CharLocations, guess:CharLocations):CharLocations =
    val targetGroups = target.groupBy(_._1)
    val guessGroups = guess.groupBy(_._1)

    for 
        (char, pairs) <- guessGroups.toSeq if targetGroups.contains(char)
        (_, loc) <- pairs.take(targetGroups(char).size)
    yield (char, loc)

def compare(target:String, guess:String):Guess =
    val green = greenLetters(target, guess)
    val targetRemaining = target.zipWithIndex.filterNot(green.contains)
    val guessRemaining = guess.zipWithIndex.filterNot(green.contains)
    val orange = orangeLetters(targetRemaining, guessRemaining)
    val grey = targetRemaining.filterNot(orange.contains)

    for (c, l) <- guess.zipWithIndex yield
        if green.contains((c, l)) then (c, Feedback.RightPlace)
        else if orange.contains((c, l)) then (c, Feedback.WrongPlace)
        else (c, Feedback.Absent)


def maxColour(char:Char, guesses:Seq[Guess]):Option[Feedback] = 
    val feedbacks = for 
        guess <- guesses
        (c, feedback) <- guess if c == char
    yield feedback

    if feedbacks.contains(Feedback.RightPlace) then Some(Feedback.RightPlace)
    else if feedbacks.contains(Feedback.WrongPlace) then Some(Feedback.WrongPlace)
    else if feedbacks.contains(Feedback.Absent) then Some(Feedback.Absent)
    else None