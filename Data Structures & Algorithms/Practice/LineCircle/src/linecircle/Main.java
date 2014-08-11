/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package linecircle;

/**
 *
 * @author Brendan
 */
public class Main {

    public static boolean blockedByCircle(double x1, double y1,
            double x2, double y2,
            double x3, double y3, double r) {
        double t = 0.0;
        if (x1 != x2 || y1 != y2) {
            t = ((x2 - x1) * (x3 - x1) + (y2 - y1) * (y3 - y1)) / ((x2 - x1) * (x2 - x1) + (y2 - y1) * (y2 - y1));
        }

        if (t > 1) {
            t = 1.0;
        }
        if (t < 0) {
            t = 0.0;
        }
        // find closest point (x,y)
        double x = x1 + t * (x2 - x1);
        double y = y1 + t * (y2 - y1);

        double distSq = (x - x3) * (x - x3) + (y - y3) * (y - y3);
        return (distSq <= r * r);


    }

    public static double computeBearing(double x1, double y1, double x2, double y2) {

        double length = Math.sqrt((x2 - x1) * (x2 - x1) + (y2 - y1) * (y2 - y1));
        double angleRadians = Math.acos((y2 - y1) / length); // in radians
        double angleDegrees = Math.toDegrees(angleRadians);

        if (x2 > x1) {
            return angleDegrees;
        }
        return (360 - angleDegrees);
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here

        System.out.println(blockedByCircle(0, 0, 2, 2, 1, 0, 0.5));

        System.out.println(computeBearing(1, 1, 0, 0));

    }
}
