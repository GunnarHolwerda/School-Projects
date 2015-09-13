package processormanagement;

import static java.lang.Thread.sleep;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Gunnar
 */
public class ProcessorRoundRobin extends Processor {
   
    ProcessorRoundRobin(String name) {
        super(name);
    }
    
    @Override
    public void run() {
        while (!this.jobList.isEmpty()) {
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
                Job job = this.jobList.get(i);
                // Subtract 1 from the sleep counter of the job
                job.sleepTime -= 1;
                
                // If the job has no computing time left, remove it from the list
                if (job.sleepTime == 0) {
                    this.removeJob(job);
                }
            }
        }
    }
}
