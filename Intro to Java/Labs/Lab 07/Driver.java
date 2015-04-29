
/**
 * Driver is the driver program for lab 7
 * 
 * @author John Paxton
 * @version October 16, 2012
 */

import java.util.Scanner;

public class Driver
{
    public static void main (String [] args)
    {
        Scanner in = new Scanner (System.in);
        double lowerBound;
        double upperBound;
        int numberRectangles;
        double area;
        
        System.out.println("CSCI 111, Lab 7: Integration Approximation\n");
        
        System.out.print("Please enter the lower bound > ");
        lowerBound = in.nextDouble();
        System.out.print("Please enter the upper bound > ");
        upperBound = in.nextDouble();
        System.out.print("Please enter the number of rectangles to use > ");
        numberRectangles = in.nextInt();
        
        area = Calculus.simulateIntegration(lowerBound, upperBound, numberRectangles);
        
        System.out.format("The simulated area = %.6f%n", area);
    }
}
