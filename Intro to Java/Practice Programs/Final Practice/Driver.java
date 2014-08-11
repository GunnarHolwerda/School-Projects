
/**
 * Write a description of class Driver here.
 * 
 * @author Gunnar Holwerda
 * @version 
 */

import java.util.ArrayList;

public class Driver
{
    public static void main (String [] args)
    {
        ArrayList<Animal> animals = new ArrayList<Animal>();
        animals.add(new Cat("Garfield"));
        
        for (Animal a: animals)
        {
            System.out.println(a.talk());
        }
        
    }
    
    public static void aRecursiveMethod(int number1, int number2)
    {
        System.out.println("Number 1 = " + number1 + ", Number 2 = " + number2);
        
        if (number2 != 0)
        {
            aRecursiveMethod(number2, number1 % number2);
        }
    }
    
    private static void printRow(int n)
    {
        for (int i = 0; i < n; i++)
        {
            System.out.print("*");
        }
        System.out.println();
    }
    
    public static void printSquare(int n)
    {
        printRow(n);
        for (int i = 2; i < n; i++)
        {
            for (int j = 1; j <= n; j++)
            {
                if (j == 1 || j == n)
                    System.out.print("*");
                else
                    System.out.print(" ");
            }
            System.out.println();
        }
        printRow(n);
    }
    
    public static int italianThreeDayPass(int travelers, int trainClass)
    {
        switch(trainClass)
        {
            case 1:
                if (travelers == 1)
                    return 200;
                else
                    return 150 * travelers;
            case 2:
                return 150 * travelers;
            default:
                return 0;
        }
    }
    
    public static int divide (int number1, int number2)
    {
        try
        {
            return number1/number2;
        }
        catch (ArithmeticException exception)
        {
            return Integer.MAX_VALUE;
        }
    }
    
    public static double standardDeviation (double[] numbers)
    {
        double sum = 0;
        for (int i = 0; i < numbers.length; i++)
        {
            sum += numbers[i];
        }
        double average = sum / numbers.length;
        
        double sumOfSquares = 0;
        for (int i = 0; i < numbers.length; i++)
        {
            sumOfSquares += (numbers[i] - average) * (numbers[i] - average);
        }
        
        return Math.sqrt((sumOfSquares / numbers.length));
    }
}
