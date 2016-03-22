import java.util.Random;

public class FrootLoops
{
    public FrootLoops()
    {
    }

    public void red(int lowerBound, int upperBound, int step)
    {
        for (int i = lowerBound; i <= upperBound; i += step) {
            System.out.println(i);
        }
    }

    public void green(int lowerBound, int upperBound, int step)
    {
        while (lowerBound <= upperBound) {
            System.out.println(lowerBound);
            lowerBound += step;
        }
    }

    public void purple()
    {
        String alphabetString = "";
        for (int i = (int)'a'; i <= (int)'z'; i++) {
            alphabetString += (char) i;
            System.out.println(alphabetString);
        }
    }

    public void blue(int val)
    {
        Random r = new Random();
        int[] counts = new int[10];

        for (int i = 0; i < 10; i++) {
            int num = r.nextInt(9);
            counts[num] += 1;
        }

        // Have to subtract one from val to get correct location of array
        System.out.println(val + " was found " + counts[val - 1] + " times.");
    }

    public void orange(int val)
    {
        Random r = new Random();
        int iterations = 0;
        int[] counts = new int[10];

        while (counts[val - 1] < 5) {
            int num = r.nextInt(9);
            counts[num] += 1;
            iterations++;
        }

        System.out.println("It took " + iterations + " iterations to generate " + val + " five times.");
    }
}
