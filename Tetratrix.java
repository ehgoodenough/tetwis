public class Tetratrix
{
	public final int WIDTH_IN_TETRIBITS = 10; public final int HEIGHT_IN_TETRIBITS = 18;
	public Tetribit[][] tetribits = new Tetribit[WIDTH_IN_TETRIBITS][HEIGHT_IN_TETRIBITS];
	
	public Tetratrix()
	{
		tetribits[0][0] = new Tetribit(0, 255, 0);
		tetribits[9][17] = new Tetribit(0, 255, 0);
	}
	
	public boolean canDrop(Tetromino tetromino)
	{
		for(int x = 0; x < tetromino.tetribits.length; x++)
		{
			for(int y = 0; y < tetromino.tetribits[x].length; y++)
			{
				if(tetromino.tetribits[x][y] != null)
				{
					int ex = tetromino.position.x + x;
					int ey = tetromino.position.y + y;
					
					if(ey+1 >= HEIGHT_IN_TETRIBITS) {return false;}
					try{if(tetribits[ex][ey+1] != null) {return false;}}
					catch(Exception exception) {return false;}
				}
			}
		}
		
		return true;
	}
}