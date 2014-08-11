
/**
 * A Note that goes into the MusicScore class
 * 
 * @author Gunnar Holwerda
 * @version 10/26/12
 */
public class Note
{
    private String note;
    private int beat;
    
    /**
     * Constructor for class Note
     * 
     * @param   inNote  Note
     * @param   inBear  Beat of the note
     */
    public Note(String inNote, int inBeat)
    {
        note = inNote;
        beat = inBeat;
    }
    
    /**
     * Reader method
     * 
     * @return      the note
     */
    public String getNote()
    {
        return note;
    }
    
    /**
     * Reader method
     * 
     * @return      the beat of the note
     */
    public int getBeats()
    {
        return beat;
    }
}
