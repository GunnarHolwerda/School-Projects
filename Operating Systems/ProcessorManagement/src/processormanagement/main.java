package processormanagement;
import java.util.ArrayList;

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
    
    public static ArrayList<Job> generateTestingInfo() {
        ArrayList<Job> testingData = new ArrayList();
        for (int i = 0; i < testingData.size(); i++) {
            testingData.add(new Job((long)(Math.random() * 1000 + 1)));
        }
        
        return testingData;
    }
    
    public static void runSimulation(ArrayList<Job> testingData, int numberOfProcessors) {
        // Initialize processors
        Processor[] processors = generateProcessors(NUM_PROCESSORS);
        Thread[] threads = generateThreads(processors);
        
//        // Iterate over processors and start each processor
//        for (Processor p: processors) {
//            System.out.println("Starting processor...");
//            new Thread(p).start();
//        }
        
        while (!testingData.isEmpty()) {
            
        }
    }
    
    public static Processor[] generateProcessors(int numProcessors) {
        Processor[] processors = new Processor[numProcessors];
        for (int i = 0; i < numProcessors; i++) {
            processors[i] = new Processor();
        }
        
        return processors;
    }
    
    public static Thread[] generateThreads(Processor[] processors) {
        Thread[] threads = new Thread[processors.length];
        for (int i = 0; i < processors.length; i++) {
            threads[i] = new Thread(processors[i]);
        }
        
        return threads;
    }
}
