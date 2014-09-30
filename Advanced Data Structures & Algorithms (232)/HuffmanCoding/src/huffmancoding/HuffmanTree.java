package huffmancoding;
import java.util.*;
import java.io.*;

/**
 *
 * @author Gunnar
 */
public class HuffmanTree {
    
    //Array that holds the number of occurences of each letter
    private int countChar[];
    private File compressFile;
    private File decompressFile;
    private FileWriter fwCom, fwDecom;
    private BufferedWriter bwCom, bwDecom;
    private Node root;
    
    public HuffmanTree(){
        countChar = new int[95];
        
        //Create the compressed file and create file stream to write out to it
        compressFile = new File("compressFile.txt");
        decompressFile = new File("decompressFile.txt");
        try {
            fwDecom = new FileWriter(decompressFile, true);
            fwCom = new FileWriter(compressFile, true);
        }
        catch(IOException e){
            System.out.println("File Writer doesn't work");
        }
        bwCom = new BufferedWriter(fwCom);
        bwDecom = new BufferedWriter(fwDecom);
    }
    
    public void readInFile(String fileName){
        if (compressFile.exists()){
            System.out.println("Compressed file already exists.");
            try {
                bwCom.close();
            }
            catch (IOException e){
                System.out.println("COULD NOT CLOSE THINGS");
            }
            if (compressFile.delete()){
                System.out.println("Deleted a compressed file....\nCreating a new one....");
                compressFile = new File("compressFile.txt");
                try {
                    fwCom = new FileWriter(compressFile, true);
                }
                catch(IOException e){
                    System.out.println("File Writer doesn't work");
                }
                bwCom = new BufferedWriter(fwCom);
            }
            else {
                System.out.println("File could not be deleted...");
            }
        }
        
        //Start reading in inFile.txt and compressing it
        try {
            Scanner in = new Scanner(new File(fileName));
            String currentLine;
            
            //Go through file line by line and count number of time each letter occurs
            while (in.hasNextLine()){
                currentLine = in.nextLine();
                for (int i = 0; i < currentLine.length(); i++){
                    countChar[currentLine.charAt(i) - 32]++;
                }
            }
            in.close();
            
            /*  Read-in debugging
            for (int i = 0; i < countChar.length; i ++){
                System.out.printf("%c = %d\n", (char)(i + 32), countChar[i]);
            }*/
            
            this.root = createTree();
            
            in = new Scanner(new File(fileName));
            currentLine = "";
            
            //Go through file and compress
            while (in.hasNextLine()){
                currentLine = in.nextLine();
                for (int i = 0; i < currentLine.length(); i++){
                    Node letterNode = new Node(countChar[currentLine.charAt(i) - 32], null, null, currentLine.charAt(i));
                    compressFile(this.root, letterNode);
                }
            }
            
            in.close();
            
            //After writing out to file close the bufferedwriter
            try {
                bwCom.close();
            }
            catch (IOException e){
                System.out.println("COULD NOT CLOSE THINGS");
            }
        }
        catch(FileNotFoundException e){
            System.out.println("The file could not be found.");
        }
    }
    
    public void decompressFile(){
        try{
            //If there is a decompress file, delete it and then create a new one
            if (decompressFile.exists()){
                System.out.println("Decompressed file already exists.");
                try {
                    bwDecom.close();
                }
                catch (IOException e){
                    System.out.println("COULD NOT CLOSE THINGS");
                }
                if (decompressFile.delete()){
                    System.out.println("Deleted a decompressed file....\nCreating a new one....");
                    decompressFile = new File("decompressFile.txt");
                    try {
                        fwDecom = new FileWriter(decompressFile, true);
                    }
                    catch(IOException e){
                        System.out.println("File Writer doesn't work");
                    }
                    bwDecom = new BufferedWriter(fwDecom);
                }
                else {
                    System.out.println("File could not be deleted...");
                }
            }
            Scanner in = new Scanner(compressFile);
            
            int pos = 0;
            while(in.hasNextLine()){
                String curLine = in.nextLine();
                Node curNode = this.root;
                for (int i = 0; i < curLine.length(); i++){
                    //System.out.print(curLine.charAt(i));
                    if (curNode.getLeft() == null && curNode.getRight() == null){
                        //System.out.println();
                        System.out.print(curNode.getCharacter());
                        bwDecom.write(curNode.getCharacter());
                        curNode = this.root;
                        i--;
                    }
                    else{
                        if (curLine.charAt(i) == '0'){
                            curNode = curNode.getLeft();
                        }
                        else {
                            curNode = curNode.getRight();
                        }
                    }
                }
                //Print the final character
                System.out.print(curNode.getCharacter());
                bwDecom.write(curNode.getCharacter());
            }
            
            System.out.println();
            
            //System.out.println("\n" + root.getRight().getRight().getRight().getRight().getCharacter() + " = 1111");
            in.close();
            bwDecom.close();
        }
        catch (IOException e){
            System.out.println("Could not find the compress file....");
        }
        
    }
    
    private Node createTree(){
        ArrayList<Node> priorityQueue = createPriorityQueue();
        reorderQueue(priorityQueue);
        
        while (priorityQueue.size() != 1){
            //Get first and then second items from the priority queue
            Node first = priorityQueue.get(0);
            Node second = priorityQueue.get(1);
            //Then remove them from the priority queue
            priorityQueue.remove(0);
            priorityQueue.remove(0);
            
            //This new node has left child of the lowest frequency and right child of the higher of the two frequencies, its frequency is both combined
            Node newNode = new Node(first.getFrequency() + second.getFrequency(), first, second, '\0');
            //System.out.printf("New node created:\nFrequency: %d (%d + %d), Left: %c, Right: %c\n", newNode.getFrequency(), first.getFrequency(), second.getFrequency(), first.getCharacter(), second.getCharacter());
            
            //Add the new node back to the queue
            priorityQueue.add(newNode);
            //sort the priority queue
            reorderQueue(priorityQueue);
        }
        
        //Grabs the last node in the queue which is the root of the tree
        Node root = priorityQueue.get(0);
        //Empties the priority queue
        priorityQueue.remove(0);
        
        //return the root
        return root;       
    }
    
    private void compressFile(Node root, Node letter){        
        ArrayList<Character> bitString = new ArrayList();
        
        compress(root, letter, bitString);
        
        //System.out.print("For the letter " + letter.getCharacter() + " the bit string is : ");        //DEBUG CODE
        for (int i = bitString.size() - 1; i > 0; i--){
            //System.out.print(bitString.get(i));                                                       //DEBUG CODE
            try {
                bwCom.write(bitString.get(i));
            }
            catch (IOException e){
                System.out.println("Could not write bit out to file");
            }
        }
        //System.out.println();                                                                         //DEBUG CODE
    }
    
    private boolean compress(Node root, Node letter, ArrayList<Character> bitString){
        if (root.getCharacter() == letter.getCharacter()){
            return true;
        }
        else {
            if (root.getRight() != null){
                if (compress(root.getRight(), letter, bitString)){
                    if (root.getRight().getCharacter() == letter.getCharacter()){
                        bitString.add('1');
                    }
                    bitString.add('1');
                    return true;
                }
            }
            if (root.getLeft() != null){
                if (compress(root.getLeft(), letter, bitString)){
                    if (root.getLeft().getCharacter() == letter.getCharacter()){
                        bitString.add('0');
                    }
                    bitString.add('0');
                    return true;
                }
            }
            return false;
        }
    }
    
    private ArrayList<Node> createPriorityQueue(){
        ArrayList<Node> queue = new ArrayList();
        
        for (int i = 0; i < countChar.length; i++){
            if (countChar[i] != 0){
                //System.out.printf("Adding %c to the priority queue with frequency %d\n", (char)(i + 32), countChar[i]);
                queue.add(new Node(countChar[i], null, null, (char)(i + 32)));
            }
        }
        
        return queue;
    }
    
    private void reorderQueue(ArrayList<Node> queue){
        for (int i = 0; i < queue.size() - 1; i++){
            if (queue.get(i).getFrequency() > queue.get(i + 1).getFrequency()){
                Node temp = queue.get(i + 1);
                queue.set(i + 1, queue.get(i));
                queue.set(i, temp);
                
                i = -1;
            }
        }
        
        //Debugging of priorityqueue order
        /*
        for (int i = 0; i < queue.size(); i++){
            System.out.println(queue.get(i).getFrequency() + " " + queue.get(i).getCharacter());
        }
        System.out.println("End sort\n");
        */
    }
    
}
