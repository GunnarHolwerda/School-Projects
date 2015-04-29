
/**
 * Lab 1: CellPhoneEntry holds the contact's name and cell phone number.
 * 
 * @author John Paxton
 * @version September 4, 2012 
 */

public class CellPhoneEntry
{
    // instance variables
    private String number;
    private String name;

    /**
     * Constructor for objects of class CellPhoneEntry
     * 
     * @param   inName      the contact's full name, e.g. "CS Department"
     * @param   inNumber    the contact's phone number, e.g. "406-994-4780"
     */
    
    public CellPhoneEntry(String inName, String inNumber)
    {
        // initialise instance variables
        name = inName;
        number = inNumber;
    }

    /**
     * getNumber
     * 
     * @return     the contact's full phone number
     */
    
    public String getNumber()
    {
        return number;
    }


    /**
     * getName
     * 
     * @return      the contact;s full name
     */
    public String getName()
    {
        return name;
    }
    
        /**
     * getNumberPrefix
     * 
     * @return     the area code of the contact's phone number
     */
    
    public String getAreaCode()
    {
        return number.substring(0, 3);
    }
}
