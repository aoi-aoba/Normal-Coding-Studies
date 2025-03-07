# 재귀(recursion)
- 자기호출 알고리즘
- 함수 내부에서 자신을 호출하는 것
- 성격은 같으나 크기만 작은 알고리즘을 호출

## 등차수열
초항이 1이고 공차가 3인 등차수열
- n번째 원소는 자신과 성격이 같으나 순서가 하나 작은 원소에 3을 더한 것
- 점화식 $$a_n = a_{n-1}+3$$
```
seq(n):
	if (n = 1)
    	return 1
    else
    	return seq(n-1) + 3
```
- n=5라면 seq(5) = seq(4)+3 = seq(3)+6 = seq(2)+9 = seq(1)+12 = 1+12 = 13
- seq(n)은 자신보다 작지만 성격이 같은 함수 seq(n-1)을 호출
<br>

---

## 피보나치 수열
- 첫 두 항은 1이고, 나머지 항은 각각 직전 두 항을 더한 값
- 1, 1, 2, 3, 5, 8, 13, 21, 34, ... 와 같이 진행됨

```
fib(n):
	if (n = 1 or n = 2)
    	return 1
    else
    	return fib(n-1) + fib(n-2)
```
- 자기 자신을 **두 번이나  연속으로 호출**
- 이미 계산해놓은 결과를 계속 호출하여 **지수함수적 중복** 발생
- 시간이 **지나치게 오래** 걸림

```
fib_fast(n):
	f[1] ← f[2] ← 1
    for i ← 3 to n
    	f[i] ← f[i-1] + f[i-2]
        return f[n]
```
- for loop를 활용하여 구하면 재귀 없이 빠르게 구할 수 있음
- f라는 array에서 **이미 계산한 결과를 다시 계산하는** 일을 막기 때문에 더 빠른 방식으로 구할 수 있음

---

## 팩토리얼(Factorial)

- $$n! = n × n-1 × n-2 × ... × 2 × 1$$
- 자신보다 1 작은 자신을 자신에게 곱하는 방식

**재귀 버전**
```
fact(n) :
	if (n = 0)
    	return 1
    else
    	return n * fact(n-1)
```

**비재귀 버전**
```
fact(n) :
	tmp ← 1
    for i ← 2 to n
    	tmp  ← i * tmp
    return tmp
```

---

## 하노이 탑 문제

![](https://velog.velcdn.com/images/aoi-aoba/post/40e8880a-5492-49f6-a0f3-be15949d6c91/image.png)

- 원반 n개, 기둥 3개 (a, b, c)
- **목표** : n개의 원반을 a에서 b로 옮긴다
- 규칙 1 : 원반은 한 번에 하나씩 옮길 수 있다.
- 규칙 2 : 큰 원반은 작은 원반 아래에 놓여 있어야 한다.

### 함수 정의
move(n, source, destination, spare)
- n개의 원반을 source 기둥으로부터 destination 기둥으로
- spare은 보조 기둥으로 사용할 수 있음

예시 : move(4, a, b, c)에 대해서,
1) move(3, a, c, b) : spare 기둥에 3개를 옮긴 상태
2) a의 유일하게 남은 가장 큰 원반을 b로 옮긴다
3) move(3, c, b, a) : spare 기둥에서 다시 b로 옮긴다

```
move(n, a, b, c) :
	if (n > 0)
    	move(n-1, a, c, b)
        a의 원반을 b로 옮긴다
        move(n-1, c, b, a)
```

위 방식을 반복하게 되면 마지막 move(1, ...)이 호출된다.
if문에 걸려서 그냥 빠져나와 자기호출을 반복 종료한다.
다만, 수행 과정에서 move(0, ...)이 $$2^n-1$$번 호출된다.

---

## 선택 정렬(Selection Sort)

배열 A[0 ... n-1]에 n개의 원소가 주어지고 크기순으로 정렬하려고 할 때 가장 기초적인 알고리즘인 선택 정렬을 알아보자.

- 전체를 훑어보면서 가장 큰 원소를 찾고
- 이 원소를 가장 오른쪽 원소와 맞바꿔 맨 오른쪽에 가장 큰 원소가 위치
- 남은 A[0 ... n-2]의 n-1개짜리 배열을 정렬
- n-1은 점점 작아져 n이 1까지 작아짐

**for문을 활용한 재귀적 구조**
```
selectionSort(A[], n) :
	for last ← n-1 downto 1
    	A[0...last] 중 가장 큰 수 A[k]를 찾는다
        A[k] ↔ A[last]
```

**재귀를 활용한 구조**
```
selectionSort(A[], n) :
	if(n > 1) // 경계 조건
    	A[0...last] 중 가장 큰 수 A[k]를 찾는다
        A[k] ↔ A[last]
        selectionSort(A, n-1)
```
