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
		
		Tetromino nextTetromino = new Tetromino();
		Tetromino activeTetromino = new Tetromino();
		Tetromino ghostTetromino = new Tetromino(activeTetromino);
		Tetratrix tetratrix = new Tetratrix();
		
		public TetwisJComponent()
		{
			getInputMap(WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("LEFT"), "shiftleft");
			getInputMap(WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("RIGHT"), "shiftright");
			getInputMap(WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("DOWN"), "softdrop");
			getInputMap(WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("SPACE"), "harddrop");
			getInputMap(WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("UP"), "rotate");
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
			
			reghost();
			
			(gameloop = new Thread(this)).start();
		}
		
		public void paintComponent(Graphics GFX)
		{
			Graphics2D GFX2D = (Graphics2D)GFX;
			
			Shape background = new Rectangle(getPreferredSize());
			GFX2D.setColor(Color.BLUE); GFX2D.fill(background);
			GFX2D.draw(background);
			
			Shape leftframe = new Rectangle(0, 0, 56, 504);
			Shape rightframe = new Rectangle(339, 0, 56, 504);
			Shape bottomframe = new Rectangle(0, 504, 393, 56);
			GFX2D.setColor(Color.RED);
			GFX2D.fill(leftframe); GFX2D.draw(leftframe);
			GFX2D.fill(rightframe); GFX2D.draw(rightframe);
			GFX2D.fill(bottomframe); GFX2D.draw(bottomframe);
			
			for(int x = 0; x < tetratrix.WIDTH_IN_TETRIBITS; x++)
			{
				for(int y = 0; y < tetratrix.HEIGHT_IN_TETRIBITS; y++)
				{
					if(tetratrix.tetribits[x][y] != null)
					{
						Shape square = new Rectangle(x*28+57+2, y*28, 28-1-2, 28-1-2);
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
						Shape square = new Rectangle(x*28+57+2+ghostTetromino.position.x*28, y*28+ghostTetromino.position.y*28, 28-1-2, 28-1-2);
						GFX2D.setColor(Color.MAGENTA);
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
						Shape square = new Rectangle(x*28+57+2+activeTetromino.position.x*28, y*28+activeTetromino.position.y*28, 28-1-2, 28-1-2);
						GFX2D.setColor(activeTetromino.tetribits[x][y].color);
						GFX2D.fill(square); GFX2D.draw(square);
					}
				}
			}
			
			GFX2D.setColor(Color.BLACK);
			GFX2D.setFont(new Font("Lucida Console", Font.PLAIN, 50));
			GFX2D.drawString(Integer.toString(gamescore), 56, 504+47);
			GFX2D.setFont(new Font("Lucida Console", Font.BOLD, 24));
			GFX2D.drawString("NEXT:" + nextTetromino.tetraglyph, 56 + 200-7, 504+47-22);
			if(heldTetromino != null) {GFX2D.drawString("HELD:" + heldTetromino.tetraglyph, 56 + 200-7, 504+47+1);}
		}
		
		public Dimension getPreferredSize()
		{
			return new Dimension(395, 561);
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