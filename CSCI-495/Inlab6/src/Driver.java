import java.util.Scanner;

/**
 * Driver for numerical integration.
 *
 * @author YOUR NAME
 * @version February 26, 2016
 */
public class Driver
{
    public static void main(String[] args)
    {
        Scanner in = new Scanner(System.in);

        System.out.println("What is the lower bound? >");
        double lowerBound = in.nextDouble();

        System.out.println("What is the upper bound? >");
        double upperBound = in.nextDouble();

        System.out.println("How many rectangles would you like to use? >");
        int numRec = in.nextInt();

        double area = Calculus.integrate(lowerBound, upperBound, numRec);

        System.out.println("---------------------------------");
        System.out.format("Approximate area = %.6f\n", area);
        System.out.println("---------------------------------");
    }
}
