package processormanagement;
import static java.lang.Thread.sleep;
import java.util.ArrayList;

/**
 *
 * @author Gunnar
 */
public class main {
    
    public static final int NUM_PROCESSORS = 3;
    public static final int NUMBER_OF_TEST_JOBS = 1000;

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        try {
            runSimulation(generateTestingInfo(), NUM_PROCESSORS);   
        }
        catch (InterruptedException e) {
            System.out.println("There was an interruption in the Matrix");
        }
    }
    
    public static ArrayList<Job> generateTestingInfo() {
        ArrayList<Job> testingData = new ArrayList();
        for (int i = 0; i < NUMBER_OF_TEST_JOBS; i++) {
            testingData.add(new Job((long)(Math.random() * 1000 + 1)));
        }
        
        return testingData;
    }
    
    public static void runSimulation(ArrayList<Job> testingData, int numberOfProcessors) throws InterruptedException {
        // Initialize processors
        Processor[] processors = generateProcessors(NUM_PROCESSORS);
        Thread[] threads = generateThreads(processors);
        
        // Start all of the threads holding the processors
        for (Thread t: threads) {
            t.start();
        }
        
        // Start the first job on processor 0
        Job curJob = testingData.get(0);
        int curProcessor = 0;
        processors[curProcessor].addJob(curJob);
        testingData.remove(0);
        while (!testingData.isEmpty()) {
            curProcessor = (curProcessor + 1) % NUM_PROCESSORS;
            curJob = testingData.get(0);
            processors[curProcessor].addJob(curJob);
            testingData.remove(0);
            sleep(1);
        }
    }
    
    public static Processor[] generateProcessors(int numProcessors) {
        Processor[] processors = new Processor[numProcessors];
        for (int i = 0; i < numProcessors; i++) {
            processors[i] = new Processor("Processor " + i);
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
