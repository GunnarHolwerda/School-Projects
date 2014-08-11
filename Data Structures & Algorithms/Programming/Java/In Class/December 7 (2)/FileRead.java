
/**
 * Write a description of class FileRead here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
public class FileRead
{
    private Scanner in;
    
    public FileRead(String file)
    {
        try
        {
            in = new Scanner(new File(file));
        }
        catch (FileNotFoundException exception)
        {
            System.out.println("Exception...File Not Found.");
        }
    }
    
    public double computeGPA()
    {
        int totalCredits = 0;
        double gradePoints = 0;
        
        int credits;
        double grade;
        
        while (in.hasNext())
        {
            in.next();
            in.nextInt();
            credits = in.nextInt();
            grade = in.nextDouble();
            gradePoints += credits * grade;
            totalCredits += credits;
        }
        
        return gradePoints / totalCredits;
    }
}
