/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package graph;

/**
 *
 * @author Gunnar
 */
public class Driver {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Graph g = new Graph(5);
        try{
            g.inputMatrixFromFile();
        }
        catch(Exception e){
            System.out.println("File was not found.");
        }
        g.printMatrix(g.getMatrix());
        //g.breadthFirstSearch();
        //g.depthFirstSearch(0);
        System.out.println("Djikstra....");
        g.djikstra(0);
        System.out.println("\nPrim.....");
        g.prim();
        System.out.println("\nShortest Path Matrix.....");
        g.shortestPath();
        System.out.print("\nReachability Matrix......\n");
        g.reachabilityMatrix();
    }
    
}
