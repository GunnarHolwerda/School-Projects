package graphs;

import java.util.ArrayList;

/**
 *
 * @author Brendan
 */
public class AdjMat_Graph {
    
    public int[][] M;   // adjacency matrix
    public ArrayList<Integer>[] L;     //adjacency list
    int numVertices;
    
    public AdjMat_Graph(int n) {
        numVertices = n;
        M = new int[n][n];
        L = new ArrayList[n];
    }
    
    public void convertToAdjacencyList(AdjMat_Graph graph){
        int counter = 0;
        for (int i = 0; i < M.length; i++){
            for (int j = 0; j < M[0].length; j++){
                if (M[i][j] == 1){
                    ArrayList<Integer> tempList = new ArrayList();
                    tempList.add(i);
                    tempList.add(j);
                    L[counter] = tempList;
                    counter++;
                }
            }
        }
    }
    
    public void convertToAdjacencyMatrix(AdjMat_Graph graph){
        for (int i = 0; i < L.length; i++){
            L[i].get(i);
        }
    }
}
