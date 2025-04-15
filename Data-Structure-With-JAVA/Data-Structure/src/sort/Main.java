package sort;

public class Main {
    static final int NUM_SCALE = 10000000;
    static final int SIZE = 10000000;

    public static void prepare(int[] arr) {
        for (int i = 0; i < arr.length; i++)
            arr[i] = (int) (NUM_SCALE * Math.random());
    }

    public static void main(String[] args) {
        int[] baseTestCaseArray = new int[SIZE];
        prepare(baseTestCaseArray);

        SortExecutor executor = new SortExecutor(baseTestCaseArray);

        /*
         executor.addGeneralSort("Selection", false);
         executor.addGeneralSort("Bubble", false);
         executor.addGeneralSort("Insertion", false);
         일반적으로 선택 정렬, 버블 정렬, 삽입 정렬은 사용 시 시간과 공간 복잡도에서 매우 불리하므로 주석 처리
        */

        executor.addMergeSort("Default", false);
        executor.addMergeSort("Bottom-Up", false);
        executor.addMergeSort("Hybrid", false);
        executor.addMergeSort("Smart Buffer Reuse", false);
        executor.addMergeSort("Multi-Thread", false);

        executor.addAdvancedSort("Quick", false);
        executor.addAdvancedSort("Heap", false);
        executor.addAdvancedSort("Shell (with Knuth Sequence)", false);
        executor.addAdvancedSort("Shell (with Tokuda Sequence)", false);
        executor.addAdvancedSort("Shell (with Ciura Sequence)", false);

        executor.addLinearSort("Counting", false, NUM_SCALE);
        executor.addLinearSort("No-Sum Counting", false, NUM_SCALE);
        executor.addLinearSort("Radix", false);
        // executor.addLinearSort("Bucket", false);

        executor.runAll();
    }
}