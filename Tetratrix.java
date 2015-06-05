public class Tetratrix
{
    public final int WIDTH_IN_TETRIBITS = 10; public final int HEIGHT_IN_TETRIBITS = 18;
    public Tetribit[][] tetribits = new Tetribit[WIDTH_IN_TETRIBITS][HEIGHT_IN_TETRIBITS];
    
    /**
     * Confirms whether a tetromino can be dropped
     * without colliding into any tetribits already
     * embedded in the tetratrix, or exceeding the
     * dimensions of the tetratrix.
     *
     * @param    tetromino    the tetromino to be dropped.
     * @return    a boolean condition confirming the drop.
     */
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
    
    /**
     * Confirms whether a tetromino can be shifted to
     * the left without colliding into any tetribits
     * already embedded in the tetratrix, or exceeding
     * the dimensions of the tetratrix.
     *
     * @param    tetromino    the tetromino to be shifted.
     * @return    a boolean condition confirming the shift.
     */
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
    
    /**
     * Confirms whether a tetromino can be shifted to
     * the right without colliding into any tetribits
     * already embedded in the tetratrix, or exceeding
     * the dimensions of the tetratrix.
     *
     * @param    tetromino    the tetromino to be shifted.
     * @return    a boolean condition confirming the shift.
     */
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
    
    /**
     * Confirms whether a tetromino can be rotated
     * without colliding into any tetribits already
     * embedded in the tetratrix, or exceeding the
     * dimensions of the tetratrix.
     *
     * @param    tetromino    the tetromino to be rotated.
     * @return    a boolean condition confirming the rotation.
     */
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
    
    /**
     * Confirms whether a tetromino can be embedded
     * without exceeding the dimensions of the tetratrix.
     *
     * @param    tetromino    the tetromino to be embedded.
     * @return    a boolean condition confirming the embedding.
     */
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
    
    /**
     * Embeds each tetribit from a tetromino into
     * the tetratrix in it's correlated position.
     * 
     * @param    tetromino    the tetromino to be embedded.
     */
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
    
    /**
     * Analyzes whether a tetrow has been completed.
     * 
     * @param    y    the tetrow to be analyzed.
     */
    public boolean hasTetrow(int y)
    {
        for(int x = 0; x < WIDTH_IN_TETRIBITS; x++)
        {
            if(tetribits[x][y] == null) {return false;}
        }
        
        return true;
    }
    
    /**
     * Delete a tetrow by iteratively dropping
     * each tetrow above the tetrow.
     * 
     * @param    y    the tetrow to be delete.
     */
    public void deleteTetrow(int y)
    {
        for(y = y; y > 0; y--)
        {
            for(int x = 0; x < WIDTH_IN_TETRIBITS; x++)
            {
                tetribits[x][y] = tetribits[x][y - 1];
            }
        }
    }
}
