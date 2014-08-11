
/**
 * Write a description of class FileDemo here.
 * 
 * @author Gunnar Holwerda
 * @version 
 */
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;

public class FileDemo
{
    private Scanner inputFile;
    private PrintWriter outputFile;
    private Count[] count;
    
    public FileDemo(String inFile, String outFile)
    {
        try
        {
            inputFile = new Scanner(new File(inFile));
            outputFile = new PrintWriter(new File(outFile));
            initialize();
        }
        catch (FileNotFoundException exception)
        {
            System.out.println("Exception. File was not found.");
        }
    }
    
    private void initialize()
    {
        count = new Count[26];
        for (int i = 0; i < count.length; i++)
        {
            count[i] = new Count((char)(i + 'a'));
        }
    }
    
    public void processFile()
    {
        char currentChar;
        while (inputFile.hasNextLine())
        {
            String line = inputFile.nextLine();
            for (int i = 0; i < line.length(); i++)
            {
                currentChar = line.charAt(i);
                if (Character.isLetter(currentChar))
                {
                    count[Character.toLowerCase(currentChar) - 'a'].addOne();
                }
            }
        }
    }
    
    public void sortFile()
    {
        int largestIndex;
        Count temp;
        
        for (int i = 0; i < count.length - 1; i++)
        {
            largestIndex = i;
            
            for (int j = i + 1; j < count.length; j++)
            {
                if (count[j].getAmountOf() > count[largestIndex].getAmountOf())
                {
                    largestIndex = j;
                }
            }
            
            temp = count[i];
            count[i] = count[largestIndex];
            count[largestIndex] = temp;
        }
    }
    
    public void showResults()
    {
        outputFile.print(count[0].getCharacter() + " --> " + count[0].getAmountOf());
        for (int i = 1; i < count.length; i++)
        {
            outputFile.print("\n" + count[i].getCharacter() + " --> " + count[i].getAmountOf()); 
        }
        outputFile.close();
    }
    
}
