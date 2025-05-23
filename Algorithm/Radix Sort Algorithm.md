# 기수 정렬(Radix Sort) 알고리즘

## 기수 정렬의 아이디어
기수 정렬은 원소들이 **모두 상수 k개 이하의 자리수를 가진 자연수인 경우**&ZeroWidthSpace;와 
같이 특수한 경우에(자연수가 아니라 **제한된 길이의 알파벳** 등도 해당함) 사용할 수 있는 방법으로,
$\Theta(n)$ 시간이 소요되는 정렬 알고리즘이다. 원소들이 모두 최대 4자리인 자연수에 대한 기수 정렬
예시를 아래 그림을 통해 확인해보자.

![](https://velog.velcdn.com/images/aoi-aoba/post/f4d5caee-6f3c-492d-9950-7527fa3b3d62/image.png)

먼저, 가장 낮은 자릿수만으로 모든 수를 재배열한다. 다음으로 둘째 자릿수를 대상으로 정렬한다. 이후는
설명하지 않아도 셋째 자릿수와 넷째 자릿수에 대하여 정렬하며 작업을 완료하면 된다.

## 기수 정렬의 알고리즘
```
radixSort(A[], n, k):
    for i <- 1 to k // 가장 낮은 자리를 1번째 자릿수라 하면
        i번째 자릿수에 대하여 A[0...n-1]을 안정성을 유지하면서 정렬한다
```
여기서 가장 중요한 사실은 **"안정성을 유지하면서 정렬한다"**&ZeroWidthSpace;라는 사실이다. 
이는 **값이 같은 원소끼리는 정렬 후에 원래의 순서가 바뀌지 않는** 성질을 말하는데, 영어로 stable
sort라고 한다. 가령, 위쪽에서 2150과 2154는 세 번째 자릿수의 비교에서 더 먼저 있었던 2150이 네 
번째로 가서도 역시 2150이 더 먼저 있어야 한다. 다른 정렬 알고리즘 중 하나를 쓰면 그것만으로도 이미
$\Theta(n)$을 초과하므로 다른 방법을 사용해야 한다. 예를 들어, 0부터 9까지 표시된 10개의 버킷을
준비하고 각각의 수를 가진 입력을 해당 공간에 차례대로 넣는다는 등의 방법을 사용해야 한다.

## 기수 정렬의 수행시간 판단
기수 정렬은 stable sorting을 어떻게 구현하느냐에 따라 달라지며, 이 부분을 $\Theta(n)$에 해결할
수가 있다면 알고리즘은 이 일을 $k$번 반복하므로 시간은 $\Theta(kn)$에 해결할 수 있따. 이때, $k$
가 상수일 경우 $\Theta(n)$으로 해결 가능하다. $k$가 상수가 되지 않으면 기수 정렬은 매력이 없다.

## 구현
JAVA를 활용해 구현한 코드는
Normal-Coding-Studies/Data-Structure-With-JAVA/Data-Structure/src/Data-Structure/sort
내에 있다.