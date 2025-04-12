package sort;

public class Main {
    static final int NUM_SCALE = 10000;
    static final int SIZE = 10000000;

    public static void prepare(int[] arr) {
        for (int i = 0; i < arr.length; i++)
            arr[i] = (int) (NUM_SCALE * Math.random());
    }

    public static void main(String[] args) {
        int[] baseTestCaseArray = new int[SIZE];
        prepare(baseTestCaseArray);

        SortExecutor executor = new SortExecutor(baseTestCaseArray);


//        executor.addGeneralSort("Selection", false);
//        executor.addGeneralSort("Bubble", false);
//        executor.addGeneralSort("Insertion", false);

        executor.addMergeSort("Default", false);
        executor.addMergeSort("Bottom-Up", false);
        executor.addMergeSort("Hybrid", false);
        executor.addMergeSort("Smart Buffer Reuse", false);
        executor.addMergeSort("Multi-Thread", false);

        executor.addAdvancedSort("Quick", false);

        executor.runAll();
    }
}