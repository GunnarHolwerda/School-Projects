package huffmancoding;

import java.util.Scanner;

/**
 *
 * @author Gunnar
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        HuffmanTree ht = new HuffmanTree();
        
        Scanner input = new Scanner(System.in);
        int inputNum;
        do{
            System.out.print("Would you like to:\n1) Compress File\n2) Decompress File\n3) Exit\nEnter the corresponding number here: ");
            inputNum = input.nextInt();

            if (inputNum == 1){          
                ht.readInFile("inFile.txt");
            }
            else if (inputNum == 2){
                ht.decompressFile();
            }
            else if (inputNum == 3){
                System.exit(0);
            }
        }
        while (inputNum != 3);
        
    }
    
}
