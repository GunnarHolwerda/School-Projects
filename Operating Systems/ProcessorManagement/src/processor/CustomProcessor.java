package processor;

import static java.lang.Thread.sleep;
import java.util.Collections;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Gunnar
 * 
 * This is a custom processor that keeps track of its total sleep time
 */
public class CustomProcessor extends Processor {
    
    int totalTime = 0;
    
    public CustomProcessor(String name) {
        super(name);
    }
    
    public int getTotalTime() {
        return this.totalTime;
    }
    
    /**
     * Override parent class addJob so that we sort the jobList every 
     * time we add a job
     * @param j The job to be added to the list
     */
    @Override
    public synchronized void addJob(Job j) {
        super.addJob(j);
        this.totalTime += j.sleepTime;
    }
    
    @Override
    public synchronized void removeJob(int index) {
        // Then call super to remove the job
        super.removeJob(index);
    }
    
    private synchronized void decrementTotalTime() {
        this.totalTime--;
    }
    
    @Override
    public void run() {
        while (!this.notified) {
            while (!this.jobList.isEmpty()) {
                Job job = this.getJob(0);
                //Run complete job
                for (int i = 0; i < job.sleepTime; i++) {
                    try {
                        // Sleep for 1ms to simulate the job running
                        sleep(1);
                    } catch (InterruptedException ex) {
                        Logger.getLogger(ProcessorRoundRobin.class.getName()).log(Level.SEVERE, null, ex);
                    }
                   this.decrementTotalTime();
                }
                this.removeJob(0);
            }
            //System.out.println(this.name + " has no jobs and notified is " + this.notified);
        }
    }
}
