public class Tetratrix
{
	public boolean[][] tetribits = new boolean[10][18];
	
	public Tetratrix()
	{
		tetribits[0][0] = true;
		tetribits[9][17] = true;
	}
}