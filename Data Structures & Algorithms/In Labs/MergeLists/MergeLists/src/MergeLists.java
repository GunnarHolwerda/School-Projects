
import java.util.ArrayList;

public class MergeLists {

    public static ArrayList<Integer> merge(ArrayList<Integer> list1, ArrayList<Integer> list2) {
        ArrayList<Integer> combinedList = new ArrayList<>();
        
        for (int i = 0; i < list1.size(); i++){
            int numToAdd = list1.get(i);
            combinedList.add(numToAdd);
        }
        
        for (int i = 0; i < list2.size(); i++){
            for (int j = 0; j < combinedList.size(); j++) {
                
                int currentNumber = list2.get(i);
                
                if (currentNumber <= combinedList.get(0)){
                    combinedList.add(0, currentNumber);     //Adds number to the beginning
                    break;
                }
                if (currentNumber >= combinedList.get(combinedList.size() - 1)){
                    
                    combinedList.add(currentNumber);        //Adds number to the end
                    break;
                }
                if (currentNumber == combinedList.get(j)){
                    combinedList.add(j, currentNumber);     //Adds number right after same number
                    break;
                }
                if (currentNumber > combinedList.get(j) && currentNumber < combinedList.get(j + 1)){
                    combinedList.add(j + 1, currentNumber);     //Adds the number inbetween the numbers tested
                    break;
                }
            }
        }
        
        return combinedList;
    }

    public static ArrayList<Integer> makeRandomIncreasingList(int length) {
        ArrayList<Integer> randomList = new ArrayList<>();
        
        for (int i = 0; i < length; i++){
            if (randomList.isEmpty()) {
                randomList.add((int)(Math.random() * 10));
            }
            else {
                randomList.add(randomList.get(i - 1) + (int)(Math.random() * 10));
            }
        }
        
        return randomList;
    }

    public static void doMergeTest() {
        ArrayList<Integer> list1 = makeRandomIncreasingList(10);
        ArrayList<Integer> list2 = makeRandomIncreasingList(20);
        ArrayList<Integer> mergedList = merge(list1, list2);
        System.out.println("List 1:" + list1);
        System.out.println("List 2:" + list2);
        System.out.println("Merged list:" + mergedList);
    }

    public static void main(String[] args) {
        for (int i = 0; i < 10; i++) {
            System.out.println("Performing merge test #" + (i + 1) + ":");
            doMergeTest();
        }
    }
}
