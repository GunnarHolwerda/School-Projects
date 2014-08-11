package printqueuesimulation;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Random;

/**
 *
 * @author Gunnar Holwerda
 */
public class PrintQueueSimulation {
    static final double ARRIVAL_PROBABILITY = 0.1;
    static final int UPDATE_RATE = 10;              //seconds
    static final int SECONDS_PER_PAGE = 5;          //seconds
    static final int MAXIMUM_SIZE = 100;            //pages
    static final int SIMULATION_TIME = 36000;       //seconds
    static int NUMBER_OF_PRINTERS = 1;        //printers
    static final int THRESHOLD = 600;
    
    static Random randomGenerator; 
    
    public static int doSimulation(){
        //print queue
        LinkedList<PrintJob>[] printQ = new LinkedList[NUMBER_OF_PRINTERS];
        
        //list of jobs
        ArrayList<PrintJob> jobList = new ArrayList();
        
        
        for (int i = 0; i < NUMBER_OF_PRINTERS; i++){
            printQ[i] = new LinkedList();
        }
        
        //simulation loop
        int[] nextFreeTime = new int[NUMBER_OF_PRINTERS];
        for (int i = 0; i < NUMBER_OF_PRINTERS; i++){
            nextFreeTime[i] = 0;
        }
        boolean done = false;
        int t = 0;
        while (!done){
            
            //check to add jobs to the queue
            if (t % UPDATE_RATE == 0 && t < SIMULATION_TIME){
                if(randomGenerator.nextDouble() < ARRIVAL_PROBABILITY){
                    //creates a new print job
                    PrintJob newJob = new PrintJob(randomGenerator.nextInt(MAXIMUM_SIZE) + 1);
                    newJob.arrivalTime = t;
                    int randQ = randomGenerator.nextInt(NUMBER_OF_PRINTERS);
                    printQ[randQ].offer(newJob);
                    jobList.add(newJob);
                }
            }
            done = (t > SIMULATION_TIME);
            for (int i = 0; i < NUMBER_OF_PRINTERS; i++){
                if (t >= nextFreeTime[i] && !printQ[i].isEmpty()){
                    //print the next job
                    PrintJob nextJob = printQ[i].poll();
                    int printTime = nextJob.numOfPages * SECONDS_PER_PAGE;
                    nextJob.finishTime = t + printTime;
                    nextFreeTime[i] = t + printTime + 1;
                }
                //checking to see if all of the printQ's are empty
                done =  (done && printQ[i].isEmpty());
            }
            t++;
        }
        
        //simulation complete, look at the stats
        int longestWaitTime = 0;
        PrintJob longJob = null;
        double averageWaitTime = 0;
        Iterator<PrintJob> jobIter = jobList.iterator();
        
        while(jobIter.hasNext()){
            PrintJob nextJob = jobIter.next();
            int waitTime = nextJob.finishTime - nextJob.arrivalTime - nextJob.numOfPages * SECONDS_PER_PAGE;
            averageWaitTime += waitTime;
            if (waitTime > longestWaitTime){
                longestWaitTime = waitTime;
                longJob = nextJob;
            }
        }
        averageWaitTime /= jobList.size();
        System.out.println("Number of printers =  " + NUMBER_OF_PRINTERS);
        System.out.println("Longest wait time = " + longestWaitTime);   
        System.out.format("%s%.2f\n\n","The average wait time = ", averageWaitTime);
        
        return longestWaitTime;
    }
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        randomGenerator = new Random();
        NUMBER_OF_PRINTERS = 1;
        while (doSimulation() > THRESHOLD){
            NUMBER_OF_PRINTERS++;
        }
        System.out.println("The system reccomends that you buy: " + NUMBER_OF_PRINTERS + " printers...");
    }
}
