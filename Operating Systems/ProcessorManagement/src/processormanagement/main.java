/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package processormanagement;

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
        for (int i = 0; i < testingData.length; i++) {
            testingData[i] = new Job((long)(Math.random() * 1000 + 1));
        }
        
        return testingData;
    }
    
    public static void runSimulation(Job[] testingData, int numberOfProcessors) {
        // Initialize processors
        Processor[] processors = generateProcessors(NUM_PROCESSORS);
        
//        // Iterate over processors and start each processor
//        for (Processor p: processors) {
//            System.out.println("Starting processor...");
//            new Thread(p).start();
//        }
        
        Thread thread = new Thread(processors[0]);
        thread.start();
        
        Job j = testingData[0];
        processors[0].addJob(j);
    }
    
    public static Processor[] generateProcessors(int numProcessors) {
        Processor[] processors = new Processor[numProcessors];
        for (int i = 0; i < numProcessors; i++) {
            processors[i] = new Processor();
        }
        
        return processors;
    }
}
