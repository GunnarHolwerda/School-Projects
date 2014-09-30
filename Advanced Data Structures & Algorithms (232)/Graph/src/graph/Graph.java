package graph;

import java.util.*;
import java.io.*;

/**
 *
 * @author Gunnar
 */
public class Graph {
    private int numV;
    private int[][] matrix;
    private int[] visited;
    
    public int[][] getMatrix(){
        return matrix;
    }
    
    public Graph(int numOfVertices){
        numV = numOfVertices;
        matrix = new int[numV][numV];
        visited = new int[numV];
        resetVisited();
    }
    
    private void resetVisited(){
        for (int i = 0; i < visited.length; i++){
            visited[i] = 99;
        }
    }
    
    public void inputMatrixFromFile() throws FileNotFoundException{
        Scanner in = new Scanner(new FileReader("./src/graph/matrix.txt"));
        String currentLine = in.nextLine();
        
        for (int i = 0; i < matrix.length; i++){
            for (int j = 0; j < matrix[i].length; j++){
                matrix[i][j] = Character.getNumericValue(currentLine.charAt(j));
            }
            if (in.hasNextLine()){
                currentLine = in.nextLine();
            }
        }
    }
    
    public void printMatrix(int matrix[][]){
        for (int i = 0; i < matrix.length; i++){
            for (int j = 0; j < matrix[i].length; j++){
                System.out.print(matrix[i][j]);
            }
            System.out.println();
        }
    }
    
    public void breadthFirstSearch(){
        ArrayList<Integer> queue = new ArrayList();
        queue.add(0);
        int currentNum = 0;
        
        while(!queue.isEmpty()){
            int u = queue.get(0);
            queue.remove(0);
            if (!isIn(visited, u)){
                visited[currentNum] = u;
                currentNum++;
            }
            for (int i = 0; i < matrix[u].length; i++){
                if (matrix[u][i] == 1){
                    if (!isIn(visited, i)){
                        queue.add(i);
                    }
                }
            }
        } 
        printArray(visited);
        resetVisited();
    }
    
    public void depthFirstSearch(int currentVertex){
        visited[currentVertex] = currentVertex;
        
        for (int i = 0; i < matrix[currentVertex].length; i++){
            if (matrix[currentVertex][i] == 1 && !isIn(visited, i)){
                //System.out.println("GOING IN " + i + " from " + currentVertex);
                depthFirstSearch(i);
            }
        }
        System.out.print(currentVertex + " ");
    }
    
    private void printArray(int[] array){
        for(int i = 0; i < array.length; i++){
            System.out.print(array[i] + " ");
        }
        System.out.println();
    }
    
    private boolean isIn(int[] array, int value){
        for (int i = 0; i < array.length; i++){
            if (value == array[i]){
                return true;
            }
        }
        return false;
    }
    
    public void djikstra(int start){
        //Array to hold the shortest path
        int shortestPath[] = new int[numV];
        
        //Array holding vertices that have been visited
        boolean visited[] = new boolean[numV];
        
        //Setting the arrays up properly
        for (int i = 0; i < numV; i++){
            shortestPath[i] = 999;
            visited[i] = false;
        }
        
        //Path to self is 0
        shortestPath[start] = 0;
        
        //Find minimum distances
        for (int i = 0; i < numV -1; i++){
            int num = findMinDistance(shortestPath, visited);
            visited[num] = true;
            
            for (int j = 0; j < numV; j++){
                //If the vertex isn't visited, has an edge, has a path, and the shortestPath + edge from source < shortestpath to the current vertex
                //we set its shortest path
                if (!visited[j] && (matrix[num][j] > 0) && (shortestPath[num] != 999) && (shortestPath[num] + matrix[num][j] < shortestPath[j])){
                    shortestPath[j] = shortestPath[num] + matrix[num][j];
                }
            }
        }
        
        //Print
        for (int i = 0; i < numV; i++){
            System.out.printf("Destination %d distance from %d: %d\n", i, start, shortestPath[i]);
        }
    }
    
    private int findMinDistance(int shortestPath[], boolean visited[]){
        int minimum = 999;
        int minimum_index = 0;
        
        for (int i = 0; i < numV; i++){
            if (visited[i] == false && shortestPath[i] <= minimum){
                minimum = shortestPath[i];
                minimum_index = i;
            }
        }
        
        return minimum_index;
    }

    public void prim(){
        int parent[] = new int[numV];
        int key[] = new int[numV];
        boolean MST[] = new boolean[numV];
        
        for (int i = 0; i < numV; i++){
            key[i] = 999;
            MST[i] = false;
        }
        
        key[0] = 0;
        parent[0] = -1;
        
        for (int i = 0; i < numV - 1; i++){
            int num = findMinimumKey(key, MST);
            
            MST[num] = true;
            
            for (int vertex = 0; vertex < numV; vertex++){
                if (matrix[num][vertex] > 0 && MST[vertex] == false && matrix[num][vertex] < key[vertex]){
                    parent[vertex] = num;
                    key[vertex] = matrix[num][vertex];
                }
            }
        }
        
        //Print
        for (int i = 1; i < numV; i++){
            System.out.printf("Edge %d -> %d Weight: %d\n", parent[i], i, matrix[i][parent[i]]);
        }
        
    }
    
    private int findMinimumKey(int key[], boolean MST[]){
        int minimum = 999;
        int minimum_index = 0;
        
        for (int vertex = 0; vertex < numV; vertex++){
            if (MST[vertex] == false && key[vertex] < minimum){
                minimum = key[vertex];
                minimum_index = vertex;
            }
        }
        
        return minimum_index;
    }
    
    public void shortestPath(){
        int shortestPathMatrix[][] = new int[numV][numV];
        
        for(int i = 0; i < numV; i++){
            for (int j = 0; j < numV; j++){
                shortestPathMatrix[i][j] = matrix[i][j];
            }
        }
        
        for(int i = 0; i < numV; i++){
            for (int k = 0; k < numV; k++){
                for (int j = 0; j < numV; j++){
                    if (k != j){
                        if(shortestPathMatrix[k][j] == 0){
                            shortestPathMatrix[k][j] = shortestPathMatrix[k][i] + shortestPathMatrix[i][j];
                        }
                        if (shortestPathMatrix[k][j] > shortestPathMatrix[k][i] + shortestPathMatrix[i][j]){
                            shortestPathMatrix[k][j] = shortestPathMatrix[k][i] + shortestPathMatrix[i][j];
                        }
                    }
                }
            }
        }
        
        printMatrix(shortestPathMatrix);
    }
    
    public void reachabilityMatrix(){
        int reachabilityMatrix[][] = new int[numV][numV];
        
        for(int i = 0; i < numV; i++){
            for (int j = 0; j < numV; j++){
                reachabilityMatrix[i][j] = matrix[i][j];
            }
        }
        
        for(int i = 0; i < numV; i++){
            for (int k = 0; k < numV; k++){
                for (int j = 0; j < numV; j++){
                    if (k != j){
                        if (reachabilityMatrix[k][j] == 0){
                            reachabilityMatrix[k][j] = reachabilityMatrix[k][i] & reachabilityMatrix[i][j];
                        }
                    }
                }
            }
        }
        
        printMatrix(reachabilityMatrix);
    }
}
