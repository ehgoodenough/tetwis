import java.awt.Point;

public class Tetromino
{
	public Point position = new Point(4, 8);
	
	public boolean[][] tetribits;
	
	public Tetromino()
	{
		tetribits = new boolean[2][3];
		
		tetribits[0][0] = true;
		tetribits[0][1] = true;
		tetribits[1][1] = true;
		tetribits[1][2] = true;
	}
}