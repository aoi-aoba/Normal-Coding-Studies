package sort;

import java.util.ArrayList;
import java.util.List;

public class SortExecutor {
    private final int[] baseArray; // 초기 배열 (임의 난수로 생성되는 main prepare 결과값)
    private final List<Runnable> tasks = new ArrayList<>(); // 정렬 작업 저장 리스트

    public SortExecutor(int[] baseArray) {
        this.baseArray = baseArray;
    }

    // baseArray를 깊은 복사해서 정렬마다 독립된 배열을 사용
    private int[] copyArray() {
        return java.util.Arrays.copyOf(baseArray, baseArray.length);
    }

    // GeneralSort 인스턴스 생성, 지정 타입으로 정렬 후 리포트 출력
    public void addGeneralSort(String type, boolean printSortedArray) {
        tasks.add(() -> {
            GeneralSort sorter = new GeneralSort(copyArray());
            sorter.sort(type);
            System.out.println(sorter.getReport());
            if (printSortedArray) {
                int[] result = sorter.getArray();
                StringBuilder sb = new StringBuilder();
                for (int num : result) sb.append(num).append(" ");
                System.out.println(sb.toString().trim());
            }
        });
    }

    // MergeSort 인스턴스 생성, 지정 타입으로 정렬 후 리포트 출력
    public void addMergeSort(String type, boolean printSortedArray) {
        tasks.add(() -> {
            MergeSort sorter = new MergeSort(copyArray());
            sorter.sort(type);
            System.out.println(sorter.getReport());
            if (printSortedArray) {
                int[] result = sorter.getArray();
                StringBuilder sb = new StringBuilder();
                for (int num : result) sb.append(num).append(" ");
                System.out.println(sb.toString().trim());
            }
        });
    }

    public void addAdvancedSort(String type, boolean printSortedArray) {
        tasks.add(() -> {
            AdvancedSort sorter = new AdvancedSort(copyArray());
            sorter.sort(type);
            System.out.println(sorter.getReport());
            if (printSortedArray) {
                int[] result = sorter.getArray();
                StringBuilder sb = new StringBuilder();
                for (int num : result) sb.append(num).append(" ");
                System.out.println(sb.toString().trim());
            }
        });
    }

    public void addLinearSort(String type, boolean printSortedArray) {
        tasks.add(() -> {
            LinearSort sorter = new LinearSort(copyArray());
            sorter.sort(type, null);
            System.out.println(sorter.getReport());
            if (printSortedArray) {
                int[] result = sorter.getArray();
                StringBuilder sb = new StringBuilder();
                for (int num : result) sb.append(num).append(" ");
                System.out.println(sb.toString().trim());
            }
        });
    }

    public void addLinearSort(String type, boolean printSortedArray, int num_scale) {
        tasks.add(() -> {
            LinearSort sorter = new LinearSort(copyArray());
            sorter.sort(type, num_scale);
            System.out.println(sorter.getReport());
            if (printSortedArray) {
                int[] result = sorter.getArray();
                StringBuilder sb = new StringBuilder();
                for (int num : result) sb.append(num).append(" ");
                System.out.println(sb.toString().trim());
            }
        });
    }

    public void runAll() {
        for (Runnable task : tasks) task.run();
    }
}