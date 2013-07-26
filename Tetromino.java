import java.awt.Point;

public class Tetromino
{
	public Point position;
	public Tetribit[][] tetribits;
	
	public Tetromino()
	{
		position = new Point(4, -3);
		tetribits = new Tetribit[3][3];
		
		tetribits[0][0] = new Tetribit(0, 255, 0);
		tetribits[0][1] = new Tetribit(0, 255, 0);
		tetribits[1][1] = new Tetribit(0, 255, 0);
		tetribits[1][2] = new Tetribit(0, 255, 0);
	}
	
	public void drop()
	{
		position.y++;
	}
	
	public void shiftleft()
	{
		position.x--;
	}
	
	public void shiftright()
	{
		position.x++;
	}
	
	public void rotate()
	{
		Tetribit[][] tetribits = new Tetribit[this.tetribits.length][this.tetribits[0].length];
		
		for(int x = 0; x < tetribits.length; x++)
		{
			for(int y = 0; y < tetribits[0].length; y++)
			{
				if(this.tetribits[y][tetribits[x].length-x-1] != null)
				{
					tetribits[x][y] = this.tetribits[y][tetribits[x].length-x-1];
				}
			}
		}
		
		this.tetribits = tetribits;
	}
}