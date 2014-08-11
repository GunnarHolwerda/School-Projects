package graphs;

import java.io.FileReader;
import java.util.Scanner;

/**
 *
 * @author Brendan
 */
public class TestGraphs {
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        String fileName = "C:/Users/Gunnar/Desktop/testgraph3.txt";
        try {
            Scanner scanner = new Scanner(new FileReader(fileName));
            int n = scanner.nextInt(); // first line
            AdjMat_Graph graph = new AdjMat_Graph(n);
            while (scanner.hasNextLine()) {
                int a = scanner.nextInt();
                int b = scanner.nextInt();
                graph.M[a][b] = graph.M[b][a] = 1;
                System.out.println("added edge: " + a + " " + b);
            }
        } catch (Exception e) {
            System.out.println(e);
            e.printStackTrace();
        }
    }
}
