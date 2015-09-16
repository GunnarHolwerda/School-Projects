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
    public static final int NUMBER_OF_SIMULATIONS_TO_RUN = 1;

    public static void main(String[] args) {
        // Run simulation for the custom processor
        System.out.println("Custom Processor");
        simulateProcessor("CP", false);
        // Run the simulation for the Round Robin processor
        System.out.println("Round Robin");
        simulateProcessor("RR", false);
    }
    
    public static void simulateProcessor(String procType, boolean useRandomTestData) {
        try {
            long minRuntime = -1, maxRuntime = 0, currentRuntime = 0;
            long[] runTimes = new long[NUMBER_OF_SIMULATIONS_TO_RUN];
            Processor[] processors = new Processor[NUM_PROCESSORS];
            for (int i = 1; i <= NUMBER_OF_SIMULATIONS_TO_RUN; i ++) {
                // Print to console which simulation we are on currently
                System.out.print(i + "\r");
                
                // Determine which processors to use for the simulation based on procType
                if (procType.equals("RR")) {
                    processors = generateRoundRobinProcessors();
                }
                else {
                    processors = generateCustomProcessors();
                }
                
                // testingInfo will either be Benhai's data or random jobs
                ArrayList<Job> testingInfo = useRandomTestData ? generateTestingInfo() : generateTestJobs();
                // Run the simulation with the testData on the processors
                currentRuntime = procType.equals("RR") ? 
                        runRRSimulation(testingInfo, generateRoundRobinProcessors()) : 
                        runCPSimulation(testingInfo, generateCustomProcessors());
                
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
            testingData.add(new Job(i + 1, (int)(Math.random() * 125 + 1)));
        }
        
        return testingData;
    }
    
    public static long runCPSimulation(ArrayList<Job> testingData, CustomProcessor[] processors) throws InterruptedException {
        Thread[] threads = generateThreads(processors);
        
        for (Thread t: threads) {
            t.start();
        }
        
        Job jobToAdd = testingData.get(0);
        sleep(jobToAdd.arrivalTime);
        CustomProcessor p = findShortestProcessor(processors);
        p.addJob(jobToAdd);
        long startTime = new Date().getTime();
        int prevArrivalTime = jobToAdd.arrivalTime;
        
        for (int i = 1; i < testingData.size(); i++) {
            // Get the job at the current index
            jobToAdd = testingData.get(i);
            
            // Sleep until the jobToAdd actually arrives
            sleep(jobToAdd.arrivalTime - prevArrivalTime);
            
            // Find the processor with the shortest total sleepTime
            p = findShortestProcessor(processors);
            
            //Add the job to that processor
            p.addJob(jobToAdd);
            
            //Set the previous arrival time to the current job's
            prevArrivalTime = jobToAdd.arrivalTime;
        }
        
        notifyAllProcessors(processors);
        
        for (Thread t: threads) {
            t.join();
        }
        
        return (new Date().getTime()) - startTime;
    }
    
    /**
     * Returns the processor with the smallest amount of sleep time
     * @param processors
     * @return the CustomProcessor with the shortest total wait time
     */
    public static CustomProcessor findShortestProcessor(CustomProcessor[] processors) {
        CustomProcessor minProcessor = processors[0];
        for (int i = 1; i < processors.length; i++) {
            if (processors[i].getTotalTime() < minProcessor.getTotalTime()) {
                minProcessor = processors[i];
            }            
        }
        
        return minProcessor;
    }
    
    public static long runRRSimulation(ArrayList<Job> testingData, ProcessorRoundRobin[] processors) throws InterruptedException {
        Thread[] threads = generateThreads(processors);
 
        // Start all of the threads holding the processors
        for (Thread t: threads) {
            t.start();
        }
        
        // Start the first job on processor 0
        Job curJob = testingData.get(0);
        int curProcessor = 0;
        // Sleep until the job arrives
        sleep(curJob.arrivalTime);
        // Add the job to the first processor and then remove it
        processors[curProcessor].addJob(curJob);
        long startTime = new Date().getTime();
        testingData.remove(0);
        
        while (!testingData.isEmpty()) {
            // The next processor will be the (currentProcessor  + 1) % total number of processors
            curProcessor = (curProcessor + 1) % NUM_PROCESSORS;
            System.out.print(curProcessor);
            
            // Take note of the arrivale time of the previous job
            int prevArrivalTime = curJob.arrivalTime;
            
            // Get the new job
            curJob = testingData.get(0);
            
            //Sleep until the next job will arrive
            sleep(curJob.arrivalTime - prevArrivalTime);
            
            // Add the job to the currentProcessor
            processors[curProcessor].addJob(curJob);
            testingData.remove(0);
        }
        
        // Notify all processors that they will receive no more jobs
        notifyAllProcessors(processors);
        
        // Join threads back in
        for (Thread t: threads) {
            t.join();
        }
        
        // return the turnaround time for the simulation
        return (new Date().getTime()) - startTime;
    }
    
    /**
     * This method generates RoundRobinProcessors to be used in a simulation
     * @return an an array of ProcessorRoundRobin objects
     */
    public static ProcessorRoundRobin[] generateRoundRobinProcessors() {
        ProcessorRoundRobin[] processors = new ProcessorRoundRobin[NUM_PROCESSORS];
        for (int i = 0; i < processors.length; i++) {
            processors[i] = new ProcessorRoundRobin("Processor " + i);
        }
        
        return processors;
    }
    
    /**
     * This method generates CustomProcessors to be used in a simulation
     * @return an an array of CustomProcessor objects
     */
    public static CustomProcessor[] generateCustomProcessors() {
        CustomProcessor[] processors = new CustomProcessor[NUM_PROCESSORS];
        for (int i = 0; i < processors.length; i++) {
            processors[i] = new CustomProcessor("Processor " + i);
        }
        
        return processors;
    }
    
    /**
     * Generates thread objects for each processor passed in
     * @param processors
     * @return an array of Thread objects
     */
    public static Thread[] generateThreads(Processor[] processors) {
        Thread[] threads = new Thread[processors.length];
        for (int i = 0; i < processors.length; i++) {
            threads[i] = new Thread(processors[i]);
        }
        
        return threads;
    }
    
    /**
     * Randomly generates n test jobs that have sleeptime between 1 and 500 ms
     * @return ArrayList<Job> of all of the jobs
     */
    public static ArrayList<Job> generateTestJobs() {
        ArrayList<Job> testingData = new ArrayList();
        testingData.add(new Job(4,9));
        testingData.add(new Job(15, 2));
        testingData.add(new Job(18, 16));
        testingData.add(new Job(20, 3));
        testingData.add(new Job(26, 29));
        testingData.add(new Job(29, 198));
        testingData.add(new Job(35, 7));
        testingData.add(new Job(45, 170));
        testingData.add(new Job(57, 180));
        testingData.add(new Job(83, 178));
        testingData.add(new Job(88, 73));
        testingData.add(new Job(95, 8));
        
        return testingData;
    }
    
    /**
     * Notify all processors in the array that they are done receiving jobs.
     * @param processors 
     */
    public static void notifyAllProcessors(Processor[] processors) {
        for (Processor p: processors) {
            p.notifyReadyToFinish();
        }
    }
}
