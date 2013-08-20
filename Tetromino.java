import java.awt.Point;
import java.awt.Color;
import java.util.Random;

public class Tetromino
{
	public Point origin;
	public Point position;
	public Tetribit[][] tetribits;
	public char tetraglyph;
	
	public Color lineColor = new Color(51, 51, 51);
	public Color leftangleColor = new Color(76, 76, 76);
	public Color rightangleColor = new Color(102, 102, 102);
	public Color squareColor = new Color(127, 127, 127);
	public Color intersectionColor = new Color(153, 153, 153);
	public Color leftparallelogramColor = new Color(178, 178, 178);
	public Color rightparallelogramColor = new Color(204, 204, 204);
	
	/**
	 * Constructs the tetromino through randomization.
	 */
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
	
	/**
	 * Constructs the tetromino by it's tetraglyph.
	 *
	 * @param	tetraglyph	a character of the tetromino.
	 */
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
	
	/**
	 * Constructs the tetromino by cloning the 
	 * tetribits of another tetromino.
	 *
	 * @param	tetromino	the tetromino to be cloned.
	 */
	public Tetromino(Tetromino tetromino)
	{
		position = new Point();
		tetribits = tetromino.tetribits;
	}
	
	/**
	 * Generates the tetromino as a line.
	 */
	public void generateLine()
	{
		// *
		// *
		// *
		// *
		
		tetraglyph = 'I';
		origin = new Point(3, -4);
		position = new Point(3, -4);
		tetribits = new Tetribit[4][4];
		tetribits[1][0] = new Tetribit(lineColor);
		tetribits[1][1] = new Tetribit(lineColor);
		tetribits[1][2] = new Tetribit(lineColor);
		tetribits[1][3] = new Tetribit(lineColor);
	}
	
	/**
	 * Generates the tetromino as a left angle.
	 */
	public void generateLeftangle()
	{
		// *
		// *
		// **
		
		tetraglyph = 'L';
		origin = new Point(4, -3);
		position = new Point(4, -3);
		tetribits = new Tetribit[3][3];
		tetribits[0][0] = new Tetribit(leftangleColor);
		tetribits[0][1] = new Tetribit(leftangleColor);
		tetribits[0][2] = new Tetribit(leftangleColor);
		tetribits[1][2] = new Tetribit(leftangleColor);
	}
	
	/**
	 * Generates the tetromino as a right angle.
	 */
	public void generateRightangle()
	{
		//  *
		//  *
		// **
		
		tetraglyph = 'J';
		origin = new Point(4, -3);
		position = new Point(4, -3);
		tetribits = new Tetribit[3][3];
		tetribits[1][0] = new Tetribit(rightangleColor);
		tetribits[1][1] = new Tetribit(rightangleColor);
		tetribits[1][2] = new Tetribit(rightangleColor);
		tetribits[0][2] = new Tetribit(rightangleColor);
	}
	
	/**
	 * Generates the tetromino as a square.
	 */
	public void generateSquare()
	{
		// **
		// **
		
		tetraglyph = 'O';
		origin = new Point(4, -2);
		position = new Point(4, -2);
		tetribits = new Tetribit[2][2];
		tetribits[0][0] = new Tetribit(squareColor);
		tetribits[0][1] = new Tetribit(squareColor);
		tetribits[1][0] = new Tetribit(squareColor);
		tetribits[1][1] = new Tetribit(squareColor);
	}
	
	/**
	 * Generates the tetromino as an intersection.
	 */
	public void generateIntersection()
	{
		// *
		// **
		// *
		
		tetraglyph = 'T';
		origin = new Point(4, -3);
		position = new Point(4, -3);
		tetribits = new Tetribit[3][3];
		tetribits[0][0] = new Tetribit(intersectionColor);
		tetribits[0][1] = new Tetribit(intersectionColor);
		tetribits[1][1] = new Tetribit(intersectionColor);
		tetribits[0][2] = new Tetribit(intersectionColor);
	}
	
	/**
	 * Generates the tetromino as a left parallelogram.
	 */
	public void generateLeftparallelogram()
	{
		// *
		// **
		//  *
		
		tetraglyph = 'S';
		origin = new Point(4, -3);
		position = new Point(4, -3);
		tetribits = new Tetribit[3][3];
		tetribits[0][0] = new Tetribit(leftparallelogramColor);
		tetribits[0][1] = new Tetribit(leftparallelogramColor);
		tetribits[1][1] = new Tetribit(leftparallelogramColor);
		tetribits[1][2] = new Tetribit(leftparallelogramColor);
	}
	
	/**
	 * Generates the tetromino as a right parallelogram.
	 */
	public void generateRightparallelogram()
	{
		//  *
		// **
		// *
		
		tetraglyph = 'Z';
		origin = new Point(4, -3);
		position = new Point(4, -3);
		tetribits = new Tetribit[3][3];
		tetribits[1][0] = new Tetribit(rightparallelogramColor);
		tetribits[1][1] = new Tetribit(rightparallelogramColor);
		tetribits[0][1] = new Tetribit(rightparallelogramColor);
		tetribits[0][2] = new Tetribit(rightparallelogramColor);
	}
	
	/**
	 * Drops the tetromino.
	 */
	public void drop()
	{
		position.y++;
	}
	
	/**
	 * Shifts the tetromino to the left.
	 */
	public void shiftleft()
	{
		position.x--;
	}
	
	/**
	 * Shifts the tetromino to the right.
	 */
	public void shiftright()
	{
		position.x++;
	}
	
	/**
	 * Rotates the tetromino.
	 */
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