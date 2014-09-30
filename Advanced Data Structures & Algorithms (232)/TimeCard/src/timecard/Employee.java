package timecard;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
/**
 *
 * @author Gunnar
 */
public class Employee {
    private static final int NUM_SECONDS_IN_HOUR = 3600;
    private String firstName, 
                   lastName, 
                   position;
    private double wage;
    private float currentNumHours,
                  totalHoursThisPayPeriod,
                  totalHoursThisWeek;            
    private Date  startTime,
                  stopTime;
    private Business business;
    
    //This constructor should be called the first time an employee is added to the business
    Employee(String firstName, String lastName, String position, double wage, Business business){
        this.firstName = firstName;
        this.lastName = lastName;
        this.position = position;
        this.wage = wage;
        this.business = business;
        
        currentNumHours = 0;
        totalHoursThisPayPeriod = 0;
        totalHoursThisWeek = 0;
    }
    
    Employee(String fileName){
        //A constructor if I want to open employees from a file
    }
    
    public void ClockIn(){
        this.currentNumHours = 0;
        
        this.startTime = new Date();
        System.out.printf("%s %s clocked in at %s\n", this.firstName, this.lastName, this.startTime.toString());
        
        //Figure out way to update currentNumHours until clock out
    }
    
    public void ClockOut(){
        this.stopTime = new Date();
        System.out.printf("%s %s clocked out at %s\n", this.firstName, this.lastName, this.stopTime.toString());
        this.currentNumHours = this.calculateCurrentNumHours();
        this.totalHoursThisWeek += this.currentNumHours;
        this.totalHoursThisPayPeriod += this.currentNumHours;
    }
    
    public void printTimeCard(){
        System.out.printf("%s %s clocked in at:  %s\n", this.firstName, this.lastName, this.startTime.toString());
        System.out.printf("%s %s clocked out at: %s\n", this.firstName, this.lastName, this.stopTime.toString());
        System.out.printf("%s %s worked a total of %f hours today\n", this.firstName, this.lastName, currentNumHours);
    }
    
    //This function can be implemented later when we need to save employees
    private void SaveDay(){
        
    }
    
    private float calculateCurrentNumHours(){
        return ((this.startTime.getTime()/1000L) - (this.stopTime.getTime()/1000L)) / NUM_SECONDS_IN_HOUR;
    }
    
    
    public String getFirstName(){
        return firstName;
    }
    
    public String getLastName(){
        return lastName;
    }
    
    public String getPosition(){
        return position;
    }
    
    public double getWage(){
        return wage;
    }
    
    public float getCurrentNumHours(){
        return currentNumHours;
    }
    
    public float getTotalHoursThisPayPeriod(){
        return totalHoursThisPayPeriod;
    }
    
    public float getTotalHoursThisWeek(){
        return totalHoursThisWeek;
    }
    
    public Date getStartTime(){
        return startTime;
    }
    
    public Date getStopTime(){
        return stopTime;
    }
    
    public Business getBusiness(){
        return business;
    }
}
