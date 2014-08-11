import java.util.*;
/**
 * Battleship is the main method class for Program 6, Battleship
 * 
 * @author Joshwa Moellenkamp 
 * @version November 29, 2012
 */
public class Battleship
{
    private int guesses = 0; 
    
    private Square[][] board;    
    
    /**
     * Constructor for class Battleship
     * 
     * @param size     the size of the board squared 
     */
    public Battleship(int size)
    {
        board = new Square[size][size];
    }          
    
    /**
     * Method that clears the board of all previous information
     * 
     */
    public void resetBoard()
    {      
        for(int row = 0; row < 5; row++)                    //initializing the board to be full of unknown zeros
        {
            for(int column = 0; column < 5; column++)
            {
                board[row][column] = new Square(0, false); 
            }
        }
        
        for(int i = 5; i >= 2; i--)
            placeContents(i);
    }
    
    /**
     * Method that places the pieces onto the board
     * 
     * @param x             the length and number of the ship 
     * @param randomNum     a random variable to determine horizontal or verical orientation
     */
    public void placeContents(int ship)
    {
        int x = (int)(Math.random() * board.length);
        int y = (int)(Math.random() * board.length);        

        double randomNum = Math.random(); 
        boolean entered = false;
        
        while(!entered)
        {
            for(int i = 0; i < ship - 1; i++)                   //check all spaces within reach of the current ship to see if they are full
            {
                if(board[i][y].getContents() > 0)               //if anything is in the reach of the ship vertically
                {                                               //I think there may be an issue in this if/else if loop
                    x = (int)(Math.random() * board.length);
                    y = (int)(Math.random() * board.length); 
                    randomNum = Math.random();
                    entered = false;
                }
                else if(board[x][i].getContents() > 0)          //if anything is in the reach of the ship horizontally
                {
                    x = (int)(Math.random() * board.length);
                    y = (int)(Math.random() * board.length); 
                    randomNum = Math.random();
                    entered = false;
                }
                else                                            //if there is room then place the ship
                {
                    if(randomNum <= .5)
                    {
                        for(int j = 0; j <= ship - 1; j++)
                        {
                            board[j][y].setContents(ship);               
                        }
                    }
                    else
                    {
                        for(int j = 0; j <= ship - 1; j++)
                        {
                            board[x][j].setContents(ship);
                        }
                    }   
                    entered = true;
                }
            }            
        }    
    }    

    /**
     * Method for printing the board
     * 
     */
    public void printBoard(boolean isTrue)
    {
        if(isTrue == true)
        {
            printFormatting();
            for(int i = 0; i < board.length; i++)
            {
                System.out.println();
                System.out.print((i + 1) + " |");
                printInfo(i);
                System.out.println();
                printFormatting();
            }
        }  
        
        if(isTrue == false)
        {
            System.out.println();
            System.out.println("Hello, Mr. Bond.");
            printFormatting();
        }
        System.out.println();
        System.out.println("   1 2 3 4 5");
    }    
   
    /**
     * print the location of the ships on the board
     * 
     */
    public void printInfo(int i)
    {
        for (int column = 0; column < board.length; column++)
        {
            System.out.print(board[i][column].getContents() + "|");
        }
    }    
   
    /**
     * Print the seperating symbols that make the array look pretty
     * 
     */
    public void printFormatting()
    {
            System.out.print("  +-+-+-+-+-+");
    }    
   
    /**
     * Method for ending the game
     * 
     * 
     */
    public boolean over()
    {
        int knownSquares = 0;
        boolean isOver = false;      
        for(int row = 0; row < 5; row++)
            for(int column = 0; column < board.length; column++)
                knownSquares += board[row][column].getContents();
        if(knownSquares == 54)
            isOver = true;         
        return isOver;
    }    
   
    /**
     * Method that lets the user guess where the enemy ships may be located
     * 
     */
    public void makeGuess()
    {
        Scanner in = new Scanner(System.in);
       
        System.out.format("Enter a row to guess > ");
        int rowGuess = in.nextInt();
        int r = rowGuess - 1;
        
        System.out.format("Enter a column to guess > ");
        int columnGuess = in.nextInt();   
        int c = columnGuess - 1;
        
        if(board[r][c].getContents() == 2)
            System.out.println("Hit! You have sunken part of the Patrol Boat.");
        else if(board[r][c].getContents() == 3)
            System.out.println("Hit! You have sunken part of the Submarine.");
        else if(board[r][c].getContents() == 4)
            System.out.println("Hit! You have sunken part of the Battleship.");
        else if(board[r][c].getContents() == 5)
            System.out.println("Hit! You have sunken part of the Aircraft Carrier."); 
        else
            System.out.println("Sorry. There are no ships at these coordinates.");             
       
        guesses++;
    }   
   
    /**
     * Method that prints the statistics about the game
     * 
     */
    public void printStatistics()
    {
        System.out.println("Congratulations! You sunk the enemy fleet using " + guesses + " guesses. Game over./n");
        System.out.println("Goodbye. Thanks for playing.");
     }
}


