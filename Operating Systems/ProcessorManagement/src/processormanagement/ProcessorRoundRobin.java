package processormanagement;

import static java.lang.Thread.sleep;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Gunnar
 */
public class ProcessorRoundRobin implements Runnable {
    ArrayList<Job> jobList;
    String name;
    
    ProcessorRoundRobin(String name) {
        this.jobList = new ArrayList();
        this.name = name;
    }
    
    public synchronized void addJob(Job j) {
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
                    i = this.jobList.size() - 1;
                }
                
                try {
                    // Sleep for 1ms to simulate the job running
                    sleep(1);
                } catch (InterruptedException ex) {
                    Logger.getLogger(ProcessorRoundRobin.class.getName()).log(Level.SEVERE, null, ex);
                }
                System.out.println(this.name + " Getting job " + i + " with job.size() = " + this.jobList.size());
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
