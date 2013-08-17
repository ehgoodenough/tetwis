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

##Feedback for Tetwis##
If you have anything to add to the project, you can always fork the repository for yourself, and if you have anything to discuss with me, you can always contact me at either andrewmcp333@gmail.com or psn719@mocs.utc.edu. Thanks!

[0]: http://web.archive.org/web/20051104034215/http://www.lupinegames.com/articles/path_to_dev.html
[1]: http://tetris.wikia.com/wiki/Tetris_Guideline
[2]: http://www.oracle.com/technetwork/java/javase/downloads/index.html