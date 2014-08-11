import javax.swing.*;
import java.awt.*;

/**
 *
 * @author Josh Moellenkamp & Gunnar Holwerda
 */
public class Minesweeper extends JFrame {
    
    private Square[][] board;               //will hold information on each grid square
    public int numOfMines;  
    public int size;       
    private Sound sound = new Sound();
    
    /**
     * Constructor for class Minesweeper
     */
    public Minesweeper(int size, int numOfMines) {
        
        super("Minesweeper");   
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        
        this.size = size;
        this.numOfMines = numOfMines;
        
        board = new Square[size][size];    //initialize board
        
        initializeBoard();
        
        getContentPane().add(new SweeperPanel(this));
        pack();
        
        setLocationRelativeTo(null);
        
        setVisible(true);   //Show the JFrame
    }
    
    public boolean checkEndGame(){
        int emptySpaces = 0;
        for (int i = 0; i < size; i++){
            for (int j = 0; j < size; j++){
                if (board[i][j].getContents() == false && board[i][j].getKnown() == true){
                    emptySpaces++;
                }
            }
        }
        return emptySpaces == ((size * size) - numOfMines);
    }
    
    /**
     * Writer method
     * 
     * @return sizeOfBoard  the size of the board to be played on
     */
    public int getSizeofBoard(){
        return size;
    }
    
    /**
     * Writer Method
     * @return  numOfMines  number of mines in the game
     */
    public int getNumOfMines(){
        return numOfMines;
    }
    
    /**
     * Fills the board with Square objects and then picks mines
     * 
     */
    private void initializeBoard()
    {
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {                
                board[i][j] = new Square(false, false);
            }
        }
        placeMines(numOfMines);
    }
    
    /**
     * Places the mines randomly on the board
     * 
     * @param numOfMines    the number of mines to be placed on the board
     */
    private void placeMines(int numOfMines)
    {
        for (int i = 0; i < numOfMines; i++){
            int x = (int) (Math.random() * size);
            int y = (int) (Math.random() * size);
            
            //again this needs wizard magic for static context
            if (board[x][y].getContents() != true){
                board[x][y].setContents(true);
            }
        }
    }
    
    
    /**
     * Returns square
     * 
     * @param   x   the x coordinate on the board
     * @param   y   the y coordinate on the board
     * 
     * @return  the value held in the board using those coordinates
     */
    public Square getSquare(int x, int y){
        return board[x][y];
    }
    
    /**
     * Checks the surrounding squares to the guessed square for mines
     * 
     * @param   x   the x coordinate on the board
     * @param   y   the y coordinate on the board
     * 
     * @return  mineCount   the number of mines in the surrounding area of the guessed square
     */
    private void checkSurrounding(int x, int y){
        int mineCount = 0;
        
        if (y + 1 != size && board[x][y + 1].getContents() == true){
            mineCount++;
        }
        if (y - 1 != -1 && board[x][y - 1].getContents() == true){
            mineCount++;
        }
        if (x + 1 != size&& board[x + 1][y].getContents() == true){
            mineCount++;
        }
        if (x - 1 != -1 && board[x - 1][y].getContents() == true){
            mineCount++;
        }
        if (y - 1 != -1 && x - 1 != -1 && board[x - 1][y - 1].getContents() == true){
            mineCount++;
        }
        if (y + 1 != size && x - 1 != -1 && board[x - 1][y + 1].getContents() == true){
            mineCount++;
        }
        if (y + 1 != size && x + 1 != size && board[x + 1][y + 1].getContents() == true){
            mineCount++;
        }
        if (y - 1 != -1 && x + 1 != size && board[x + 1][y - 1].getContents() == true){
            mineCount++;
        
        }
        board[x][y].setNumToDisplay(mineCount);
    }
    
    /**
     * Shows all of the squares on the board and what they contain
     */
    public void showBoard()
    {
        for (int i = 0; i < size; i++)
        {
            for (int j = 0; j < size; j++)
            {
                checkSurrounding(i, j);
                board[i][j].setKnown(true);
            }
        }
    }
    
    /**
     * Checks to see if the square clicked on is a mine or not
     * 
     * @param   x   the x coordinate of the grid
     * @param   y   the y coordinate of the grid
     */
    public void checkSquare(int x, int y){
        if (board[x][y].getContents() == false){
            checkSurrounding(x, y);
        }
        else{
            sound.playExplosion();
            showBoard();
            repaint();
            //play explosion sound
            JOptionPane.showMessageDialog(this, "Sorry! You have hit a mine...");
            playEndScenario();
        }        
    }
    
    public void playEndScenario(){
        boolean validAnswer = false;
        while(!validAnswer)
        {
                String answer = JOptionPane.showInputDialog("Would you like to play again?").toUpperCase();
                switch(answer){
                    case "YES":
                        resetBoard();
                        validAnswer = true;
                        break;
                    case "NO":
                        System.exit(0);
                        break;
                    default:
                        JOptionPane.showMessageDialog(this, "Please enter a valid answer.\n Either \"yes\" or \"no\"");
                }
        }
    }
    
    private void paintSquare(Graphics g) {
        g.setColor(Color.lightGray);
        g.fillRect(0, 0, size*25, size*25);
        
    }
    public void resetBoard(){
        initializeBoard();
        repaint();
    }
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        boolean answerValid = false;
        
        while (!answerValid){
            String answer = JOptionPane.showInputDialog("Would you like to enable hard mode?").toUpperCase();

            if (answer.equals("YES")){
                Minesweeper newGame = new Minesweeper(20, 50);
                answerValid = true;
            }
            else if (answer.equals("NO")){
                Minesweeper newGame = new Minesweeper(15, 25);
                answerValid = true;
            }
            else{
                answerValid = false;
            }

        }
        
    }
    
}