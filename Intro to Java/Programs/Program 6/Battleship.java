import java.util.Scanner;
import java.util.InputMismatchException;

/**
 * Class that allows you to play the classic game Battleship
 * 
 * @author Gunnar Holwerda
 * @version 11/4/12
 */
public class Battleship
{
    private Square[][] board;                               //Initiliazes the board full of "Squares"
    
    private final int EMPTY = 0;                            //Constant designated to represent an empty space
    private final int AIRCRAFTCARRIER = 5;                  //Constant designated to the Aircraft Carrier
    private final int BATTLESHIP = 4;                       //Constant designated to the Battleship
    private final int SUBMARINE = 3;                        //Constant designated to the Submarine
    private final int PATROLBOAT = 2;                       //Constant designated to the Patrol Boat
    
    private int guesses;                                    //Keeps track of the number of guesses you have taken
    private int shipsSunk;                                  //Keeps track of number of ship parts sunk
    private double money = 0.0;                             //The amount of money the user has
    private double betAmount = 0;                           //The amount bet by the user 
    
    /**
     * Constructor for the class Battleship
     * 
     * @param   size    The size of the grid you want to play with
     */
    public Battleship(int size)
    {
        board = new Square[size][size];
    }
    
    /**
     * Fills the board with empty spaces and ships 
     */
    public void fillBoard()
    {
        for (int row = 0; row < board.length; row++)
        {
            for (int column = 0; column < board[row].length; column++)
            {
                board[row][column] = new Square(EMPTY);
            }
        }        
        
        enterShip(AIRCRAFTCARRIER);                         //Places the Aircraft Carrier on the board
        enterShip(BATTLESHIP);                              //Places the Battleship on the board
        enterShip(SUBMARINE);                               //Places the Submarine on the board
        enterShip(PATROLBOAT);                              //Places the Patrol Boat on the board
    }
    
    /**
     * Enters the ship into a random location on the grid
     * 
     * @param   ship    the ship that will be put into the grid
     */
    private void enterShip(int ship)
    {
        boolean entered = false;
        int x = (int)(Math.random() * board.length);                    //randomly generated row
        int y = (int)(Math.random() * board[0].length);                 //randomly generated column
        
        while (!entered)
        {
            if (board[x][y].getContents() == EMPTY)                       
            {
                if (Math.random() > 0.5)              //Randomly number to decide orientation of ship  
                {
                    if (checkRow(ship, x, y))         //Checks if the ship can fit in the row of the random point
                    {
                        fillRow(ship, x, y);
                        entered = true;
                    }
                    else
                    {
                        if (checkColumn(ship, x, y))   //Checks if the ship can fit in the column of the random point
                        {
                            fillColumn(ship, x, y);
                            entered = true;
                        }
                    }
                }
                else
                {
                    if (checkColumn(ship, x, y))
                    {
                        fillColumn(ship, x, y);
                        entered = true;
                    }
                    else
                    {
                        if (checkRow(ship, x, y))
                        {
                            fillRow(ship, x, y);
                            entered = true;
                        }
                    }
                }
            }
            else                                                    //If the ship can't fit at that point, we find a new point
            {
                x = (int)(Math.random() * board.length);                            
                y = (int)(Math.random() * board[0].length);
            }
        }
    }
    
    /**
     * Fills the ship into a row
     * 
     * @param   ship    the ship being filled in
     * @param   row     the row to start at
     * @param   column  the column to start at
     */
    private void fillRow(int ship, int row, int column)
    {
        int shipLength = ship;                                              //The length of the ship
        
        for (int i = column; i < board.length; i++)                         //fills in to the right of the point
        {
            if (board[row][i].getContents() == EMPTY)
            {
                if (shipLength > 0)
                {
                    board[row][i].setContents(ship);
                    shipLength--;
                }
            }
            else
                i = board.length;                                           //Can't fit the ship anymore so we bump out
        }
        
        for (int i = column - 1; i >= 0; i--)                               //fills out to the left of the point
        {
            if (board[row][i].getContents() == EMPTY)
            {
                if (shipLength > 0)
                {
                    board[row][i].setContents(ship);
                    shipLength--;
                }
            }
            else
                i = -1;                                                     //Can't fit the ship anymore so we bump out
        }
    }
    
    /**
     * Fills the ship into the column
     * 
     * @param   ship    the ship being filled in
     * @param   row     the row to start at
     * @param   column  the column to start at
     */
    private void fillColumn(int ship, int row, int column)
    {
        int shipLength = ship;
        
        for (int i = row; i < board.length; i++)                        //fills in below the point
        {
            if (board[i][column].getContents() == EMPTY)
            {
                if (shipLength > 0)
                {
                    board[i][column].setContents(ship);
                    shipLength--;
                }
            }
            else
                i = board.length;                                           //Can't fit the ship anymore so we bump out
        }
        
        for (int i = row - 1; i >= 0; i--)                              //fills in above the point
        {
            if (board[i][column].getContents() == EMPTY)
            {
                if (shipLength > 0)
                {
                    board[i][column].setContents(ship);
                    shipLength--;
                }
            }
            else
                i = -1;                                                     //Can't fit the ship anymore so we bump out
        }
    }
        
    /**
     * Checks to see if there is sufficient space in the row to fit a ship
     * 
     * @param   ship    the ship being checked
     * @param   row     the row in question
     * @param   column  the column we start at
     * 
     * @return  if the ship can fit in this row
     */
    private boolean checkRow(int ship, int row, int column)
    {
        int emptySpaces = 1;
        boolean previousEmpty = true;
        
        for (int i = column + 1; i < board[row].length; i++)
        {
            if (board[row][i].getContents() == EMPTY && previousEmpty)
            {
                emptySpaces++;
            }
            else
            {
                previousEmpty = false;
            }
        }
        
        previousEmpty = true;                         //Sets to true because we start at the empty beginning point
        
        for (int i = column - 1; i >= 0; i--)
        {
            if (board[row][i].getContents() == EMPTY && previousEmpty)
            {
                emptySpaces++;
            }
            else
            {
                previousEmpty = false;
            }
        }
        
        return (emptySpaces >= ship);
    }
    
    /**
     * Checks to see if there is sufficient space in the column to fit a ship
     * 
     * @param   ship    the ship being checked
     * @param   row     the row in question
     * @param   column  the column we start at
     * 
     * @return          if the ship can fit in this row
     */
    private boolean checkColumn(int ship, int row, int column)
    {
        int emptySpaces = 1;
        boolean previousEmpty = true;
        
        for (int i = row + 1; i < board.length; i++)
        {
            if (board[i][column].getContents() == EMPTY && previousEmpty)
            {
                emptySpaces++;
            }
            else
            {
                previousEmpty = false;
            }
        }
        
        previousEmpty = true;
        
        for (int i = row - 1; i >= 0; i--)
        {
            if (board[i][column].getContents() == EMPTY && previousEmpty)
            {
                emptySpaces++;
            }
            else
            {
                previousEmpty = false;
            }
        }
        
        return (emptySpaces >= ship);
    }
    
    /**
     * Prints the board out
     * 
     * @param   printKnown  If it is true it prints out the value of each square
     */
    public void printBoard(boolean printKnown)
    {
        if (printKnown)                                                   //Sets all of the squares to known
            setSquaresToKnown(printKnown);
        
        for (int row = 0; row < board.length; row++)
        {
            System.out.print("  ");
            printSeperator(board.length);
            System.out.format("%-2d", (row + 1));
            
            for (int column = 0; column < board[row].length; column++)
            {
                System.out.print("|");
                
                if (board[row][column].getKnown() != true)
                    System.out.print(" ");
                else
                    System.out.print(board[row][column].getContents());
                    
                if (column == board[row].length - 1)
                    System.out.println("|");
            }
        }
        
        System.out.print("  ");
        printSeperator(board.length);
        System.out.print("  ");
        
        for (int i = 1; i <= board.length; i++)
        {
            System.out.print(" " + i);
        }
        
        System.out.println("\n");
        printMoney();
        System.out.println();
        
        if (printKnown == true)                //Sets all of the squares back to not known
            setSquaresToKnown(false);
    }
    
    /**
     * Prints out the amount of money the user has left
     */
    private void printMoney()
    {
        System.out.format("You have $%.2f left.%n%n", money);
    }
    
    /**
     * Prints a seperator
     * 
     * @param   length  tells what length of seperator to use
     */
    private void printSeperator(int length)
    {
        for (int i = 1; i <= length; i++)
        {
            System.out.print("+-");
            
            if (i == length)
                System.out.print("+");
        }
        
        System.out.println();
    }
    
    /**
     * Sets all of the squares either known or not known
     * 
     * @param   known   sets the squares to the value of known which is either true or false
     */
    private void setSquaresToKnown(boolean known)
    {
        for (int row = 0; row < board.length; row++)
        {
            for (int column = 0; column < board[row].length; column++)
            {
                board[row][column].setKnown(known);
            }
        }
    }
    
    /**
     * Refills the board with new ship placements
     */
    public void resetBoard()
    {
        fillBoard();
        guesses = 0;
        shipsSunk = 0;
        money = 100.00;
        System.out.format("You have $%.2f. Each round you will be able to make a bet on whether or not you will hit a ship!%n",
                            money);
        System.out.println("The current multipliers for each ship are:");
        System.out.format("%-16s: %.2f%n", "Aircraft Carrier", ((double)board.length / AIRCRAFTCARRIER));
        System.out.format("%-16s: %.2f%n", "Battleship", ((double)board.length / BATTLESHIP));
        System.out.format("%-16s: %.2f%n", "Submarine", ((double)board.length / SUBMARINE));
        System.out.format("%-16s: %.2f%n", "Patrol Boat", ((double)board.length / PATROLBOAT));
        
    }
    
    /**
     * Allows the user to make a guess on the row and column he wants to guess
     */
    public void makeGuess()
    {
        betMoney();
        int row = 0;
        int column = 0;
        boolean entered = false;
        Scanner in = new Scanner(System.in);
        
        while(!entered)
        {
            try
            {
                System.out.print("Enter a row to guess > ");
                row = in.nextInt() - 1;
                System.out.print("Enter a column to guess > ");
                column = in.nextInt() - 1;
                
                if (checkLegalMove(row, column))
                {
                    entered = true;
                }
            }
            catch(InputMismatchException exception)
            {
                System.out.println("Error. Please enter a number.");
            }
            
            in.nextLine();
            System.out.println();
            guesses++;
        }
    }
    
    /**
     * Checks to see if the user input is correct
     * 
     * @param   row     the row
     * @param   column  the column
     */
    private boolean checkLegalMove(int row, int column)
    {
        if (row < 0 || row >= board.length)
        {
            System.out.println("Error: Invalid Row.\n");
            return false;
        }
        else if (column < 0 || column >= board[row].length)
        {
            System.out.println("Error: Invalid Column.\n");
            return false;
        }
        else if (board[row][column].getKnown())
        {
            System.out.println("Error: This square was already guessed.\n");
            return false;
        }
        
        if (board[row][column].getContents() != EMPTY)
        {
            board[row][column].setKnown(true);
            shipsSunk++;
            System.out.print("Hit!  ");
            
            switch(board[row][column].getContents())
            {
                case 2:
                    System.out.println("You just sunk part of the Patrol Boat!\n");
                    money += betAmount * ((double)board.length / PATROLBOAT);
                    break;
                case 3:
                    System.out.println("You just sunk part of the Submarine!\n");
                    money += betAmount * ((double)board.length / SUBMARINE);
                    break;
                case 4:
                    System.out.println("You just sunk part of the Battleship!\n");
                    money += betAmount * ((double)board.length / BATTLESHIP);
                    break;
                case 5:
                    System.out.println("You just sunk part of the Aircraft Carrier!\n");
                    money += betAmount * ((double)board.length / AIRCRAFTCARRIER);
            }
        }
        else
        {
            board[row][column].setKnown(true);
            System.out.println("Sorry.  There is nothing there.\n");
            money -= betAmount;
        }
        
        return true;
    }
    
    /**
     * Allows the user to bet some of their money
     */
    private void betMoney()
    {
        betAmount = 0;
        Scanner in = new Scanner(System.in);
        String answer;
        
        if (money == 0.0)
        {
            System.out.println("Sorry but you do not have any more money to bet!");
        }
        else
        {
            System.out.print("Would you like to bet money that you will hit a ship next guess? (y or n) > ");
            answer = in.next();
            in.nextLine();
            if (answer.equals("y"))
            {
                boolean entered = false;
                while(!entered)
                {
                    
                    System.out.print("Enter your bet > $");
                    try
                    {
                        betAmount = in.nextDouble();
                        
                        if ((betAmount <= money) && (betAmount > 0))
                        {
                            entered = true;
                        }
                        else
                            System.out.println("Please enter an amount that is valid.");
                    }
                    catch (InputMismatchException exception)
                    {
                        System.out.println("Error. Please enter a decimal number.");
                    }
                    in.nextLine();
                }
                
                System.out.format("Thank you for entering your bet of $%.2f, you now have $%.2f left.%n%n",
                                                                                                        betAmount, money - betAmount);
            }
            else
            {
                System.out.println("Maybe next time then...\n");
            }
        }
    }
    
    /**
     * Prints the number of guesses it took you at the end of the game
     */
    public void printStatistics()
    {
        System.out.println("Congratulations!  You sunk the enemy fleet using only " + guesses + 
                            " guesses. With $" + money + " left. Game over.\n");
    }
    
    /**
     * Tells you if the game is over or not
     */
    public boolean over()
    {
        if (shipsSunk == 14)
            return true;
            
        return false;
    }
}