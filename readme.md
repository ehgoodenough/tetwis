# Tetwis #

![Tetwis](http://i.imgur.com/lHgYe9R.gif)

Tetwis is a badly disguised clone of Alexey Pajitnov's Tetris, and is designed and developed
entirely in Java. This [awesome article by Geoff Howland][0] challenges you to start by making
something simple, such as Tetris.

## Instructions ##

Tetwis doesn't require anything beyond the standard libaries provided in the JDK, which
you can [always be redownload][2]. It isn't hard to compile everything together from the terminal.

```
    javac *.java
    java Tetwis
```

## Features ##

Tetwis attempts to adhere to the [specifications defined in the official guidelines][1], but
has ignored certain features, such as scoring complex manuevers or including additional game modes.

 - Playing with multiple players.
 - Maintaining a list of all highscores.
 - Resizing the tetratrix to any dimension.
 - Increasing the difficulty as the game continues.
 - Reconfiguring the gameplay.
   - Key bindings
   - Color schemes
   - Random seed
   - Easy spins
 - Softdropping and harddroping a tetromino.
 - Wallkicking a tetromino after rotating.
 - Previewing the next tetromino.
 - Holding another tetromino.
 - Ghosting a tetromino.

## Architecture ##

Tetwis is comprised of tetribits, tetrominos, and tetratrix.

### Tetribits ###

The tetribit is the atomic unit. It is nothing more than a square that is assigned a positioned and
color. Everything in the game is composed of tetribits, including both the tetratrix and tetrominos.

### Tetrominos ###

A tetromino is an orthogonal assortment of four tetribits. There are only seven different shapes of
tetrominos, which can each be labelled with a letter, including I, L, J, O, T, S and Z. A tetromino
can be dropped down, rotated around, as well as shifted to the left and right.

### Tetratrix ###

The tetratrix is a multi-dimensional array of tetribits. It is sometimes known as the "well" or "field."
The tetratrix is responsible for handling the collision of tetrominos and tetribits. When a tetromino
has dropped to the bottom of the tetratrix, the tetribits that are in the tetromino are embedded into
the tetratrix. If a row of the tetratrix is completed, it is removed, and the score is increased.

## To Do ##
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
 - Replace the tetromino.tetribits.length with getTetrominoHeight().
 - Begin with a titlescreen for navigating around the configurations of the game.
 - Play a sound when a tetromino is dropped or when the tetratrix is completed.
 - Remove any and all magic numbers in generating the swing compoents.
 - Confirm the effectiveness of the increasing difficulty across the game.
 - What is the best approach towards anonymous functions as keystrokes?
 - Should ghosts be capable of referencing the data of the tetromino?
 - Are the try catches necessary when calculating the collision?
 - Should the tetribits be maintained by index or point?

[0]: http://web.archive.org/web/20051104034215/http://www.lupinegames.com/articles/path_to_dev.html
[1]: http://tetris.wikia.com/wiki/Tetris_Guideline
[2]: http://www.oracle.com/technetwork/java/javase/downloads/index.html
