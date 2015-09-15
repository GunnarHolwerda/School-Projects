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
public class Statistics {
    
    public static void printStatistics(long[] runTimes, long maxRuntime, long minRuntime) {
        double mean = calculateMean(runTimes);
        double stdDev = calculateStandardDeviation(runTimes, mean);
            System.out.printf("Average turnaround time: %.2f\nMax runtime: %d\nMin runtime: %d\nStdev: %.2f\n\n",
                    mean, maxRuntime, minRuntime, stdDev);
    }
    
    private static double calculateStandardDeviation(long[] array, double mean) {
        int sum = 0;
        
        for (long num: array) {
            sum += Math.pow(((double)num - mean), 2);
        }
        
        return Math.sqrt(sum / array.length);
    }
    
    private static double calculateMean(long[] array) {
        double sum = 0;
        
        for (double num: array) {
            sum += num;
        }
        
        return sum / array.length;
    }
}
