package processormanagement;
import processor.Job;
import processor.CustomProcessor;
import processor.ProcessorRoundRobin;
import processor.Processor;
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
        System.out.println("Custom Processor");
        simulateProcessor(generateCustomProcessors(), false);
        System.out.println("Round Robin");
        simulateProcessor(generateRoundRobinProcessors(), false);
    }
    
    public static void simulateProcessor(Processor[] processors, boolean useRandomTestData) {
        try {
            long minRuntime = -1, maxRuntime = 0, currentRuntime = 0;
            long[] runTimes = new long[NUMBER_OF_SIMULATIONS_TO_RUN];
            for (int i = 1; i <= NUMBER_OF_SIMULATIONS_TO_RUN; i ++) {
                ArrayList<Job> testingInfo = useRandomTestData ? generateTestingInfo() : generateTestJobs();
                currentRuntime = runSimulation(testingInfo, processors);
                
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
                runTimes[i - 1] = currentRuntime;
                System.out.print("\r" + i + "/" + NUMBER_OF_SIMULATIONS_TO_RUN);
            }
            Statistics.printStatistics(runTimes, maxRuntime, minRuntime);
        }
        catch (InterruptedException e) {
            System.out.println("There was an interruption in the Matrix");
        }
    }
    
    public static ArrayList<Job> generateTestingInfo() {
        ArrayList<Job> testingData = new ArrayList();
        for (int i = 0; i < NUMBER_OF_TEST_JOBS; i++) {
            testingData.add(new Job((int)(Math.random() * 500 + 1)));
        }
        
        return testingData;
    }
    
    public static long runSimulation(ArrayList<Job> testingData, Processor[] processors) throws InterruptedException {
        Thread[] threads = generateThreads(processors);
 
        // Start all of the threads holding the processors
        for (Thread t: threads) {
            t.start();
        }
        
        long startTime = new Date().getTime();
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
        
        // Join threads back in
        for (Thread t: threads) {
            t.join();
        }
        
        // return the turnaround time for the simulation
        return (new Date().getTime()) - startTime;
    }
        
    public static ProcessorRoundRobin[] generateRoundRobinProcessors() {
        ProcessorRoundRobin[] processors = new ProcessorRoundRobin[NUM_PROCESSORS];
        for (int i = 0; i < processors.length; i++) {
            processors[i] = new ProcessorRoundRobin("Processor " + i);
        }
        
        return processors;
    }
    
    public static CustomProcessor[] generateCustomProcessors() {
        CustomProcessor[] processors = new CustomProcessor[NUM_PROCESSORS];
        for (int i = 0; i < processors.length; i++) {
            processors[i] = new CustomProcessor("Processor " + i);
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
    
    public static ArrayList<Job> generateTestJobs() {
        ArrayList<Job> testingData = new ArrayList();
        testingData.add(new Job(9));
        testingData.add(new Job(2));
        testingData.add(new Job(16));
        testingData.add(new Job(3));
        testingData.add(new Job(29));
        testingData.add(new Job(198));
        testingData.add(new Job(7));
        testingData.add(new Job(170));
        testingData.add(new Job(180));
        testingData.add(new Job(178));
        testingData.add(new Job(73));
        testingData.add(new Job(8));
        
        return testingData;
    }
}
