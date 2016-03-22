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
        boolean sorted = false;
        while (!sorted) {
            boolean edited = false;
            for (int i = 0; i < array.length - 1; i++) {
                if (array[i] > array[i + 1]) {
                    int temp = array[i  + 1];
                    array[i + 1] = array[i];
                    array[i] = temp;
                    edited = true;
                }
            }
            sorted = !edited;
        }
    }

    public void printFrequency() {
        int[] temp = new int[10];
        for (int element: array) {
            temp[element]++;
        }
        for (int i = 0; i < temp.length; i++) {
            System.out.println("There are " + temp[i] + ", " + i + "'s");
        }

    }
}
