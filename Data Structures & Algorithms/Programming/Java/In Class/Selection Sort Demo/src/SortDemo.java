/**
 *
 * @author Gunnar Holwerda
 */

import java.util.Random;
public class SortDemo {
    
    public static void printArray(int[] array){
        for(int i = 0; i < array.length; i++){
            System.out.print(" " + array[i]);
        }
        System.out.println();
    }
    
    public static void exchange(int[] array, int i, int j){
        //exchange values at postions i and j
        int temp = array[i];
        array[i] = array[j];
        array[j] = temp;
        
    }
    
    public static void selectionSort(int[] array){
        for (int fill = 0; fill <= array.length - 2; fill++){
            int minPos = fill;
            for (int j = fill + 1; j < array.length; j++){
                if (array[j] < array[minPos]){
                    minPos = j;
                }
            }
            exchange(array, fill, minPos);
        }  
    }
    
    public static void bubbleSort(int[] array){
        do{
            for (int last = array.length - 1; last > 0; last--){
                for (int i = 0; i < last; i++){
                    if (array[i + 1] < array[i]){
                        exchange(array, i, i + 1);
                    }
                }
            }
        }
        while(!isSorted(array));
    }
    
    public static void insertionSort(int[] array){
        for (int nextPos = 1; nextPos < array.length; nextPos++){
            int nextVal = array[nextPos];
            int curPos = nextPos;
            while (curPos > 0 && array[curPos - 1] > nextVal){
                array[curPos] = array[curPos - 1];
                curPos--;
            }
            array[curPos] = nextVal;
        }
    }
    
    public static void shellSort(int[] array){
        //Will be uploaded in the code that is online
    }
    
    public static void mergeSort(int[] array){
        if (array.length > 1){
            int half = array.length / 2;
            int leftSize = array.length / 2;
            int rightSize = array.length - leftSize;
            int[] leftSide = new int[leftSize];
            int[] rightSide = new int[rightSize];
            for (int i = 0; i < leftSize; i++){
                leftSide[i] = array[i];
            }
            for (int i = leftSize; i < array.length; i++){
                rightSide[i - leftSize] = array[i];
            }
            mergeSort(leftSide);
            mergeSort(rightSide);
            merge(leftSide, rightSide, array);
        }
    }
    
    public static void merge(int[] left, int[] right, int[] mergedArray){
        int leftPos = 0;
        int rightPos = 0;
        int mergePos = 0;
        while(leftPos < left.length || rightPos < right.length){
            if (leftPos < left.length && rightPos < right.length){
                    if (left[leftPos] <= right[rightPos]){
                        mergedArray[mergePos++] = left[leftPos++];
                    }
                    else {
                        mergedArray[mergePos++] = right[rightPos++];
                    }
            }
            else {
                if (leftPos == left.length){
                    mergedArray[mergePos++] = right[rightPos++];
                }
                else {
                    mergedArray[mergePos++] = left[leftPos++];
                }
            }
        }
    }
    
    public static void quickSort(int[] array){
        qS(array, 0, array.length - 1);
    }
    
    private static void qS(int[] array, int low, int high){
        //sort array in the range from low to high
        if (low < high){
            int pivot = array[low];
            int pivotIndex = partition(array, low, high);
            qS(array, low, pivotIndex - 1);
            qS(array, pivotIndex + 1, high);
        }
    }
    
    private static int partition(int[] array, int low, int high){
        int pivot = array[high];
        int i = low - 1;
        for (int j = low; j < high; j++){
            if (array[j] <= pivot){
                i = i + 1;
                exchange(array, i , j);
            }
        }
        //no idea what i should be returning...
    }
    
    public static boolean isSorted(int[] array){
        for (int i = 0; i <= array.length - 2; i++){
            if (array[i] > array[i + 1]){
                return false;
            }
        }
        return true;
    }
    
    public static void createRandomArray(int[] array){
        Random r = new Random();
        
        for (int i = 0; i < array.length; i++){
            array[i] = r.nextInt(10 * array.length);
        }
    }
    
    public static void main(String args[]){
        int size = 10;
        int[] array = new int[size];
        createRandomArray(array);
        
        //Selection Sort
        System.out.println("Selection Sort");
        System.out.println("Original array...");
        printArray(array);
        selectionSort(array);
        if (isSorted(array)){
            System.out.println("Sorted array...");
            printArray(array);
            System.out.println("\n");
        }
        else{
            System.out.println("Array is not sorted\n");
        }
        
        createRandomArray(array);
        
        //Bubble Sort
        System.out.println("Bubble Sort");
        System.out.println("Original array....");
        printArray(array);
        bubbleSort(array);
        if (isSorted(array)){
            System.out.println("Sorted array...");
            printArray(array);
            System.out.println("\n");
        }
        else{
            System.out.println("Array is not sorted...\n");
        }
        
        createRandomArray(array);
        
        //Insertion Sort
        System.out.println("Insertion Sort");
        System.out.println("Original array....");
        printArray(array);
        insertionSort(array);
        if (isSorted(array)){
            System.out.println("Sorted array...");
            printArray(array);
            System.out.println("\n");
        }
        else{
            System.out.println("Array is not sorted...\n");
        }
        
        createRandomArray(array);
        
        //Merge Sort
        System.out.println("Merge Sort");
        System.out.println("Original array....");
        printArray(array);
        mergeSort(array);
        if (isSorted(array)){
            System.out.println("Sorted array...");
            printArray(array);
            System.out.println("\n");
        }
        else{
            System.out.println("Array is not sorted...\n");
        }
        
        createRandomArray(array);
        
        //Quick Sort
        System.out.println("Quick Sort");
        System.out.println("Original array....");
        printArray(array);
        quickSort(array);
        if (isSorted(array)){
            System.out.println("Sorted array...");
            printArray(array);
            System.out.println("\n");
        }
        else{
            System.out.println("Array is not sorted...\n");
        }
    }
}
