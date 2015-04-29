/**
 * This is the driver program for Program 2.  
 * 
 * @author John Paxton
 * @version September 12, 2012
 */

public class Driver
{
    public static void main (String [] args)
    {
        GringottsAccount harry;         // account for Harry Potter
        GringottsAccount ron;           // account for Ron Weasley
        GringottsAccount ginny;         // account for Ginny Weasley
        GringottsAccount hermione;      // account for Hermione Granger
        GringottsAccount draco;         // account for Draco Malfoy
        
        harry = new GringottsAccount("Harry Potter", "American", 2000, 1000, 492);
        ron = new GringottsAccount("Ron Weasley", "English", 1, 5, 10);
        ginny = new GringottsAccount("Ginny Weasley", "Chinese", 0, 0, 0);
        hermione = new GringottsAccount("Hermione Granger", "Japanese", 350, 51, 290);
        draco = new GringottsAccount("Draco Malfoy", "European", 1000000000, 1000000000, 1000000000);
        
        printInfo(harry);
        printInfo(ron);
        printInfo(ginny);
        printInfo(hermione);
        printInfo(draco);
    }
    
    private static void printInfo (GringottsAccount account)
    {
        System.out.println("The owner of the account is " + account.getOwner());
        System.out.println("The nationality of the account owner is " + account.getNationality());
        System.out.println("Actual number of Galleons in account = " + account.getGalleons());
        System.out.println("Actual number of Sickles in account = " + account.getSickles());
        System.out.println("Actual number of Knuts in account = " + account.getKnuts());
        System.out.println("Possible number of Galleons in account = " + account.calculateMaximumGalleons());
        System.out.println("Possible number of Sickles in account = " + account.calculateMaximumSickles());
        System.out.println("Possible number of Knuts in account = " + account.calculateMaximumKnuts());
        account.convertToPounds();
        System.out.println();
    }
    
}