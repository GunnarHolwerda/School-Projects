
import java.awt.GridLayout;
import javax.swing.*;
import java.lang.ArrayIndexOutOfBoundsException;


/**
 * Write a description of class TicTacToe here.
 * 
 * @author Brendan
 * @version 13 Jan 2011
 */
public class TicTacToe extends JFrame
{
    // instance variables - replace the example below with your own
    private char curPlayer;
    private char[][] board;

    /**
     * Constructor for objects of class TicTacToe
     */
    public TicTacToe()
    {
        super("Tic Tac Toe");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        board = new char[3][3]; // initialize board

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {                
                board[i][j] = ' '; // blank char
            }
        }
        getContentPane().add(new TTTPanel(this));
        pack();
        curPlayer = 'X';
        
        setLocationRelativeTo(null);

        setVisible(true); // Show the JFrame.

    }

    public char getCurPlayer() {
        return curPlayer;
    }


    public void switchPlayer() {
        if (curPlayer == 'X')
            curPlayer = 'O';
        else
            curPlayer = 'X';
    }

    public void setSquare(int x, int y, char c) 
    {
        board[x][y] = c;
    }

    public char getSquare(int x, int y) 
    {
        return board[x][y];
    }

    public void printBoard() 
    {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                System.out.print(" " + board[i][j]);
            }
            System.out.println();
        }
    }

    private boolean checkForWin(char p) {

        boolean win = false;
        // check row wins:
        for (int i = 0; i < 3; i++) {
            win = win || (board[i][0] == p && board[i][1] == p && board[i][2] == p);
        }
        // check column wins:
        for (int j = 0; j < 3; j++) {
            win = win || (board[0][j] == p && board[1][j] == p && board[2][j] == p);
        }
        // check diagonal wins:
        win = win || (board[0][0] == p && board[1][1] == p && board[2][2] == p);
        win = win || (board[0][2] == p && board[1][1] == p && board[2][0] == p);

        return win;
    }

    private boolean checkFullBoard() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (board[i][j] == ' ')
                    return false;
            }
        }
        return true;
    }

    public void checkForGameEnd() {
        
        String input;
        boolean inputValid = false;
        
        if (checkForWin('X')) {
            JOptionPane.showMessageDialog(this, "X wins!!!!");
            
            while(!inputValid)
            {
                input = JOptionPane.showInputDialog("Would you like to play again?");
                if (input.toUpperCase().equals("NO")){
                    System.exit(0);
                    inputValid = true;
                }
                else if (input.toUpperCase().equals("YES")){
                    resetGame();
                    inputValid = true;
                }
                else
                    JOptionPane.showMessageDialog(this, "Please enter a valid answer (yes or no)");
            }
        } 
        else if (checkForWin('O')) {
            JOptionPane.showMessageDialog(this, "O wins!!!!");
            
           while(!inputValid)
            {
                input = JOptionPane.showInputDialog("Would you like to play again?");
                if (input.toUpperCase().equals("NO")){
                    System.exit(0);
                    inputValid = true;
                }
                else if (input.toUpperCase().equals("YES")){
                    resetGame();
                    inputValid = true;
                }
                else
                    JOptionPane.showMessageDialog(this, "Please enter a valid answer (yes or no)");
            } 
        } 
        else if (checkFullBoard()) {
            JOptionPane.showMessageDialog(this, "Game over, draw.");
            
           while(!inputValid)
            {
                input = JOptionPane.showInputDialog("Would you like to play again?");
                if (input.toUpperCase().equals("NO")){
                    System.exit(0);
                    inputValid = true;
                }
                else if (input.toUpperCase().equals("YES")){
                    resetGame();
                    inputValid = true;
                }
                else
                    JOptionPane.showMessageDialog(this, "Please enter a valid answer (yes or no)");
            }
        }
    }
    
    private void resetGame()
    {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {                
                board[i][j] = ' '; // blank char
            }
        }
        curPlayer = 'X';
        repaint();
    }
    
    public void computerMove()
    {
        if(twoInARow('O'))
        {
            System.out.println("Optimization placed.");
        }
        else if (twoInARow('X'))
        {
            System.out.println("Block Placed");
        }
        else {
            randomMove();
        }
    }
    
    private boolean twoInARow(char c)
    {
        boolean blockNeeded = false;
        
        if (checkRows(c)){
            //System.out.println("Row Blocked");
            blockNeeded = true;
        }
        else if (checkColumns(c)){
            //System.out.println("Column Blocked");
            blockNeeded = true;
        }
        else if (checkDiagonals(c)){
            //System.out.println("Diagonal Blocked");
            blockNeeded = true;
        }
        
        return blockNeeded;
    }
    
    //Parameter for each of the check method is to tell it whether or not it is for optimization or to block. 0 means to block and a 1 means to optimize
    private boolean checkRows(char c)
    {
        char otherPlayer = c;
        
        boolean blocked = false;
        for (int i = 0; i < 3; i++){
            if (board[i][0] == otherPlayer && board[i][1] == otherPlayer && board[i][2] == ' ')             //checks far right
            {
                setSquare(i, 2, curPlayer);
                blocked = true;
            }
            else if (board[i][0] == otherPlayer && board[i][1] == ' ' && board[i][2] == otherPlayer)        //checks middle
            {
                setSquare(i, 1, curPlayer);
                blocked = true;
            }
            else if (board[i][0] == ' ' && board[i][1] == otherPlayer && board[i][2] == otherPlayer)        //checks far left
            {
                setSquare(i, 0, curPlayer);
                blocked = true;
            }
        }
        return blocked;
    }
    
    private boolean checkColumns(char c)
    {
        char otherPlayer = c;
        
        boolean blocked = false;
        for (int i = 0; i < 3; i++){
            if (board[0][i] == otherPlayer && board[1][i] == otherPlayer && board[2][i] == ' ')             //checks bottom
            {
                setSquare(2, i, curPlayer);
                blocked = true;
            }
            else if (board[0][i] == otherPlayer && board[1][i] == ' ' && board[2][i] == otherPlayer)             //checks middle
            {
                setSquare(1, i, curPlayer);
                blocked = true;
            }
            else if (board[0][i] == ' ' && board[1][i] == otherPlayer && board[2][i] == otherPlayer)             //checks top
            {
                setSquare(0, i, curPlayer);
                blocked = true;
            }
        }
        return blocked;
    }
    
    private boolean checkDiagonals(char c)
    {
        char otherPlayer = c;
        
        boolean blocked = false;
            if (board[0][0] == otherPlayer && board[1][1] == otherPlayer && board[2][2] == ' ')             //checks bottom right corner 
            {
                setSquare(2, 2, curPlayer);
                blocked = true;
            }
            else if (board[0][0] == ' ' && board[1][1] == otherPlayer && board[2][2] == otherPlayer)        //checks top left corner 
            {
                setSquare(0, 0, curPlayer);
                blocked = true;
            }
            else if (board[2][0] == ' ' && board[1][1] == otherPlayer && board[0][2] == otherPlayer)        //checks bottom left corner
            {
                setSquare(0, 2, curPlayer);
                blocked = true;
            }
            else if (board[2][0] == otherPlayer && board[1][1] == otherPlayer && board[0][2] == ' ')        //checks top right corner
            {
                setSquare(0, 2, curPlayer);
                blocked = true;
            }
            else if (board[0][0] == otherPlayer && board[1][1] == ' ' && board[2][2] == otherPlayer)        //checks middle negative slope
            {
                setSquare(1, 1, curPlayer);
                blocked = true;
            }
            else if (board[2][0] == otherPlayer && board[1][1] == ' ' && board[0][2] == otherPlayer)        //checks middle positive slope
            {
                setSquare(1, 1, curPlayer);
                blocked = true;
            }
            
        return blocked;
    }

    private void randomMove()
    {
        int x, y;
        boolean movePlaced = false;
        
        while(!movePlaced)
        {
            x = (int)(Math.random() * 3);
            y = (int)(Math.random() * 3);

            if (board[x][y] == ' ')
            {
                setSquare(x, y, curPlayer);
                System.out.println("Random moved place since no optimizable move is available.");
                movePlaced = true;  
            }        
        }
    }
    

    public static void main(String[] args) {
        TicTacToe newGame = new TicTacToe();
        //JOptionPane.showMessageDialog(newGame, "This is a test.");
    }
}
