
/**
 * Write a description of class Note here.
 * 
 * @author Gunnar Holwerda
 * @version 10/26/12
 */
public class Note
{
    private char note;
    private int beat;
    
    /**
     * Constructor for class Note
     * 
     * @param   inNote  Note
     * @param   inBear  Beat of the note
     */
    public Note(char inNote, int inBeat)
    {
        note = inNote;
        beat = inBeat;
    }
    
    /**
     * getNote
     * 
     * @return      the note
     */
    public char getNote()
    {
        return note;
    }
    
    /**
     * getBeat
     * 
     * @return      the beat of the note
     */
    public int getBeat()
    {
        return beat;
    }
}
