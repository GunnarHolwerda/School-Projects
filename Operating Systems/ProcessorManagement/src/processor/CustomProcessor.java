/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package processor;

import processor.ProcessorRoundRobin;
import processor.Processor;
import static java.lang.Thread.sleep;
import java.util.Collections;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Gunnar
 */
public class CustomProcessor extends Processor {
    
    public CustomProcessor(String name) {
        super(name);
    }
    
    /**
     * Override parent class addJob so that we sort the jobList every 
     * time we add a job
     * @param j The job to be added to the list
     */
    @Override
    public synchronized void addJob(Job j) {
        super.addJob(j);
        Collections.sort(this.jobList);
    }
    
    @Override
    public void run() {
        while(!this.jobList.isEmpty()) {
            // Grab the first job, which will be the top job
            Job job = this.jobList.get(0);
            
            //Run complete job
            while (job.sleepTime > 0) {
                try {
                    // Sleep for 1ms to simulate the job running
                    sleep(1);
                } catch (InterruptedException ex) {
                    Logger.getLogger(ProcessorRoundRobin.class.getName()).log(Level.SEVERE, null, ex);
                }
                job.sleepTime -= 1;
            }
            
            // Job is finished remove from list
            this.removeJob(job);
        }
    }
}
