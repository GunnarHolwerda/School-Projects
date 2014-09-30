import java.util.Random;
import java.util.Scanner;
import java.io.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.*;
import javax.swing.*;
/**
 *
 * @author hunterl
 */
public class Maze extends JFrame {

    private static final int MAX_WIDTH = 255;
    private static final int MAX_HEIGHT = 255;
    
    private char [][] maze = new char[MAX_HEIGHT][MAX_WIDTH];

    private Random random = new Random();
    private JPanel mazePanel = new JPanel();
    private int width = 0;
    private int height = 0;
    private boolean step = false;
    
    private boolean timerFired = false;
    private Timer timer;
    private final int TIMER_DELAY = 200;
    
    private final int SPRITE_WIDTH = 25;
    private final int SPRITE_HEIGHT = 25;
    
    private BufferedImage mazeImage;
    private ImageIcon ground = new ImageIcon("sprites/ground.png");
    private ImageIcon wall1 = new ImageIcon("sprites/cactus.png");
    private ImageIcon wall2 = new ImageIcon("sprites/rock.png");
    private ImageIcon finish = new ImageIcon("sprites/well.png");
    private ImageIcon south1 = new ImageIcon("sprites/cowboy-forward-1.png");
    private ImageIcon south2 = new ImageIcon("sprites/cowboy-forward-2.png");
    private ImageIcon north1 = new ImageIcon("sprites/cowboy-back-1.png");
    private ImageIcon north2 = new ImageIcon("sprites/cowboy-back-2.png");
    private ImageIcon west1 = new ImageIcon("sprites/cowboy-left-1.png");
    private ImageIcon west2 = new ImageIcon("sprites/cowboy-left-2.png");
    private ImageIcon east1 = new ImageIcon("sprites/cowboy-right-1.png");
    private ImageIcon east2 = new ImageIcon("sprites/cowboy-right-2.png");
    
    private long startTime;
    private long currentTime;
    
    File saveFile = new File("saveFile.txt");;
    FileWriter fw = null;
    BufferedWriter bw;
    
    
    /**
     * Constructor for class Maze.  Opens a text file containing the maze, then attempts to 
     * solve it.
     * 
     * @param   fname   String value containing the filename of the maze to open.
     */
    public Maze(String fname) {        
        openMaze(fname);
        mazeImage = printMaze();

        timer = new Timer(TIMER_DELAY, new TimerHandler());     // setup a Timer to slow the animation down.
        timer.start();
        
        
        addWindowListener(new WindowHandler());     // listen for window event windowClosing
        
        setTitle("Cowboy Maze");
        setSize(width*SPRITE_WIDTH+10, height*SPRITE_HEIGHT+30);
        setVisible(true);

        add(mazePanel);
        setContentPane(mazePanel);
        
        solveMaze();
    }
    
    /**
     * Called from the operating system.  If no command line arguments are supplied,
     * the method displays an error message and exits.  Otherwise, a new instace of
     * Maze() is created with the supplied filename from the command line.
     * 
     * @param   args[]  Command line arguments, the first of which should be the filename to open.
     */
    public static void main(String [] args) {
        int runny = 1;
        if (args.length > 0) {
            new Maze(args[0]);
        }
        else {
            System.out.println();
            System.out.println("Usage: java Maze <filename>.");
            System.out.println("Maximum Maze size:" + MAX_WIDTH + " x " + MAX_HEIGHT + ".");
            System.out.println();
            System.exit(1);
        }
    }
    
    /**
     * Finds the starting location and passes it to the recursive algorithm solve(x, y, facing).
     * The starting location should be the only '.' on the outer wall of the maze.
     */
    public void solveMaze() {
        boolean startFound = false;
        if (!startFound) {
            for (int i=0; i<width; i++) {       // look for the starting location on the top and bottom walls of the Maze.
                if (maze[0][i] == '.') {
                    preSolve(i, 0, "south");
                    startFound = true;
                }
                else if (maze[height-1][i] == '.') {
                    preSolve(i, height-1, "north");
                    startFound = true;
                }
            }
        }
        if (!startFound) {
            for (int i=0; i<height; i++) {      // look for the starting location on the left and right walls of the Maze.
                if (maze[i][0] == '.') {
                    preSolve(0, i, "east");
                    startFound = true;
                }
                else if (maze[i][width-1] == '.') {
                    preSolve(width-1, i, "west");
                    startFound = true;
                }
            }
        }
        if (!startFound) {
            System.out.println("Start not found!");
        }        
    }
    
    
    public void preSolve(int x, int y, String facing)
    {
        //Graphics2D g2 = (Graphics2D)mazePanel.getGraphics();
        //g2.drawImage(mazeImage, null, 0, 0);
        //g2.drawImage(printGuy(facing), x*SPRITE_WIDTH, y*SPRITE_HEIGHT, null, null);
        Scanner input = new Scanner(System.in);
        System.out.println("Press 1 to start");
        input.nextLine();
        startTime = System.currentTimeMillis();
        
        /*
        //Fill  solution array with blankness
        for (int i = 0; i < maze.length; i++){
            for (int j = 0; j < maze[0].length; j++){
                maze[i][j] = '.';
            }
        }*/
        
        //Import solution data from file
        importSave();       
        
        for (int i = 0; i < 20; i++){
            for (int j = 0; j < 20; j++){
                System.out.print(maze[i][j]);
            }
            System.out.println();
        }
        
        try {
            fw = new FileWriter(saveFile, true);
        }
        catch(IOException e){
            System.out.println("File Writer doesn't work");
        }
        bw = new BufferedWriter(fw);
        
        solve(x, y, facing);
    }
    
    private void importSave(){
        try {
            if (!saveFile.exists()){
                saveFile.createNewFile();
                System.out.println("Save file created.");
            }
            else{
                System.out.println("Found save file");
            }
            
            Scanner in = new Scanner(saveFile);
            
            while(in.hasNextLine()){
                int yCoord = in.nextInt();
                int xCoord = in.nextInt();
                char direction = in.next().charAt(0);
                
                //System.out.printf("Putting %c at [%d][%d]\n", direction, yCoord, xCoord);
                
                //Set the node to a direction
                maze[yCoord][xCoord] = direction;
            } 
        }
        catch (IOException e){
            System.out.println("File could not be created.");
        }
    }
    
    private void writeOut(int y, int x, char direction){
        String output = Integer.toString(y) + " " + Integer.toString(x) + " " + Character.toString(direction);
        try {
            bw.newLine();
            bw.write(output);
        }
        catch(IOException e){
            System.out.println("Writing to file did not work.");
        }
    }
    
    private int numPaths(int x, int y){
        int paths = 0;
        if (x != 0 && y != 0){
            if (maze[y + 1][x] != '#' && maze[y + 1][x] != '%'){ //Check north for path
                paths++;
            }
            if(maze[y - 1][x] != '#' && maze[y - 1][x] != '%'){ //Check south for path
                paths++;
            }
            if(maze[y][x + 1] != '#' && maze[y][x + 1] != '%'){ //Check east for path
                paths++;
            }
            if(maze[y][x - 1] != '#' && maze[y][x - 1] != '%'){ //Check west for path
                paths++;
            }
        }
        
        return paths;
    }
    
    private void setPrevious(int x, int y, String facing, char character){
        switch(facing){
            case "north":
                //System.out.println("Set " + ( y + 1) + " " + x + " to V");
                maze[y + 1][x] = character;
                writeOut(y + 1, x, character);
                break;
            case "south":
                //System.out.println("Set " + ( y - 1) + " " + x + " to V");
                maze[y - 1][x] = character;
                writeOut(y - 1, x, character);
                break;
            case "west":
                //System.out.println("Set " + y + " " + (x + 1) + " to V");
                maze[y][x + 1] = character;
                writeOut(y, x + 1, character);
                break;
            case "east":
                //System.out.println("Set " + y + " " + (x - 1) + " to V");
                maze[y][x - 1] = character;
                writeOut(y, x - 1, character);
                break;
            default:
                System.out.println("Set previous visited is not working properyly");
        }
    }
    
    private void setForward(int x, int y, String facing){
        switch(facing){
            case "north":
                maze[y - 1][x] = 'R';
                writeOut(y - 1, x, 'R');
                break;
            case "south":
                maze[y + 1][x] = 'R';
                writeOut(y + 1, x, 'R');
                break;
            case "west":
                maze[y][x - 1] = 'R';
                writeOut(y, x - 1, 'R');
                break;
            case "east":
                maze[y][x + 1] = 'R';
                writeOut(y, x + 1, 'R');
                break;
            default:
                System.out.println("Set Forward visited is not working properyly");
        }
    }
    
    private int numFreePaths(int x, int y){
        int paths = 0;
        if (x != 0 && y != 0){
            if (maze[y + 1][x] != '#' && maze[y + 1][x] != '%' && maze[y + 1][x] != 'V' && maze[y + 1][x] != 'R'){ //Check north for path
                paths++;
            }
            if(maze[y - 1][x] != '#' && maze[y - 1][x] != '%' && maze[y - 1][x] != 'V' && maze[y - 1][x] != 'R'){ //Check south for path
                paths++;
            }
            if(maze[y][x + 1] != '#' && maze[y][x + 1] != '%' && maze[y][x + 1] != 'V' && maze[y][x + 1] != 'R'){ //Check east for path
                paths++;
            }
            if(maze[y][x - 1] != '#' && maze[y][x - 1] != '%' && maze[y][x - 1] != 'V' && maze[y][x - 1] != 'R'){ //Check west for path
                paths++;
            }
        }
        
        return paths;
    }
    
    private boolean thereIsRightPath(int x, int y, char character){
        if (x != 0 && y != 0){
            if (maze[y + 1][x] == character){ //Check north for path
                return true;
            }
            if(maze[y - 1][x] == character){ //Check south for path
                return true;
            }
            if(maze[y][x + 1] == character){ //Check east for path
                return true;
            }
            if(maze[y][x - 1] == character){ //Check west for path
                return true;
            }
            return false;
        }
        else 
            return false;
    }
    
    private void takePath(int x, int y, String facing, char character){
        if (maze[y][x + 1] == character){
            maze[y][x] = 'E';
            writeOut(y, x, 'E');
            solve(x + 1, y, "east");
        }
        else if (maze[y][x - 1] == character){
            maze[y][x] = 'W';
            writeOut(y, x, 'W');
            solve(x - 1, y, "west");
        }
        else if (maze[y + 1][x] == character){
            maze[y][x] = 'S';
            writeOut(y, x, 'S');
            solve(x, y + 1, "south");
        }
        else if (maze[y - 1][x] == character){
            maze[y][x] = 'N';
            writeOut(y, x, 'N');
            solve(x, y - 1, "north");
        }
        else {
            System.out.println("There is no path with that character.");
        }
    }
   
    /**
     * Recursive algorithm to solve a Maze.  Places a X at locations already visited.
     * This algorithm is very inefficient, it follows the right hand wall and will
     * never find the end if the path leads it in a circle.
     * 
     * @param   x       int value of the current X location in the Maze.
     * @param   y       int value of the current Y location in the Maze.
     * @param   facing  String value holding one of four cardinal directions 
     *                  determined by the current direction facing.
     */
    private void solve(int x, int y, String facing) {
        Graphics2D g2 = (Graphics2D)mazePanel.getGraphics(); //don't mess with the next 
 
        while (!timerFired) {   // wait for the timer.
          try{Thread.sleep(10);} catch(Exception e){}
        }
        timerFired = false;
        currentTime = System.currentTimeMillis();
        if((currentTime - startTime) > 50000)
        {
            try {
                bw.close();
            }
            catch(IOException e){}
            closingMethod();
        }
        
        //Do not mess with the above part of this method
        
        //Below is where you put your solution to solving the maze. 
        
        if (maze[y][x] != 'F') {  //this is if it doesn't find the finish on a turn.........
            g2.drawImage(mazeImage, null, 0, 0); 
            g2.drawImage(printGuy(facing), x*SPRITE_WIDTH, y*SPRITE_HEIGHT, null, null);
            mazePanel.setSize(width*SPRITE_WIDTH+10, height*SPRITE_HEIGHT+30);
            //maze[y][x] = 'X';   // mark this spot as visited. This is how you can keep track of where you've been.            
            
            if (numPaths(x, y) >= 3){           //This position will become a pivot                           
                if (maze[y][x] == '.'){  //New pivot
                    System.out.println("Pivot created.");
                    //Create direction for pivot by telling rightHandRule it is a pivot so it outputs a direction
                    rightHandRule(y, x, facing, true);
                }
                else {  //Current space already has a direction
                    //Back at a pivot, whatever we were just doing was not good set that to off limits
                    setPrevious(x, y, facing, 'V');
                     if (numFreePaths(x, y) <= 1 || thereIsRightPath(x, y, 'R')){
                         if (returnDirection(maze[y][x]).equals("east") && facing.equals("west") ||
                             returnDirection(maze[y][x]).equals("west") && facing.equals("east") ||
                             returnDirection(maze[y][x]).equals("north") && facing.equals("south")||
                             returnDirection(maze[y][x]).equals("south") && facing.equals("north")){
                             rightHandRule(y, x, facing, true);
                         }
                         if (returnDirection(maze[y][x]).equals("north")){
                             if (thereIsRightPath(x, y, '.')){  //Check to see if there is an unexplored path
                                 takePath(x, y, facing, '.');
                             }
                             else { //Go the way with the R
                                solve(x, y - 1, "north");
                             }
                         }
                         else if (returnDirection(maze[y][x]).equals("south")){
                             if (thereIsRightPath(x, y, '.')){
                                 takePath(x, y, facing, '.');
                             }
                             else {
                                solve(x, y + 1, "south");
                             }
                         }
                         else if (returnDirection(maze[y][x]).equals("east")){
                             if (thereIsRightPath(x, y, '.')){
                                 takePath(x, y, facing, '.');
                             }
                             else {
                                 solve(x + 1, y, "east");
                             }
                         }
                         else {
                             if (thereIsRightPath(x, y, '.')){
                                 takePath(x, y, facing, '.');
                             }
                             else {
                                solve(x - 1, y, "west");
                             }
                         }    
                     }
                     else {
                        rightHandRule(y, x, facing, true);
                     }
                } 
            }
            else{
                    rightHandRule(y, x, facing, false);
            }
        }
        else {
            System.out.println("Found the finish!");
            
            try {
                bw.close();
            }
            catch(IOException e){}
            
            //don't mess with the following 4 lines, but you can add stuff below that if you need. 
            currentTime = System.currentTimeMillis();
            long endTime = currentTime - startTime;
            long finalTime = endTime / 1000;
            System.out.println("Final Time = " + finalTime);
            closingMethod();
            
        }        
    }
  
    private void rightHandRule(int y, int x, String facing, boolean pivot){
        //Right hand rule
        if (facing.equals("east")) {    // if guy is facing east .......you will have four cases, east, west, south, north
            if (maze[y + 1][x] != '#' && maze[y + 1][x] != '%'){
                //Set maze to S at this location to indicate we turned South
                if (pivot){
                    maze[y][x] = 'S';
                    setForward(x, y, "south");
                    setPrevious(x, y, facing, 'V');
                    writeOut(y, x, 'S');
                }
                solve(x, y + 1, "south");
            }
            else {
                //Right hand is still on a wall
                if (maze[y][x + 1] != '#' && maze[y][x + 1] != '%'){
                    //Move straight if nothing is in the way
                    if (pivot){
                        maze[y][x] = 'E';
                        setForward(x, y, "east");
                        setPrevious(x, y, facing, 'V');
                        writeOut(y, x, 'E');
                    }
                    solve(x + 1, y, "east");
                }
                else if (maze[y - 1][x] != '#' && maze[y - 1][x] != '%'){
                    //Wall in front, check north, if no wall we move north
                    if (pivot){
                        maze[y][x] = 'N';
                        setForward(x, y, "north");
                        setPrevious(x, y, facing, 'V');
                        writeOut(y, x, 'N');
                    }
                    solve(x, y - 1, "north");
                }
                else{
                    //We are boxed in and need to turn around
                    solve(x - 1, y, "west");
                }
            }

        }  
        else if (facing.equals("west")){
            if (maze[y - 1][x] != '#' && maze[y - 1][x] != '%'){
                //If right hand is on empty space, turn right into empty space

                //Set maze to N at this location to indicate we turned North
                if (pivot){
                    maze[y][x] = 'N';
                    setForward(x, y, "north");
                    setPrevious(x, y, facing, 'V');
                    writeOut(y, x, 'N');
                }
                solve(x, y - 1, "north");
            }
            else {
                //Right hand is still on a wall
                if ((maze[y][x - 1] != '#' && maze[y][x - 1] != '%')){
                    //Move straight if nothing is in the way
                    if (pivot){
                        maze[y][x] = 'W';
                        setForward(x, y, "west");
                        setPrevious(x, y, facing, 'V');
                        writeOut(y, x, 'W');
                    }
                    solve(x - 1, y, "west");
                }
                else if (maze[y + 1][x] != '#' && maze[y + 1][x] != '%'){
                    //Wall in front, check south, if no wall we move south
                    if (pivot){
                        maze[y][x] = 'S';
                        setForward(x, y, "south");
                        setPrevious(x, y, facing, 'V');
                        writeOut(y, x, 'S');
                    }
                    solve(x, y + 1, "south");
                }
                else{
                    //We are boxed in and need to turn around
                    solve(x + 1, y, "east");
                }
            }
        }
        else if (facing.equals("north")){
            if (maze[y][x + 1] != '#' && maze[y][x + 1] != '%'){
                //If right hand is on empty space, turn right into empty space

                //Set maze to E at this location to indicate we turned East
                if (pivot){
                    maze[y][x] = 'E';
                    setForward(x, y, "east");
                    setPrevious(x, y, facing, 'V');
                    writeOut(y, x, 'E');
                }
                solve(x + 1, y, "east");
            }
            else {
                //Right hand is still on a wall
                if ((maze[y - 1][x] != '#' && maze[y - 1][x] != '%')){
                    //Move straight if nothing is in the way
                    if (pivot){
                        maze[y][x] = 'N';
                        setForward(x, y, "north");
                        setPrevious(x, y, facing, 'V');
                        writeOut(y, x, 'N');
                    }
                    solve(x, y - 1, "north");
                }
                else if (maze[y][x - 1] != '#' && maze[y][x - 1] != '%'){
                    //Wall in front, check west, if no wall we move west
                    if (pivot){
                        maze[y][x] = 'W';
                        setForward(x, y, "west");
                        setPrevious(x, y, facing, 'V');
                        writeOut(y, x, 'W');
                    }
                    solve(x - 1, y, "west");
                }
                else{
                    //We are boxed in and need to turn around
                    solve(x, y + 1, "south");
                }
            }
        }
        else { //facing south
            if (maze[y][x - 1] != '#' && maze[y][x - 1] != '%'){
                //If right hand is on empty space, turn right into empty space
                
                if (pivot){;
                    maze[y][x] = 'W';
                    setForward(x, y, "west");
                    setPrevious(x, y, facing, 'V');
                    writeOut(y, x, 'W');
                }
                solve(x - 1, y, "west");
            }
            else {
                //Right hand is still on a wall
                if ((maze[y + 1][x] != '#' && maze[y + 1][x] != '%')){
                    //Move straight if nothing is in the way
                    if (pivot){
                        maze[y][x] = 'S';
                        setForward(x, y, "south");
                        setPrevious(x, y, facing, 'V');
                        writeOut(y, x, 'S');
                    }
                    solve(x, y + 1, "south");
                }
                else if (maze[y][x + 1] != '#' && maze[y][x + 1] != '%'){
                    //Wall in front, check east, if no wall we move east
                    if (pivot){
                        maze[y][x] = 'E';
                        setForward(x, y, "east");
                        setPrevious(x, y, facing, 'V');
                        writeOut(y, x, 'E');
                    }
                    solve(x + 1, y, "east");
                }
                else{
                    //We are boxed in and need to turn around
                    solve(x, y - 1, "north");
                }
            }
        }
    }
    
    /**
     * Takes a character and returns the string direction
     * @param direction character value representing a direction
     * @return  the string direction you are supposed to go
     */
    private String returnDirection(char direction){
        switch (direction){
            case 'N':
                return "north";
            case 'S':
                return "south";
            case 'W':
                return "west";
            case 'E':
                return "east";
            default:
                System.out.println("This space does not have a direction");
                return "ERROR";
        }
    }
    

    
    /**
     * Opens a text file containing a maze and stores the data in the 2D char array maze[][].
     * 
     * @param   fname   String value containing the file name of the maze to open.
     */
    public void openMaze(String fname) {
        String in = "";
        int i = 0;
        try {
            Scanner sc = new Scanner(new File(fname));
            while (sc.hasNext()) {
                in = sc.nextLine();
                in = trimWhitespace(in);
                if (in.length() <= MAX_WIDTH && i < MAX_HEIGHT) {
                    for (int j=0; j<in.length(); j++) {
                        if (in.charAt(j) == '#') {      // if this spot is a wall, randomize the wall peice to display
                            if (random.nextInt(2) == 0) {
                                maze[i][j] = '#';   
                            }
                            else {
                                maze[i][j] = '%';
                            }
                        }
                        else {
                            maze[i][j] = in.charAt(j);
                        }
                    }
                }
                else {
                    System.out.println("Maximum maze size exceeded: (" + MAX_WIDTH + " x " + MAX_HEIGHT + ")!");
                    System.exit(1);
                }
                i++;
            }
            width = in.length();
            height = i;
            System.out.println("("+width+" x "+height+ ") maze opened.");
            System.out.println();
            sc.close();
        }
        catch (FileNotFoundException e) {
            System.err.println("File not found: " + e);
        }
    }
    
    /**
     * Removes white space from the supplied string and returns the trimmed String.
     * 
     * @param   s   String value to strip white space from.
     * @return  String stripped of white space.
     */
    public String trimWhitespace(String s) {
        String newString = "";
        for (int i=0; i<s.length(); i++) {
            if (s.charAt(i) != ' ') {
                newString += s.charAt(i);
            }
        }
        return newString;
    }
    
    /**
     * Returns the sprite facing the direction supplied.
     * 
     * @param   facing  String value containing 1 of 4 cardinal directions to make the sprite face.
     * @return  Image of the sprite facing the proper direction.
     */
    private Image printGuy(String facing) {
        if(facing.equals("south")) {  // draw sprite facing south
            if (step) {
                step = false;
                return south1.getImage();
             }
            else {
                step = true;
                return south2.getImage();
            }
        }
        else if(facing.equals("north")) {  // draw sprite facing north
            if (step) {
                step = false;
                return north1.getImage();
             }
            else {
                step = true;
                return north2.getImage();
            }
        }
        else if(facing.equals("east")) {  // draw sprite facing east
            if (step) {
                step = false;
                return east1.getImage();
            }
            else {
                step = true;
                return east2.getImage();
            }
        }
        else if(facing.equals("west")) {  // draw sprite facing west
            if (step) {
                step = false;
                return west1.getImage();
            }
            else {
                step = true;
                return west2.getImage();
            }
        }
        return null;
    }
    
    /**
     * Prints the Maze using sprites.
     * 
     * @return BufferedImage rendition of the maze.
     */
    public BufferedImage printMaze() {
        BufferedImage mi = new BufferedImage(width*SPRITE_WIDTH, height*SPRITE_HEIGHT, BufferedImage.TYPE_INT_ARGB);
        Graphics g2 = mi.createGraphics();
        
        for (int i=0; i<height; i++) {
            for (int j=0; j<width; j++) {
                if (maze[i][j] == '#') {    // draw wall
                    g2.drawImage(wall1.getImage(), j*SPRITE_WIDTH, i*SPRITE_HEIGHT, null, null);
                }
                else if (maze[i][j] == '%') {   // draw wall
                    g2.drawImage(wall2.getImage(), j*SPRITE_WIDTH, i*SPRITE_HEIGHT, null, null);
                }
                else if (maze[i][j] == '.' || maze[i][j] == 'X') {  // draw ground
                    g2.drawImage(ground.getImage(), j*SPRITE_WIDTH, i*SPRITE_HEIGHT, null, null);
                }
                else if (maze[i][j] == 'F') {   // draw finish
                    g2.drawImage(finish.getImage(), j*SPRITE_WIDTH, i*SPRITE_HEIGHT, null, null);
                }
            }
        }
         return mi;
    }

     public void closingMethod()
     {
         
            long endTime = currentTime - startTime;
            long finalTime = endTime / 100;
            System.out.println("Final Time = " + ((double)finalTime/(double)10));  
            System.exit(0);
      }
    /**
     * Handles the Timer, updates the boolean timerFired every time the Timer ticks.
     * Used to slow the animation down.
     */
    private class TimerHandler implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            timerFired = true;
        }
    }
    
    /**
     * Catch the windowClosing event and exit gracefully.
     */
    private class WindowHandler extends WindowAdapter {
        public void windowClosing (WindowEvent e) {
            removeAll();
            closingMethod();
            System.exit(0);
        }        
    }    

}