# 선택 정렬(Selection Sort) 알고리즘
정렬 중에서 가장 이해하기 쉬운 구현 난이도에 해당하는 정렬 방식이다.

## 선택 정렬의 아이디어

아래의 그림을 보면서 선택 정렬의 아이디어를 알아보자.

![](https://velog.velcdn.com/images/aoi-aoba/post/d8136e71-abde-4ac2-ad78-6b7fa7efc365/image.png)

먼저 임의의 배열 `A[0...n-1]`에서 **가장 큰 원소를 찾아서** 맨 끝자리 원소 `A[n-1]`과 자리를 바꾼다.
그러면 방금 맨 끝자리로 옮긴 원소는 가장 큰 원소로서 **자신의 자리를 찾았으므로** 더 이상 이동할
필요가 없다. 가장 오른쪽 자리에 옮긴 원소는 정렬이 끝난 것이다. 이제 이 원소를 제외한, 나머지 배열
`A[0...n-2]`를 대상으로 하여 같은 작업을 반복하면 된다.


## 선택 정렬의 알고리즘
```
selectionSort(A[], n):
    for last <- n-1 downto 1
        A[0...last] 중 가장 큰 수 A[k]를 찾는다
        A[k] <-> A[last] // swap
```
입력을 배열로서 제공해주면 되고, `last` 변수는 **정렬 배열의 끝 경계** 구분을 위한 것이다. 가장
큰 수를 찾아 오른쪽으로 옮길 때마다 `last`의 값이 1씩 줄어들고, 정렬할 배열의 크기를 for loop가
줄여주고 있다.

조금 더 일반적인 형태로 정리해보자.
```
selectionSort(A[], n):
    for last <- n-1 downto 1
        k <- theLargest(A, last)
        tmp <- A[k]
        A[k] <- A[last]
        A[last] <- tmp
        
theLargest(A[], last):
    largest <- 0
    for i <- 1 to last
        if (A[i] > A[largest])
            largest <- i
    return largest
```


## 선택 정렬의 수행 시간
선택 정렬은 기본적으로 시간을 좌우하는 작업이 두 수를 비교하는 작업이다. 즉, **두 수 비교 작업을
몇 번 하는가**&ZeroWidthSpace;라는 내용이 선택 정렬의 점근적 수행 시간 척도가 된다.

선택 정렬의 수행 시간은 모든 경우에 대해 $\theta(n^2)$이다.
- for 루프가 `n-1`번 순환한다.
- 단순히 두 수를 swap하는 부분에 대해서는 상수 시간이 든다.
- 각 부분 배열에서 가장 큰 수를 찾아야 하므로 시간은 **부분 배열의 크기에 비례한다.**
- 부분 배열은 `n`부터 `2`까지의 크기를 가지므로, 그들의 합은 `n(n-1)/2`로서 최고차항이 2차이다.


## 구현
JAVA를 활용해 구현한 코드는 Normal-Coding-Studies/Data-Structure-With-JAVA/Data-Structure/src/Data-Structure/sort 내에 있다.