/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Josh and Gunnar
 */
public class Square {
    private boolean known;             // has the user already guessed this square?
    private boolean contents;          // the contents of the square (empty or mine)
    private int numToDisplay;           //Keeps track of mines in the surrounding area and is the number displayed on the square when clicked
    private boolean toggleFlag;         //whether or not the user has chosen to mark the square with a bomb           
                
    /**
     * Constructor for class Square
     * 
     * @param known
     * @param value
     */
    public Square(boolean contents, boolean known)
    {
        this.contents = contents;
        this.known = known;
        numToDisplay = 0;
        toggleFlag = false;
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
    
    public boolean getContents()
    {
        return contents;
    }
    
    /**
     * Writer method.
     * 
     * @param   value   indicates the contents of the square
     */
    
    public void setContents(boolean value)
    {
        contents = value;
    }
    
    /**
     * Writer method
     * 
     * @return numToDisplay     the number to display on a clicked square 
     */
    public int getNumToDisplay(){
        return numToDisplay;
    }
    
    /**
     * Allows you to set the numToDisplay
     * 
     * @param num   the number to set numToDisplay to 
     */
    public void setNumToDisplay(int num){
        this.numToDisplay = num;
    }
    
    /**
     * Writer method
     * 
     * @param value indicates whether the user thinks a bomb is here
     */
    public void setToggleFlag(boolean value) {
        toggleFlag = value;
    }
    
    /**
     * Reader method
     * 
     * @return whether or not the user thinks there is a bomb here
     */
    public boolean getToggleFlag() {
        return toggleFlag;
    }
}
