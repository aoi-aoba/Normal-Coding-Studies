package sort;

import javax.lang.model.type.ArrayType;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class LinearSort {
    int[] Array;
    float[] floatArray;
    StringBuilder report;

    public LinearSort(int[] Target) {
        Array = Target;
        report = new StringBuilder();
    }

    public StringBuilder getReport() {
        return report;
    }

    public void sort(String type, Integer numScale) {
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
            case "Counting" -> countingSort(numScale);
            case "No-Sum Counting" -> noSumCountingSort(numScale);
            case "Radix" -> radixSort();
            case "Bucket" -> bucketSort();
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

    private void countingSort(int numScale) {
        int[] cnt = new int[numScale];
        for (int k : Array) cnt[k]++;
        cnt[0]--;
        for(int i = 1; i < numScale; i++) cnt[i] += cnt[i-1];
        int[] B = new int[Array.length];
        for(int j = Array.length-1; j >= 0; j--) { // stable sorting (reverse)
            B[cnt[Array[j]]] = Array[j];
            cnt[Array[j]]--;
        }
        Array = B;
    }

    private void noSumCountingSort(int numScale) {
        int min = numScale + 1, max = 0;
        int[] cnt = new int[numScale];
        for (int k : Array) {
            cnt[k]++;
            max = Math.max(max, k);
            min = Math.min(min, k);
        }
        int idx = 0;
        for (int i = min; i <= max; i++)
            for (int j = 0; j < cnt[i]; j++)
                Array[idx++] = i;
    }

    private void radixSort() {
        int[] cnt = new int[10], start = new int[10], B = new int[Array.length];
        int max = -1;
        for (int i : Array) max = Math.max(i, max);
        int numDigits = (int)Math.log10(max) + 1; // 상용로그를 활용해 최대 자릿수를 계산
        for (int digit = 1; digit <= numDigits; digit++) { // 최대 자릿수까지 해당 자릿수에 대해 정렬
            Arrays.fill(cnt, 0); // cnt 배열을 초기화
            for (int i : Array) cnt[(int)(i / Math.pow(10, digit - 1)) % 10]++;
            // digit이 1인 경우 1의 자리, 2인 경우 10의 자리와 같이 기준을 잡아서 각 원소의 자릿수를 추출해 해당 자릿수값을 구해 cnt++
            start[0] = 0; // 최종 배열 B에서 어디에 배치될지에 대해 결정하는 시작 인덱스를 저장하는 배열

            for (int d = 1; d <= 9; d++) start[d] = start[d-1] + cnt[d-1];
            // 각 자릿수에 대한 빈도를 cnt에서 받아와서 해당 숫자가 배열에서 시작할 인덱스를 구함
            // 가령, 0이 1개, 1이 2개, 2가 3개 있다면 start[0] = 0 (첫 번째 위치부터 시작)
            // start[1]=start[0]+cnt[0]=0+1=1 (두 번째 위치부터 시작), start[2]=start[1]+cnt[1]=1+2=3 (네 번째 위치부터 시작)

            for (int i : Array) B[start[(int)(i/Math.pow(10, digit - 1)) % 10]++] = i;
            // 숫자를 받아와 해당 숫자의 현재 확인중인 자릿수를 체크, start에서 해당 값을 확인 후 B 배열의 해당 위치에 저장
            // 1을 더해주는 것은 위에서 예시를 들었듯 해당 자릿수에 1인 수가 2개라면 첫 번째 위치가 저장되어 있으니 1 더해야 다음 위치 지정 가능

            System.arraycopy(B, 0, Array, 0, Array.length);
        }
    }

    public void bucketSort() {
        // A[0...]: 음이 아닌 정수 범위에서 균등 분포 배열
        intLinkedList B[];
        int numLists = Array.length;
        B = new intLinkedList[numLists];
        for(int i = 0; i < numLists; i++)
            B[i] = new intLinkedList();
        int max;
        if(Array[0] < Array[1]) max = 1;
        else max = 0;
        for(int i = 2; i < Array.length; i++)
            if(Array[max] < Array[i]) max = i;
        int band = Array[max] + 1;
        int bucketId;
        for(int i = 0; i < Array.length; i++) {
            bucketId = (int)((float)(Array[i]/band)*numLists);
            B[bucketId].add(0, Array[i]);
        }
        int finger = 0, p, r = -1;
        for(int i = 0; i < numLists; i++) {
            for(int j = 0; j < B[i].len(); j++)
                Array[finger++] = B[i].getNode(j).item;
            p = r + 1; r = finger - 1;
            rangeInsertionSort(p, r);
        }
    }

    private void rangeInsertionSort(int p, int r) {
        for(int i = p+1; i <= r; i++) {
            int loc = i-1;
            int x = Array[i];
            while (loc >= p && x < Array[loc]) {
                Array[loc+1] = Array[loc];
                loc--;
            }
            Array[loc+1] = x;
        }
    }

    public void FloatBucketSort() {
        int n = floatArray.length;
        if (n <= 1) return;

        ArrayList<Float>[] buckets = new ArrayList[n];
        for (int i = 0; i < n; i++) buckets[i] = new ArrayList<>();

        for (float num : floatArray) {
            int bucketIndex = (int) (num * n);
            buckets[bucketIndex].add(num);
        }

        for (ArrayList<Float> bucket : buckets) Collections.sort(bucket);

        int index = 0;
        for (ArrayList<Float> bucket : buckets)
            for (float num : bucket)
                floatArray[index++] = num;
    }
}
