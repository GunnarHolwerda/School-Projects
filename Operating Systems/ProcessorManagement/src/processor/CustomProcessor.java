/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package processor;

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
        //Collections.sort(this.jobList);
    }
    
    @Override
    public void run() {
        while (!this.notified) {
            while (!this.jobList.isEmpty()) {
                Job job = this.getJob(0);
                //Run complete job
                while (job.sleepTime > 0) {
                    try {
                        // Sleep for 1ms to simulate the job running
                        sleep(1);
                    } catch (InterruptedException ex) {
                        Logger.getLogger(ProcessorRoundRobin.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    job.decrementSleepTime();
                }

                // Job is finished remove from list
                if (job.isFinished()) {
                    this.removeJob(0);
                }
            }
            //System.out.println(this.name + " has no jobs and notified is " + this.notified);
        }
    }
}
