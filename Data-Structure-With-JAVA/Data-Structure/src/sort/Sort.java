package sort;

public class Sort {
    int Array[];

    public Sort(int[] Target) {
        Array = Target;
    }

    public void selectionSort() {
        int k, tmp;
        for (int last = Array.length - 1; last >= 1; last--) {
            k = theLargest(last);
            tmp = Array[k];
            Array[k] = Array[last];
            Array[last] = tmp;
        }
    }

    private int theLargest(int last) {
        int largest = 0;
        for (int i = 0; i <= last; i++)
            if (Array[i] > Array[largest]) largest = i;
        return largest;
    }

    public int[] getArray() {
        return Array;
    }
}