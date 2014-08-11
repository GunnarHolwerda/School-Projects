package printqueuesimulation;

/**
 *
 * @author Gunnar Holwerda
 */
public class PrintJob {
    public int arrivalTime;
    public int finishTime;
    public int numOfPages;
    
    public PrintJob(int numOfPages){
        this.numOfPages = numOfPages;
    }
}
