package sort;

public class Main {
    static final int NUM_SCALE = 100;
    static final int SIZE = 10000;

    public static void prepare(int[] arr) {
        for (int i = 0; i < arr.length; i++)
            arr[i] = (int) (NUM_SCALE * Math.random());
    }

    public static void main(String[] args) {
        int[] baseTestCaseArray = new int[SIZE];
        prepare(baseTestCaseArray);

        SortExecutor executor = new SortExecutor(baseTestCaseArray);

        executor.addGeneralSort("Selection");
        //executor.addGeneralSort("Bubble");
        executor.addGeneralSort("Insertion");

        executor.addMergeSort("Default", false);
        executor.addMergeSort("Bottom-Up", false);
        executor.addMergeSort("Hybrid", false);
        executor.addMergeSort("Smart Buffer Reuse", false);
        executor.addMergeSort("Multi-Thread", false);

        executor.runAll();
    }
}