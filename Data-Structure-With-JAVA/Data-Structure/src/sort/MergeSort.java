package sort;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class MergeSort {
    private final int INSERTION_SORT_THRESHOLD = 16;
    int[] Array;
    StringBuilder report;

    public MergeSort(int[] Target) {
        Array = Target;
        report = new StringBuilder();
    }

    public StringBuilder getReport() {
        return report;
    }

    public int[] getArray() {
        return Array;
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
            case "Default" -> defaultMergeSort();
            case "Bottom-Up" -> bottomUpMergeSort();
            case "Hybrid" -> hybridMergeSort();
            case "Smart Buffer Reuse" -> smartBufferReuseMergeSort();
            case "Multi-Thread" -> multiThreadMergeSort(Array);
        }

        long endTime = System.currentTimeMillis();
        long afterUsedMemory = runtime.totalMemory() - runtime.freeMemory();

        long memoryUsed = Math.max(afterUsedMemory - beforeUsedMemory, 0);
        // 음수 방지

        report = new StringBuilder();
        report.append("정렬 방식 : ").append(type).append(" merge sort\n");
        report.append("걸린 시간(ms) : ").append(endTime - startTime).append(" ms\n");
        report.append("메모리 사용량(bytes) : ").append(memoryUsed).append(" bytes\n");
        report.append("----------------------------------------------");
    }

    // 일반적인 병합 정렬 알고리즘
    private void defaultMergeSort() {
        int[] A = new int[Array.length];
        defaultMSort(0, Array.length - 1, A);
    }

    private void defaultMSort(int p, int r, int[] B) {
        if (p < r) {
            int q = (p + r) / 2;
            defaultMSort(p, q, B);
            defaultMSort(q + 1, r, B);
            defaultMerge(p, q, r, B);
        }
    }

    private void defaultMerge(int p, int q, int r, int[] B) {
        int i = p, j = q + 1, t = 0;
        while (i <= q && j <= r) {
            if (Array[i] <= Array[j]) B[t++] = Array[i++];
            else B[t++] = Array[j++];
        }
        while (i <= q) B[t++] = Array[i++];
        while (j <= r) B[t++] = Array[j++];
        i = p;
        t = 0;
        while (i <= r) Array[i++] = B[t++];
    }

    // 배열 반복문 형태를 사용하여 병합 정렬을 진행하는 bottom-up 방식
    public void bottomUpMergeSort() {
        int n = Array.length;
        int[] temp = new int[n];

        // subSize: 현재 병합할 블록 크기 (1, 2, 4, 8, ...)
        for (int subSize = 1; subSize < n; subSize *= 2) {
            for (int left = 0; left < n - subSize; left += 2 * subSize) {
                int mid = left + subSize - 1;
                int right = Math.min(left + 2 * subSize - 1, n - 1);

                defaultMerge(left, mid, right, temp);
                // default merge sort의 merge 방식은 그대로
            }
        }
    }

    // 팀소트의 형태를 활용하는 삽입 정렬 하이브리드 방식
    private void hybridMergeSort() {
        int[] temp = new int[Array.length];
        hybridMSort(0, Array.length - 1, temp);
    }

    private void hybridMSort(int p, int r, int[] temp) {
        if (r - p + 1 <= INSERTION_SORT_THRESHOLD) {
            // 16이 임계값으로 잡혀있으나 조정할 수 있음
            insertionSortRange(p, r);
            return;
        }

        int q = (p + r) / 2;
        hybridMSort(p, q, temp);
        hybridMSort(q + 1, r, temp);
        defaultMerge(p, q, r, temp);
    }

    private void insertionSortRange(int left, int right) {
        for (int i = left + 1; i <= right; i++) {
            int location = i - 1;
            int newItem = Array[i];
            while (location >= left && newItem < Array[location]) {
                Array[location + 1] = Array[location];
                location--;
            }
            Array[location + 1] = newItem;
        }
    }

    private void smartBufferReuseMergeSort() {
        int[] tmp = new int[Array.length / 2];
        smartBufferReuseMSort(0, Array.length - 1, tmp);
    }

    private void smartBufferReuseMSort(int p, int r, int[] tmp) {
        if (p < r) {
            int q = (p + r) / 2;
            smartBufferReuseMSort(p, q, tmp);
            smartBufferReuseMSort(q + 1, r, tmp);
            smartBufferReuseMerge(p, q, r, tmp);
        }
    }

    private void smartBufferReuseMerge(int p, int q, int r, int[] tmp) {
        int leftSize = q - p + 1;

        // 왼쪽의 절반만 tmp로 복사해서 사용하기
        for (int i = 0; i < leftSize; i++) tmp[i] = Array[p + i];

        int i = 0; // tmp[] index (왼쪽)
        int j = q + 1; // Array[] index (오른쪽)
        int k = p; // 병합 결과를 다시 Array에 작성

        while (i < leftSize && j <= r) {
            if (tmp[i] <= Array[j]) Array[k++] = tmp[i++];
            else Array[k++] = Array[j++];
        }

        // 남은 원소 복사 (오른쪽은 이미 Array에 있으니까)
        while (i < leftSize) Array[k++] = tmp[i++];
    }

    // 멀티 스레드 방식을 활용한 병합 정렬 구현
    private void multiThreadMergeSort(int[] arr) {
        int[] tmp = new int[arr.length]; // 보조 배열
        int THRESHOLD = 10000; // 스레드 제작 한도

        class MergeSortTask extends Thread { // 내부의 스레드 클래스
            int low, high;
            public MergeSortTask(int low, int high) {
                this.low = low;
                this.high = high;
            }
            public void run() {
                parallelMergeSort(arr, tmp, low, high, THRESHOLD);
            } // 병합 정렬 로직을 호출함에 따라 재귀적 정렬 구조를 시작할 수 있음
        }

        MergeSortTask task = new MergeSortTask(0, arr.length - 1);
        task.start(); // task 형태로 받아들여서 실행
        try {
            task.join(); // 메인 스레드는 전체 작업이 끝날 때까지 대기
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    // 실질적 병렬 정렬 로직
    private void parallelMergeSort(int[] arr, int[] tmp, int low, int high, int threshold) {
        if (high - low + 1 <= threshold) {
            Arrays.sort(arr, low, high + 1);
            return;
        } // 너무 작은 배열의 경우 굳이 스레드를 만들기보다는 기본 정렬을 활용 (오버헤드 방지)

        int mid = (low + high) / 2;
        Thread left = new Thread(() -> parallelMergeSort(arr, tmp, low, mid, threshold));
        Thread right = new Thread(() -> parallelMergeSort(arr, tmp, mid + 1, high, threshold));
        // 배열의 크기가 충분히 크다면 중심을 기준으로 왼쪽과 오른쪽을 기존의 병합 정렬처럼 나누고, 각각을 스레드화

        left.start();
        right.start();
        // 각각의 스레드를 시작

        try {
            left.join();
            right.join(); // join을 통해 각각의 왼/오른쪽 스레드가 종료될 때까지 대기
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

        merge(arr, tmp, low, mid, high); // 이후에 병합하기
    }

    private void merge(int[] arr, int[] tmp, int lo, int mid, int hi) {
        // 보조 배열에 복사
        for (int k = lo; k <= hi; k++) tmp[k] = arr[k]; // 병합 대상 범위만 복사해서 사용

        int i = lo, j = mid + 1; // 왼쪽 절반의 인덱스, 오른쪽 절반의 인덱스
        for (int k = lo; k <= hi; k++) {
            if (i > mid) arr[k] = tmp[j++]; // 끝에 도달했는지 검사
            else if (j > hi) arr[k] = tmp[i++]; // 끝에 도달했는지 검사
            else if (tmp[j] < tmp[i]) arr[k] = tmp[j++]; // 끝에 도달 안했으면 비교해서 저장
            else arr[k] = tmp[i++]; // 끝에 도달 안했으면 비교해서 저장
        }
    }
}