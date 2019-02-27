# CSC 335: Reversi/Othello

Note that this is an empty repository. Please create a new Eclipse project in the repository and use that to build and test your program.

## Description

Reversi (often called by the trademarked name Othello) is two-player game played on an 8x8 grid. In the physical game, there are tokens with a different color on each side. The color white represents the first player and the color black represents the second. The board starts out with four pieces, 2 from each player in the center of the board like so:

| 1 |   |   |   |   |   |   |   |   |
| --- | --- | --- | --- | --- | --- | --- | --- | --- |
| 2 |   |   |   |   |   |   |   |   |
| 3 |   |   |   |   |   |   |   |   |
| 4 |   |   |   | W | B |   |   |   |
| 5 |   |   |   | B | W |   |   |   |
| 6 |   |   |   |   |   |   |   |   |
| 7 |   |   |   |   |   |   |   |   |
| 8 |   |   |   |   |   |   |   |   |
|   | a | b | c | d | e | f | g | h |

Each player takes turns placing a token with their color face up onto the board and has the requirement that their token be placed so that it forms at least one straight (horizontal, vertical, or diagonal) occupied line between the new piece and another of their colored pieces, with one or more contiguous opposite-color pieces in between. From the above starting configuration, the legal moves for white are indicated with an asterisk below:

| 1 |   |   |   |   |   |   |   |   |
| --- | --- | --- | --- | --- | --- | --- | --- | --- |
| 2 |   |   |   |   |   |   |   |   |
| 3 |   |   |   |   | \* |   |   |   |
| 4 |   |   |   | W | B | \* |   |   |
| 5 |   |   | \* | B | W |   |   |   |
| 6 |   |   |   | \* |   |   |   |   |
| 7 |   |   |   |   |   |   |   |   |
| 8 |   |   |   |   |   |   |   |   |
|   | a | b | c | d | e | f | g | h |

For example, if the white player places a token at e3, the new white token is in line vertically with another white piece at e5 white a black piece in between (at e4). The move d3 would not be legal, because there are no black pieces between d3 and d4, and there is no white piece at f5.

When a legal move is played, all opposite-colored pieces along the horizontal, vertical, and diagonal lines that end with the closest same-colored piece are &quot;captured&quot; and flipped over (in the physical game) to be your color. So, for our move e3, the board would then become:

| 1 |   |   |   |   |   |   |   |   |
| --- | --- | --- | --- | --- | --- | --- | --- | --- |
| 2 |   |   |   |   |   |   |   |   |
| 3 |   |   |   |   | W |   |   |   |
| 4 |   |   |   | W | W |   |   |   |
| 5 |   |   |   | B | W |   |   |   |
| 6 |   |   |   |   |   |   |   |   |
| 7 |   |   |   |   |   |   |   |   |
| 8 |   |   |   |   |   |   |   |   |
|   | a | b | c | d | e | f | g | h |

The current score is the number of pieces of each color. After this move, white has a score of 4 and black has a score of 1. It is now black&#39;s turn, and their legal moves are:

| 1 |   |   |   |   |   |   |   |   |
| --- | --- | --- | --- | --- | --- | --- | --- | --- |
| 2 |   |   |   |   |   |   |   |   |
| 3 |   |   |   | \* | W | \* |   |   |
| 4 |   |   |   | W | W |   |   |   |
| 5 |   |   |   | B | W | \* |   |   |
| 6 |   |   |   |   |   |   |   |   |
| 7 |   |   |   |   |   |   |   |   |
| 8 |   |   |   |   |   |   |   |   |
|   | a | b | c | d | e | f | g | h |

The game continues until the board is full or there are no legal moves for either player. If there is no legal move for one player, the other player plays consecutive turns, until the game completes.

The winner is determined by which player has more tokens at the end of the game. A tie is possible.

For more information on the game, you can see https://en.wikipedia.org/wiki/Reversi

# _Implementation_

Your job for this project is to implement the game of Reversi using all of the tools we have learned so far. That means you should follow the MVC design, use UML to plan and document your class structure, provide full source code documentation using javadoc, a complete test suite that tests your controller to 100% branch coverage, and, of course, works.

Rather than using white and black colors, we will represent the human player as a W, the computer player as an B, and a blank as an underscore ( \_ ).

The board will be 8 rows high by 8 columns wide.

The computer will be a weak player, whose choice of move is to pick from the legal moves the one that maximizes their score (considering only the current move). If there is more than one such move, simply select one of the best moves randomly.

A run of the program will look like:
```
Welcome to Reversi

You are W.

1 _ _ _ _ _ _ _ _
2 _ _ _ _ _ _ _ _
3 _ _ _ _ _ _ _ _
4 _ _ _ W B _ _ _
5 _ _ _ B W _ _ _
6 _ _ _ _ _ _ _ _
7 _ _ _ _ _ _ _ _
8 _ _ _ _ _ _ _ _
  a b c d e f g h

The score is 2-2.
Where would you like to place your token? c5

1 _ _ _ _ _ _ _ _
2 _ _ _ _ _ _ _ _
3 _ _ _ _ _ _ _ _
4 _ _ _ W B _ _ _
5 _ _ W W W _ _ _
6 _ _ _ _ _ _ _ _
7 _ _ _ _ _ _ _ _
8 _ _ _ _ _ _ _ _
  a b c d e f g h

The score is 4-1.
The computer places a piece at c6.

1 _ _ _ _ _ _ _ _
2 _ _ _ _ _ _ _ _
3 _ _ _ _ _ _ _ _
4 _ _ _ W B _ _ _
5 _ _ W B W _ _ _
6 _ _ B _ _ _ _ _
7 _ _ _ _ _ _ _ _
8 _ _ _ _ _ _ _ _
  a b c d e f g h

The score is 3-3.
Where would you like to place your token?

…

You win!
```
## Requirements

As part of your submission, you must provide:

- At least four classes, Reversi, ReversiView, ReversiController, and ReversiModel. The Reversi class will have main, separating it from the view this time. You may have as many additional classes as you need.
- Complete javadoc for every class and method, using the @author, @param, @return, and @throws javadoc tags. Generate your documentation into a doc folder.
- A complete UML diagram for your design, drawn using [http://draw.io](http://draw.io) and the xml file committed as part of your repository
- Test cases for your model and controller with 100% branch coverage

Your code must follow the MVC architecture as we have described it in class. That means:

- No input or output code except in the View
- A model that represents the state of the game but guards access through public methods
- A controller that allows the view to interact indirectly with the model, providing the abstracted operations of your game
  - Including a humanTurn(int row, int col) method and a computerTurn() method that represent the turns
  - A set of methods that determine the end of game and the winner
  - Some way to access the board (not just a toString() or similar on the model) to be able to display it as part of the view
  - As few public methods as possible, with helper methods being private and all non-final fields being private
- Your UML diagram should be used to plan out the program and will be a Section Lab grade taken as part of your final submission

## Submission

Make sure to periodically commit and push your changes to GitHub.

We will grade the last commit that was pushed prior to the deadline.

