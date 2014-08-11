/**
 * Write a description of class Battleship here.
 * 
 * @author Richard Hunnicutt
 * @version 4 December 2012
 */
public class Battleship
{
    private char[][] board;
        
    public int SIZE;
    private final char HIT = 'X';
    private final char MISS = 'M';
    private final char EMPTY = ' ';
    boolean done = true;

    /**
     * Constructor for objects of class Battleship
     */
    public Battleship(int inSIZE)
    {
        SIZE = inSIZE;
        board = new char[SIZE][SIZE];
        initialize();
    }
    
    public void initialize()
    {
        for (int row = 0; row < SIZE; row++)
        {
            for (int column = 0; column < SIZE; column++)
            {
                board[row][column] = EMPTY;
            }
        }
    }
    
    public void printBoard(boolean done)
    {
            while(true)
            {
                for (int s = 0; s < SIZE - 1; s++)
                {
                    printRow(s);
                    printSeparator();
                }   
                printRow(SIZE - 1);
            }
    }
    
     private void printRow(int row)
    {
        for (int column = 0; column < SIZE - 1; column++)
        {
            System.out.print(board[row][column] + "|");
        }
        System.out.println(board[row][SIZE - 1]);
    }
    
    private void printSeparator()
    {
        for (int s = 0; s < SIZE - 1; s++)
        {
            System.out.print("-+");
        }
        System.out.println("-");
    }
    
    public void resetBoard()
    {
    }
    
    public boolean over()
    {
        return false;
    }
    
    public void makeGuess()
    {
    }
    
    public void printStatistics()
    {
    }
}
