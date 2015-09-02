package processormanagement;

import static java.lang.Thread.sleep;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Gunnar
 */
public class Processor implements Runnable {
    ArrayList<Job> jobList;
    
    Processor() {
        this.jobList = new ArrayList();
    }
    
    public void addJob(Job j) {
        this.jobList.add(j);
    }
    
    @Override
    public void run() {
        System.out.println("Starting processor");
        // Infinite wait until the jobList has a Job in it
        while (this.jobList.isEmpty()) {}
        
        System.out.println("A job has arrived");
        // While we have jobs, keep processing them
        while (!jobList.isEmpty()) {
            int previousSize = jobList.size();
            for (int i = this.jobList.size() - 1; i >= 0; i--) {
                // Check if the size of the list has changed, if so we have new job, run that
                if (this.jobList.size() > previousSize) {
                    i = this.jobList.size();
                }
                
                try {
                    // Sleep for 1ms to simulate the job running
                    System.out.println("Sleeping");
                    sleep(1);
                } catch (InterruptedException ex) {
                    Logger.getLogger(Processor.class.getName()).log(Level.SEVERE, null, ex);
                }
                
                Job job = this.jobList.get(i);
                // Subtract 1 from the sleep counter of the job
                job.sleepTime -= 1;
                
                // If the job has no computing time left, remove it from the list
                if (job.sleepTime == 0) {
                    this.jobList.remove(job);
                }
            }
        }
    }
    
}
