/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package processor;

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
    }
    
    public synchronized void removeJob(Job j) {
        this.jobList.remove(j);
    }

    @Override
    public void run() {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
