package testpriorityqueue;

import java.util.PriorityQueue;

/**
 *
 * @author Gunnar Holwerda
 */
public class TestPriorityQueue {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        PriorityQueue<Integer> testQ = new PriorityQueue();
        testQ.add(new Integer(10));
        testQ.add(new Integer(26));
        testQ.add(new Integer(7));
        testQ.add(new Integer(12));
        testQ.add(new Integer(40));
        
        while(!testQ.isEmpty()){
            System.out.println("Next element: " + testQ.poll());
        }
    }
}