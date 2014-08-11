/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Brendan
 */


import java.io.FileReader;
import java.io.IOException;
import java.util.Deque;
import java.util.ArrayDeque;
import java.util.Scanner;

/** Program to solve a maze represented as a graph.
 *  This program performs a breadth-first search of the graph
 *  to find the "shortest" path from the start vertex to the
 *  end. It is assumed that the start vertex is 0, and the
 *  end vertex is numV-1.
 *  @author Koffman and Wolfgang
 */
public class TestDijkstra {

    /** Main method to solve the maze.
     *  pre: args[0] contains the name of the input file.
     *  @param args Command line argument
     */
    public static void main(String[] args) {
        int numV = 0; // The number of vertices.
        Graph theGraph = null;
        // Load the graph data from a file.
        try {
            Scanner scan = new Scanner(new FileReader("C:\\Users\\Gunnar\\Desktop\\testgraph3.txt"));
            theGraph = AbstractGraph.createGraph(scan, false, "Matrix");
            numV = theGraph.getNumV();
        } catch (IOException ex) {
            System.err.println("IO Error while reading graph");
            System.err.println(ex.toString());
            System.exit(1);
        }
        // Perform Dijkstra's algorithm:
        int start = 0; // starting  vertex
        int parent[] = new int[numV];
        double dist[] = new double[numV];
        DijkstrasAlgorithm.dijkstrasAlgorithm(theGraph, start, parent, dist);

        int destination = theGraph.getNumV() - 1;

        // Construct the path.
        Deque<Integer> thePath = new ArrayDeque<Integer>();
        int v = destination;
        while (parent[v] != -1) {
            thePath.push(new Integer(v));
            v = parent[v];
        }
        // Output the path.
        System.out.println("The Shortest path is:");
        while (!thePath.isEmpty()) {
            System.out.println(thePath.pop());
        }
        System.out.println("The path distance is: " + dist[destination]);
    }
}
/*</listing>*/

