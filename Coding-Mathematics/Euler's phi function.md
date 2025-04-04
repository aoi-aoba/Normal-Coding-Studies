# 오일러 피 함수와 오일러의 정리

## 🖋️ 오일러 피 함수란?
> 어떤 자연수 $$n$$에 대하여 $$n$$보다 **작거나 같은 자연수** 중 $$n$$과 **서로소인 수의 개수**&ZeroWidthSpace;를 함숫값으로 가지는 함수이다. 

피(phi, $$φ$$) 기호를 사용해서 $$φ(x)$$라고 쓴다.
- 1~6에서 6의 소인수는 1과 5이므로 $$φ(6) = 2$$
- 1~11에서 11의 약수는 1과 11이므로 $$φ(11) = 10$$  

특수성을 활용하면 소수인 $$x$$에 대하여 $$φ(x) = x - 1$$이 성립한다.

## 🖋️ 오일러의 정리
> 자연수 $$n$$과 $$a$$가 서로소이면 $$a^{φ(n)}$$을 $$n$$으로 나눈 나머지는 1이다.

### ☑️ 증명
집합 $$S = \{b_1, \cdots , b_{φ(n)}\}$$를 $$n$$ 이하의 자연수 중 $$n$$과 서로소인 수만 모은 집합으로 하자.   
오일러 피 함수의 정의에 의해 원소 개수는 $$φ(x)$$이다.   
집합의 각 원소 모두에 ($$n$$과 서로소인) $$a$$를 곱한 집합을 구하면 이 집합을 $$aS$$라고 할 때 $$aS = \{ab_1, \cdots , ab_{φ(n)}\}$$이다.   
이때 집합 $$aS$$의 모든 원소는 $$n$$과 서로소인 수들끼리 곱했기 때문에 그들 역시 모두 서로소이다.

---

집합 $$aS$$의 원소 중 $$ab_i$$와 $$ab_j$$가 $$n$$에 대한 모듈러 연산을 진행했을 때 같다고 가정해보자.
그러면 1 이상 $$φ(n)$$ 이하인 서로 다른 정수 $$i, j$$가 존재한다는 것이므로 $$a(b_i - b_j)$$ 역시 $$n$$의 배수이다.   
그런데, 우리는 이미 위에서 집합 $$S$$에 대해 $$n$$과 서로소인 $$a$$를 곱했다.
그러므로 $$a(b_i - b_j)$$에서 $$n$$의 배수인 부분은 $$b_i - b_j$$이다.
$$b_i$$와 $$b_j$$는 처음의 집합 $$S$$의 성질에 의하여 1 이상 $$n$$ 이하의 자연수라고 할 수 있다.
이를 이용하면 $$-(n-1) ≤ b_i - b_j ≤ n-1$$이라는 범위가 나온다.  ($$1-n$$을 가독성을 위해 $$-(n-1)$$로 쓴다)
그러나 이 범위 내에서 $$n$$의 배수가 되게 하려면 값이 0밖에 없다.   
결과적으로 집합 $$aS$$의 모든 원소는 $$n$$으로 나눈 나머지가 서로 다르다.

---

위에서 증명한 것을 바탕으로 했을 때 $$aS$$의 모든 원소들을 $$n$$으로 나눈 나머지는 $$S$$의 원소들을 재배열한 것과 같다.
위 과정을 통해 우리는 $$S$$의 모든 원소의 곱과 $$aS$$의 모든 원소의 곱이 $$n$$으로 나눈 나머지가 같음을 알 수 있다.
이는 $$b_1 \cdots b_{φ(n)} = a^{φ(n)}b_1 \cdots b_{φ(n)}\pmod{n}$$ 이다.
따라서, $$a^{φ(n)}≡1\pmod{n}$$임을 증명할 수 있다.


## 🖋️ 특수 케이스 예시 1) 백준 4375번 문제의 전제조건 설명
**문제 설명**
2와 5로 나누어 떨어지지 않는 10000 이하의 자연수 n이 주어졌을 때 각 자릿수가 모두 1로만 이루어진 n의 배수를 찾는 프로그램을 작성하시오.

문제에서 판별할 1로만 이루어진 자연수를 $$N_k$$라고 하자.
그러면 $$N_k = \frac{10^n - 1}{9}$$라고 둘 수 있다.
이때 문제 조건에서 우리에게 주어지는 수는 2와 5로 나누어떨어지지 않는 수만이 주어진다.
즉, $$n$$이라는 수는 **10에 대하여 서로소임이 이미 입증되어있는** 셈이다.
여기서 **페르마-오일러 정리를 활용하면** 자연수 n과 10이 서로소이므로 $$10^{φ(n)}≡1\pmod{n}$$
$$φ(n) = k$$라고 하면 식으로 정리했을 때 $$10^k = 1\pmod{n}$$이라고 정리할 수 있다.
이때 $$(10^k - 1)\bmod{n}$$이라고 하면 **모듈러 연산의 분배법칙**&ZeroWidthSpace;이 성립한다.   
$$(10^k \bmod{n})- (1 \bmod{n}) = 0$$이 되어 이 과정에서 $$10^k - 1 = 0\pmod{n}$$임이 자명하다.
이 값을 9로 나누게 되어도 $$\frac{10^k - 1}{9} = 0\pmod{n}$$이 일반성을 잃지 않는다.
동시에 $$φ(n)$$이라는 함수 자체는 정의에서부터 n보다 작은, 즉 $$p(n) < n$$임이 보장된다.
따라서 자릿수를 1부터 $$n$$까지 늘리는 동안에 $$n$$의 배수는 적어도 하나 나오게 된다.
결국 조건에서 주어지는 모든 $$n$$에 대하여 성립하는 111...111 의 수는 존재할 수밖에 없다.
