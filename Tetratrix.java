public class Tetratrix
{
	public final int WIDTH_IN_TETRIBITS = 10; public final int HEIGHT_IN_TETRIBITS = 18;
	public Tetribit[][] tetribits = new Tetribit[WIDTH_IN_TETRIBITS][HEIGHT_IN_TETRIBITS];
	
	public Tetratrix()
	{
		tetribits[0][0] = new Tetribit(0, 255, 0);
		tetribits[9][17] = new Tetribit(0, 255, 0);
	}
}