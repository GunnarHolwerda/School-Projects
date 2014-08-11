
/**
 * Write a description of class GringottsAccount here.
 * 
 * @author Gunnar Holwerda
 * @version 9/11/12
 */
public class GringottsAccount
{
    private String owner,       //The owner of the Gringotts Account
                   nationality; //The nationality of the Gringotts Account Owner
    
    private int galleons,       //Number of galleons in the account
                sickles,        //Number of sickles in the account
                knuts;          //Number of knuts in the account
                
    // constructor for class GringottsAccount            
    public GringottsAccount(String inOwner, String inNationality, int inGalleons, int inSickles, int inKnuts)
    {
        owner = inOwner;
        galleons = inGalleons;
        sickles = inSickles;
        knuts = inKnuts;
        nationality = inNationality;
    }
    
    // constructor for class GringottsAccount so that you don't need to input a nationality          
    public GringottsAccount(String inOwner, int inGalleons, int inSickles, int inKnuts)
    {
        owner = inOwner;
        galleons = inGalleons;
        sickles = inSickles;
        knuts = inKnuts;
        nationality = "None";
    }
    
    //returns the owner of the gringotts account
    public String getOwner()
    {
        return owner;
    }
    
    //returns the nationality of the account owner
    public String getNationality()
    {
        return nationality;
    }
    
    //returns the number of galleons
    public int getGalleons()
    {
        return galleons;
    }
    
    //returns the number of sickles  
    public int getSickles()
    {
        return sickles;
    }
    
    //returns the number of knuts
    public int getKnuts()
    {
        return knuts;
    }
    
    //returns the number of galleons if all sickles and knuts are converted to galleons
    public int calculateMaximumGalleons()
    {
        return galleons + (sickles / 17) + ((knuts / 29) / 27);
    }
    
    //returns the number of sickles if all galleons and knuts are converted to sickles
    public long calculateMaximumSickles()
    {
        long result = (long)sickles + (galleons * 17) + (knuts / 29);
        return result;
    }
    
    //returns the number of knuts if all sickles and galleons are converted to knuts
    public long calculateMaximumKnuts()
    {
        long result = (((long) galleons * 17) * 29) + (sickles * 29) + knuts;
        return result;
    }
    
    //returns the amount of galleons into currency of accounts nationality
    public void convertToPounds()
    {
        switch (this.getNationality())
        {
            case("English"):
            System.out.println("Your Gringotts converted into English pounds = " + (double) calculateMaximumGalleons()*5);
            break;
                
            case("American"):
            System.out.println("Your Gringotts converted into American Dollars = $" + (double) calculateMaximumGalleons()*8.112);
            break;
            
            case("Chinese"):
            System.out.println("Your Gringotts converted into Chinese Yuan = " + (double) calculateMaximumGalleons()*51.224);
            break;
            
            case("Japanese"):
            System.out.println("Your Gringotts converted into Japanese Yen = " + (double) calculateMaximumGalleons()*634.725);
            break;
            
            case("European"):
            System.out.println("Your Gringotts converted into Euros = " + (double) calculateMaximumGalleons()*6.181);
            break;
            
            default:
            System.out.println("We could not find your nationality so we were unable to convert your Gringotts Account.");
        }
    }
}

