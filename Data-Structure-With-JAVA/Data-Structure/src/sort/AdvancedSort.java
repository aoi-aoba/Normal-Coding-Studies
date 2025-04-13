package sort;

public class AdvancedSort {
    int[] Array;
    StringBuilder report;

    public AdvancedSort(int[] Target) {
        Array = Target;
        report = new StringBuilder();
    }

    public StringBuilder getReport() {
        return report;
    }

    public void sort(String type) {
        Runtime runtime = Runtime.getRuntime();

        System.gc(); // 가비지 컬렉션으로 초기 상테를 정리하는 것이 관행임
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

        // 메모리 사용량 측정 전이므로 사용 가능한 메모리 기준으로 측정
        long beforeUsedMemory = runtime.totalMemory() - runtime.freeMemory();
        long startTime = System.currentTimeMillis();

        switch (type) {
            case "Quick" -> quickSort();
            case "Heap" -> heapSort();
            case "Shell (with Knuth Sequence)" -> shellKnuthSort();
            case "Shell (with Tokuda Sequence)" -> shellTokudaSort();
            case "Shell (with Ciura Sequence)" -> shellCiuraSort();
        }

        long endTime = System.currentTimeMillis();
        long afterUsedMemory = runtime.totalMemory() - runtime.freeMemory();

        long timeUsed = endTime - startTime;
        long memoryUsed = Math.max(afterUsedMemory - beforeUsedMemory, 0); // 음수 방지

        report = new StringBuilder();
        report.append("정렬 방식 : ").append(type).append(" sort\n");
        report.append("걸린 시간(ms) : ").append(timeUsed).append(" ms\n");
        report.append("메모리 사용량(bytes) : ").append(memoryUsed).append(" bytes\n");
        report.append("----------------------------------------------");
    }

    public int[] getArray() {
        return Array;
    }

    private void quickSort() {
        qSort(0, Array.length - 1);
    }

    private void qSort(int p, int r) {
        if (p < r) {
            int q = partition(p, r);
            qSort(p, q - 1);
            qSort(q + 1, r);
        }
    }

    private void swap(int idx1, int idx2) {
        int tmp = Array[idx1];
        Array[idx1] = Array[idx2];
        Array[idx2] = tmp;
    }

    private int partition(int p, int r) {
        int x = Array[r], i = p - 1, tmp;
        for (int j = p; j <= r - 1; j++) {
            if (Array[j] < x) {
                i++;
                swap(i, j);
            }
        }
        swap(i + 1, r);
        return i + 1;
    }

    private void heapSort() {
        buildHeap();
        for (int i = Array.length - 1; i >= 1; i--) {
            swap(0, i);
            percolateDown(0, i - 1);
        }
    }

    private void buildHeap() {
        if (Array.length >= 2)
            for (int i = (Array.length - 2) / 2; i >= 0; i--)
                percolateDown(i, Array.length - 1);
    }

    private void percolateDown(int i, int n) {
        int child = 2 * i + 1;
        int rightChild = 2 * i + 2;
        if (child <= n) {
            if ((rightChild <= n) && Array[child] < Array[rightChild]) child = rightChild;
            if (Array[i] < Array[child]) {
                swap(i, child);
                percolateDown(child, n);
            }
        }
    }

    private final int[] KnuthSeq = new int[]{1, 4, 13, 40, 121, 364, 1093, 3280, 9841, 29524, 88573, 265720, 797161, 2391484, 7174453, 21523360, 64570081, 193710244};
    private final int[] TokudaSeq = new int[]{1, 4, 9, 20, 46, 103, 233, 525, 1182, 2660, 5985, 13463, 30240, 67907, 152851, 344826, 778103, 1758946, 3972615, 8978386, 20288288, 45848726, 103636191, 234991147, 532230530};
    private final int[] CiuraSeq = new int[]{1, 4, 10, 23, 57, 132, 301, 701, 1750, 3937, 8858, 19930, 44842, 100894, 227011, 510774, 1149241, 2585791, 5818029, 13090565, 29453771, 66270984, 149110714, 335498106, 754870738};

    private void shellKnuthSort() {
        int index = 0, len = (int)(Array.length / 3); // N/3을 넘지 않는 범위에서 적용이 전제
        while (KnuthSeq[index] < len) index++;

        for(int i = index; i >= 0; i--)
            for(int j = 0; j < KnuthSeq[i]; j++)
                stepInsertionSort(Array, j, Array.length, KnuthSeq[i]);
    }

    private void shellTokudaSort() {
        int index = 0, len = (int)(Array.length / 2.25); // 적어도 2개씩은 비교가 가능하게 전제
        while (TokudaSeq[index] < len) index++;

        for(int i = index; i >= 0; i--)
            for(int j = 0; j < TokudaSeq[i]; j++)
                stepInsertionSort(Array, j, Array.length, TokudaSeq[i]);
    }

    private void shellCiuraSort() {
        int index = 0, len = (int)(Array.length / 2.25); // 적어도 2개씩은 비교가 가능하게 전제
        while (CiuraSeq[index] < len) index++;

        for(int i = index; i >= 0; i--)
            for(int j = 0; j < CiuraSeq[i]; j++)
                stepInsertionSort(Array, j, Array.length, CiuraSeq[i]);
    }

    // @param a (배열), @param start (부분 배열의 첫 번째 원소 인덱스), @param size 배열 전체크기, @param gap 현재 gap
    private void stepInsertionSort(int[] a, int start, int size, int gap) {
        // 부분 배열의 두 번째 원소부터 size 까지 반복 (gap 값씩 건너뜀)
        for (int i = start + gap; i < size; i += gap) {
            int target = Array[i], j = i - gap;
            // 타겟 원소가 이전 원소보다 작을 때까지 반복
            while (j >= start && target < Array[j]) {
                Array[j + gap] = Array[j]; // 이전 원소를 뒤로 우시프트
                j -= gap;
            } // 반복문을 escape하는 경우 앞의 원소가 타겟보다 작다는 것이므로 타겟 원소는 j번째 원소 뒤에 와야 함
            // 즉, 타겟은 j + gap에 위치해야 함
            Array[j + gap] = target;
        }
    }
}
