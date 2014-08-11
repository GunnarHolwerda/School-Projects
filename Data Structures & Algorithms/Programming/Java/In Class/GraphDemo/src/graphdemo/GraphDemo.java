package graphdemo;

import java.io.FileReader;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Scanner;

/**
 *
 * @author Gunnar Holwerda
 */
public class GraphDemo {

    
    public static void printShortestPath(AdjacencyMatrix_Graph graph, int i){
        Vertex currentVertex = graph.vertex[i];
        LinkedList<Vertex> pathList = new LinkedList();
        System.out.println("Shortest path to vertex " + currentVertex + ": ");
        while (currentVertex != null){
            pathList.addFirst(currentVertex);
            currentVertex = currentVertex.getPrevious();
        }
        Iterator<Vertex> pathIter = pathList.iterator();
        while (pathIter.hasNext()){
            System.out.print(pathIter.next() + "->");
        }
        System.out.println("END");
        System.out.println();
    }
    
    public static void printShortestPaths(AdjacencyMatrix_Graph graph){
        for (int i = 0; i < graph.getNumVertices(); i++){
            printShortestPath(graph, i);
        }
    }
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        AdjacencyMatrix_Graph graph = new AdjacencyMatrix_Graph(0);
        String fileName = "C:/Users/Gunnar Holwerda/Desktop/graph.txt";
        
        try{
            Scanner scanner = new Scanner(new FileReader(fileName));
            int n = scanner.nextInt();
            graph = new AdjacencyMatrix_Graph(n);
            while(scanner.hasNextLine()){
                int a = scanner.nextInt();
                int b = scanner.nextInt();
                graph.M[a][b] = graph.M[b][a] = 1;
                //System.out.println("Added edge: " + a + " " + b);
            }
        }
        catch(Exception e){
            System.out.println(e);
        }
        
        graph.printGraph();
        graph.breadthFirstSearch(0);
        printShortestPaths(graph);
    }
}
