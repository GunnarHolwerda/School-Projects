import java.util.Random;

public class Array {

    private int[] array;

    Array(int size) {
        this.array = new int[size];
    }

    public void fill() {
        Random r = new Random();
        for (int i = 0; i < array.length; i++){
            array[i] = r.nextInt(10);
        }
    }

    public void print() {
        String seperator = "";
        String printString = "";

        for (int element: array) {
            seperator += "---";
            printString += "|" + element + "|";
        }
        System.out.println(seperator);
        System.out.println(printString);
        System.out.println(seperator);
    }

    public void sort() {
        System.out.println("Sorting!");
    }

    public void printFrequency() {
        System.out.println("Frequency printed");
    }
}
