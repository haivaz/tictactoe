Tic-Tac-Toe vs CPU

A console-based 5×5 Tic-Tac-Toe game written in Java as a project for my **CS142 class**.

## Description

This project is an extended version of the classic Tic-Tac-Toe game. Instead of a standard 3×3 board, the game uses a **5×5 board with 25 playable positions**.

The player competes against the computer (CPU). The player uses `X`, while the CPU uses `O`.

The player selects a position from **1 to 25**, and the CPU automatically chooses its move.

## Features

- 5×5 Tic-Tac-Toe board
- 25 playable positions
- Player vs CPU gameplay
- Player uses `X`
- CPU uses `O`
- CPU attempts to complete its own winning combination
- CPU attempts to block the player's winning combination
- Random CPU moves when there is no immediate winning or blocking move
- Win detection
- Tie detection
- Surrender option
- Console-based game board
- Special "SIUUUUUU" victory animation
- Sound effect using `Toolkit.beep()`

## How the Game Works

The board contains 25 positions numbered from 1 to 25.

```text
 1 | 2 | 3 | 4 | 5
---+---+---+---+---
 6 | 7 | 8 | 9 | 10
---+---+---+---+---
11 |12 |13 |14 |15
---+---+---+---+---
16 |17 |18 |19 |20
---+---+---+---+---
21 |22 |23 |24 |25

The player enters a number between 1 and 25 to place an X.

For example:

Enter your number to play form (1 - 25) or enter 0 to surrender:
13

The CPU then selects its position and places an O.

CPU Logic

The computer uses a simple strategy instead of choosing completely random moves.

First, the CPU checks all possible winning combinations. If it already has four positions in a winning line and one position is empty, it chooses the empty position to win.

If the CPU cannot win immediately, it checks whether the player has four positions in a winning line with one empty position. If so, the CPU takes that position to block the player.

If neither situation applies, the CPU chooses a random available position.

Winning Combinations

The game checks:

5 horizontal rows
5 vertical columns
2 diagonal lines

This gives the game 12 possible winning combinations.

Rows:
1  2  3  4  5
6  7  8  9  10
11 12 13 14 15
16 17 18 19 20
21 22 23 24 25

Columns:
1  6  11 16 21
2  7  12 17 22
3  8  13 18 23
4  9  14 19 24
5  10 15 20 25

Diagonals:
1  7  13 19 25
5  9  13 17 21

The winning combinations are stored using Java List and ArrayList.

Victory Animation

When the player wins, the game displays a special "SIUUUUUU" animation.

The animation:

Clears the console
Displays ASCII art
Plays a beep sound
Uses delays to create an animation effect
Displays a congratulations message

The animation is repeated several times using a for loop and Thread.sleep().

Surrender

The player can enter:

0

to surrender and end the game.

Technologies
Java
2D Arrays
ArrayList
List
Scanner
Random
Arrays.asList()
switch statements
Loops
Conditional statements
Methods
Console input/output
Toolkit
Thread.sleep()
Main Methods
main()

Controls the main game loop, receives player input, places pieces, calls the CPU logic, and checks for a winner.

getSmartCpuMove()

Determines the CPU's next move. The CPU first looks for an opportunity to win, then tries to block the player, and finally chooses a random available position.

printGameBoard()

Prints the current 5×5 game board to the console.

isPositionTaken()

Checks whether a selected position is already occupied.

placePiece()

Places the player's X or CPU's O on the selected position.

checkWinner()

Checks all winning combinations and determines whether the player won, the CPU won, or the game ended in a tie.

Learning Objectives

This project was created for CS142 to practice Java programming concepts, including:

2D arrays
ArrayList
List
Loops
if/else statements
switch statements
Methods
Random number generation
User input with Scanner
Boolean logic
Game state management
Basic algorithm design
Working with collections
Exception handling with try/catch
How to Run
Clone the repository:
git clone https://github.com/your-username/your-repository-name.git
Open the project in Eclipse, IntelliJ IDEA, or another Java IDE.
Run:
TicTacToe.java
Follow the instructions displayed in the console.
Example
Enter your number to play form (1 - 25 ) or enter 0 to surrender:
13

CPU chose position: 7

        X
...

The game continues until:

The player wins
The CPU wins
All 25 positions are occupied
The player surrenders
