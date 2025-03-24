# KMP 알고리즘
본문 텍스트 내에서 특정 문자열 혹은 패턴을 찾는 **문자열 검색** 효율성을 높이기 위한 알고리즘 방식이다. Knutt-Morris-Pratt의 머릿글자를 따서 KMP 알고리즘이라고 부른다.

## 브루트 포스 알고리즘을 활용한 검색 방식

Java의 `String.contains()` 역시 비슷한 느낌의 브루트 포스 알고리즘이다. 앞에서부터 해당하는 substring을 찾는 방식인데...

![](https://velog.velcdn.com/images/aoi-aoba/post/2b684ca2-7c37-4f43-acaa-dfeb5f1e0a4e/image.png)

앞에서부터 일일이 확인해나가면서 같은지 다른지를 확인하는 것이다.


```JAVA
int BruteForceMatch(string text, string pattern) {
	for (int i = 0; i <= text.size() - pattern.size(); i++) {
    	int j = 0;
        while (j < pattern.size() && text[i + j] == pattern[j])
        	j++;
        if (j == pattern.size())
        	return i;
    }
    return -1;
}
```

시간 복잡도는 $$O(NM)$$ (text의 길이와 pattern의 길이의 곱)으로, 최악의 경우 $$O(N^2)$$에 수렴하는 수준이다.

## 접두사(prefix)와 접미사(suffix)

단어의 앞부분에 해당하는 것이 '접두사'   
단어의 뒷부분에 해당하는 것이 '접미사'   
길이를 늘려가며 단어 자신까지 늘리면서 체크할 수 있다.

![](https://velog.velcdn.com/images/aoi-aoba/post/119f11dc-bd33-4563-9d78-1bec291b7236/image.png)

## pi 배열 (실패 함수)
**실패 함수**&ZeroWidthSpace;라고 부르는데, 문자열을 차례로 비교하다가 **불일치가 발생했을 때 어떻게 해야 하는지에 대한 함수**&ZeroWidthSpace;이다. 문자 매칭에 실패했을 때, 불필요한 비교를 줄이기 위해 문자열을 얼마나 건너뛰어야 하는지에 대한 정보를 담고 있다. 문자열 비교는 **건너뛴 위치부터 다시 시작한다.**

`pi[i]`에 대하여 주어진 문자열의 0부터 `index i`까지 접두사와 접미사가 같아질 수 있는 부분 문자열 중에서 가장 긴 것의 길이를 의미한다. 예시를 보며 이해하자.

![](https://velog.velcdn.com/images/aoi-aoba/post/6c1b5831-1f08-40b8-910e-7fb09473b72a/image.png)

 접두사이면서 접미사인 최대 문자열의 길이를 구하는 것이다. 이를 배열의 형태로 `pi[]`에 저장하는 것이다.
 
 ![](https://velog.velcdn.com/images/aoi-aoba/post/5715bf0a-5d76-4da3-ac57-79d7d7f86df6/image.png)

> ! -- 중요 -- !   
잘 보면 알겠지만, ABABA는 1이 아니고 3이다. 쉽게 말해서, prefix와 suffix가 공통적으로 존재해야 한다면 1이 맞을 것이나 겹치면 안 된다거나 공통적으로 존재해야 한다고는 하지 않았다. 즉, 앞으로 n자 센 것과 뒤에서 n자 센(그렇다고 역순으로 적진 않고) 것이 같으면 pi가 되는 것이다. 5~7을 보면 더 이해가 쉬울 것이다. 이걸 제대로 이해하지 않으면 시간을 엄청 버린다...
 
## 정보의 활용
 일반적으로 돌아가는 문자열 탐색(브루트-포스 알고리즘)은 중요한 정보들을 모두 날리는 셈이다.
아래와 같이 문자열 STRISTRINSTRINGSTR에서 STRING을 찾는다고 생각해보자.
![](https://velog.velcdn.com/images/aoi-aoba/post/2a560d4a-5925-432e-8d9a-43addaf4bca4/image.png)

일반적 구조에서는 다음과 같이 진행되는데, 너무 비효율적이라고 생각이 들지 않는가? 지금까지 발견해놓은 정보들이 의미가 없다. STRI까지는 같았고 뒷 부분이 다르단 것을 알았는데 이 정보는 모두 사라지지 않는가?

![](https://velog.velcdn.com/images/aoi-aoba/post/1631ff33-a78e-4e14-8100-c43e6bde96c2/image.png)

- `STRIST` 에 대한 `pi[5]`의 값은 `2` (`ST`의 접두/접미사 동일)

그래서 이 정보를 활용해보려는 것이다. 이 정보를 활용함에 따라 **앞쪽의 시도를 모두 건너뛰고** 다음 단계인 STRINS 부분에 대해 STRING을 비교할 수 있는 것이다.

![](https://velog.velcdn.com/images/aoi-aoba/post/3d068d00-7fdd-4b9f-b57d-c478c3d3772f/image.png)

벌써 세 개의 의미 없는 시도를 모두 넘어갈 수 있다! 동시에, **비교값 역시 6번부터 시작** 하면 되는데, 이미 `pi[5]`에 의해서 `ST`가 뒤에서 일치함을 알 수 있기 때문이다. 이렇게  조금이라도 일치했던 정보에 주목해서 **전처리해둔 `pi[]` 배열** 활용에 집중하는 것이 KMP 알고리즘의 특징이다.

## 코드화 (JAVA)
자바 언어를 활용하여 코드로 이 과정을 만들어보자. 중요한 것은 함수 두 개를 구현하는 부분으로 생각한다.
- 주어진 패턴에 대해 `pi` 배열을 얻는 `getPi` 함수
- `pi` 배열을 활용해 문자열 검색을 하는 `kmp` 함수

### kmp 함수 구현하기
```JAVA
public static Vector<Integer> kmp(String s, String p) {
        Vector<Integer> answer = new Vector<>();
        Vector<Integer> pi = getPi(p);
        int n = s.length(), m = p.length(), j = 0;
        for (int i = 0; i < n; i++) {
            while (j > 0 && s.charAt(i) != p.charAt(j)) j = pi.get(j - 1);
            if (s.charAt(i) == p.charAt(j)) {
                if (j == m - 1) {
                    answer.add(i - m + 1);
                    j = pi.get(j);
                } else j++;
            }
        }
        return answer;
    }
```
윗부분은 `getPi` 함수를 활용하여 `pi`배열을 얻고, 답을 저장할 벡터를 만들어서 각각의 문자열을 받아오는 부분이니 넘어가자.

1. `for`문에서 `n` 길이만큼 반복하면서 이 과정을 검사한다.
2. (`while`은 잠시 재껴두고) 첫 문자열의 `i`번째 문자와 substring의 `j`번째 문자가 같으면?

	2-1. `j`가 `substring 길이-1`과 같으면 substring의 마지막 문자까지 찾아낸 것이므로 패턴의 시작 인덱스를 배열에 저장한다.
    - 가령 `i`가 7일 때 8글자짜리 substring을 발견하게 될 테니 이 시작은 `i-m`이 아니라 `i-(m-1)`이 된다는 점에 유의하자.
    
    2-2. 그렇지 않다면 아직 비교 중인거니까 큰 의미를 가지지 말고 `j`를 증가시키면서(`else`) `for` 문을 나가며 `i`까지 증가시켜 다음 문자를 비교한다.
    
![](https://velog.velcdn.com/images/aoi-aoba/post/1b6cbd29-ebcd-42b5-a9ab-02787f5f9aba/image.png)


3. `while` 문에서 >> 첫 문자열 `i`번째 문자와 substring의 `j` 문자가 다르다면?

	3-1.`j`가 0이라면 `i`를 1 증가시킨다.
   	- 결국 substring의 첫 글자부터 맞지 않다는 거니까 `i`에서 substring을 대입할 곳을 잘못 잡은 거고, 그걸 옮긴다는 것이다!

	3-2.`j`가 0이 아니라면 `i`를 그대로 두고 `j`를 `pi[j-1]`로 바꾼다.
    - 여기가 중요하다. 가령 `...abcabc`인데 substring이 `abcabb`라서 틀렸다면, 이걸 스킵해야 하지 않겠는가?
   	- 그래서 `pi[5]=2`니까 앞의 `ab`까지는 같다는 것이므로 `...abcab`까지의 비교는 관두고 맨 마지막 c에 대해서 substring의 `cabb`를 붙여서 비교하기 시작하는 것이다.
    
![](https://velog.velcdn.com/images/aoi-aoba/post/21df7508-50e0-41d1-8032-04b85bdf0145/image.png)

    
### while문의 역할이 중요하다
여기에서 나오는 while 문이 무슨 내용인지 이해해보도록 하자.   
결과적으로는 pi 배열과 정보를 활용하여 중간 단계를 넘어가는 부분인데, `while` 문 안에서 왜 만족할 때까지 반복되는걸까?

패턴에 대한 pi 배열이 모두 주어져 있다는 상태를 가정하자.   
이때, "ABABABABBABABABABC"에서 "ABABABABC"를 찾아보자.   
`pi = {0, 0, 1, 2, 3, 4, 5, 6, 0}`으로 나타난다는 것은 바로 이해 가능하다.   

자세한 것은 아래 그림을 통해 이해해보자.
![](https://velog.velcdn.com/images/aoi-aoba/post/9080af55-13d3-4895-92d9-2b7ae2509919/image.png)

즉, while문이 지속적으로 상황을 조성하기 위해 점프를 해줘야 하는 부분인 것이다.   
점프를 한 번 하고 지나가버린다면 비교가 이상해질 것이다!


### getPi 함수 구현하기
getPi 함수를 가장 단순하게 구현하려면 패턴 길이 `m`에 대하여 $$O(m^3)$$의 시간이 필요한데, 패턴의 길이가 짧다는 것을 생각하면 그렇게 긴 시간은 아니나 비약적으로 길이를 줄일 방법이 있다. 바로, 위에서 사용한 kmp 함수의 방식을 비슷하게 사용해주는 것이다!

```JAVA
public static Vector<Integer> getPi(String p) {
    int m = p.length(), j = 0;
    Vector<Integer> pi = new Vector<Integer>();
    pi.setSize(m);
    Collections.fill(pi, 0);
    for(int i = 1; i < m; i++) {
        while(j > 0 && p.charAt(i) != p.charAt(j)) j = pi.get(j-1);
        if(p.charAt(i) == p.charAt(j)) pi.set(i, ++j);
    }
    return pi;
}
```

pi 배열을 만들어주는 이 함수의 방식 역시 위에서와 비슷하게 구현할 수 있다.   
- pi 배열을 저장할 `Vector`를 만들어주고 `Vector.setSize()`를 통해 초기 크기를 지정, 0으로 초기화해주자.
- for 문을 돌면서 위에서 나온 kmp 방식을 사용해준다.
	- pi 배열을 받아올 문자열에 대해서 `i`와 `j`가 같다면 `pi[i]`를 `j+1`로 설정하고 `j`를 증가시킨다.
   	- while의 첫 조건인 j에 대하여 `i==j, j==0`인 상태면  어차피 다 무시되고 `i`가 증가된다.
    - j가 0이 아닌데 i와 다르면 이전 인덱스에 대한 `pi[i]`를 받아와서 `j`로 설정한다.
    
> 여기서 `new Vector<Integer>(m)`는 크기가 아님에 주의하자! constructor에 주는 parameter는 **'저장공간'을 할당하는 것이다!**

ABAABAB의 pi 배열을 만든다고 생각해보자.

![](https://velog.velcdn.com/images/aoi-aoba/post/5ce0a054-4e2a-49ab-8b85-51c74188efe1/image.png)


## 정리
솔직히 이해가 안 되서 직접 확인해보고 만들면서 굉장한 시간을 보냈다.   
그런데 진짜 열심히 정리해보다 보니까 이해가 간다.   
중간에 점프를 뛰는 while 문 구간이 정말 중요한 부분이고, 해당 부분이 있음으로서 불필요한 시간을 많이 줄일 수 있다.   
KMP 알고리즘은 **패턴과 문자열의 길이 합에 비례하는 시간** 복잡도인 $$O(N+M)$$으로 동작한다.    
**부분 일치 테이블(Pi 배열)** 덕분에 불필요한 비교를 줄이고, 패턴을 효율적으로 탐색할 수 있다.
문자열 찾기는 `contains()`를 활용할 수 있으면 사용하겠지만, 시간 복잡도를 고려하면 **KMP 알고리즘** 사용을 적극적으로 활용해야겠다.



## 출처
- bowbowbow 님의 티스토리 블로그 "KMP : 문자열 검색 알고리즘"
	- https://bowbowbow.tistory.com/6
- DevHwan 님의 velog 블로그 "[알고리즘] KMP 알고리즘"
	- https://velog.io/@hwan2da/%EC%95%8C%EA%B3%A0%EB%A6%AC%EC%A6%98-KMP-%EC%95%8C%EA%B3%A0%EB%A6%AC%EC%A6%98
- 이모저모 님의 네이버 블로그 "[C++] KMP 알고리즘"
	- https://blog.naver.com/bbeongji/223263871976
