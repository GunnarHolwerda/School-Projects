
/**
 * Game class for the Petals Game program
 * 
 * @author Gunnar Holwerda
 * @version 9/27/12
 */
public class PetalsGame
{
    private int size;                          //used to declare the size of the array
    private int[] dice;                        //array that holds dice values
                
    /*
     * Constructor for the PetalsGame class
     *
     */
    PetalsGame()
    {
        setNumberOfDie(5);
    }
    
    /*
     * Sets the number of die for the game
     * 
     * @param   number of dice that the user wants to play with
     */
    public void setNumberOfDie(int numberOfDie)
    {
        size = --numberOfDie;       //do this because the array would be one too large
        dice = new int[size + 1];   //creates the array of dice to the specified size
    }
    
    /*
     * Rolls the dice
     * 
     */
    public void rollDice()
    {
        for (int i = 0; i <= size; i++)
        {
            dice[i] = (int)((Math.random() * 6) + 1);
        }
        
    }
    
    /*
     * Prints the value of all of the dice
     */
    public void printDice()
    {
        System.out.print("You rolled the following: ");
        for (int i = 0; i <= size; i++)
        {
            System.out.print(dice[i] + ", ");
        }
        System.out.println();
    }
    
    /*
     * Calculates the value of all of the pedals
     * 
     * @return      the petals around the rose
     */
    public int calculateAllPetals()
    {
        int total = 0;
        
        for (int i = 0; i <= size; i++)
        {
            switch (dice[i])
            {
                case 3:
                total += 2;
                break;
                
                case 5:
                total += 4;
                break;
                
                default:
                total += 0;
            }
        }
        
        return total;
    }
}
