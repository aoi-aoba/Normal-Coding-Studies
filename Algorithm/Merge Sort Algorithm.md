# 병합 정렬(Merge Sort) 알고리즘

## 병합 정렬의 아이디어
병합 정렬은 먼저 입력을 반으로 나눈다. 이렇게 나눈 전반부와 후반부를 각각 독립적으로 정렬한다. 
마지막으로 **정렬된 두 부분을 합쳐서** 정렬된 배열을 얻는다. 여기서 전반부를 정렬할 때 역시
반으로 나눈 다음 병합한다. 즉, **원래의 정렬 문제와 성격이 같고 크기를 반으로 줄인** 것이다.
후반부에서도 마찬가지기 때문에 병합 정렬은 **원래 크기를 반으로 나눈 문제를 2개 풀고 이들을
병합한다** 라는 작업을 재귀적으로 반복한다.

![](https://velog.velcdn.com/images/aoi-aoba/post/3c9d43aa-0524-4140-840d-64e615cd76b6/image.png)

## 병합 정렬의 알고리즘
```
mergeSort(A[], p, r):
    if (p < r)
        q <- floor((p+r)/2)
        mergeSort(A, p, q)
        mergeSort(A, q+1, r)
        merge(A, p, q, r)

merge(A[], p, q, r):
    정렬된 두 배열 A[p...q]와 A[q+1...r]을 합쳐 정렬된 하나의 배열 A[p...r]을 만든다
```
기본적인 알고리즘으로서 맨 처음 호출하게 되는 것은 `mergeSort()`이다. 먼저 배열을 이등분하기 위해
중간 지점을 계산한다. 일반적으로 `int` 값을 사용할 것이므로 `floor`는 자동으로 적용될 것이다. 그런
다음에 전반부와 후반부를 각각 독립적으로 정렬한다. 마지막으로 정렬된 전반부와 후반부를 병합해
하나의 정렬된 배열을 만든다.

여기에서 `merge()` 메소드가 모호하게 느껴질 수 있으니, 조금 더 자세히 기술하자면...
```
merge(A[], p, q, r):
    i <- p; i <- q+1; t <- 0;
    while (i <= q and j <= r)
        if (A[i] <= A[j)
            tmp[t++] <- A[i++]  // tmp[t] <- A[i] 실행 후 t와 i를 1 증가
        else tmp[t++] <- A[j++] // tmp[t] <- A[j] 실행 후 t와 j를 1 증가
    while (i <= q) // 왼쪽 부분의 배열이 남은 경우
        tmp[t++] <- A[i++] 
    while (j <= r) // 오른쪽 부분의 배열이 남은 경우
        tmp[t++] <- A[j++]
    i <- p; t <- 0;
    while (i <= r)
        A[i++] <- tmp[t++]
```
이 과정을 직관적으로 코드를 보고 이해할 수 있다면 좋겠다. 글로 따로 설명은 해두자면,
- 우선 인덱스 역할로 둘 변수 `i`와 `j`를 사용하여, 전반부와 후반부의 시작지점인 `p`와 `q+1`을 대입한다.
- `tmp[]`라는 배열에 임시적로 정렬해둘 것이므로 `t`라는 인덱스로 전체를 돌자.
- 만약, `i`와 `j`가 모두 종료범위인 `q`와 `r` 내에 있다면
  - 전반부와 후반부의 인덱스가 가리키는 값을 비교해보자.
  - 전반부의 값이 더 작다면 이를 `tmp`에 대입하고 `t`와 `i`의 인덱스를 증가
  - 후반부의 값이 더 작다면 이를 `tmp`에 대입하고 `t`와 `j`의 인덱스를 증가
- 이렇게까지 진행하면 분명 어느 한 쪽(전반부 혹은 후반부)은 남게 될 것이다.
  - 이를 `i`와 `j`의 값이 종료지점에 위치했는지 확인해보고, 아니면 이동한다.
- 완료되면 모든 값을 `A[]`에 복사해서 종료한다.

![](https://velog.velcdn.com/images/aoi-aoba/post/51584334-1c2c-45da-8a4a-612f9e0e3e62/image.png)

## 병합 정렬의 수행시간 판단 - (1) 처리 시간 일반화
병합 정렬의 수행 시간은 $\Theta(n \log{n})$으로 나타난다. **최악, 최선, 평균** 모든 경우에서 
동일하게 나타난다. 비교 횟수를 기준으로 해도 결과는 바뀌지 않으나 비교 횟수보다 더 잦은 작업이
바로 **원소 이동** 횟수이다. 
- 배열 `A[]`에서 배열 `tmp[]`로 원소를 모두 옮기게 되는 작업 (`merge()`의 초반부 `tmp[]` 생성)
- 배열 `tmp[]`에서 배열 `A[]`로 원소를 모두 옮기게 되는 작업 (`merge()`의 마지막 `A[]` 이동)

물론 비교 횟수를 기준으로 분석한다면 $2n$ 대신에 $n/2$ ~ $n-1$이 된다. 점근적 분석 결과는
다 같지만 시간을 지배하는 작업을 하나 정한다면 이동 횟수가 된다. 크기가 $n$인 배열에 대해 병합
정렬로 처리하는 시간을 $T(n)$이라 한다면, 다음과 같이 쓸 수 있다.

$$T(n) = 2T(\frac{n}{2}) + 2n$$

직관적으로 이해 가능하겠지만, 배열을 반으로 나누기 때문에 나누면서 각각의 전반부와 후반부에 대해
일어나는 이동이 앞의 $2T(\frac{n}{2})$이다. $2n$은 마지막에 이동하는 작업이 되겠다.

## 병합 정렬의 수행시간 판단 - (2) N의 지수함수로서의 가정
이런 분석에서는 일반성을 잃지 않고 $n = 2^k$로 가정해도 된다. 왜냐하면, $n$에 대한 지수함수나
계승함수가 아니라면 $n = 2^k$와 $2n = 2^{k+1}$ 에 대해 복잡도 차이가 상수 배 밖에 안 난다.
점근적 복잡도 분석에서 상수 배는 영향을 미치지 않는다. 그래서 점근적 복잡도는 동일하다. 시간
복잡도 함수는 입력 값의 크기가 증가할 때 감소하지 않으므로 그 사이의 모든 $n$에 대해 점근적으로
동일하다. 그래서 이 경우에 $n = 2^k$로 놓아도 일반성을 잃지 않는다.

결과적으로 정리하자면,
- 위의 $T(n)$ 식을 보면 배열을 반으로 나누는 구조로 인해 $\frac{n}{2}$이 존재한다.
- 그러므로 $n = 2^k$이면 수식 전개가 깔끔하고 더 간단해진다.
- 모든 $n$에 대해서 이렇게 진행해도 되는가? 당연히 가능하다.
  - $n$과 $2^k$ 사이의 차이는 크지 않기 때문이다.
  - 가령, $n = 1030$이면 2의 거듭제곱으로 표현하면 $2^{10} < n = 1030 < 2^{11}$
  - 즉, 가장 가까운 거듭제곱과의 차이는 상수 배 수준
  - 점근적 분석에서 이런 상수 배 차이는 무시 ($O(cn)$이 아니라 $O(n)$과 같이 나타내기 때문)
  
## 병합 정렬의 수행시간 판단 - (3) 계산
(1)에서와 (2)에서 사용했던 것을 기반으로 하여 식을 전개해보자. $n = 2^k$로 전개한 것이다.

$$T(n) = 2T(\frac{n}{2}) + 2n = 2(2T(\frac{n}{4})+n) + 2n = 2^2T(\frac{n}{2^2}) + 2 \cdot 2n$$
$$ = 2^2(2T(\frac{n}{2^3}) + \frac{n}{2}) + 2 \cdot 2n = 2^3T(\frac{n}{2^3}) + 3 \cdot 2n$$
$$ \cdots $$
$$ = 2^k T(1) + k \cdot 2n = 2^k c + 2nk = cn + 2n \log_2{n}$$
마지막 줄에서 $k = \log_2{n}$임을 이용한 결과에 대해 상수배는 무시하고, $n < n \log{n}$임을 이용하면
결과적으로 모든 경우에 대해 $\Theta(n \log{n})$이 성립함을 유도해낼 수 있다.

## 병합 정렬의 특징
병합 정렬이 제대로 작동한다는 것은 수학적 귀납법으로 증명할 수 있다.
> 정렬하고자 하는 원소의 총 개수를 `n (n=r-p+1)`이라고 하자. `mergeSort()`는 `n=0` 혹은 `n=1`이면 
> 아무 것도 하지 않고 바로 알고리즘을 마친다. `n=1`이면 원소가 하나이므로 제대로 정렬한다고 할 수
> 있다. `n=0`이면 원소가 없으므로 이때도 '논리적으로' 제대로 정렬한다고 말할 수 있다. n보다 작은
> 모든 문제에 대해 `mergesort()`가 제대로 정렬한다고 가정하자. 문제 크기 `n`에 대해 병합 정렬이
> 호출되면 더 작은 전/후반부를 호출하는데 크기가 `n`보다 작은 문제이므로 귀납적 가정에서 각각
> 이들은 제대로 정렬한다고 했으므로 이들을 받아서 합치는 `merge()`는 하나의 정렬된 배열로 정렬해
> 합치기 때문에 전체 배열은 정렬된다. 즉, 병합 정렬은 0 이상의 모든 `n`에 대해 제대로 정렬한다. (증명 끝)

## 병합 정렬의 약점
병합 정렬은 이론적으로는 완벽한 $\Theta(n \log{n})$을 보장하는 알고리즘이지만, 병합 과정에서 주어진
배열에 대하여 같은 크기의 보조 배열이 필요하고 이 보조 배열은 병합 결과를 임시로 저장해 다시 써줘야 한다.
주어진 배열 이외에 추가로 공간을 사용하지 않는 배열을 **내부 정렬(In-Place Sorting)** 이라고 한다. 그러나,
병합 정렬은 **내부 정렬이 아니다.** 그래서 보조 배열로 병합했다가 다시 써주는 과정에서 메모리와 함께 시간
낭비가 생긴다.

그래서 추가 공간이 필요하다는 점은 피할 수 없으나, 배열 `tmp[]`로 병합했다가 `A[]`로 되써주는 과정은 개선할 수 
있다. 즉, 배열 `A[]`와 배열 `tmp[]`가 주 배열과 보조 배열의 역할을 매번 바꾸면서 요령 있게 정렬을 할 수 있다.
이런 최적화 과정을 **배열 역할 교대 방식(Role-swapping 혹은 Ping-pong 방식)**&ZeroWidthSpace;라고
한다. 크기 100만에서 평균 25%, 크기 1억에서 평균 15% 정도 빨라지고 캐시 효율도 좋아지며 메모리 대역폭을 절약한다.

## 최적화된 병합 정렬
### (1) Hybrid Merge Sort : 작은 배열에는 삽입 정렬을 사용한다
- 아이디어 : 재귀적으로 정렬할 배열 크기가 작을 때(16 이하), **병합 정렬 대신 삽입 정렬을 사용**&ZeroWidthSpace;한다.
- 이유 : 작은 배열에서는 삽입 정렬이 **캐시 친화적이고 오버헤드가 적기** 때문이다.
- 효과 : 실질적인 성능 향상을 기대할 수 있으며 JAVA의 TimSort 등의 방식에서도 사용된다.

hybrid merge sort의 경우, **시간과 메모리 면에서 모두 장점을 보인다.**
시간 면에서는,
- 일반 merge sort는 끝까지 재귀적으로 나눠서 병합하기 때문에 매우 깊은 재귀 호출과 많은 병합 연산이 요구된다.
- 하지만, hybrid는 **일정 크기 이하는 삽입 정렬로 전환해** 캐시 친화적이고 **분기 예측이 유리해져** 재귀 횟수도 줄고 병합이 생략된다.

그 결과, 시간이 크게 줄어드는 효과가 있다. 메모리의 경우는,
- 동일한 배열 내의 구간만 삽입 정렬해서 **추가적인 객체나 배열을 사용하지 않으므로** 메모리 사용량에 큰 차이를 보인다.
- 일반 merge sort나 다른 개량 방법과는 큰 차이가 보여지진 않는다. (Garbage Collect 오차 범위이거나 JVM 힙 상태 등에 연관)
- 오히려 측정 노이즈에 의해 차이가 발생하는 미비한 차이 정도만이 다른 merge sort 개량과의 차이이다.

### (2) 병합 전에 이미 정렬된 부분은 건너뛴다
- 이미 정렬된 경우 병합을 하지 않고 그냥 넘어간다.
- 이유 : 병합하는 부분은 $O(n)$이 걸리기 때문에 불필요한 병합을 생략하면 빨라진다.
- 효과 : 데이터가 부분 정렬 되어 있다면 매우 효과적이다.

### (3) Bottom-up Merge Sort : 아래에서 위로 정렬하기
- 아이디어 : 재귀 없이 반복문으로 구현하는 방식이며, 배열을 길이 1짜리로 시작해 2개, 4개, ...씩 병합한다.
- 장점 : 재귀 호출이 없기 때문에 **스택 오버헤드가 없다.** 대규모 정렬에서 안정적이다.
- 효과 : 비트 연산으로 병합 크기를 조절하면 최적화가 쉽다.

왜 bottom-up 방식을 사용할까?

| 구분     | top-down      | bottom-up            |
|--------|---------------|----------------------|
| 방식     | 재귀 호출로 분할 정복  | 반복문으로 하위 구간부터 병합     |
| 구현 스타일 | 재귀            | 반복문                  |
| 메모리 구조 | 스택 프레임 사용     | 루프 기반으로 스택 오버플로우가 없음 |
| 성능 차이  | 거의 없음(이론적 동일) | 구현에 따라 캐시 성능에 차이가 있음 |
| 장점     | 직관적이며 코드가 간결함 | 반복 기반으로 재귀 제한 없음     |

### (4) Smart Buffer Reuse : 보조 배열의 절반만 사용하기
- 전체 크기의 보조 배열 대신 절반만 사용해서 병합하는 방법도 있다.
- 복잡한 포인터 트릭이 필요하지만 메모리를 절약할 수 있다.

**보조 배열을 절반만 사용**&ZeroWidthSpace;하기 때문에, 메모리 사용량은 최대 절반으로 감소하고,
한쪽(왼쪽)만 복사하고 오른쪽은 그대로 비교해서 진행하다 보니 **복사량이 줄고 CPU 캐시 효율도 좋아지는**
효과가 있어서 시간도 빨라지고, 메모리도 적게 사용된다. 그리고, 원본 배열에 바로 병합 후에 반영되기 때문에
**불필요한 재복사 과정이 없다는** 점 역시 시간을 절약하는 큰 요소 중 하나이다.

### (5) Multi-Threading : 병렬화된 병합 정렬
- 아이디어 : 멀티코어 환경에서 병합 정렬을 병렬화할 수 있는데, 기본 구조 형식이 $T(n) = 2T(\frac{n}{2}) + 2n$로 나타남에 기인한다.
- 장점 : 두 개의 재귀 호출을 각각 다른 Thread로 실행하면 병렬화하기 쉽다.
- 방식 : C++에서는 `std::async`나 `OpenMP` 등을 활용하면 가능하다.

JAVA의 스레드는 동시에 여러 작업이 수행 가능하게 하는 단위로서, **멀티 스레드** 방식을 사용하면 병렬로
작업을 진행할 수 있고 좌우 분할을 이런 병렬화를 사용할 수 있다. 다만, 너무 작게 분할하면 오히려 **오버헤드로
인해 더 느려질 수도** 있다.

> **오버헤드란?**   
> 정렬 자체와는 무관한 부수적인 비용을 말하는데, `Thread` 객체 생성, `start()` 호출(OS 레벨에서 스케줄링
> 처리), `join` 대기, 스레드 간 컨텍스트 스위칭(CPU가 다른 스레드로 전환하는 과정) 등이 너무 작업 단위가
> 촘촘한 경우 정렬보다 비용이 더 비싸져서 스레드 조작 비용을 더 크게 사용해 비효율적으로 작용한다. 그래서
> 일정 크기 이상이면 병렬 처리하고, 작으면 단일 스레드에서 재귀적으로 처리하는 것이 유리하다.

### (6) TimSort
- 아이디어 : 병합 정렬과 삽입 정렬의 하이브리드 방식임에 동시에 정렬된 구간(run)을 탐지해서 최적화한다.
- 효과 : 일반적인 병합 정렬보다 훨씬 빠르다 (실제 데이터에서)
- Python이나 JAVA의 표준 정렬 알고리즘이 이 방식을 채택하고 있다.
- 별도의 문서에서 설명할 예정.

## 구현
JAVA를 활용해 구현한 코드는 Normal-Coding-Studies/Data-Structure-With-JAVA/Data-Structure/src/Data-Structure/sort 내에 있다.
직접 Merge Sort와 함께 다른 소트를 비교해볼 수 있다!