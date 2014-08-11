/**
 * The Square class holds information about one square on the Battleship board.
 * 
 * @author John Paxton
 * @version November 4, 2012
 */

public class Square
{
    private boolean known;      // has the user already guessed this square?
    private int contents;       // the contents of the square (empty, patrol boat, etc.)
    
    
    public Square (int inValue, boolean inKnown)
    {
        contents = inValue;
        known = inKnown;
    }
    
    /**
     * Reader method.    
     * 
     * @return  whether the user has guessed the square
     */
    
    public boolean getKnown()
    {
        return known;
    }
    
    /**
     * Writer method
     * 
     * @param   value   indicates whether the user has guessed the square
     */
    
    public void setKnown(boolean value)
    {
        known = value;
    }
    
    /**
     * Reader method.
     * 
     * @return  an integer indicating the contents of the square (e.g. empty, submarine, etc.)
     */
    
    public int getContents()
    {
        return contents;
    }
    
    /**
     * Writer method.
     * 
     * @param   value   indicates the contents of the square (e.g. empty, battleship, etc.)
     */
    
    public void setContents(int value)
    {
        contents = value;
    }
}