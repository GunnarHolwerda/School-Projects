package processor;

/**
 *
 * @author Gunnar
 */
public class Job implements Comparable{
    private int originalSleepTime;
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
    
    public int getOriginalSleepTime() {
        return this.originalSleepTime;
    }

    @Override
    public int compareTo(Object o) {
        Job comparator = (Job) o;
        if (this.sleepTime > comparator.sleepTime) {
            return 1;
        }
        else if (this.sleepTime == comparator.sleepTime) {
            return 0;
        }
        else {
            return -1;
        }
    }
    
    @Override
    public String toString() {
        return "Job sleep: " + this.originalSleepTime + " arrivalTime: " + this.arrivalTime;
    }
}
