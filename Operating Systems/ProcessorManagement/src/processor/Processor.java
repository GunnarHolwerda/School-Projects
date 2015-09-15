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
    String name;
    
    // Variables that will be changed while the thread processes
    volatile ArrayList<Job> jobList;
    volatile boolean notified;
    
    Processor (String name) {
        this.jobList = new ArrayList();
        this.name = name;
        this.notified = false;
    }
    
    public synchronized void addJob(Job j) {
        this.jobList.add(j);
    }
    
    public synchronized Job getJob(int index) {
        return this.jobList.get(index);
    }
    
    public synchronized void removeJob(int index) {
        this.jobList.remove(index);
    }
    
    public synchronized void notifyReadyToFinish() {
        this.notified = true;
    }
    
    public synchronized int getJobListSize() {
        return this.jobList.size();
    }

    public void dumpJobList() {
        System.out.println(this.name);
        for (Job j: jobList) {
            System.out.print(j + " ");
        }
        System.out.println();
    }
    
    @Override
    public void run() {
        throw new UnsupportedOperationException("Not supported yet.");
    }
    
    public String getName() {
        return name;
    }
}