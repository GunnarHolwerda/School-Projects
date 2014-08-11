
/**
 * Write a description of class Student here.
 * 
 * @author Gunnar Holwerda 
 * @version 8/30/12
 */
public class Student
{
   private String name;
   private int creditsComplete,
               gradePoints;
   public double gpa()
   {
       return (double) gradePoints/creditsComplete;
   }
   public String getName()
   {
       return name;
   }
   public int getCreditsComplete()
   {
       return creditsComplete;
   }
   public int getGradePoints()
   {
       return gradePoints;
   }
   public int creditsRemain()
   {
       return 120 - creditsComplete;
   }
}
