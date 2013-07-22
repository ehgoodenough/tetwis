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
		public TetwisJComponent()
		{
			(new Thread(this)).start();
		}
		
		public void paintComponent(Graphics GFX)
		{
			Graphics2D GFX2D = (Graphics2D)GFX;
			
			Shape background = new Rectangle(getPreferredSize());
			GFX2D.setColor(Color.BLUE); GFX2D.fill(background);
			GFX2D.draw(background);
			
			Shape leftframe = new Rectangle(0, 0, 56, 504);
			Shape rightframe = new Rectangle(336, 0, 56, 504);
			Shape bottomframe = new Rectangle(0, 505, 392, 561);
			GFX2D.setColor(Color.RED);
			GFX2D.fill(leftframe); GFX2D.draw(leftframe);
			GFX2D.fill(rightframe); GFX2D.draw(rightframe);
			GFX2D.fill(bottomframe); GFX2D.draw(bottomframe);
		}
		
		public Dimension getPreferredSize()
		{
			return new Dimension(392, 561);
		}
		
		public void run()
		{
			while(true)
			{
				repaint();
				
				try {Thread.sleep(1000);}
				catch(InterruptedException err)
				{System.out.println(err.toString());}
			}
		}
	}
}