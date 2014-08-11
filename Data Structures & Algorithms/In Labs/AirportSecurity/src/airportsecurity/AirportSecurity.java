package airportsecurity;

import java.util.LinkedList;
import java.util.ArrayList;
import java.util.Random;

/**
 *
 * @author Gunnar
 */
public class AirportSecurity {

    static ArrayList<Party> security = new ArrayList();
    
    static LinkedList<Party> Q1 = new LinkedList();
    static LinkedList<Party> Q2 = new LinkedList();
    static LinkedList<Party> Q3 = new LinkedList();
    
    static final int SIMULATION_LENGTH = 500;
    static final int MULTIPLE = 2;
    
    static int securityCounter = 0;
    static int totalNumberOfPeopleInQs = 0;
    static int maxNumOfPeopleInQ = 0;
    
    private static int countPeople(LinkedList<Party> Q){
        int numOfPeople = 0;
        for (int i = 0; i < Q.size(); i++){
            numOfPeople += Q.get(i).getNumOfMembers();
        }
        return numOfPeople;
    }
    
    public static void checkMaxQ(){
        int peopleQ1 = countPeople(Q1);
        int peopleQ2 = countPeople(Q2);
        int peopleQ3 = countPeople(Q3);
        
        if(peopleQ1 > peopleQ2 && peopleQ1 > peopleQ3 && peopleQ1 > maxNumOfPeopleInQ){
            maxNumOfPeopleInQ = peopleQ1;
        }
        else if(peopleQ2 > peopleQ1 && peopleQ2 > peopleQ3 && peopleQ2 > maxNumOfPeopleInQ){
            maxNumOfPeopleInQ = peopleQ2;
        }
        else if(peopleQ3 > peopleQ2 && peopleQ3 > peopleQ1 && peopleQ3 > maxNumOfPeopleInQ){
            maxNumOfPeopleInQ = peopleQ3;
        }
    }
    
    public static void addParty(int t){
            Party newParty = new Party((int)(Math.random() * 4 + 1));
            if (countPeople(Q1) <= countPeople(Q2) && countPeople(Q1) <= countPeople(Q3)){
                Q1.add(newParty);
                System.out.println("Minute " + t + ": A party of " + newParty.getNumOfMembers()
                                    + " just entered Q1");
            }
            else if(countPeople(Q2) <= countPeople(Q1) && countPeople(Q2) <= countPeople(Q3)){
                Q2.add(newParty);
                System.out.println("Minute " + t + ": A party of " + newParty.getNumOfMembers()
                                    + " just entered Q2");
            }
            else {
                Q3.add(newParty);
                System.out.println("Minute " + t + ": A party of " + newParty.getNumOfMembers()
                                    + " just entered Q3.");
            }
            totalNumberOfPeopleInQs += newParty.getNumOfMembers();
            
            
    }
    
    public static void proceedThroughSecurity(){
            switch((int)(Math.random() * 3)){
                case 0:
                    if (!Q1.isEmpty()){
                        security.add(Q1.poll());
                        System.out.println("A party of " + security.get(securityCounter).getNumOfMembers() 
                                            + " from queue Q1 proceeded through security.");
                        securityCounter++;
                    }
                    break;
                case 1:
                    if (!Q2.isEmpty()){
                        security.add(Q2.poll());
                        System.out.println("A party of " + security.get(securityCounter).getNumOfMembers() 
                                            + " from queue Q2 proceeded through security.");
                        securityCounter++;
                    }
                    break;
                case 2:
                    if(!Q3.isEmpty()){
                        security.add(Q3.poll());
                        System.out.println("A party of " + security.get(securityCounter).getNumOfMembers() 
                                            + " from queue Q3 proceeded through security.");
                        securityCounter++;
                    }
                    break;
                default:
                    System.out.println("Something went wrong...");
            }
    }
    
    public static void main(String[] args) {
        for (int t = 1; t < SIMULATION_LENGTH + 1; t++){
            addParty(t);
            
            if (t % MULTIPLE == 0){
                proceedThroughSecurity();
            }
            checkMaxQ();
        }
        System.out.println("\n\nThe average number of people in a Q was: " + (totalNumberOfPeopleInQs / 3));
        System.out.println("The maximum number of people in a Q at one time was: " + maxNumOfPeopleInQ);
    }
}
