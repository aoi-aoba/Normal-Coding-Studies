package sort;

public class Main {
    static final int NUM_SCALE = 100000;
    static final int SIZE = 1000;
    public static void prepare(int[] arr) {
        for (int i = 0; i < arr.length; i++)
            arr[i] = (int) (NUM_SCALE * Math.random());
    }
    public static void main(String[] args) {
        int[] arr= new int[SIZE];
        prepare(arr);

        Sort sort = new Sort(arr);
        // sort.selectionSort();
        sort.bubbleSort();

        arr = sort.getArray();
        for(int i : arr) System.out.print(i + " ");
    }
}