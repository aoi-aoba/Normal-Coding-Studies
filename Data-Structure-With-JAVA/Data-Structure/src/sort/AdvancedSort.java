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
        qSort(0, Array.length-1);
    }

    private void qSort(int p, int r) {
        if (p < r) {
            int q = partition(p, r);
            qSort(p, q-1);
            qSort(q+1, r);
        }
    }

    private void swap(int idx1, int idx2) {
        int tmp = Array[idx1];
        Array[idx1] = Array[idx2];
        Array[idx2] = tmp;
    }

    private int partition(int p, int r) {
        int x = Array[r], i = p-1, tmp;
        for (int j = p; j <= r-1; j++) {
            if (Array[j] < x) {
                i++;
                swap(i, j);
            }
        }
        swap(i+1, r);
        return i+1;
    }
}
