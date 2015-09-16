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
    
    private int totalTime;
    
    public CustomProcessor(String name) {
        super(name);
        this.totalTime = 0;
    }
    
    public int getTotalTime() {
        return this.totalTime;
    }
    
    @Override
    public synchronized void addJob(Job j) {
        super.addJob(j);
        this.totalTime += j.sleepTime;
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
                        Logger.getLogger(ProcessorRoundRobin.class.getName())
                                .log(Level.SEVERE, null, ex);
                    }
                   this.decrementTotalTime();
                }
                this.removeJob(0);
            }
        }
    }
}
