/**
 * Calculus allows us to numerically approximate common Calculus operations.
 * 
 * @author Gunnar Holwerda
 * @version October 16, 2012
 */

public class Calculus
{
    public static double simulateIntegration (double lowerBound, double upperBound, int numberRectangles)
    {
        double area = 0.0;
        double widthOfRectangles = 0.0;
        double boundSize = (upperBound - lowerBound);
        double height = 0.0;
        
        widthOfRectangles = (upperBound - lowerBound) / numberRectangles;
        
        for (int i = 0; i < numberRectangles; i++)
        {
             if (lowerBound != 0)
                    height +=  function(boundSize + boundSize / (2 * numberRectangles) + i * (boundSize / numberRectangles));
             else
                    height +=  function(lowerBound + boundSize / (2 * numberRectangles) + i * (boundSize / numberRectangles));
        }
        
        area = height * widthOfRectangles;
        
        return area;
    }
    
    private static double function (double x)
    {
        return x * x;
    }
}