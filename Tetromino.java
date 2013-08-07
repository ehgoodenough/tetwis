import java.awt.Point;
import java.util.Random;

public class Tetromino
{
	public Point origin;
	public Point position;
	public Tetribit[][] tetribits;
	public char tetraglyph;
	
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
	
	public Tetromino(char tetraglyph)
	{
		switch(tetraglyph)
		{
		case 'I': generateLine(); break;
		case 'L': generateLeftangle(); break;
		case 'J': generateRightangle(); break;
		case 'O': generateSquare(); break;
		case 'T': generateIntersection(); break;
		case 'S': generateLeftparallelogram(); break;
		case 'Z': generateRightparallelogram(); break;
		}
	}
	
	public Tetromino(Tetromino tetromino)
	{
		position = new Point();
		tetribits = tetromino.tetribits;
	}
	
	public void generateLine()
	{
		tetraglyph = 'I';
		origin = new Point(3, -4);
		position = new Point(3, -4);
		tetribits = new Tetribit[4][4];
		tetribits[1][0] = new Tetribit(0, 255, 255);
		tetribits[1][1] = new Tetribit(0, 255, 255);
		tetribits[1][2] = new Tetribit(0, 255, 255);
		tetribits[1][3] = new Tetribit(0, 255, 255);
	}
	
	public void generateLeftangle()
	{
		tetraglyph = 'L';
		origin = new Point(4, -3);
		position = new Point(4, -3);
		tetribits = new Tetribit[3][3];
		tetribits[0][0] = new Tetribit(255, 128, 0);
		tetribits[0][1] = new Tetribit(255, 128, 0);
		tetribits[0][2] = new Tetribit(255, 128, 0);
		tetribits[1][2] = new Tetribit(255, 128, 0);
	}
	
	public void generateRightangle()
	{
		tetraglyph = 'J';
		origin = new Point(4, -3);
		position = new Point(4, -3);
		tetribits = new Tetribit[3][3];
		tetribits[1][0] = new Tetribit(0, 0, 128);
		tetribits[1][1] = new Tetribit(0, 0, 128);
		tetribits[1][2] = new Tetribit(0, 0, 128);
		tetribits[0][2] = new Tetribit(0, 0, 128);
	}
	
	public void generateSquare()
	{
		tetraglyph = 'O';
		origin = new Point(4, -2);
		position = new Point(4, -2);
		tetribits = new Tetribit[2][2];
		tetribits[0][0] = new Tetribit(255, 255, 0);
		tetribits[0][1] = new Tetribit(255, 255, 0);
		tetribits[1][0] = new Tetribit(255, 255, 0);
		tetribits[1][1] = new Tetribit(255, 255, 0);
	}
	
	public void generateIntersection()
	{
		tetraglyph = 'T';
		origin = new Point(4, -3);
		position = new Point(4, -3);
		tetribits = new Tetribit[3][3];
		tetribits[0][0] = new Tetribit(128, 0, 128);
		tetribits[0][1] = new Tetribit(128, 0, 128);
		tetribits[1][1] = new Tetribit(128, 0, 128);
		tetribits[0][2] = new Tetribit(128, 0, 128);
	}
	
	public void generateLeftparallelogram()
	{
		tetraglyph = 'S';
		origin = new Point(4, -3);
		position = new Point(4, -3);
		tetribits = new Tetribit[3][3];
		tetribits[0][0] = new Tetribit(0, 255, 0);
		tetribits[0][1] = new Tetribit(0, 255, 0);
		tetribits[1][1] = new Tetribit(0, 255, 0);
		tetribits[1][2] = new Tetribit(0, 255, 0);
	}
	
	public void generateRightparallelogram()
	{
		tetraglyph = 'Z';
		origin = new Point(4, -3);
		position = new Point(4, -3);
		tetribits = new Tetribit[3][3];
		tetribits[1][0] = new Tetribit(255, 0, 0);
		tetribits[1][1] = new Tetribit(255, 0, 0);
		tetribits[0][1] = new Tetribit(255, 0, 0);
		tetribits[0][2] = new Tetribit(255, 0, 0);
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