/**
 *
 * @author Josh and Gunnar
 */
public class Location {
    
    private String name;
    private double xCoor,
                   yCoor;
    
    /**
     * Constructor for class Location
     * @param name
     * @param xCoor
     * @param yCoor 
     */
    public Location(String name, double xCoor, double yCoor) {
        this.name = name;
        this.xCoor = xCoor;
        this.yCoor = yCoor;
    }
    
    /**
     * Method used to access object name 
     * @return note
     */
    public String getName() {
        return name; 
    }  
    
    /**
     * Method to access x location of object
     * @return xCoor
     */
    public double getXCoor() {
        return xCoor;       //self-explanitory, retrieves the integer of the beats
    }
    
    /**
     * Method to access y location of object
     * @return yCoor
     */
    public double getYCoor() {
        return yCoor;       //self-explanitory, retrieves the integer of the beats
    }
    
    @Override
    public String toString(){
        return name;
    }
}
