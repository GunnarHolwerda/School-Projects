/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package airportsecurity;

/**
 *
 * @author Joshwa Moellenkamp
 */
import java.util.LinkedList;

public class SecuritySimulation {
    static int simulationTime = 500;    //minutes
    static int securityLines = 3;       // initial value
    
    LinkedList<Integer> waitingLine = new LinkedList();

    /**
     * @param args the command line arguments
     */
    public void doSimulation() {
        for (int i = 0; i < simulationTime; i++) {
            int groupSize = (int)((Math.random()*4) + 1);
            int whichQueue;
            
            if (i == 0) {
                waitingLine.add(groupSize);
                whichQueue = 0;
            }
            else if (i == 1) {
                waitingLine.add(groupSize);
                whichQueue = 1;
            }
            else if (i == 2) {
                waitingLine.add(groupSize);
                whichQueue = 2;
            }
            else {
                //LinkedList<Integer> smallestLine = findSmallestLine();
                int whichLine = findShortestLine();
                waitingLine.add(groupSize);
                whichQueue = whichLine;
            }
            
            System.out.println("Minute " + i + ": A group of " + groupSize + " just entered Q" + whichQueue + ".");
            
            if ((i % 2) > 0) {  //this should happen every 2nd minute
                int whichPartyProceeds = (int)(Math.random()*3);      //generate a number between 0 and 2
                waitingLine.poll();  
                
                System.out.println("A group of " + waitingLine.size() + " from Q" + whichPartyProceeds + "proceeded through security.");                   
            }
        }           
    }
    
    public int findShortestLine() {
        int size = 800;
        int howMany;
        int shortestLine = 0;
        
        for (int i = 0; i < 3; i++) {
            howMany = peopleInLine(i);
            if (howMany < size) {
                shortestLine = i;
            }
        }
       
        return shortestLine;   
    }

    public int peopleInLine(int x) {
        int totalPeople = 0;
        for (int j = 0; j < waitingLine.size(); j++) {
            totalPeople += waitingLine.get(j);
        }
        return totalPeople;
    }
    
    public static void main(String[] args) {
        SecuritySimulation simulation = new SecuritySimulation();
        simulation.doSimulation();
    }
}
