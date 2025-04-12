# 힙 정렬(Heap Sort) 알고리즘

## 힙 정렬의 아이디어
힙 정렬은 다른 정렬과 달리 힙 자료구조를 사용해서 정렬을 진행한다. 배열이 주어지면 먼저
`buildHeap()`으로 힙을 만들고, 원소를 하나씩 제거하면서 `percolateDown()`으로 수선한다.
하나씩 빼주면서 차례대로 저장하면 정렬이 된다.

![](https://velog.velcdn.com/images/aoi-aoba/post/898adafa-486c-426d-a397-fb29367fed2c/image.png)
![](https://velog.velcdn.com/images/aoi-aoba/post/563d9058-0630-4b47-ab62-6be9b70245a5/image.png)
![](https://velog.velcdn.com/images/aoi-aoba/post/4151023a-6dac-4ca5-8691-7d76b21e7589/image.png)


## 힙 정렬의 주요 알고리즘
```
heapSort():
    buildheap()
    for i <- n-1 downto 1
        A[i] <- deleteMax()
```

원소를 하나씩 삭제해가면서 정렬한다. 이때 삭제 결과를 뒤에서부터 차례로 저장해야 한다. 함수
`deleteMax(A)`가 한 번 수행될 때마다 배열의 크기가 1씩 감소하므로 저장된 원소는 `deleteMax()`
자체에서 건드리지 않는다. 알고리즘이 끝나면 `A[0...n-1]`은 정렬되어 있다.

## 힙 정렬의 수행시간 판단
힙 정렬의 수행 시간은 $O(n\log{n})$의 시간 복잡도를 가지게 된다.   
- 최악의 경우 : $\Theta(n\log{n})$
- 최선의 경우 : $\Theta(n)$ (모든 원소가 동일한 경우 등)

힙 정렬은 병합 정렬처럼 추가 공간을 요구하지 않는 **In-Place Sort(내부 정렬)** 방식이다. 그런
동시에 최악의 경우에도 $\Theta(n\log{n})$을 보장하므로 이론적으로는 완벽한 알고리즘이나
수행 시간은 병합 정렬이 힙 정렬보다 빠르다. 실전에서는 퀵 정렬보다도 느리다.

## 평균 수행 시간 분석을 통한 힙 정렬의 수행시간 비교
병합 정렬, 힙 정렬, 퀵 정렬에 대해 평균 수행 시간 분석을 기반으로 알아보자.

### 병합 정렬(Merge Sort)
- 재귀식은 $T(n) = 2T(\frac{n}{2}) + cn$
- 이를 해석하면 $T(n) = cn\log_2{n}$으로 $\Theta(n\log{n})$이나 실제로는 $c \approx 1.0 \sim 1.2$
- 퀵 정렬보다 이론적 계수는 낮으나 메모리 복사가 많고, in-place가 아니며, 캐시 친화성이 떨어지고, 재귀 깊이나 기타 최적화 등에 의하여 병합 정렬은 퀵 정렬보다 일반적으로 더 느림 (단, 힙 정렬보다는 확실히 빠름)

### 퀵 정렬(Quick Sort)
- 재귀식은 $T(n) = \frac{2}{n} \sum_{k=0}^{n-1}{T(k)+cn}$
- 이를 해석하면 $T(n) \approx 2n \ln{n} + O(n)$
- 로그 밑을 $e$로 바꿔서 $\log_2{n} = \frac{\ln{n}}{\ln{2}}$ 대입하면 $T(n) \approx 1.386n\log_2{n}+O(n)$
- 즉, $T(n) = cn\log{n}$이며 $c \approx 1.38$

### 힙 정렬(Heap Sort)
- heapify 전체 : $O(n)$
- $n$개 원소에 대해 extract-max + heapify : $\sum_{k=1}^{n} {O(log k)} \approx O(n\log{n})$
- 정확히는 $T(n)=cn+\sum_{k=1}^{n}c\log{k}\approx cn+c'n\log{n}-c'n=(c'\log{n}+c-c')n$
- 결과적으로 $T(n)=c''n\log{n}$이고 $c''>c$이나 힙 재구성의 연산이 상대적으로 많아 실측 계수는 높다.
- 그 결과 일반적으로 실측계수 $c'' \approx 1.5 \sim 1.8$로 관측된다.

## 구현
JAVA를 활용해 구현한 코드는 Normal-Coding-Studies/Data-Structure-With-JAVA/Data-Structure/src/Data-Structure/sort 내에 있다.