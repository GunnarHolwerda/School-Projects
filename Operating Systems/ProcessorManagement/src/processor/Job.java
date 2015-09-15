package processor;

/**
 *
 * @author Gunnar
 */
public class Job implements Comparable{
    public int sleepTime;
    public int arrivalTime;
    
    public Job(int arrivalTime, int sleepTime) {
        this.sleepTime = sleepTime;
        this.arrivalTime = arrivalTime;
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
}
