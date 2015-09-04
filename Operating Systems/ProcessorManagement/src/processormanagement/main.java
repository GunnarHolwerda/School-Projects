package processormanagement;
import static java.lang.Thread.sleep;
import java.util.ArrayList;
import java.util.Date;

/**
 *
 * @author Gunnar
 */
public class main {
    
    public static final int NUM_PROCESSORS = 3;
    public static final int NUMBER_OF_TEST_JOBS = 1000;
    public static final int NUMBER_OF_SIMULATIONS_TO_RUN = 100;

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        try {
            long totalRuntime = 0, minRuntime = -1, maxRuntime = 0, currentRuntime = 0;
            for (int i = 1; i <= NUMBER_OF_SIMULATIONS_TO_RUN; i ++) {
                currentRuntime = runRoundRobinSimulation(generateTestingInfo(), NUM_PROCESSORS);
                // Determine the max runtime that has occured
                maxRuntime = (currentRuntime > maxRuntime) ? currentRuntime : maxRuntime;
                // Determine the min runtime that has occured
                if (minRuntime == -1) {
                    minRuntime = currentRuntime;
                }
                else {
                    minRuntime = (currentRuntime < minRuntime) ? currentRuntime : minRuntime;   
                }
                //Increase the total runtime of the simulation
                totalRuntime += currentRuntime;
                System.out.println("Finished simulation #" + i);
            }
            System.out.printf("Average turnaround time: %.2f\nMax runtime: %.2f\nMin runtime: %.2f", totalRuntime/1000, maxRuntime, minRuntime);
        }
        catch (InterruptedException e) {
            System.out.println("There was an interruption in the Matrix");
        }
    }
    
    public static ArrayList<Job> generateTestingInfo() {
        ArrayList<Job> testingData = new ArrayList();
        for (int i = 0; i < NUMBER_OF_TEST_JOBS; i++) {
            testingData.add(new Job((long)(Math.random() * 500 + 1)));
        }
        
        return testingData;
    }
    
    public static long runRoundRobinSimulation(ArrayList<Job> testingData, int numberOfProcessors) throws InterruptedException {
        // Initialize processors
        ProcessorRoundRobin[] processors = generateProcessors(NUM_PROCESSORS);
        Thread[] threads = generateThreads(processors);
        long startTime = new Date().getTime();
        
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
        
        return (new Date().getTime()) - startTime;
    }
    
    public static ProcessorRoundRobin[] generateProcessors(int numProcessors) {
        ProcessorRoundRobin[] processors = new ProcessorRoundRobin[numProcessors];
        for (int i = 0; i < numProcessors; i++) {
            processors[i] = new ProcessorRoundRobin("Processor " + i);
        }
        
        return processors;
    }
    
    public static Thread[] generateThreads(ProcessorRoundRobin[] processors) {
        Thread[] threads = new Thread[processors.length];
        for (int i = 0; i < processors.length; i++) {
            threads[i] = new Thread(processors[i]);
        }
        
        return threads;
    }
}
