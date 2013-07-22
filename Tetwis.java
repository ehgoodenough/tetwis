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
		}
		
		public Dimension getPreferredSize()
		{
			return new Dimension(360, 480);
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