package processor;

import static java.lang.Thread.sleep;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
  * @author Gunnar
 * This is the round robin processor
 */
public class ProcessorRoundRobin extends Processor {
   
    public ProcessorRoundRobin(String name) {
        super(name);
    }
    
    @Override
    public void run() {
        while (!this.notified) {
            while (!this.jobList.isEmpty()) {
                // Get the most recent job added to the list
                Job job = this.getJob(this.getJobListSize() - 1);

                try {
                    // Sleep for 1ms to simulate the job running
                    sleep(1);
                } catch (InterruptedException ex) {
                    Logger.getLogger(ProcessorRoundRobin.class.getName())
                            .log(Level.SEVERE, null, ex);
                }
                // Subtract 1 from the sleep counter of the job
                job.decrementSleepTime();

                //If the job has no computing time left, remove it from the list
                if (job.isFinished()) {
                    this.removeJob(this.getJobListSize() - 1);
                }
            }
       }
    }
}
