package timecard;

/**
 *
 * @author Gunnar
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Business abilityPlus = new Business("AbilityPlus", 14);
        
        Employee konnor = new Employee("Konnor", "Brimhall", "Workerman", 10.00, abilityPlus);
        abilityPlus.addEmployee(konnor);
        
        konnor.ClockIn();
        try {
            Thread.sleep(5000);
        }
        catch(Exception e){
            System.out.println(e);
        }
        konnor.ClockOut();
        
        abilityPlus.printTimeCardReport();
    }
    
}
