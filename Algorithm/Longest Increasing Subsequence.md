# LIS (최장 증가 부분 수열) 구하기
최장 증가 부분 수열(Longest Increasing Subsequence, LIS)은 주어진 수열 내에서 부분 수열 중 증가하는 원소들로 이루어진 가장 긴 수열을 찾는 문제이다. LIS를 구하는 가장 기본적인 문제는 아래와 같다.

> 주어진 수열 $a_n$이 { $a_1, a_2, \cdots, a_n$ }의 형태로 주어질 때, 가장 긴 길이의 증가하는 부분 수열 $b_n$에 대하여
> - 해당 부분 수열 $b_n$의 최대 길이를 구하여라
> - 해당 부분 수열 $b_n$이 실제 어떤 수열인지 출력하여라

우리는 이 LIS를 여러 가지 방법으로 구해 보도록 할 것이다. JAVA 기반으로 작성하였다.

## 다이나믹 프로그래밍을 활용한 LIS 찾기

다이나믹 프로그래밍으로 LIS를 찾는 방법은 대략적으로 다음과 같은 로직을 따른다.
- 먼저, `dp[i]`가 수열 `i`번째 원소를 마지막으로 하는 LIS 길이라고 정의하자.
- 모든 `dp` 배열의 값을 1로 초기화한다. 즉, 모든 원소가 자기 자신만으로 LIS가 되는 상황을 전제한다.
- `0 <= j < i`인 두 인덱스에 대하여 `arr[j] < arr[i]`일 때, `dp[i] < dp[j] + 1`이라면 `dp[i] = dp[j] + 1`로 업데이트한다.
- 동시에 `dp[i]`를 업데이트 하는 데 값을 제공받은 인덱스 `j`를 `prev` 배열의 해당 인덱스 `i`에 값으로 저장한다. 수열 복원에 사용한다.
- `dp` 배열을 순회하며 최댓값을 찾고, 그 최댓값이 있는 인덱스를 찾는다. 최댓값은 LIS의 길이이며, 인덱스를 역추적하며 `-1` 값을 가리킬 때까지 `prev`를 돌면 수열도 복원 가능하다.

### 어떻게 작동하는지 확인하자
```
0  1  2  3  4  5  6  7  8   index
10 22 9  33 21 50 41 60 80	arr[]
1  1  1  1  1  1  1  1  1	dp[]
-1 -1 -1 -1 -1 -1 -1 -1 -1	prev[]
```

다음과 같은 초기 상황으로 주어진다고 해 보자. 그러면, 어차피 첫 번째 인덱스는 자기 자신이 LIS이므로 그건 그대로 두고, `i = 1`부터 확인해보자. `i = 1, j = 0`일 때 `arr[0] < arr[1]` 즉 10보다 22가 더 크다. 그러면 우리는 `10 22`라는 LIS가 성립할 수 있음을 찾은 셈이다. 그러니까, `dp[1] = max(dp[1], dp[0] + 1)`에 의해서 생각해보면 기존의 `dp[1]`의 값은 1이었고, `dp[0] + 1 = 2`이므로 2로 값이 변화하며 값의 변화에 따라 `prev[1] = 0`이 된다.

```
0  1  2  3  4  5  6  7  8   index
10 22 9  33 21 50 41 60 80	arr[]
1  2  1  1  1  1  1  1  1	dp[]
-1 0  -1 -1 -1 -1 -1 -1 -1	prev[]
```

`i = 2`일 때
- `j = 0`에서 10보다 9는 작으므로 더 이상 생각하지 않는다.
- `j = 1`에서 22보다 9는 작으므로 더 이상 생각하지 않는다.

즉, 변화하지 않고 그 상태에서 종료된다.

`i = 3`일 때
- `j = 0`일 때 `arr[0] < arr[3]` 즉, 10보다 33이 크므로 `dp[3] = max(dp[3], dp[0] + 1) = 2`, `prev[3] = 0`
- `j = 1`일 때 `arr[1] < arr[3]` 즉, 22보다 33이 크므로 `dp[3] = max(dp[3], dp[1] + 1) = max(2, 3) = 3`, `prev[3] = 1`
- `j = 2`일 때 9보다 33이 크긴 하지만, max 조건으로 따져본다 해도 3보다 커지지 않으므로 무시됨

```
0  1  2  3  4  5  6  7  8   index
10 22 9  33 21 50 41 60 80	arr[]
1  2  1  3  1  1  1  1  1	dp[]
-1 0  -1 1  -1 -1 -1 -1 -1	prev[]
```

- `i = 4, j = 0`일 때 `arr[0] < arr[4]` (10보다 21이 큼), dp 계산 시 `dp[0] + 1`이 더 커서 2, `prev[4] = 0`
- `i = 4, j = 1`일 때 `arr[1] > arr[4]` (22보다 21이 작음, 더 이상 생각하지 않음)
- `i = 4, j = 2`일 때 `arr[2] < arr[4]` (9보다 21이 큼), dp 계산으로 변화 없음
- `i = 4, j = 3`일 때 `arr[3] > arr[4]` (33보다 21이 작음, 더 이상 생각하지 않음)

다음과 같은 과정을 쭉 이어나간다고 하면, 마지막 상황은

```
0  1  2  3  4  5  6  7  8   index
10 22 9  33 21 50 41 60 80	arr[]
1  2  1  3  2  4  4  5  6	dp[]
-1 0  -1 1  0  3  3  5  7	prev[]
```
다음과 같이 나타난다. 이 과정에서 `dp[]` 배열의 최댓값을 구하면 최장 증가 부분 수열의 길이를 구할 수 있고, 그 값은 6으로 나타난다. 그 상황에서의 인덱스는 8인데, 그러면 `prev`를 인덱스 8번부터 역추적하면

> 80 (prev 7) -> 60 (prev 5) -> 50 (prev 3) -> 33 (prev 1)  -> 22 (prev 0) -> 10 (prev -1, 종료)

다음과 같이 나타나고, 이를 list 혹은 array 형태로 나타내어 역으로 출력하게 되면

> 10 22 33 50 60 80

이라는 LIS를 구할 수 있다.

### 코드화 (java)

```java
import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        // 입력은 수열의 길이 N과 수열 {a_n}으로
        int n = Integer.parseInt(br.readLine());
        int[] arr = new int[n];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) arr[i] = Integer.parseInt(st.nextToken());

        // dp 배열과 추적배열
        int[] dp = new int[n], prev = new int[n];
        Arrays.fill(dp, 1);
        Arrays.fill(prev, -1);

        // dp로 추적
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < i; j++) {
                // i 자신보다 작은 인덱스 j 모두에 대하여
                if (arr[j] < arr[i] && dp[j] + 1 > dp[i]) {
                    // 인덱스 j에 저장된 수열값보다 i에 저장된 수열값이 더 크고
                    // 동시에 dp 배열에서도 새로 고치는 것이 더 크다면
                    dp[i] = dp[j] + 1;
                    prev[i] = j; // 해당 참조값을 제공한 j 인덱스를 자신의 이전으로 입력해둠
                }
            }
        }

        // 최대 길이, 인덱스 찾기
        int maxLen = 0, idx = 0;
        for (int i = 0; i < n; i++) {
            if (dp[i] > maxLen) {
                maxLen = dp[i];
                idx = i;
            }
        }

        // 수열 복원
        LinkedList<Integer> lis = new LinkedList<>();
        while (idx != -1) {
            lis.addFirst(arr[idx]);
            idx = prev[idx];
        }

        // 결과 출력
        System.out.println("LIS 길이: " + maxLen);
        System.out.println("LIS 수열: " + lis);
    }
}
```

## 이분 탐색을 활용한 LIS 알고리즘

위에서 사용한 방법보다 더 빠른 속도로 체크가 가능하다.

### 이분 탐색
이분 탐색은 찾으려는 배열 혹은 리스트가 정렬되어 있을 때, 중앙값(`mid`) 위치를 확인하여 그 대소를 비교하여 위치를 찾는 방식이다. `start`와 `end` 값을 받아서 주로 `mid` 값을 평균치로 구하고, 위치를 찾으려는 값과 중앙 위치의 값을 비교하여 그것보다 크다면 중앙값 위치+1, 아니면 중앙값 위치를 `end`에 다시 지정하고, `start == end` 혹은 `start > end`인 상황이 되는 때까지 진행하여 `end`를 구하는 방식이다.

### 어떻게 작동하는지 확인하자
```
0  1  2  3  4  5  6  7  8   index
10 22 9  33 21 50 41 60 80	arr[]
-1 -1 -1 -1 -1 -1 -1 -1 -1	prev[]

lis    : [ ]
lisIdx : [ ]
```

이 상태에서 우선 `List`인 `lis`는 비어 있게 된다. 여기에서 우리는 이분 탐색을 넘길 건데, `lis`를 기준으로 하여 0번째 인덱스부터 넘길 것이다. 그러면 대략 이분 탐색이 `bs(lis, 0, lis.size(), arr[i]` 같은 식으로 넘어갈 것이다. 그냥 그런 포맷 정도라고 생각하자. 아무튼 리스트가 비어있기 때문에 `return` 값은 0일 것이다. 그러면 크기가 0이기 때문에 크기와 리턴 값이 같아지므로 맨 마지막에 그 값을 추가하고 `lisIdx`에도 동일하게 인덱스를 추가한다.

```
0  1  2  3  4  5  6  7  8   index
10 22 9  33 21 50 41 60 80	arr[]
-1 -1 -1 -1 -1 -1 -1 -1 -1	prev[]

lis    : [ 10 ]
lisIdx : [ 0 ]
```

그 다음으로 22를 이분 탐색으로 넘기게 된다면, 10보다 22가 크기 때문에 리턴 값은 1이 오고 이 역시 리스트 크기와 동일하다. 그렇기 때문에 lis에 값을 추가, lisIdx에 인덱스를 추가한다. 이때, 우리는 lis가 [10, 22] 즉 22라는 값이 맨 처음에 오지 않는다는 것을 알게 되므로 이 경우는 자신의 이전 값을 참조해주기 위해 prev를 조정해준다.

```
0  1  2  3  4  5  6  7  8   index
10 22 9  33 21 50 41 60 80	arr[]
-1 0  -1 -1 -1 -1 -1 -1 -1	prev[]

lis    : [ 10 22 ]
lisIdx : [ 0 1 ]
```

그 다음으로 9를 이분 탐색으로 넘기게 된다면, 9는 10보다 작기 때문에 lis의 맨 앞 위치인 0이 리턴된다. 그러면 이때는 lis가 0번 인덱스인 10으로 시작될지, 아니면 현재의 9부터 시작될지 잘 알 수 없으므로 9를 그냥 0번 인덱스 리스트에 수정해두고 lisIdx의 경우는 `arr[2]`를 받아왔다는 의미로 `lisIdx.set(0, 2)` 즉, 0번 lisIdx 값을 2로 바꾸어준다.

```
0  1  2  3  4  5  6  7  8   index
10 22 9  33 21 50 41 60 80	arr[]
-1 0  -1 -1 -1 -1 -1 -1 -1	prev[]

lis    : [ 9 22 ]
lisIdx : [ 2 1 ]
```

조금 더 빠르게 확인하자.
- 33의 이분 탐색 결과 22보다 크므로 2가 리턴, lis 크기와 같음
- lisIdx와 lis에 적절히 값과 인덱스 추가

```
0  1  2  3  4  5  6  7  8   index
10 22 9  33 21 50 41 60 80	arr[]
-1 0  -1 1  -1 -1 -1 -1 -1	prev[]

lis    : [ 9 22 33 ]
lisIdx : [ 2 1  3 ]
```

- 21의 이분 탐색 결과 22보다 작으므로 1 리턴, lis 크기와 다름
- lis에 22 대신 21 놓고, lisIdx는 1이 있던 위치에 4 지정
(21의 arr 인덱스인 4번을 참조했다는 뜻)
- prev의 경우는, lisIdx 상에서 21이 위치할 곳 바로 이전의 lisIdx를 받아와 2를 지정

> 이 과정에서 우리는 더 확실하게 **lis 리스트가 정말 LIS를 그대로 반영하지는 않을 가능성**&ZeroWidthSpace;을 알 수 있게 된다. 현재 상황이 진행됨에 따라 `lisIdx`는 `2 4 3` 즉 완전히 순서가 꼬인 상태이며, 그래서 **lisIdx 리스트와 prev[] 배열이 중요한 것**&ZeroWidthSpace;이다.

```
0  1  2  3  4  5  6  7  8   index
10 22 9  33 21 50 41 60 80	arr[]
-1 0  -1 1  2  -1 -1 -1 -1	prev[]

lis    : [ 9 21 33 ]
lisIdx : [ 2 4  3 ]
```

- 50의 이분 탐색 결과 33보다 크므로 3 리턴, lis 크기와 같음
- lis에 50 추가하고, lisIdx는 5 추가됨
- prev의 경우는 lis상 바로 이전인 33의 인덱스 3을 지정

```
0  1  2  3  4  5  6  7  8   index
10 22 9  33 21 50 41 60 80	arr[]
-1 0  -1 1  2  3  -1 -1 -1	prev[]

lis    : [ 9 21 33 50 ]
lisIdx : [ 2 4  3  5 ]
```

- 41의 이분 탐색 결과 50보다 작으므로 3 리턴, lis 크기와 다름
- lis의 3번 인덱스에 50 대신 41 지정하고, lisIdx의 3번 인덱스에 41이 arr에 있던 인덱스인 6 지정
- prev의 경우는 lis상 바로 이전인 33의 인덱스 3 지정

```
0  1  2  3  4  5  6  7  8   index
10 22 9  33 21 50 41 60 80	arr[]
-1 0  -1 1  2  3  3 -1 -1	prev[]

lis    : [ 9 21 33 41 ]
lisIdx : [ 2 4  3  6 ]
```

- 60의 이분 탐색 결과 50보다 크므로 4 리턴, lis 크기와 다름
- lis에 60 값 추가, lisIdx에 60의 인덱스 7 추가
- prev의 경우는 lis상 바로 이전인 41의 인덱스 6 지정

```
0  1  2  3  4  5  6  7  8   index
10 22 9  33 21 50 41 60 80	arr[]
-1 0  -1 1  2  3  3  6 -1	prev[]

lis    : [ 9 21 33 41 60 ]
lisIdx : [ 2 4  3  6  7 ]
```

- 80의 이분 탐색 결과 60보다 크므로 5 리턴, lis 크기와 다름
- lis에 80 값 추가, lisIdx에 80의 인덱스 8 추가
- prev의 경우는 lis상 바로 이전인 60의 인덱스 7 지정

---

```
0  1  2  3  4  5  6  7  8   index
10 22 9  33 21 50 41 60 80	arr[]
-1 0  -1 1  2  3  3  6  7	prev[]

lis    : [ 9 21 33 41 60 80 ]
lisIdx : [ 2 4  3  6  7  8 ]
```

그러면 위와 같은 결과값이 되고, 우리는 이 과정에서 LIS 수열 길이가 총 6임을 알 수 있다. lisIdx 리스트의 맨 마지막 값 8부터 시작해서 역추적하면 되는데, 이 과정에서 `prev` 배열이 사용된다. 그 결과 LIS를 올바르게 출력해낼 수 있다.

### 코드화 (java)

```java
public class Main {
    public static int n;
    
    public static int binarySearch(ArrayList<Integer> list, int start, int end, int value) {
        while (start < end) {
            int mid = (start + end) / 2;
            if (list.get(mid) < value) start = mid + 1;
            else end = mid;
        }
        return end;
    }
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        n = Integer.parseInt(br.readLine());
        int[] arr = new int[n], prev = new int[n];
        ArrayList<Integer> lis = new ArrayList<>(), lisIdx = new ArrayList<>();

        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) arr[i] = Integer.parseInt(st.nextToken());
        Arrays.fill(prev, -1);

        for (int i = 0; i < n; i++) {
            int posNum = binarySearch(lis, 0, lis.size(), arr[i]);
            if (posNum == lis.size()) {
                lis.add(arr[i]);
                lisIdx.add(i);
            } else {
                lis.set(posNum, arr[i]);
                lisIdx.set(posNum, i);
            }
            if (posNum > 0) prev[i] = lisIdx.get(posNum - 1);
        }

        int idx = lisIdx.get(lis.size() - 1);
        Stack<Integer> resultStack = new Stack<>();
        while (true) {
            resultStack.push(arr[idx]);
            if (prev[idx] == -1) break;
            idx = prev[idx];
        }

        sb.append(lis.size()).append("\n");
        while (!resultStack.isEmpty())
            sb.append(resultStack.pop()).append(" ");
        System.out.print(sb);
    }
}
```

## 정리
정렬된 상태의 배열 리스트에서 원소를 $$O(\log{n})$$ 시간에 검색할 수 있게 하는 알고리즘이다. 이분 탐색(이진 탐색)은 생각보다 코딩 테스트에서도 많이 사용되는 탐색 방식 중 하나라고 하니까, 잘 정리해두면 좋을 것 같다.   
무엇보다 이 알고리즘은 **처음에 정렬된 배열을 전제**&ZeroWidthSpace;하기 때문에 배열의 정렬이 $$O(\log{n})$$ 이상이면 코드 전체는 최악의 경우 배열 정렬의 시간 복잡도를 따르게 된다는 점을 기억하자.
