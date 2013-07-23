public class Tetratrix
{
	public final int WIDTH_IN_TETRIBITS = 10; public final int HEIGHT_IN_TETRIBITS = 18;
	public boolean[][] tetribits = new boolean[WIDTH_IN_TETRIBITS][HEIGHT_IN_TETRIBITS];
	
	public Tetratrix()
	{
		tetribits[0][0] = true;
		tetribits[9][17] = true;
	}
}