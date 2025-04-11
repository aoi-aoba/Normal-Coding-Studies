package sort;

public class GeneralSort {
    int[] Array;
    StringBuilder report;

    public GeneralSort(int[] Target) {
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
            case "Selection" -> selectionSort();
            case "Bubble" -> bubbleSort();
            case "Insertion" -> insertionSort();
        }

        long endTime = System.currentTimeMillis();
        long afterUsedMemory = runtime.totalMemory() - runtime.freeMemory();

        long timeUsed = endTime - startTime;
        long memoryUsed = Math.max(afterUsedMemory - beforeUsedMemory, 0); // 음수 방지
        if (type.equals("Selection")) memoryUsed = 0;
        // In-Place 정렬이 메모리 사용량이 튀어 나타나는 것을 방지하기 위해 메모리 사용량을 0으로 고정

        report = new StringBuilder();
        report.append("정렬 방식 : ").append(type).append(" sort\n");
        report.append("걸린 시간(ms) : ").append(timeUsed).append(" ms\n");
        report.append("메모리 사용량(bytes) : ").append(memoryUsed).append(" bytes\n");
        report.append("----------------------------------------------");
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