package processor;

/**
 *
 * @author Gunnar
 * 
 * This class represents a job
 */
public class Job{
    private final int originalSleepTime;
    public int sleepTime;
    public int arrivalTime;
    
    public Job(int arrivalTime, int sleepTime) {
        this.sleepTime = sleepTime;
        this.originalSleepTime = sleepTime;
        this.arrivalTime = arrivalTime;
    }
    
    public synchronized void decrementSleepTime() {
        this.sleepTime -= 1;
    }
    
    public synchronized boolean isFinished() {
        return this.sleepTime <= 0;
    }
    
    @Override
    public String toString() {
        return "Job sleep: " + this.originalSleepTime + " arrivalTime: " 
                + this.arrivalTime;
    }
}
