/**
 * The Driver program for Program 6, Battleship
 * 
 * @author John Paxton
 * @version November 4, 2012
 */

public class Driver
{
    public static void main (String [] args)
    {
        Battleship game = new Battleship(5);        // play on a 5 by 5 board
        
        System.out.println("Battleship!");
        System.out.println("-----------\n");
        
        for (int gameNumber = 1; gameNumber <= 2; gameNumber++)
        {
            game.resetBoard();
            
            System.out.println("Revealed board (for verification purposes):\n");
            game.printBoard(true);          // print contents of all squares
    
            while (!game.over())
            {
                game.printBoard(false);     // only print known squares           
                game.makeGuess();
            }
        
            game.printStatistics();
        }
        
        System.out.println("Goodbye.  Thanks for playing.");
    }            
}