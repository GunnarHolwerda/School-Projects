import java.util.*;

/**
 * Write a description of class MusicScore here.
 * 
 * @author Gunnar Holwerda
 * @version 
 */
public class MusicScore
{
    private ArrayList<Note> score;
    Scanner in = new Scanner(System.in);
    
    /**
     * Constructor for class MusicScore
     */
    public MusicScore ()
    {
        score = new ArrayList<Note>();
    }
    
    /**
     * Creates a new score
     */
    public void newScore()
    {
        score.clear();
    }
    
    /**
     * Reader method
     */
    public ArrayList<Note> getScore()
    {
        return score;
    }
    
    /**
     * Adds a note to the score
     */
    public void addNote()
    {
        String stringNote;
        int beat;
        
        System.out.print("Enter the note > ");                          //gets the note from user
        stringNote = in.next().toUpperCase();
        
        System.out.print("Enter the length of the note in beats > ");   //gets the beats from user
        beat = in.nextInt();
        
        Note note = new Note(stringNote, beat);                         //creates Note to be added
        
        score.add(note);                                                //adds the note to the score
        in.nextLine();                                                  //Used to clear out the input so it runs correctly
    }
    
    /**
     * Prints the score
     */
    public void printScore()
    {
        if (score.isEmpty())
            System.out.println("\nThe score is currently empty");
        else
        {
            for (int i = 0; i < score.size(); i++)
            {
                System.out.format("%n%s%d%s %s %d", "Note ", i + 1, ":", score.get(i).getNote(), score.get(i).getBeats());
            }
            System.out.println();
        }
        removeNote();
    }
    
    /**
     * Repeats the Notes
     */
    public void repeatNotes()
    {
        int start, end;
        
        printScore();                                                                   //prints the current score that we have for the user to reference
        
        System.out.print("\nEnter the note number that starts the repetition > ");        //prompts to enter the start of the repetition
        start = in.nextInt();
        
        System.out.print("Enter the note number that ends the repetition > ");          //prompts user to enter the end of the repetition
        end = in.nextInt();
        
        if (start <= score.size() && start > 0 && end <= score.size() && end >= start)
        {
            for (int i = 0; i <= ((end - 1) - (start - 1)); i++)
            {
                Note tempNote = new Note(score.get((start - 1) + i).getNote(), score.get((start - 1) + i).getBeats());
                score.add(tempNote);
            }
        }
        else
        {
            System.out.print("Error.  ");
            if (end < 1 || end > score.size())
                System.out.println("The ending note number is not valid.");
            else if (start < 1 || start > score.size())
                System.out.println("The starting note number is not valid.");
            else if (start > end)
                System.out.println("The starting note number can not be bigger than the ending note number."); 
        }
    }
    
    /**
     * Removes a Note
     */
    public void removeNote()
    {   
        System.out.print("\nWould you like to remove any of these notes? (y or n) ");
        String stringAnswer = in.nextLine();
        
        if (stringAnswer.equals("y"))
        {
            System.out.print("Enter the note number of the note you would like to remove > ");
            int noteToRemove = in.nextInt();
            
            score.remove(noteToRemove - 1);
            in.nextLine();
        }
    }
}
