package timecard;
import java.util.Date;
import java.util.ArrayList;

/**
 *
 * @author Gunnar
 */
public class Business {
    private String name;
    private int numEmployees;
    private ArrayList<Employee> employees;
    private int payPeriodLengthInDays;
    private Date curPayPeriodStart, curPayPeriodEnd;
    
    
    Business(String name, int payPeriod){
        this.name = name;
        this.payPeriodLengthInDays = payPeriod;
        
        employees = new ArrayList();
    }
    
    public void addEmployee(Employee newEmploy){
        System.out.printf("New Employee, %s %s, added as a %s\n", newEmploy.getFirstName(), newEmploy.getLastName(), newEmploy.getPosition());
        employees.add(newEmploy);
        numEmployees++;
    }
    
    public int calculateTotalLaborHours(){
        int total = 0;
        
        for(Employee emp: employees){
            total += emp.getTotalHoursThisPayPeriod();
        }
        
        return total;
    }
    
    public float calculateTotalLaborCost(){
        float totalCost = 0;
        
        for(Employee emp: employees){
            totalCost += (emp.getTotalHoursThisPayPeriod() * emp.getWage());
        }
        
        return totalCost;
    }
    
    public float calculateAverageLaborCostPerHour(){
        return calculateTotalLaborCost() / calculateTotalLaborHours();
    }
    
    //Make this be able to take an array of employees as input and print just their timecards
    public void printTimeCardReport(){
        for(Employee emp: employees){
            System.out.printf("%s %s:\n", emp.getFirstName(), emp.getLastName());
            emp.printTimeCard();
        }
    }
}
