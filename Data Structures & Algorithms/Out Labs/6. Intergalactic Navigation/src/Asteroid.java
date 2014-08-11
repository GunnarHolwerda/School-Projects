/**
 *
 * @author Josh and Gunnar
 */
public class Asteroid extends Location{
    
    private double radius;
    
    /**
     * Constructor for class Asteroid
     * @param radius
     * @param xCoor
     * @param yCoor 
     */
    public Asteroid(double xCoor, double yCoor) {
        super("Asteroid Field", xCoor, yCoor);
        radius = Math.random() * 50 + 50;
    }
    
    /**
     * Method used to access asteroid radius
     * @return note
     */
    public double getRadius() {
        return radius; 
    }  
}