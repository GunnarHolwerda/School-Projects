package testsorting;

import java.util.Random;
import java.util.Arrays;

/**
 *
 * @author Brendan
 */
public class TestSorting {

    public static void printArray(int[] array) {
        System.out.print("Array: ");
        for (int i = 0; i < array.length; i++) {
            System.out.print(" " + array[i]);
        }
        System.out.println();
    }

    public static void exchange(int[] array, int i, int j) {
        // exchange values at positions i and j in array
        int temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }

    public static void selectionSort(int[] array) {
        for (int fill = 0; fill <= array.length - 2; fill++) {
            int minPos = fill;
            for (int j = fill + 1; j < array.length; j++) {
                if (array[j] < array[minPos]) {
                    minPos = j;
                }
            }
            exchange(array, fill, minPos);
        }
    }

    public static boolean isSortedAscending(int[] array) {
        for (int i = 0; i <= array.length - 2; i++) {
            if (array[i] > array[i + 1]) {
                return false;
            }
        }
        return true;
    }

    public static void bubbleSort(int[] array) {
        for (int last = array.length - 1; last > 0; last--) {
            boolean madeExchange = false;
            for (int i = 0; i < last; i++) {
                if (array[i + 1] < array[i]) {
                    exchange(array, i, i + 1);
                    madeExchange = true;
                    //printArray(array);
                }
            }
            if (!madeExchange) {
                return;
            }
        }
    }

    public static void insertionSort(int[] array) {
        for (int nextPos = 1; nextPos < array.length; nextPos++) {
            int nextVal = array[nextPos];
            int curPos = nextPos;
            while (curPos > 0 && array[curPos - 1] > nextVal) {
                array[curPos] = array[curPos - 1];
                curPos--;
            }
            array[curPos] = nextVal;
        }
    }

    public static void shellSort(int[] array) {
        // Gap between adjacent elements.
        int gap = array.length / 2;
        while (gap > 0) {
            for (int nextPos = gap; nextPos < array.length;
                    nextPos++) {
                // Insert element at nextPos in its subarray.
                insert(array, nextPos, gap);
            } // End for.

            // Reset gap for next pass.
            if (gap == 2) {
                gap = 1;
            } else {
                gap = (int) (gap / 2.2);
            }
        } // End while.
    } // End sort.
   
    private static void insert(int[] array,
            int nextPos,
            int gap) {
        int nextVal = array[nextPos]; // Element to insert.
        // Shift all values > nextVal in subarray down by gap.
        while ((nextPos > gap - 1) // First element not shifted.
                && (array[nextPos - gap] > nextVal)) {
            array[nextPos] = array[nextPos - gap]; // Shift down.
            nextPos -= gap; // Check next position in subarray.
        }
        array[nextPos] = nextVal; // Insert nextVal.
    }
    
    private static void randomizeArray(int[] array){
        Random r = new Random();

        for (int i = 0; i < array.length; i++) {
            array[i] = r.nextInt(10 * array.length);
        }
    }
    

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here

        int size;
        long startTime, endTime, totalTime = 0;
        
        System.out.println("Selection Sort....");
        for (int i = 0; i < 20; i++){
            if (i == 0){
                totalTime = 0;
            }
            int[] selectionSortArray;
            //randomly decides the size of the array
            switch((int)(Math.random() * 5 + 1)){
                case 1:
                    selectionSortArray = new int[100];
                    break;
                case 2:
                    selectionSortArray = new int[1000];
                    break;
                case 3:
                    selectionSortArray = new int[10000];
                    break;
                case 4:
                    selectionSortArray = new int[100000];
                    break;
                case 5:
                    selectionSortArray = new int[1000000];
                    break;
                default:
                    selectionSortArray = new int[10];
            }
            System.out.println("Round " + (i + 1) + " " + "Array Size: " + selectionSortArray.length);
            randomizeArray(selectionSortArray);
            //printArray(A);
            startTime = System.nanoTime();
            selectionSort(selectionSortArray);
            endTime = System.nanoTime();
            if (i > 10){
                totalTime += (endTime - startTime);
            }
            //System.out.println("Sorted Array...");
            //printArray(A);
            System.out.println("Run time: " + (endTime - startTime));
            if (i == 19){
                System.out.println("Average Run Time of final 10: " + (totalTime/10));
            }
            System.out.println();
        }
        
        System.out.println("Bubble Sort....");
        for (int i = 0; i < 20; i++){
            if (i == 0){
                totalTime = 0;
            }
            int[] bubbleSortArray;
            //randomly decides the size of the array
            switch((int)(Math.random() * 5 + 1)){
                case 1:
                    bubbleSortArray = new int[100];
                    break;
                case 2:
                    bubbleSortArray = new int[1000];
                    break;
                case 3:
                    bubbleSortArray = new int[10000];
                    break;
                case 4:
                    bubbleSortArray = new int[100000];
                    break;
                case 5:
                    bubbleSortArray = new int[1000000];
                    break;
                default:
                    bubbleSortArray = new int[10];
            }
            System.out.println("Round " + i + " " + "Array Size: " + bubbleSortArray.length);
            randomizeArray(bubbleSortArray);
            //printArray(A);
            startTime = System.nanoTime();
            bubbleSort(bubbleSortArray);
            endTime = System.nanoTime();if (i > 10){
                totalTime += endTime - startTime;
            } 
            //System.out.println("Sorted Array...");
            //printArray(A);
            System.out.println("Run time: " + (endTime - startTime));
            if (i == 19){
                System.out.println("Average Run Time of final 10: " + (totalTime/10));
            }
            System.out.println();
        }
        
        System.out.println("Shell Sort....");
        for (int i = 0; i < 20; i++){
            if (i == 0){
                totalTime = 0;
            }
            int[] shellSortArray;
            //randomly decides the size of the array
            switch((int)(Math.random() * 5 + 1)){
                case 1:
                    shellSortArray = new int[100];
                    break;
                case 2:
                    shellSortArray = new int[1000];
                    break;
                case 3:
                    shellSortArray = new int[10000];
                    break;
                case 4:
                    shellSortArray = new int[100000];
                    break;
                case 5:
                    shellSortArray = new int[1000000];
                    break;
                default:
                    shellSortArray = new int[10];
            }
            System.out.println("Round " + i + " " + "Array Size: " + shellSortArray.length);
            randomizeArray(shellSortArray);
            //printArray(A);
            startTime = System.nanoTime();
            shellSort(shellSortArray);
            endTime = System.nanoTime();
            if (i > 10){
                totalTime += endTime - startTime;
            }
            //System.out.println("Sorted Array...");
            //printArray(A);
            System.out.println("Run time: " + (endTime - startTime));
            if (i == 19){
                System.out.println("Average Run Time of final 10: " + (totalTime/10));
            }
            System.out.println();
        }
        
        System.out.println("Array.sort");
        for (int i = 0; i < 20; i++){
            if (i == 0){
                totalTime = 0;
            }
            int[] arraySortArray;
            //randomly decides the size of the array
            switch((int)(Math.random() * 5 + 1)){
                case 1:
                    arraySortArray = new int[100];
                    break;
                case 2:
                    arraySortArray = new int[1000];
                    break;
                case 3:
                    arraySortArray = new int[10000];
                    break;
                case 4:
                    arraySortArray = new int[100000];
                    break;
                case 5:
                    arraySortArray = new int[1000000];
                    break;
                default:
                    arraySortArray = new int[10];
            }
            System.out.println("Round " + i + " " + "Array Size: " + arraySortArray.length);
            randomizeArray(arraySortArray);
            //printArray(A);
            startTime = System.nanoTime();
            Arrays.sort(arraySortArray);
            endTime = System.nanoTime();
            if (i > 10){
                totalTime += endTime - startTime;
            }
            //System.out.println("Sorted Array...");
            //printArray(A);
            System.out.println("Run time: " + (endTime - startTime));
            if (i == 19){
                System.out.println("Average Run Time of final 10: " + (totalTime/10));
            }
            System.out.println();
        }
    }
}
