public class Tetratrix
{
	public final int WIDTH_IN_TETRIBITS = 10; public final int HEIGHT_IN_TETRIBITS = 18;
	public Tetribit[][] tetribits = new Tetribit[WIDTH_IN_TETRIBITS][HEIGHT_IN_TETRIBITS];
	
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
					catch(Exception exception) {}
				}
			}
		}
		
		return true;
	}
	
	public boolean canShiftLeft(Tetromino tetromino)
	{
		for(int x = 0; x < tetromino.tetribits.length; x++)
		{
			for(int y = 0; y < tetromino.tetribits[x].length; y++)
			{
				if(tetromino.tetribits[x][y] != null)
				{
					int ex = tetromino.position.x + x;
					int ey = tetromino.position.y + y;
					
					if(ex-1 < 0) {return false;}
					try{if(tetribits[ex-1][ey] != null) {return false;}}
					catch(Exception exception) {}
				}
			}
		}
		
		return true;
	}
	
	public boolean canShiftRight(Tetromino tetromino)
	{
		for(int x = 0; x < tetromino.tetribits.length; x++)
		{
			for(int y = 0; y < tetromino.tetribits[x].length; y++)
			{
				if(tetromino.tetribits[x][y] != null)
				{
					int ex = tetromino.position.x + x;
					int ey = tetromino.position.y + y;
					
					if(ex+1 >= WIDTH_IN_TETRIBITS) {return false;}
					try{if(tetribits[ex+1][ey] != null) {return false;}}
					catch(Exception exception) {}
				}
			}
		}
		
		return true;
	}
	
	public boolean canRotate(Tetromino tetromino)
	{
		for(int x = 0; x < tetromino.tetribits.length; x++)
		{
			for(int y = 0; y < tetromino.tetribits[x].length; y++)
			{
				if(tetromino.tetribits[x][y] != null)
				{
					int ex = tetromino.position.x + y;
					int ey = tetromino.position.y + tetromino.tetribits[0].length - x - 1;
					
					if(ex < 0) {return false;}
					if(ex >= WIDTH_IN_TETRIBITS) {return false;}
					if(ey >= HEIGHT_IN_TETRIBITS) {return false;}
					try{if(tetribits[ex][ey] != null) {return false;}}
					catch(Exception exception) {}
				}
			}
		}
		
		return true;
	}
	
	public boolean canEmbed(Tetromino tetromino)
	{
		for(int x = 0; x < tetromino.tetribits.length; x++)
		{
			for(int y = 0; y < tetromino.tetribits[x].length; y++)
			{
				if(tetromino.tetribits[x][y] != null)
				{
					int ex = tetromino.position.x + x;
					int ey = tetromino.position.y + y;
					
					if(ey < 0) {return false;}
				}
			}
		}
		
		return true;
	}
	
	public void embed(Tetromino tetromino)
	{
		for(int x = 0; x < tetromino.tetribits.length; x++)
		{
			for(int y = 0; y < tetromino.tetribits[x].length; y++)
			{
				if(tetromino.tetribits[x][y] != null)
				{
					int ex = tetromino.position.x + x;
					int ey = tetromino.position.y + y;
					
					tetribits[ex][ey] = tetromino.tetribits[x][y];
				}
			}
		}
	}
}