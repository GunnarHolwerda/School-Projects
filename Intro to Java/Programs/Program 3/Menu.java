
/**
 * Allow a user to play the Petals around the Roses game.
 * This is the driver program for Program 3.
 * 
 * @author John Paxton
 * @version September 19, 2012
 */

import java.util.Scanner;

public class Menu
{
    private static final int QUIT = 1;      // indicates the user wants to quit playing
    private static final int PLAY = 2;      // indicates the user wants to play another game
    private static final int GUESS = 3;     // indicates the user wants to guess the total
    
    public static void main (String [] args)
    {
        Scanner in = new Scanner(System.in);    // to read input from the console
        
        PetalsGame game = new PetalsGame();     // a Petals Around the Roses game
        
        int choice = PLAY;                      // the player's menu choice
        boolean guessedRight = false;           // tells whether the player guessed correctly
        
        System.out.println("CSCI 111: Petals Around the Roses");
        System.out.println("---------------------------------\n");
        
        while (choice != QUIT)                  
        {
            System.out.println("1. Quit.");
            System.out.println("2. Play.");
            System.out.println("3. Guess.");
            
            System.out.print("\nPlease enter your choice > ");
            choice = in.nextInt();
            System.out.println();
            
            switch (choice)
            {
                case QUIT:
                    System.out.println("Thank you for playing.");
                    break;
                case PLAY:
                    game.rollDice();
                    game.printDice();
                    System.out.println("The number of petals around the roses is " 
                                       + game.calculateAllPetals() + "\n");
                    break;
                    
                case GUESS:
                     if (guessedRight == true)
                     {
                            System.out.println("You guessed correctly last time.");
                            System.out.println("Would you like to play with a different amount of dice? \n1. Yes\n2. No");
                            System.out.print("Enter your choice here: ");
                            choice = in.nextInt();
                
                            switch (choice)
                            {
                                case 1:
                                    System.out.print("How many dice would you like to use? ");
                                    choice = in.nextInt();
                                    game.setNumberOfDie(choice);
                                    break;
                                
                                case 2:
                                    System.out.println("Maybe next time you will want to change the number of die.\n");
                                    break;
                    
                                default:
                                    System.out.println("Sorry, that was not a valid input.");
                            }
                     }
                     game.rollDice();
                     game.printDice();
                    
                     System.out.println("What number would you like to guess?");
                     choice = in.nextInt();
                    
                     System.out.println("\n");
                     if (choice == game.calculateAllPetals())
                     {
                        System.out.println("Great guess! You got it right! You must be the master...\n");
                        guessedRight = true;
                     }
                     else
                        System.out.println("Sorry but you guess incorrectly...\n");
                     game.setNumberOfDie(5);
                     break;
                    
                default:
                    System.out.println("That is an invalid choice.  Please try again.\n");
            }
        }                
    }
       
}