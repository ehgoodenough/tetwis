import java.awt.Point;
import java.util.Random;

public class Tetromino
{
	public Point position;
	public Tetribit[][] tetribits;
	
	public Tetromino()
	{
		Random random = new Random();
		switch(random.nextInt(7))
		{
		case 0: generateLine(); break;
		case 1: generateLeftangle(); break;
		case 2: generateRightangle(); break;
		case 3: generateSquare(); break;
		case 4: generateIntersection(); break;
		case 5: generateLeftparallelogram(); break;
		case 6: generateRightparallelogram(); break;
		}
	}
	
	public void generateRightangle()
	{
		position = new Point(4, -3);
		tetribits = new Tetribit[3][3];
		tetribits[1][0] = new Tetribit(0, 255, 0);
		tetribits[1][1] = new Tetribit(0, 255, 0);
		tetribits[1][2] = new Tetribit(0, 255, 0);
		tetribits[0][2] = new Tetribit(0, 255, 0);
	}
	
	public void generateLeftangle()
	{
		position = new Point(4, -3);
		tetribits = new Tetribit[3][3];
		tetribits[0][0] = new Tetribit(0, 255, 0);
		tetribits[0][1] = new Tetribit(0, 255, 0);
		tetribits[0][2] = new Tetribit(0, 255, 0);
		tetribits[1][2] = new Tetribit(0, 255, 0);
	}
	
	public void generateRightparallelogram()
	{
		position = new Point(4, -3);
		tetribits = new Tetribit[3][3];
		tetribits[0][0] = new Tetribit(0, 255, 0);
		tetribits[0][1] = new Tetribit(0, 255, 0);
		tetribits[1][1] = new Tetribit(0, 255, 0);
		tetribits[1][2] = new Tetribit(0, 255, 0);
	}
	
	public void generateLeftparallelogram()
	{
		position = new Point(4, -3);
		tetribits = new Tetribit[3][3];
		tetribits[1][0] = new Tetribit(0, 255, 0);
		tetribits[1][1] = new Tetribit(0, 255, 0);
		tetribits[0][1] = new Tetribit(0, 255, 0);
		tetribits[0][2] = new Tetribit(0, 255, 0);
	}
	
	public void generateSquare()
	{
		position = new Point(4, -2);
		tetribits = new Tetribit[2][2];
		tetribits[0][0] = new Tetribit(0, 255, 0);
		tetribits[0][1] = new Tetribit(0, 255, 0);
		tetribits[1][0] = new Tetribit(0, 255, 0);
		tetribits[1][1] = new Tetribit(0, 255, 0);
	}
	
	public void generateLine()
	{
		position = new Point(3, -4);
		tetribits = new Tetribit[4][4];
		tetribits[1][0] = new Tetribit(0, 255, 0);
		tetribits[1][1] = new Tetribit(0, 255, 0);
		tetribits[1][2] = new Tetribit(0, 255, 0);
		tetribits[1][3] = new Tetribit(0, 255, 0);
	}
	
	public void generateIntersection()
	{
		position = new Point(4, -3);
		tetribits = new Tetribit[3][3];
		tetribits[0][0] = new Tetribit(0, 255, 0);
		tetribits[0][1] = new Tetribit(0, 255, 0);
		tetribits[1][1] = new Tetribit(0, 255, 0);
		tetribits[0][2] = new Tetribit(0, 255, 0);
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