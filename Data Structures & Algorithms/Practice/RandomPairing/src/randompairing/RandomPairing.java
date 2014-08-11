/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package randompairing;
import java.util.ArrayList;
import java.util.Scanner;
import java.io.FileReader;
import java.io.FileNotFoundException;
/**
 *
 * @author Gunnar
 */
public class RandomPairing {
    private static ArrayList<String> people = new ArrayList();
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        try {
            Scanner in = new Scanner(new FileReader("C:/Users/Gunnar/Desktop/JulyNames.txt"));
            while(in.hasNext()){
                String name = in.nextLine();
                people.add(name);
            }
        }
        catch(FileNotFoundException e){
        }
        
        
        while(!people.isEmpty()){
            int firstSelection = (int)(Math.random() * people.size());
            System.out.println(people.get(firstSelection));
            people.remove(firstSelection);
            
            int secondSelection = (int)(Math.random() * people.size());
            System.out.println(people.get(secondSelection));
            people.remove(secondSelection);
            System.out.println("\n\n");
        }
    }
}
