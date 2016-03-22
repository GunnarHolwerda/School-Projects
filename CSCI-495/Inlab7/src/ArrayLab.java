/**
 * Created by Gunnar on 3/8/2016.
 */

import java.util.Random;
public class ArrayLab {

    int[] array;

    ArrayLab(int size) {
        this.array = new int[size];
    }

    public void print() {
        for (int i = 0; i < array.length; i++) {
            System.out.print(array[i]);

            if (i != array.length - 1) {
                System.out.print(",");
            }
        }
        System.out.println();
    }

    public void initialize() {
        Random r = new Random();

        for (int i = 0; i < array.length; i++) {
            array[i] = r.nextInt(9) + 1;
        }
    }

    public void printStats() {
        double sum = 0;
        int max = 0;
        int min = 11;

        for (int element: array) {
            sum += element;

            if (element > max) {
                max = element;
            }

            if (element < min) {
                min = element;
            }
        }

        System.out.println("Average Value: " + (sum/array.length));
        System.out.println("Maximum Value: " + (max));
        System.out.println("Minimum Value: " + (min));

    }

    public void search(int num) {
        for (int element: array) {
            if (element == num) {
                System.out.println(num + " was found");
                return;
            }
        }
        System.out.println(num + " was not found");
    }
}
