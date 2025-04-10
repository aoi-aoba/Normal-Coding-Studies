package sort;

public class Sort {
    int Array[];

    public Sort(int[] Target) {
        Array = Target;
    }

    public int[] getArray() {
        return Array;
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

    public void bubbleSort() {
        int tmp = 0;
        boolean swapped;
        for (int last = Array.length - 1; last >= 2; last--) {
            swapped = false;
            for (int i = 0; i <= last - 1; i++) {
                if (Array[i] > Array[i + 1]) {
                    tmp = Array[i];
                    Array[i] = Array[i + 1];
                    Array[i + 1] = tmp;
                    swapped = true;
                }
            }
            if (!swapped) break;
        }
    }

    public void insertionSort() {
        for (int i = 1; i <= Array.length - 1; i++) {
            int location = i - 1;
            int newItem = Array[i];
            while (location >= 0 && newItem < Array[location]) {
                Array[location + 1] = Array[location];
                location--;
            }
            Array[location + 1] = newItem;
        }
    }
}