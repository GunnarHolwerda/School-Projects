package mazesolution;

import java.util.Scanner;

/**
 *
 * @author Josh & Gunnar
 */
public class Driver {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here      
        Scanner in = new Scanner (System.in);
        
        System.out.print("Enter the location of the maze you wish to use " + 
                         "(use forward slashes instead of backslashes): ");
        String location = in.next();      
        
        Maze maze = new Maze(location);
        maze.print();
        maze.solve();
        maze.print();
    }
}
