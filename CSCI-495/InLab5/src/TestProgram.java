/**
 * TestProgram is the driver program for Inlab 5.
 *
 * @author Devin Gray
 * @version February 15, 2016
 **/
public class TestProgram
{
    public static void main (String [] args)
    {
        int bahnClass;

        System.out.println("BahnCard 25 Testing ...");
        System.out.println("-----------------------");
        bahnClass = 25;

        testSuite (bahnClass, 18);
        testSuite (bahnClass, 19);

        System.out.println("BahnCard 50 Testing ...");
        System.out.println("-----------------------");
        bahnClass = 50;

        testSuite (bahnClass, 17);
        testSuite (bahnClass, 18);
        testSuite (bahnClass, 26);
        testSuite (bahnClass, 27);
        testSuite (bahnClass, 59);
        testSuite (bahnClass, 60);

        System.out.println("BahnCard 100 Testing ...");
        System.out.println("-----------------------");
        bahnClass = 100;

        testSuite (bahnClass, 17);
        testSuite (bahnClass, 81);
    }

    private static void testSuite (int bahnClass, int age)
    {
        issueTicket(bahnClass, age, false, 1);
        issueTicket(bahnClass, age, true, 1);
        issueTicket(bahnClass, age, false, 2);
        issueTicket(bahnClass, age, true, 2);
        System.out.println();
    }

    private static void issueTicket (int cardType, int age, boolean isStudent, int desiredClass)
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
