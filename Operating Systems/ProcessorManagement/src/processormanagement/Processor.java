/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package processormanagement;

import java.util.ArrayList;

/**
 *
 * @author Gunnar
 */
public class Processor implements Runnable{
    ArrayList<Job> jobList;
    String name;
    
    Processor (String name) {
        this.jobList = new ArrayList();
        this.name = name;
    }
    
    public synchronized void addJob(Job j) {
        this.jobList.add(j);
        //System.out.println(name + " now contains " + this.jobList.size() + " jobs");
    }
    
    public synchronized void removeJob(Job j) {
        this.jobList.remove(j);
        //System.out.println(name + " now contains " + this.jobList.size() + " jobs");
    }

    @Override
    public void run() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
