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
    
    @Override
    public void run() {
        while(!this.jobList.isEmpty()) {
            Job job = this.jobList.get(0);
            try {
                // Sleep for 1ms to simulate the job running
                sleep(1);
            } catch (InterruptedException ex) {
                Logger.getLogger(ProcessorRoundRobin.class.getName()).log(Level.SEVERE, null, ex);
            }
            job.sleepTime -= 1;
            
            if (job.sleepTime == 0) {
                this.removeJob(job);
            }
        }
    }
}
