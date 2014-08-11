/**
 * TestProgram is the driver program for lab 5.
 * 
 * @author John Paxton 
 * @version October 2, 2012
 */

public class TestProgram
{
    public static void main (String [] args)
    {   
        int bahnClass;
        
        System.out.println("BahnCard 25 Testing ...");
        System.out.println("-----------------------");
        bahnClass = 25;
        
        ticket (bahnClass, 18, false, 1);
        ticket (bahnClass, 18, true, 1);
        ticket (bahnClass, 18, false, 2);
        ticket (bahnClass, 18, true, 2);
        
        System.out.println("--");
        
        ticket (bahnClass, 19, false, 1);
        ticket (bahnClass, 19, true, 1);
        ticket (bahnClass, 19, false, 2);
        ticket (bahnClass, 19, true, 2);
        
        System.out.println();
        System.out.println();
        
        System.out.println("BahnCard 50 Testing ...");
        System.out.println("-----------------------");
        bahnClass = 50;
        
        ticket (bahnClass, 17, false, 1);
        ticket (bahnClass, 17, true, 1);
        ticket (bahnClass, 17, false, 2);
        ticket (bahnClass, 17, true, 2);
        
        System.out.println("--");
                
        ticket (bahnClass, 18, false, 1);
        ticket (bahnClass, 18, true, 1);
        ticket (bahnClass, 18, false, 2);
        ticket (bahnClass, 18, true, 2);
        
        System.out.println("--");
        
        ticket (bahnClass, 26, false, 1);
        ticket (bahnClass, 26, true, 1);
        ticket (bahnClass, 26, false, 2);
        ticket (bahnClass, 26, true, 2);
        
        System.out.println("--");
        
        ticket (bahnClass, 27, false, 1);
        ticket (bahnClass, 27, true, 1);
        ticket (bahnClass, 27, false, 2);
        ticket (bahnClass, 27, true, 2);
        
        System.out.println("--");
        
        ticket (bahnClass, 59, false, 1);
        ticket (bahnClass, 59, true, 1);
        ticket (bahnClass, 59, false, 2);
        ticket (bahnClass, 59, true, 2);
        
        System.out.println("--");
        
        ticket (bahnClass, 60, false, 1);
        ticket (bahnClass, 60, true, 1);
        ticket (bahnClass, 60, false, 2);
        ticket (bahnClass, 60, true, 2);
        
        System.out.println();
        System.out.println();
        
        System.out.println("BahnCard 100 Testing ...");
        System.out.println("-----------------------");
        bahnClass = 100;
        
        ticket (bahnClass, 17, false, 1);
        ticket (bahnClass, 17, true, 1);
        ticket (bahnClass, 17, false, 2);
        ticket (bahnClass, 17, true, 2);
        
        System.out.println("--");
        
        ticket (bahnClass, 88, false, 1);
        ticket (bahnClass, 88, true, 1);
        ticket (bahnClass, 88, false, 2);
        ticket (bahnClass, 88, true, 2);
    }
    
    private static void ticket (int cardType, int age, boolean isStudent, int desiredClass)
    {
        BahnCard card = new BahnCard();
        int cost;
        
        card.setAge(age);
        card.setStudent(isStudent);
        
        if (cardType == 25)
        {
            cost = card.bahnCard25Price(desiredClass);
        }
        else if (cardType == 50)
        {
            cost = card.bahnCard50Price(desiredClass);
        }
        else if (cardType == 100)
        {
            cost = card.bahnCard100Price(desiredClass);
        }
        else
        {
            cost = -100;    // should not occur
        }
        
        System.out.println("Age: " + age + ", student status: " + isStudent +
            ", desired class: " + desiredClass + ", cost in euros: " + cost);
    }

}