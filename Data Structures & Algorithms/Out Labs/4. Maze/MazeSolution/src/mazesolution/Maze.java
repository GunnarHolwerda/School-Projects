package mazesolution;


import java.io.FileReader;
import java.util.Scanner;

/**
 *
 * @author Josh & Gunnar
 */
public class Maze {
    private char[][] maze;
    private int startPositionX, startPositionY;

    public Maze(String fileName) {
        try {
            Scanner scanner = new Scanner(new FileReader(fileName));
            int rows = scanner.nextInt();
            int columns = scanner.nextInt();
            maze = new char[rows][columns];
            int counter = -1;
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                char[] row = line.toCharArray();
                for (int x = 0; x < row.length; x++){
                    if (x < columns && counter < rows){
                        maze[counter][x] = row[x];
                    }
                }
                counter++;
//                System.arraycopy(row, 0, maze[counter], 0, row.length);
            }
        } 
        catch (Exception e) {
            System.out.println(e);
            e.printStackTrace();
        }
    }
    
    public void print(){
        for (int i = 0; i < maze.length; i++){
            for (int j = 0; j < maze[0].length; j++) {
                System.out.print(maze[i][j]);
                if (j == maze[0].length - 1){
                    System.out.println();
                }
            }
        }
    }
    
    private int findPositionX(char c){
        for (int x = 0; x < maze.length; x++){
            for (int y = 0; y < maze[0].length; y++){
                if (maze[x][y] == c){
                    return x;
                }
            }
        }
        return 0;
    }
    
    private int findPositionY(char c){
        for (int x = 0; x < maze.length; x++){
            for (int y = 0; y < maze[0].length; y++){
                if (maze[x][y] == c){
                    return y;
                }
            }
        }
        return 0;
    }
    
    public void solve(){
        startPositionX = findPositionX('S');
        startPositionY = findPositionY('S');
        
        if (tReachable()){
            explore(startPositionX, startPositionY);
            setBreadToBlank();
            maze[startPositionX][startPositionY] = 'S';
        }
        else {
            System.out.println("Sorry T is unreachable in that maze...");
        }
    }
    
    public boolean explore(int x, int y){
        if (maze[x][y] == 'T'){
            return true;
        }
        else{
            if (maze[x - 1][y] != '*' && maze[x - 1][y] != 'b'){        //Checks above
                if (maze[x - 1][y] != 'S' && maze[x - 1][y] != 'T'){
                    maze[x - 1][y] = 'b';
                }
                if (explore(x - 1, y)){
                    if(maze[x][y] != 'S'){
                        maze[x][y] = '+';
                    }
                    return true;
                }
            }
            if (maze[x + 1][y] != '*' && maze[x + 1][y] != 'b'){         //Checks below
                if (maze[x + 1][y] != 'S' && maze[x + 1][y] != 'T'){
                    maze[x][y] = 'b';
                }
                if (explore(x + 1, y)){
                    if(maze[x][y] != 'S'){
                        maze[x][y] = '+';
                    }
                    return true;
                }
            }
            if (maze[x][y + 1] != '*' && maze[x][y + 1] != 'b'){        //Checks to the right
                if (maze[x][y + 1] != 'S' && maze[x][y + 1] != 'T'){
                    maze[x][y + 1] = 'b';
                }
                if (explore(x, y + 1)){
                    if(maze[x][y] != 'S'){
                        maze[x][y] = '+';
                    }
                    return true;
                }
            }
            if (maze[x][y - 1] != '*' && maze[x][y - 1] != 'b'){        //Checks to the left
                if (maze[x][y - 1] != 'S' && maze[x][y - 1] != 'T'){
                    maze[x][y - 1] = 'b';
                }
                if (explore(x, y - 1)){
                    if(maze[x][y] != 'S'){
                        maze[x][y] = '+';
                    }
                    return true;
                }  
            }
        }
        return false;
    }
    
    private boolean tReachable(){
        int x = findPositionX('T');
        int y = findPositionY('T');
        int counter = 0;
        
        if (maze[x + 1][y] == '*'){
            counter++;
        }
        if (maze[x - 1][y] == '*'){
            counter++;
        }
        if (maze[x][y + 1] == '*'){
            counter++;
        }
        if (maze[x][y - 1] == '*'){
            counter++;
        }
        
        return (counter < 4);
    }
    
    private void setBreadToBlank(){
        for (int i = 0; i < maze.length; i++){
            for (int j = 0; j < maze[0].length; j++){
                if (maze[i][j] == 'b'){
                    maze[i][j] = ' ';
                }
            }
        }
    }
}
