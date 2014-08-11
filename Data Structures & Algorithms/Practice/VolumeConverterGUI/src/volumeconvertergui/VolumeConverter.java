/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package volumeconvertergui;

/**
 *
 * @author Gunnar
 */
public class VolumeConverter {
    
    //Constant that holds the converstion rate for litres to gallons
    public static final double LITRES_TO_GALLONS = 3.785411784;
    
    /*
     * Converts litres to gallons
     * @param   litres  number of litres
     * @return  the litres converted into gallons
     */
    public static double toGallons(double litres)
    {
        return litres / LITRES_TO_GALLONS;
    }
    
    /*
     * Converts gallons into litres
     * @param   gallons     number of gallons
     * @return  the gallons converted into litres
     */
    public static double toLitres(double gallons)
    {
        return gallons * LITRES_TO_GALLONS;
    }
    
}
