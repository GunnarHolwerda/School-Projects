
/**
 * Write a description of class Count here.
 * 
 * @author Gunnar Holwerda
 * @version 
 */
public class Count
{
    private char character;
    private int amountOf;
    
    public Count(char inChar)
    {
        character = inChar;
    }
    
    public void addOne()
    {
        amountOf++;
    }
    
    public int getAmountOf()
    {
        return amountOf;
    }
    
    public char getCharacter()
    {
        return character;
    }
}
