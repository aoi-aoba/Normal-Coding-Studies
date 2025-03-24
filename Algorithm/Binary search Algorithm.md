# 이분 탐색/이진 탐색 알고리즘
오름차순으로 정렬된 정수의 리스트를 같은 크기의 두 부분 리스트로 나누고 필요한 부분에서만 탐색하게 제한하는 방식을 여러 번 반복하여 원하는 원소를 찾는 방식으로, **두 부분으로 나눈다(二分/二進)** 라는 의미에서 이분 탐색 혹은 이진 탐색 알고리즘으로 부른다.

## 기존의 정수 리스트 탐색 - 선형 탐색 방법

일반적으로 시간이 그렇게 타이트하게 잡혀있지 않거나 자료형이 많지 않다면, 선형 탐색(선형 알고리즘)을 활용하여 찾을 수 있다. JAVA의 `contains()` 메소드가 이 방법에 해당한다.
![](https://velog.velcdn.com/images/aoi-aoba/post/ed47761c-bed0-417e-adf9-a9d1542fbcf2/image.png)

물론 이 경우 인덱스와 딱 맞는 사이즈의 숫자들이 0부터 주어져 있어서 배열을 정렬해서 해당 인덱스로 가면 값을 찾을 수 있다지만, 그렇지 않은 배열이 주어진다면 알 길이 없다. 무엇보다 이 경우 시간 복잡도가 $$O(N)$$으로 그대로 복잡해져버리므로, **배열의 원소 개수가 많거나 찾아야 할 원소가 많은 경우** 시간 낭비가 심하다.

## 이분 탐색의 아이디어

아래의 그림을 보면서 이분 탐색의 아이디어를 알아보자.
![](https://velog.velcdn.com/images/aoi-aoba/post/4f6fb907-b37d-4927-b94b-43041dc42271/image.png)
우리가 23이라는 값을 찾기 위해서 거쳐야 하는 경우를 나열한 것이다.
- left는 왼쪽 끝, right는 오른쪽 끝에서 시작한다.
- mid는 (left + right) / 2이며 int 자료형을 사용하므로 값은 소수점을 버린 형태이다.
- mid와 같은지 계속 검사하고 아니라면 대소관계를 판단한다.
- 종료 시점은 값이 존재하지 않는다면 **right < left**인 경우이다.

잘 이해되지 않는다면, Up-down 게임을 생각하면 된다.
- 1부터 100 사이의 수 중 하나를 찾는다고 해보자.
- 가장 많은 수를 좁히려면 50을 부르면 된다.
- Up이라면 1~50에는 없는 것이므로 51~100으로 범위가 좁혀진다.
- 중간값인 75를 불렀을 때 Down이면 75~100에는 없는 것이므로 51~74로 범위가 좁혀진다.
- 중간값인 62를 불렀을 때 Up이면 51~62에는 없는 것이므로 63~74로 범위가 좁혀진다.
- 중간값인 68을 불렀을 때 down이면 63~67로 범위가 좁혀진다.
- 중간값인 65를 불렀을 때 up이면 63~64로 범위가 좁혀진다.
- 이때 63일 때 up이면 64이며, 64일 때 down이면 63이고 둘 중 하나를 맞출 수도 있다.


## 이분 탐색의 시간 복잡도
배열에서 가운데 자리의 원소와 찾고자 하는 원소를 비교하는 수고를 하고 나면, 자신과 성격이 똑같지만 크기가 반이 되는 문제를 만난다. 즉, 한 번 호출에 문제의 크기가 반이 되는 구조이다. 그렇기 때문에 **크기 n인 배열이 한 번에 반씩 줄어든다.**

16개의 요소에 대해 이를 실행하면 16 > 8 > 4 > 2 > 1로 총 4번 실행하면 된다. 식으로 나타내면 $$16 × (1/2)^4 = 1$$이다.

N개의 요소로 일반화하면 x번 실행하면 된다. 식으로 나타내면 $$N × (1/2)^x = 1$$이며, $$\frac{N}{2^x} = 1$$이고 $$N = 2^x$$이므로 정리하면 $$x = \log_2{N}$$임에 따라 **시간 복잡도는 $$O(\log N)$$으로 나타낼 수 있다.**

## 코드화 (JAVA)
배열 `arr[p...r]`에 대하여 원소 x를 찾는다고 하자.
```
binarySearch(arr[], str, fin, x):
	if (str > fin) return NOT_FOUND
    else
    	mid <- (str + fin) / 2
        if (x = arr[mid]) return mid
        else if (x < arr[q])
        	return binarySearch(arr, str, mid-1, x)
        else binarySearch(arr, mid+1, fin, x)
```

이분 탐색이 리턴해줄 `return value` 타입은 `int`로 하여 몇 번째에 위치하는지 알려주게 했는데, `boolean`으로 하여 있는지 없는지 여부만을 출력해도 된다. 아래에는 `boolean` 리턴 타입으로 구현한 코드이다.

```JAVA
 public static boolean binarySearch(int[] arr, int targetNum) {
	int left = 0, right = arr.length - 1;
	while (left <= right) {
		int middle = (left + right) / 2;
		int midVal = arr[middle];
		if (targetNum > midVal) left = middle + 1;
		else if (targetNum < midVal) right = middle - 1;
		else return true;
	}
	return false;
}
```


## 정리
정렬된 상태의 배열 리스트에서 원소를 $$O(\log{n})$$ 시간에 검색할 수 있게 하는 알고리즘이다. 이분 탐색(이진 탐색)은 생각보다 코딩 테스트에서도 많이 사용되는 탐색 방식 중 하나라고 하니까, 잘 정리해두면 좋을 것 같다.   
무엇보다 이 알고리즘은 **처음에 정렬된 배열을 전제**&ZeroWidthSpace;하기 때문에 배열의 정렬이 $$O(\log{n})$$ 이상이면 코드 전체는 최악의 경우 배열 정렬의 시간 복잡도를 따르게 된다는 점을 기억하자.



## 출처
- 코딩하는 엔지 님의 티스토리 블로그 "[알고리즘 / Python] 이분 탐색 / 이진 탐색 (Binary Search)"
	- https://code-angie.tistory.com/3
- 영문 위키피디아 "Binary search" 문서
	- https://en.wikipedia.org/wiki/Binary_search
- 책 <쉽게 배우는 자료구조 with 자바> 중 148~149쪽
