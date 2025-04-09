package sort;

public class Main {
    static final int NUM_SCALE = 1000;
    static final int SIZE = 20;
    public static void prepare(int[] arr) {
        for (int i = 0; i < arr.length; i++)
            arr[i] = (int) (NUM_SCALE * Math.random());
    }
    public static void main(String[] args) {
        int[] arr = new int[SIZE];
        prepare(arr);
        Sort sort = new Sort(arr);
        sort.selectionSort();

        arr = sort.getArray();
        for (int i : arr) System.out.print(i + " ");
    }
}
