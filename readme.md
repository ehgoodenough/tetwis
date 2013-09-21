#Tetwis in Java#
Tetwis is a badly disguised clone of Alexey Pajitnov's Tetris, and is designed and developed entirely in Java. I was inspired to put it all together after reading through [this article by Geoff Howland][0], which challenges programmers to begin their development of videogames by remaking something simple, such as Tetris.

##Compiling Tetwis##
The process of compilation doesn't require anything beyond the standard libaries provided in development kit, which can[ always be redownload over on their website][2]. It is easy to compile everything together from command line.

`javac *.java; java Tetwis;`

##Features of Tetwis##
The project attempts to adhere to the [specifications defined in the official guidelines][1], but has ignored certain features, such as scoring complex manuevers or including any additional gamemodes.

  - Playing with multiple players.
  - Maintaining a list of all highscores.
  - Resizing the tetratrix to any dimension.
  - Increasing the difficulty as the game continues.
  - Reconfiguring the gameplay.
    - key bindings
    - color schemes
    - random seed
    - easy spins
  - Softdropping and harddroping a tetromino.
  - Wallkicking a tetromino after rotating.
  - Previewing the next tetromino.
  - Holding another tetromino.
  - Ghosting a tetromino.

##Structure of Tetwis##
The code is structured around the object oriented paradigm, with each intrinsic component individually encapsulated into a class. This includes the Tetribits, Tetrominos, and Tetratrix.
###Tetribits###
The tetribit is the unit of bit that composes everything in the game. It is nothing more than a square that is repositioned and recolored in both the tetratrix and tetrominos.
###Tetrominos###
The tetromino is an orthogonal compilation of four tetribits. There are only seven different shapes of tetrominos, which can each be labelled with a letter. This includes I, L, J, O, T, S and Z. A tetromino can be dropped down, rotated around, as well as shifted left and right.
###Tetratrix###
The tetratrix is a matrix of tetribits. It is sometimes known as the "well" or "field" in other versions of tetris. The tetratrix is responsible for handling the collision of tetrominos and the embedding of tetribits. When a tetromino has dropped to the bottom of the tetratrix, the tetribits that compose the tetromino are embedded into the tetratrix. If a row of tetratrix is completed, it is removed and refactored, which increases the score.

##To Do of Tetwis##
  - Instantiate the tetrominos a bit lower in the tetratrix.
  - Initiate the game as either monoplayer or multiplayer.
  - Highlight the game when a player reaches a highscore.
  - Construct an system for generating the next tetromino.
  - Recognize players who have achieved a highscore.
  - Read the keybindings from an external reconfigurable file.
  - Prompt the players to provide their name for their highscore.
  - Render graphics for the held tetromino and next tetromino.
  - Comment your code more thoroughly.
  - Stop the players from performing consecutive holds of tetrominos.
  - Replace the tetromino.tetribi\[0\].length with getTetrominoHeight().
  - Begin with a titlescreen for navigating around the configurations of the game.
  - Play a sound when a tetromino is dropped or when the tetratrix is completed.
  - Remove any and all magic numbers in generating the swing compoents.
  - Confirm the effectiveness of the increasing difficulty across the game.
  - What is the best approach towards anonymous functions as keystrokes?
  - Should ghosts be capable of referencing the data of the tetromino?
  - Are the try catches necessary when calculating the collision?
  - Should the tetribits be maintained by index or point?

##Feedback for Tetwis##
If you have anything to add to the project, you can always fork the repository for yourself, and if you have anything to discuss with me, you can always contact me at either andrewmcp333@gmail.com or psn719@mocs.utc.edu. Thanks!

[0]: http://web.archive.org/web/20051104034215/http://www.lupinegames.com/articles/path_to_dev.html
[1]: http://tetris.wikia.com/wiki/Tetris_Guideline
[2]: http://www.oracle.com/technetwork/java/javase/downloads/index.html