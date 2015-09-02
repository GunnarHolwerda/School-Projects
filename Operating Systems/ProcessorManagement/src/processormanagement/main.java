/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package processormanagement;

import static java.lang.Thread.sleep;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Gunnar
 */
public class main {
    
    public static final int NUM_PROCESSORS = 3;

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        runSimulation(generateTestingInfo(), NUM_PROCESSORS);
    }
    
    public static Job[] generateTestingInfo() {
        Job[] testingData = new Job[1000];
        return testingData;
    }
    
    public static void runSimulation(Job[] testingData, int numberOfProcessors) {
        Processor[] processors = generateProcessors(NUM_PROCESSORS);
    }
    
    public static Processor[] generateProcessors(int numProcessors) {
        Processor[] processors = new Processor[numProcessors];
        for (int i = 0; i < numProcessors; i++) {
            processors[i] = new Processor();
        }
        
        return processors;
    }
}
