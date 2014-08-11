package graphdemo;

import java.util.LinkedList;

/**
 *
 * @author Gunnar Holwerda
 */
public class AdjacencyMatrix_Graph {
    public int[][] M;   //adjacency matrix
    public int numVertices;
    public Vertex[] vertex;
    
    public AdjacencyMatrix_Graph(int v){
        numVertices = v;
        M = new int[v][v];
        vertex = new Vertex[v];
        for (int i = 0; i < numVertices; i++){
            vertex[i] = new Vertex("Vertex " + i, i);
        }
    }
    
    public int getNumVertices(){
        return numVertices;
    }
    
    public void printGraph(){
        System.out.println("Graph: ");
        System.out.println("Number of vertices: " + numVertices);
        System.out.println("Vertices: ");
        for (int i = 0; i < numVertices; i ++){
            System.out.print(" " + vertex[i]);
        }
        System.out.println();
        System.out.append("Edges: ");
        for (int i = 0; i < M.length; i++){
            for (int j = 0; j < M[i].length; j++){
                if (M[i][j] == 1){
                    System.out.print(" (" + vertex[i] +", " + vertex[j] + ")");
                }
            }
        }
        System.out.println();
    }
    
    public void breadthFirstSearch(int source){
        for (int i =0 ; i < numVertices; i++){
            vertex[i].setVisited(false);
            vertex[i].setPrevious(null);
        }
        
        LinkedList<Vertex> queue = new LinkedList();
        queue.offer(vertex[source]);
        vertex[source].setVisited(true);
        
        while(!queue.isEmpty()){
            Vertex next = queue.poll();
            int nextIndex = next.getIndex();
            
            for (int j = 0; j < numVertices; j++){
                if (M[nextIndex][j] > 0 && !vertex[j].getVisited()){
                    queue.offer(vertex[j]);
                    vertex[j].setVisited(true);
                    //System.out.println("Visited: " + vertex[j]);
                    vertex[j].setPrevious(next);
                }
            }
        }
    }
}
