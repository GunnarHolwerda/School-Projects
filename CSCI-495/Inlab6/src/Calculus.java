/**
 * Calculus class to do numerical integration.
 *
 * @author YOUR NAME
 * @version February 26, 2016
 */
public class Calculus
{
    public static double integrate(double lowerBound, double upperBound, int numRec)
    {
        double area;
        double widthOfRectangles;
        double boundSize = (upperBound - lowerBound);
        double height = 0.0;

        widthOfRectangles = (upperBound - lowerBound) / numRec;

        for (int i = 0; i < numRec; i++)
        {
            if (lowerBound != 0)
                height +=  function(boundSize + boundSize / (2 * numRec) + i * (boundSize / numRec));
            else
                height +=  function(lowerBound + boundSize / (2 * numRec) + i * (boundSize / numRec));
        }

        area = height * widthOfRectangles;
        return area;
    }

    private static double function(double x)
    {
        return x * x;
    }
}
