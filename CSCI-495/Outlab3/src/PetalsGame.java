import java.util.Random;

/**
 * Created by Gunnar on 2/11/2016.
 */
public class PetalsGame {
    int[] dice;

    public void rollDice() {
        dice = new int[5];
        Random rand = new Random();
        for (int die = 0; die < dice.length; die++) {
            dice[die] = rand.nextInt(5) + 1;
        }
    }

    public void printDice() {
        System.out.print("You rolled the following: ");
        for (int i = 0; i < dice.length; i++) {
            System.out.print(dice[i]);
            if (i < dice.length - 1) {
                System.out.print(", ");
            }
        }
        System.out.println();
    }

    public int calculateAllPetals() {
        int total = 0;

        for (int die: dice)
        {
            switch (die)
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

    public void checkGuess(int guess) {
        boolean correct = (guess == calculateAllPetals());
        if (correct) {
            System.out.println("Correct! Good job!");
        }
        else {
            System.out.println("Sorry! The correct answer was: " + calculateAllPetals());
        }
    }
}
