public class Driver
{
    public static void main (String [] args)
    {
        Bowling game;
        int [] rolls;

        rolls = new int[] {1, 1, 2, 2, 3, 3, 4, 4, 5, 4, 6, 3, 2, 7, 1, 8, 0, 9, 0, 0};
        oneGame(rolls, 10);   // bowl a game with 10 frames

        rolls = new int[] {10, 10, 10, 10, 10, 10, 10};
        oneGame(rolls, 5);    // bowl a game with 5 frames

        rolls = new int[] {6, 4, 5};
        oneGame(rolls, 1);    // bowl a game with 1 frame

        rolls = new int[] {10, 2, 8};
        oneGame(rolls, 1);

        rolls = new int[] {2, 8, 10};
        oneGame(rolls, 1);

        rolls = new int[] {10, 2, 7};
        oneGame(rolls, 1);

        rolls = new int[] {10, 10, 2};
        oneGame(rolls, 1);

    }

    private static void oneGame(int [] rolls, int frames)
    {
        Bowling game = new Bowling(rolls, frames);

        System.out.println(">>>>> New Bowling Game <<<<<\n");
        game.play();
    }
}