/**
 * The BahnCard class for lab 5.
 * 
 * @author your name goes here 
 * @version October 2, 2012
 */

public class BahnCard
{
    private int age;
    private boolean isStudent;

    public void setAge (int age)
    {
        this.age = age;
    }
    
    public void setStudent (boolean flag)
    {
        this.isStudent = flag;
    }
    
    public int bahnCard25Price (int desiredClass)
    {
        if (age >= 19)
        {
            if (desiredClass == 1)
                return 110;
            else
                return 55;
        }
        else
        {
            if (desiredClass == 1)
                return 110;
            else
                return 10;
        }
    }

    
    public int bahnCard50Price (int desiredClass)
    {
        if (desiredClass == 1)
        {
            if (age <= 17)
                return 220;
            
            else if (age >= 60)
                return 220;
            
           else if (age < 27 && isStudent)
                return 220;
           
           else
                return 440;
        }
        else 
        {
            if (age <= 17)
                return 110;
            
            else if (age >= 60)
                return 110;
            
           else if (age < 27 && isStudent)
                return 110;
           
           else
                return 220;
        }
    }
    
    public int bahnCard100Price (int desiredClass)
    {
        switch (desiredClass)
        {
            case 1: return 5900;
            case 2: return 3500;
            default: return 0;
        }
    }
}