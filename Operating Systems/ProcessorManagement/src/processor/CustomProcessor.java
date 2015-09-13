/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package processor;

import processor.ProcessorRoundRobin;
import processor.Processor;
import static java.lang.Thread.sleep;
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
    
    @Override
    public void run() {
        while(!this.jobList.isEmpty()) {
            for (int i = this.jobList.size() - 1; i >= 0; i--) {
                //System.out.println(this.name + " is still running with " + this.jobList.size() + " jobs");
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
