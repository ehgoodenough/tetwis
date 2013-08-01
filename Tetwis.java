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
		
		Tetromino tetromino = new Tetromino();
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
					if(tetratrix.canShiftLeft(tetromino)) {tetromino.shiftleft();}
					repaint();
				}
			});
			getActionMap().put("shiftright", new AbstractAction()
			{
				public void actionPerformed(ActionEvent event)
				{
					if(tetratrix.canShiftRight(tetromino)) {tetromino.shiftright();}
					repaint();
				}
			});
			getActionMap().put("softdrop", new AbstractAction()
			{
				public void actionPerformed(ActionEvent event)
				{
					if(tetratrix.canDrop(tetromino)) {tetromino.drop();}
					else {reset();} repaint(); gameloop.interrupt();
				}
			});
			getActionMap().put("harddrop", new AbstractAction()
			{
				public void actionPerformed(ActionEvent event)
				{
					while(tetratrix.canDrop(tetromino)) {tetromino.drop();}
					reset(); repaint(); gameloop.interrupt();
				}
			});
			getActionMap().put("rotate", new AbstractAction()
			{
				public void actionPerformed(ActionEvent event)
				{
					if(tetratrix.canRotate(tetromino)) {tetromino.rotate();}
					repaint(); gameloop.interrupt();
				}
			});
			
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
			
			for(int x = 0; x < tetromino.tetribits.length; x++)
			{
				for(int y = 0; y < tetromino.tetribits[x].length; y++)
				{
					if(tetromino.tetribits[x][y] != null)
					{
						Shape square = new Rectangle(x*28+57+2+tetromino.position.x*28, y*28+tetromino.position.y*28, 28-1-2, 28-1-2);
						GFX2D.setColor(tetromino.tetribits[x][y].color);
						GFX2D.fill(square); GFX2D.draw(square);
					}
				}
			}
			
			GFX2D.setColor(Color.BLACK);
			GFX2D.setFont(new Font("Lucida Console", Font.PLAIN, 50));
			GFX2D.drawString(Integer.toString(gamescore), 56, 504+47);
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
				
				if(tetratrix.canDrop(tetromino))
				{
					tetromino.drop();
				}
				else
				{
					reset();
				}
				
				repaint();
			}
		}
		
		public void reset()
		{
			if(tetratrix.canEmbed(tetromino))
			{
				tetratrix.embed(tetromino);
				
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
				
				gamescore += comboscore;
				
				tetromino = new Tetromino();
			}
			else
			{
				setVisible(false);
				System.exit(0);
			}
		}
		
		public void sleep()
		{
			try {Thread.sleep(1000);}
			catch(InterruptedException err)
			{sleep();}
		}
	}
}