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
}
