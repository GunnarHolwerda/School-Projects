import java.util.*;

/**
 * Holds a music score
 * 
 * @author Gunnar Holwerda
 * @version 10/31/12
 */
public class MusicScore
{
    private ArrayList<Note> score;
    Scanner in = new Scanner(System.in);
    
    /**
     * Constructor for the class MusicScore
     */
    public MusicScore ()
    {
        score = new ArrayList<Note>();
    }
    
    /**
     * Clears the current score
     */
    public void newScore()
    {
        score.clear();
    }
    
    /**
     * Adds a Note to the score ArrayList
     */
    public void addNote()
    {
        String stringNote;
        char actualNote;
        int beat;
        
        System.out.print("Enter the note > ");                          //gets the note from user
        stringNote = in.nextLine();
        actualNote = stringNote.charAt(0);
        
        System.out.print("Enter the length of the note in beats > ");   //gets the beats from user
        beat = in.nextInt();
        
        Note note = new Note(actualNote, beat);                         //creates Note to be added
        
        score.add(note);                                                //adds the note to the score
        
        in.nextLine();
    }
    
    /**
     * Prints the score ArrayList
     */
    public void printScore()
    {
        if (score.isEmpty())
            System.out.println("\nThe score is currently empty");
        else
        {
            for (int i = 0; i < score.size(); i++)
            {
                System.out.format("%n%s%d%s %c %d", "Note ", i + 1, ":", score.get(i).getNote(), score.get(i).getBeat());
            }
            System.out.println();
        }
        removeNote();
    }
    
    /**
     * Repeats the notes specified in the method
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
                Note tempNote = new Note(score.get((start - 1) + i).getNote(), score.get((start - 1) + i).getBeat());
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
     * Removes a note from the score
     */
    public void removeNote()
    {   
        System.out.print("\nWould you like to remove any of these notes? (y or n) ");
        String stringAnswer = in.nextLine();
        char answer = stringAnswer.charAt(0);
        
        if (answer == 'y')
        {
            System.out.print("Enter the note number of the note you would like to remove > ");
            int noteToRemove = in.nextInt();
            
            score.remove(noteToRemove - 1);
            in.nextLine();
        }
    }
}
