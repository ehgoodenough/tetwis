import java.io.*;
import java.util.*;
import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

public class Tetwis
{
	public static void main(String[] args)
	{
		new JFrame()
		{
			{
				setTitle("Tetwis");
				setSize(320, 320);
				setResizable(false);
				
				add(new TetwisJComponent()); pack();
				
				setLocationRelativeTo(null);
				setDefaultCloseOperation(EXIT_ON_CLOSE);
				setVisible(true);
			}
		};
	}
	
	public static class TetwisJComponent
	extends JComponent implements Runnable
	{
		Thread gameloop;
		
		int gamescore = 0;
		int loopdelay = 1000;
		
		boolean delayAfterHarddrop = false;
		
		Tetromino heldTetromino = null;
		Tetromino nextTetromino = new Tetromino();
		Tetromino activeTetromino = new Tetromino();
		Tetromino ghostTetromino = new Tetromino(activeTetromino);
		
		Tetratrix tetratrix = new Tetratrix();
		
		Color brightColor = new Color(229, 229, 229);
		Color darkColor = new Color(25, 25, 25);
		
		private final int GUTTER_FOR_TETRIBIT = 3;
		private final int SIZE_OF_TETRIBIT = 28;
		private final int WIDTH_OF_SIDEFRAME = 56;
		private final int HEIGHT_OF_SIDEFRAME = 504;
		private final int WIDTH_OF_TETRATRIX = SIZE_OF_TETRIBIT * tetratrix.WIDTH_IN_TETRIBITS + GUTTER_FOR_TETRIBIT;
		private final int HEIGHT_OF_TETRATRIX = SIZE_OF_TETRIBIT * tetratrix.HEIGHT_IN_TETRIBITS;
		private final int WIDTH_OF_JCOMPONENT = WIDTH_OF_SIDEFRAME + WIDTH_OF_TETRATRIX + WIDTH_OF_SIDEFRAME;
		private final int HEIGHT_OF_JCOMPONENT = HEIGHT_OF_TETRATRIX + WIDTH_OF_SIDEFRAME;
		
		public TetwisJComponent()
		{
			
			getInputMap(WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("LEFT"), "shiftleft");
			getInputMap(WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("RIGHT"), "shiftright");
			getInputMap(WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("DOWN"), "softdrop");
			getInputMap(WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("SPACE"), "harddrop");
			getInputMap(WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("UP"), "rotate");
			getInputMap(WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("Z"), "hold");
			getActionMap().put("shiftleft", new AbstractAction()
			{
				public void actionPerformed(ActionEvent event)
				{
					if(tetratrix.canShiftLeft(activeTetromino))
					{
						activeTetromino.shiftleft();
						
						reghost();
					}
					
					repaint();
				}
			});
			getActionMap().put("shiftright", new AbstractAction()
			{
				public void actionPerformed(ActionEvent event)
				{
					if(tetratrix.canShiftRight(activeTetromino))
					{
						activeTetromino.shiftright();
						
						reghost();
					}
					
					repaint();
				}
			});
			getActionMap().put("softdrop", new AbstractAction()
			{
				public void actionPerformed(ActionEvent event)
				{
					if(tetratrix.canDrop(activeTetromino))
					{
						activeTetromino.drop();
					}
					else
					{
						reset();
					}
					
					repaint();
					resleep();
				}
			});
			getActionMap().put("harddrop", new AbstractAction()
			{
				public void actionPerformed(ActionEvent event)
				{
					while(tetratrix.canDrop(activeTetromino))
					{
						activeTetromino.drop();
					}
					
					if(!delayAfterHarddrop)
					{
						reset();
					}
					
					repaint();
					resleep();
				}
			});
			getActionMap().put("rotate", new AbstractAction()
			{
				public void actionPerformed(ActionEvent event)
				{
					if(tetratrix.canRotate(activeTetromino))
					{
						activeTetromino.rotate();
						
						ghostTetromino.rotate();
						reghost();
					}
					
					repaint();
					resleep();
				}
			});
			getActionMap().put("hold", new AbstractAction()
			{
				public void actionPerformed(ActionEvent event)
				{
					if(heldTetromino != null)
					{
						Tetromino swapTetromino;
						swapTetromino = heldTetromino;
						heldTetromino = activeTetromino;
						activeTetromino = swapTetromino;
					}
					else
					{
						heldTetromino = activeTetromino;
						activeTetromino = nextTetromino;
						nextTetromino = new Tetromino();
					}
					
					heldTetromino.position.x = heldTetromino.origin.x;
					heldTetromino.position.y = heldTetromino.origin.y;
					
					ghostTetromino = new Tetromino(activeTetromino);
					
					reghost();
					repaint();
					resleep();
				}
			});
			
			reghost();
			
			(gameloop = new Thread(this)).start();
		}
		
		public void paintComponent(Graphics GFX)
		{
			Graphics2D GFX2D = (Graphics2D)GFX;
			
			Shape background = new Rectangle(getPreferredSize());
			GFX2D.setColor(brightColor);
			GFX2D.fill(background); GFX2D.draw(background);
			
			Shape leftframe = new Rectangle(0, 0, WIDTH_OF_SIDEFRAME, HEIGHT_OF_SIDEFRAME);
			Shape rightframe = new Rectangle(WIDTH_OF_SIDEFRAME + WIDTH_OF_TETRATRIX, 0, WIDTH_OF_SIDEFRAME, HEIGHT_OF_SIDEFRAME);
			Shape bottomframe = new Rectangle(0, HEIGHT_OF_SIDEFRAME, WIDTH_OF_JCOMPONENT, WIDTH_OF_SIDEFRAME);
			GFX2D.setColor(darkColor);
			GFX2D.fill(leftframe); GFX2D.draw(leftframe);
			GFX2D.fill(rightframe); GFX2D.draw(rightframe);
			GFX2D.fill(bottomframe); GFX2D.draw(bottomframe);
			
			for(int x = 0; x < tetratrix.WIDTH_IN_TETRIBITS; x++)
			{
				for(int y = 0; y < tetratrix.HEIGHT_IN_TETRIBITS; y++)
				{
					if(tetratrix.tetribits[x][y] != null)
					{
						int xpos = WIDTH_OF_SIDEFRAME + (x * SIZE_OF_TETRIBIT) + GUTTER_FOR_TETRIBIT;
						int ypos = y * SIZE_OF_TETRIBIT;
						int size = SIZE_OF_TETRIBIT - GUTTER_FOR_TETRIBIT;
						Shape square = new Rectangle(xpos, ypos, size, size);
						GFX2D.setColor(tetratrix.tetribits[x][y].color);
						GFX2D.fill(square); GFX2D.draw(square);
					}
				}
			}
			
			for(int x = 0; x < ghostTetromino.tetribits.length; x++)
			{
				for(int y = 0; y < ghostTetromino.tetribits[x].length; y++)
				{
					if(ghostTetromino.tetribits[x][y] != null)
					{
						int xpos = WIDTH_OF_SIDEFRAME + ((x + ghostTetromino.position.x) * SIZE_OF_TETRIBIT) + GUTTER_FOR_TETRIBIT;
						int ypos = (y + ghostTetromino.position.y) * SIZE_OF_TETRIBIT;
						int size = SIZE_OF_TETRIBIT - GUTTER_FOR_TETRIBIT;
						Shape square = new Rectangle(xpos, ypos, size, size);
						GFX2D.setColor(Color.BLACK);
						GFX2D.fill(square); GFX2D.draw(square);
					}
				}
			}
			
			for(int x = 0; x < activeTetromino.tetribits.length; x++)
			{
				for(int y = 0; y < activeTetromino.tetribits[x].length; y++)
				{
					if(activeTetromino.tetribits[x][y] != null)
					{
						int xpos = WIDTH_OF_SIDEFRAME + ((x + activeTetromino.position.x) * SIZE_OF_TETRIBIT) + GUTTER_FOR_TETRIBIT;
						int ypos = (y + activeTetromino.position.y) * SIZE_OF_TETRIBIT;
						int size = SIZE_OF_TETRIBIT - GUTTER_FOR_TETRIBIT;
						Shape square = new Rectangle(xpos, ypos, size, size);
						GFX2D.setColor(activeTetromino.tetribits[x][y].color);
						GFX2D.fill(square); GFX2D.draw(square);
					}
				}
			}
			
			GFX2D.setColor(brightColor);
			GFX2D.setFont(new Font("Lucida Console", Font.PLAIN, 50));
			GFX2D.drawString(Integer.toString(gamescore), WIDTH_OF_SIDEFRAME, HEIGHT_OF_SIDEFRAME + 47);
			GFX2D.setFont(new Font("Lucida Console", Font.BOLD, 24));
			GFX2D.drawString("NEXT:" + nextTetromino.tetraglyph, WIDTH_OF_SIDEFRAME + 193, HEIGHT_OF_SIDEFRAME + 47 - 22);
			if(heldTetromino != null) {GFX2D.drawString("HELD:" + heldTetromino.tetraglyph, WIDTH_OF_SIDEFRAME + 193, HEIGHT_OF_SIDEFRAME + 47 + 1);}
		}
		
		public Dimension getPreferredSize()
		{
			return new Dimension(WIDTH_OF_JCOMPONENT, HEIGHT_OF_JCOMPONENT);
		}
		
		public void run()
		{
			while(true)
			{
				sleep();
				
				if(tetratrix.canDrop(activeTetromino))
				{
					activeTetromino.drop();
				}
				else
				{
					reset();
				}
				
				repaint();
			}
		}
		
		public void resleep()
		{
			gameloop.interrupt();
		}
		
		public void reghost()
		{
			ghostTetromino.position.x = activeTetromino.position.x;
			ghostTetromino.position.y = activeTetromino.position.y;
			
			while(tetratrix.canDrop(ghostTetromino))
			{
				ghostTetromino.drop();
			}
		}
		
		public void reset()
		{
			if(tetratrix.canEmbed(activeTetromino))
			{
				tetratrix.embed(activeTetromino);
				
				int comboscore = 0;
				
				for(int y = 0; y < tetratrix.HEIGHT_IN_TETRIBITS; y++)
				{
					if(tetratrix.hasTetrow(y))
					{
						if(comboscore == 0)
						{
							comboscore++;
						}
						else
						{
							comboscore *= 2;
						}
						
						tetratrix.deleteTetrow(y);
					}
				}
				
				if(Math.floor(gamescore/10)*10 < Math.floor((gamescore+comboscore)/10)*10)
				{
					loopdelay -= 50; if(loopdelay < 100) {loopdelay = 100;}
				}
				
				gamescore += comboscore;
				
				activeTetromino = nextTetromino;
				ghostTetromino = new Tetromino(activeTetromino);
				nextTetromino = new Tetromino();
				
				reghost();
			}
			else
			{
				try
				{
					File highscoresFile = new File("highscores.txt");
					if(!highscoresFile.exists()) {highscoresFile.createNewFile();}
					BufferedReader highscoresReader = new BufferedReader(new FileReader(highscoresFile));
					Scanner highscoresScanner = new Scanner(highscoresReader);
					
					ArrayList<Integer> highscores = new ArrayList<Integer>();
					boolean haveRecordedYourGamescore = false;
					
					while(highscoresScanner.hasNextLine())
					{
						int highscore = Integer.parseInt(highscoresScanner.nextLine());
						
						if(!haveRecordedYourGamescore)
						{
							if(gamescore > highscore)
							{
								highscores.add(gamescore);
								haveRecordedYourGamescore = true;
							}
						}
						
						highscores.add(highscore);
					}
					
					if(!haveRecordedYourGamescore)
					{
						highscores.add(gamescore);
					}
					
					highscoresScanner.close();
					highscoresReader.close();
					
					BufferedWriter highscoresWriter = new BufferedWriter(new FileWriter(highscoresFile));
					
					for(int i = 0; i < Math.min(highscores.size(), 10); i++)
					{
						highscoresWriter.write(highscores.get(i).toString());
						if(i < Math.min(highscores.size(), 10) - 1) {highscoresWriter.write("\n");}
					}
					
					highscoresWriter.close();
				}
				catch(FileNotFoundException exception) {System.out.println("Unable to open the file for highscores.");}
				catch(IOException exception) {System.out.println("Encountered an error while reading the highscores.");}
				
				setVisible(false);
				System.exit(0);
			}
		}
		
		public void sleep()
		{
			try {Thread.sleep(loopdelay);}
			catch(InterruptedException err)
			{sleep();}
		}
	}
}