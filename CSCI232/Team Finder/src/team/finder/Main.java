package team.finder;
import java.util.*;
import java.io.*;
/**
 *
 * @author Gunnar
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        String olympics_file = "./olympics.txt";
        String ladder_golf_file = "./golf.txt";      
        boolean end = false;
        
        Scanner command_line_in = new Scanner(System.in);
        
        do {
            System.out.println("Choose what you would like to run:\n1.Olympics\n2.Ladder Golf\n>");
            int choice = command_line_in.nextInt();
            if (choice == 1) {
                System.out.print("Enter number of teams:");
                int number_of_teams = command_line_in.nextInt();
                olympics(olympics_file, number_of_teams);
            }
            else if (choice == 2){
                golf(ladder_golf_file);
            }
            else if (choice == 3){
                end = true;
            }
            else {
                System.out.println("I have no clue what you wanted to do.");
            }
        }
        while (!end);
    }
    
    public static void golf(String file_name){
        Random rand = new Random();
        ArrayList<Person> players = new ArrayList();
        try {
            Scanner in = new Scanner(new FileReader(file_name));
            while (in.hasNextLine()){
                String name = in.nextLine();
                Person person = new Person(name);
                players.add(person);
            }
        }
        catch (Exception e){
            System.out.println("Could not find file");
        }
        
        while(!players.isEmpty()){
            int first, second;
            do {
                first = rand.nextInt(players.size());
                second = rand.nextInt(players.size());
            }
            while(first == second);
            System.out.println("Team:");
            System.out.println(players.get(first).name);
            System.out.println(players.get(second).name);
            System.out.print("\n");
            players.remove(second);
            if (second < first) {
                players.remove(first - 1);
            }
            else {
                players.remove(first);
            } 
        }
    }
    
    public static void olympics(String file_name, int num_teams){
        Random rand = new Random();
        ArrayList<Person> contestants = new ArrayList();
        ArrayList<Person> team_one = new ArrayList();
        ArrayList<Person> team_two = new ArrayList();
        ArrayList<Person> team_three = new ArrayList();
        
        //Get the contestants
        try {
            Scanner in = new Scanner(new FileReader(file_name));
            while (in.hasNextLine()){
                String person = in.nextLine();
                Person temp = new Person(person);
                contestants.add(temp);
            }
        }
        catch (Exception e){
            System.out.println("Could not find file");
        }

        while(!contestants.isEmpty()){
            int contestant_selector = rand.nextInt(contestants.size());
            int team_selector = rand.nextInt(3) + 1;
            boolean successfulPlacement = false;
            Person chosen_one = contestants.get(contestant_selector);
            
            switch (team_selector) {
                case 1:
                    if (team_one.size() < 5) {
                        team_one.add(chosen_one);
                        successfulPlacement = true;
                    }
                    break;
                case 2:
                    if (team_two.size() < 5) {
                        team_two.add(chosen_one);
                        successfulPlacement = true;
                    }
                    break;
                case 3:
                    if (team_three.size() < 5) {
                        team_three.add(chosen_one);
                        successfulPlacement = true;
                    }
                    break;
                default:
                    System.out.println("We shouldn't have gotten here");
            }

            if (successfulPlacement){
                contestants.remove(contestant_selector);
            }
        }

        System.out.printf("Team One\n");
        for (int i = 0; i < team_one.size(); i++){
            System.out.println(team_one.get(i).name.toUpperCase());
        }
        
        System.out.printf("Team Two\n");
        for (int i = 0; i < team_two.size(); i++){
            System.out.println(team_two.get(i).name.toUpperCase());
        }
        
        System.out.printf("Team Three\n");
        for (int i = 0; i < team_three.size(); i++){
            System.out.println(team_three.get(i).name.toUpperCase());
        }

        
    }
    
}
