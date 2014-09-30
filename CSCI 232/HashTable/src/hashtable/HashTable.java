package hashtable;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Scanner;

/**
 *
 * @author Gunnar
 */
public class HashTable {
    private final String DUMMY_VALUE = "ERROR";
    private final int DUMMY_KEY = -99;
    
    
    private Cell[] table;
    private int size;
    private int numFilled;
    private double percentFull;
    
    
    /**
     * Constructor
     * @param size  desired size of the hashtable 
     */
    public HashTable(int size){
        this.size = size;               //Sets the size variable to size
        table = new Cell[size];         //Sets table to size 5
        numFilled = 0;
        percentFull = 0;
    }
    
    /**
     * inputFromFile
     * @param fileName location of file to input from
     */
    public void inputFromFile(String fileName){
        int number;
        String value;
        
        try {
            Scanner in = new Scanner(new FileReader(fileName));
            
            while(in.hasNextLine()){
                number = in.nextInt();
                value = in.nextLine();
                
                if (value.contains("  ")){
                    value = value.substring(2, value.length());
                }
                else {
                    value = value.substring(1, value.length());
                }
                
                System.out.println("The cell with key " + number + " and value " + value + " has been added");
                
                add(new Cell(number, value)); 
            }
        }
        catch(FileNotFoundException e){
            System.out.println("Cannot find the file: " + fileName);
        }
        System.out.println("Done adding cells");
    }
    
    /**
     * search
     * @param searchCell the cell to add to the hash table
     */
    public int search(Cell searchCell){
        int position = searchCell.getKey() % size;
        boolean found = false;
        while (!found){
            if (table[position] == null){                               //Cell of the position is empty
                return position;
            }
            else{                                                               //Cell is full
                if (table[position].getValue().equals(searchCell.getValue())){ //Cell is full with the same cell as trying to be added
                    return position;
                }
                else{
                    position++;
                    if (position >= table.length){
                        position = 0;
                    }
                }
            }
        }
        return -1;                      //If this is returned something went wrong
    }
    
    
    /**
     * add
     * @param addCell cell to be added to the hashtable
     */
    public void add(Cell addCell){
        int position = search(addCell);                                 //Search for position to set cell
        
        if (table[position] == null){                     //If position isn't returned as -1 which means cell isn't already in table
            table[position] = addCell;           //Add the cell
            numFilled++;                         //Increase the number of cells in the table
            updatePercentFull();
        }
        else{
            System.out.println("This cell already is in the table");
        }
        
        if(percentFull >= 0.8){                          //Table is > 80% full so increase size
            increaseSize();
        } 
    }
    
    /**
     * deleteCell
     * @param deleteCell  cell to delete
     */
    public void deleteCell(Cell deleteCell){
        int position = search(deleteCell);
        
        if (table[position] != null){
            table[position] = new Cell(DUMMY_KEY, DUMMY_VALUE);             //Replace the node with a dummy
        }
    }
    
    
    /**
     * Increases the size of the hashtable
     */
    private void increaseSize(){
        Cell[] tempTable = table;               //Save current table in a temporary array
        size *= 2;                              //Increase size of table by 2 times
        table = new Cell[size];                 //Create a new table twice the size of the old one
        numFilled = 0;                          //Reset counter of number of cells filled
        
        System.out.println("Increasing the size of the Hash Table to " + size + " because " + percentFull + " >= 0.8");
        
        rehashTable(tempTable);
    }
    
    /**rehashTable
     * 
     * @param oldTable old table to enter back into the new table
     */
    private void rehashTable(Cell[] oldTable){
        System.out.println("Rehashing table");
        for (Cell item : oldTable) {
            if (item != null && !item.getValue().equals(DUMMY_VALUE)) {
                //Make sure you aren't adding null or dummy
                this.add(item);   
            }
        }
    }
    
    /**
        Prints out the hashtable
    */
    public void printTable(){
        System.out.println("\nPrinting hash table:");
        int position = 0;
        for (Cell item: table){
            if (item != null && !item.getValue().equals(DUMMY_VALUE))
                System.out.format("%-5d: Value -> %-18s Key -> %d\n", position, item.getValue(), item.getKey());
            position++;
        }
        System.out.format("Total number of values = %3d\nTotal size of table = %3d\n", numFilled, size);
    }
    
    /**
     * getCell
     * @param position
     * @return  cell at the desired position 
     */
    public Cell getCell(int position){
        return table[position];
    }
    
    /**
     * getSize
     * @return size of the table 
     */
    public int getSize(){
        return size;
    }
    
    private void updatePercentFull(){
        percentFull = (double)numFilled/(double)table.length;
    }
}
