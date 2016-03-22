/**
 * The BahnCard class for Inlab 5.
 *
 * @author your name(s) go here
 * @version February 18, 2016
 **/
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
        if (desiredClass == 1) {
            return 110;
        }
        else {
            int returnValue = isStudent ? 10 : 55;
            return returnValue;
        }
    }

    public int bahnCard50Price (int desiredClass)
    {
        boolean getHalfPrice = (age <= 17 || age >= 60 || (isStudent && age < 27));

        if (desiredClass == 1) {
            if (getHalfPrice) {
                return 220;
            }
            else {
                return 440;
            }
        }
        else {
            if (getHalfPrice) {
                return 110;
            }
            else {
                return 220;
            }
        }
    }

    public int bahnCard100Price (int desiredClass)
    {
        if (desiredClass == 1) {
            return 5900;
        }
        else {
            return 3500;
        }
    }
}
